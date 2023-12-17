package co.yixiang.yshop.module.shop.service.shopads;

import cn.hutool.core.util.StrUtil;
import co.yixiang.yshop.framework.common.constant.ShopConstants;
import co.yixiang.yshop.module.store.dal.dataobject.storeshop.StoreShopDO;
import co.yixiang.yshop.module.store.dal.mysql.storeshop.StoreShopMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.util.stream.Collectors;

import co.yixiang.yshop.module.shop.controller.admin.shopads.vo.*;
import co.yixiang.yshop.module.shop.dal.dataobject.shopads.ShopAdsDO;
import co.yixiang.yshop.framework.common.pojo.PageResult;

import co.yixiang.yshop.module.shop.convert.shopads.ShopAdsConvert;
import co.yixiang.yshop.module.shop.dal.mysql.shopads.ShopAdsMapper;

import static co.yixiang.yshop.framework.common.exception.util.ServiceExceptionUtil.exception;
import static co.yixiang.yshop.module.shop.enums.ErrorCodeConstants.*;

/**
 * 广告图管理 Service 实现类
 *
 * @author yshop
 */
@Service
@Validated
public class ShopAdsServiceImpl implements ShopAdsService {

    @Resource
    private ShopAdsMapper adsMapper;
    @Resource
    private StoreShopMapper storeShopMapper;

    @Override
    public Long createAds(ShopAdsCreateReqVO createReqVO) {

        // 插入
        ShopAdsDO ads = ShopAdsConvert.INSTANCE.convert(createReqVO);
        StoreShopDO storeShopDO = storeShopMapper.selectById(createReqVO.getShopId());
        if(storeShopDO == null){
            ads.setShopName("全部");
        }else{
            ads.setShopName(storeShopDO.getName());
        }
        adsMapper.insert(ads);
        // 返回
        return ads.getId();
    }

    @Override
    public void updateAds(ShopAdsUpdateReqVO updateReqVO) {
        // 校验存在
        validateAdsExists(updateReqVO.getId());
        ShopAdsDO updateObj = ShopAdsConvert.INSTANCE.convert(updateReqVO);
        StoreShopDO storeShopDO= storeShopMapper.selectById(updateReqVO.getShopId());
        if(storeShopDO == null){
            updateObj.setShopName("全部");
        }else{
            updateObj.setShopName(storeShopDO.getName());
        }
        adsMapper.updateById(updateObj);
    }

    @Override
    public void deleteAds(Long id) {
        // 校验存在
        validateAdsExists(id);
        // 删除
        adsMapper.deleteById(id);
    }

    private void validateAdsExists(Long id) {
        if (adsMapper.selectById(id) == null) {
            throw exception(ADS_NOT_EXISTS);
        }
    }

    @Override
    public ShopAdsDO getAds(Long id) {
        return adsMapper.selectById(id);
    }

    @Override
    public List<ShopAdsDO> getAdsList(Collection<Long> ids) {
        return adsMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ShopAdsDO> getAdsPage(ShopAdsPageReqVO pageReqVO) {
        return adsMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ShopAdsDO> getAdsList(ShopAdsExportReqVO exportReqVO) {
        return adsMapper.selectList(exportReqVO);
    }

}
