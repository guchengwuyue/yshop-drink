package co.yixiang.yshop.module.product.service.storeproduct;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.StrUtil;
import co.yixiang.yshop.framework.common.enums.ShopCommonEnum;
import co.yixiang.yshop.framework.common.exception.ErrorCode;
import co.yixiang.yshop.module.product.controller.app.category.vo.AppCategoryRespVO;
import co.yixiang.yshop.module.product.controller.app.product.param.AppStoreProductQueryParam;
import co.yixiang.yshop.module.product.controller.app.product.vo.AppStoreProductAttrQueryVo;
import co.yixiang.yshop.module.product.controller.app.product.vo.AppStoreProductRespVo;
import co.yixiang.yshop.module.product.convert.category.ProductCategoryConvert;
import co.yixiang.yshop.module.product.convert.storeproduct.StoreProductConvert;
import co.yixiang.yshop.module.product.dal.dataobject.category.ProductCategoryDO;
import co.yixiang.yshop.module.product.dal.dataobject.storeproduct.StoreProductDO;
import co.yixiang.yshop.module.product.dal.dataobject.storeproductattrvalue.StoreProductAttrValueDO;
import co.yixiang.yshop.module.product.dal.mysql.storeproduct.StoreProductMapper;
import co.yixiang.yshop.module.product.dal.mysql.storeproductattrvalue.StoreProductAttrValueMapper;
import co.yixiang.yshop.module.product.enums.product.ProductEnum;
import co.yixiang.yshop.module.product.enums.product.ProductTypeEnum;
import co.yixiang.yshop.module.product.service.category.ProductCategoryService;
import co.yixiang.yshop.module.product.service.storeproductattr.AppStoreProductAttrService;
import co.yixiang.yshop.module.product.service.storeproductattrvalue.StoreProductAttrValueService;
import co.yixiang.yshop.module.product.service.storeproductreply.AppStoreProductReplyService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static co.yixiang.yshop.framework.common.exception.util.ServiceExceptionUtil.exception;
import static co.yixiang.yshop.module.product.enums.ErrorCodeConstants.PRODUCT_STOCK_LESS;
import static co.yixiang.yshop.module.product.enums.ErrorCodeConstants.STORE_PRODUCT_NOT_EXISTS;

/**
 * 商品 AppService 实现类
 *
 * @author yshop
 */
@Service
@Validated
public class AppStoreProductServiceImpl extends ServiceImpl<StoreProductMapper,StoreProductDO> implements AppStoreProductService {

    @Resource
    private AppStoreProductAttrService appStoreProductAttrService;
    @Resource
    private AppStoreProductReplyService appStoreProductReplyService;
    @Resource
    private StoreProductAttrValueService storeProductAttrValueService;
    @Resource
    private StoreProductAttrValueMapper storeProductAttrValueMapper;
    @Resource
    private ProductCategoryService categoryService;

    /**
     * 商品列表
     *
     * @param page  页码
     * @param limit 条数
     * @param order ProductEnum
     * @return List
     */
    @Override
    public List<AppStoreProductRespVo> getList(int page, int limit, int order) {
        LambdaQueryWrapper<StoreProductDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StoreProductDO::getIsShow, ShopCommonEnum.SHOW_1.getValue())
                //.eq(YxStoreProduct::getIsDel,ShopCommonEnum.DELETE_0.getValue())
                .orderByDesc(StoreProductDO::getSort);
        wrapper.eq(StoreProductDO::getIsIntegral,0);
        // order
        switch (ProductEnum.toType(order)) {
            //精品推荐
            case TYPE_1:
                wrapper.eq(StoreProductDO::getIsBest,
                        ShopCommonEnum.IS_STATUS_1.getValue());
                break;
            //首发新品
            case TYPE_3:
                wrapper.eq(StoreProductDO::getIsNew,
                        ShopCommonEnum.IS_STATUS_1.getValue());
                break;
            // 猜你喜欢
            case TYPE_4:
                wrapper.eq(StoreProductDO::getIsBenefit,
                        ShopCommonEnum.IS_STATUS_1.getValue());
                break;
            // 热门榜单
            case TYPE_2:
                wrapper.eq(StoreProductDO::getIsHot,
                        ShopCommonEnum.IS_STATUS_1.getValue());
                break;
            default:
        }
        Page<StoreProductDO> pageModel = new Page<>(page, limit);

        IPage<StoreProductDO> pageList = this.baseMapper.selectPage(pageModel, wrapper);

        return StoreProductConvert.INSTANCE.convertList03(pageList.getRecords());
    }


    /**
     * 商品列表
     *
     * @param productQueryParam AppStoreProductQueryParam
     * @return list
     */
    @Override
    public List<AppCategoryRespVO> getGoodsList(AppStoreProductQueryParam productQueryParam) {

        List<ProductCategoryDO> list = categoryService.getEnableCategoryList(productQueryParam.getShopId());
        list.sort(Comparator.comparing(ProductCategoryDO::getSort));
        List<AppCategoryRespVO> appCategoryRespVOS =  ProductCategoryConvert.INSTANCE.convertList03(list);

        for (AppCategoryRespVO appCategoryRespVO : appCategoryRespVOS) {
            LambdaQueryWrapper<StoreProductDO> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(StoreProductDO::getIsShow, ShopCommonEnum.SHOW_1.getValue())
                    .eq(StoreProductDO::getCateId,appCategoryRespVO.getId())
                    .eq(StoreProductDO::getShopId,productQueryParam.getShopId());
            List<StoreProductDO> storeProductDOList = this.baseMapper.selectList(wrapper);
            List<AppStoreProductRespVo> appStoreProductRespVoList = ListUtil.list(false);
            for (StoreProductDO storeProductDO : storeProductDOList) {
                Map<String, Object> returnMap = appStoreProductAttrService.getProductAttrDetail(storeProductDO.getId());
                AppStoreProductRespVo storeProductQueryVo = StoreProductConvert.INSTANCE.convert01(storeProductDO);

                storeProductQueryVo.setProductAttr((List<AppStoreProductAttrQueryVo>) returnMap.get("productAttr"));
                storeProductQueryVo.setProductValue((Map<String, StoreProductAttrValueDO>) returnMap.get("productValue"));

                appStoreProductRespVoList.add(storeProductQueryVo);
            }
//            List<AppStoreProductRespVo> appStoreProductRespVoList = StoreProductConvert.INSTANCE
//                    .convertList03(this.baseMapper.selectList(wrapper));

            appCategoryRespVO.setGoodsList(appStoreProductRespVoList);
        }

        return appCategoryRespVOS;

    }



    /**
     * 返回普通商品库存
     *
     * @param productId 商品id
     * @param unique    sku唯一值
     * @return int
     */
    @Override
    public int getProductStock(Long productId, String unique, String type) {
        StoreProductAttrValueDO storeProductAttrValue = storeProductAttrValueService
                .getOne(Wrappers.<StoreProductAttrValueDO>lambdaQuery()
                        .eq(StoreProductAttrValueDO::getSku, unique)
                        .eq(StoreProductAttrValueDO::getProductId, productId));

        if (storeProductAttrValue == null) {
            return 0;
        }
        if (ProductTypeEnum.PINK.getValue().equals(type)) {
            return storeProductAttrValue.getPinkStock();
        } else if (ProductTypeEnum.SECKILL.getValue().equals(type)) {
            return storeProductAttrValue.getSeckillStock();
        }
        return storeProductAttrValue.getStock();

    }

    /**
     * 获取单个商品
     *
     * @param id 商品id
     * @return YxStoreProductQueryVo
     */
    @Override
    public AppStoreProductRespVo getStoreProductById(Long id) {
        AppStoreProductRespVo storeProductRespVo = StoreProductConvert.INSTANCE.convert01(this.baseMapper.selectById(id));
        Map<String, Object> returnMap = appStoreProductAttrService.getProductAttrDetail(id);
        storeProductRespVo.setProductAttr((List<AppStoreProductAttrQueryVo>) returnMap.get("productAttr"));
        storeProductRespVo.setProductValue((Map<String, StoreProductAttrValueDO>) returnMap.get("productValue"));
        return  storeProductRespVo;
    }


    /**
     * 减少库存与增加销量
     *
     * @param num       数量
     * @param productId 商品id
     * @param unique    sku
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void decProductStock(int num, Long productId, String unique, Long activityId, String type) {


        int res = 0;
        res =  storeProductAttrValueMapper.decStockIncSales(num,productId,unique);

        if(res == 0) {
            throw exception(PRODUCT_STOCK_LESS);
        }

        int product = this.baseMapper.decStockIncSales(num, productId);
        if (product == 0) {
            throw exception(PRODUCT_STOCK_LESS);
        }


    }


    /**
     * 增加库存 减少销量
     *
     * @param num       数量
     * @param productId 商品id
     * @param unique    sku唯一值
     */
    @Override
    public void incProductStock(Integer num, Long productId, String unique, Long activityId, String type) {
        //处理属性sku
        if (StrUtil.isNotEmpty(unique)) {
            storeProductAttrValueMapper.incStockDecSales(num, productId, unique);
        }
        //更新商品
        this.baseMapper.incStockDecSales(num, productId);
    }

    /**
     * 检测商品库存 库存加锁
     *
     * @param uid               用户ID
     * @param productId         产品ID
     * @param cartNum           购买数量
     * @param productAttrUnique 商品属性Unique
     */
    @Override
    public void checkProductStock(Long uid, Long productId, Integer cartNum, String productAttrUnique) {
        StoreProductDO product = this
                .lambdaQuery().eq(StoreProductDO::getId, productId)
                .eq(StoreProductDO::getIsShow, ShopCommonEnum.SHOW_1.getValue())
                .one();
        if (product == null) {
            throw exception(STORE_PRODUCT_NOT_EXISTS);
        }

        int stock = this.getProductStock(productId, productAttrUnique, "");
        if (stock < cartNum) {
            throw exception(new ErrorCode(1008003010, product.getStoreName() + "库存不足" + cartNum));
        }

    }



}
