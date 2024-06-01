package co.yixiang.yshop.module.order.service.storeordercartinfo;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import co.yixiang.yshop.module.order.dal.dataobject.storeordercartinfo.StoreOrderCartInfoDO;
import co.yixiang.yshop.module.order.dal.mysql.storeordercartinfo.StoreOrderCartInfoMapper;
import co.yixiang.yshop.module.product.dal.dataobject.storeproduct.StoreProductDO;
import co.yixiang.yshop.module.product.dal.dataobject.storeproductattrvalue.StoreProductAttrValueDO;
import co.yixiang.yshop.module.product.service.storeproduct.AppStoreProductService;
import co.yixiang.yshop.module.product.service.storeproductattrvalue.StoreProductAttrValueService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单购物详情 Service 实现类
 *
 * @author yshop
 */
@Slf4j
@Service
@Validated
public class StoreOrderCartInfoServiceImpl extends ServiceImpl<StoreOrderCartInfoMapper, StoreOrderCartInfoDO> implements StoreOrderCartInfoService {

    @Resource
    private AppStoreProductService appStoreProductService;
    @Resource
    private StoreProductAttrValueService storeProductAttrValueService;


    /**
     * 添加购物车商品信息
     * @param oid 订单id
     * @param orderId
     * @param productIds 商品id
     * @param numbers 商品数量
     * @param specs 商品规格
     */
    @Async
    @Override
    public void saveCartInfo(Long oid, String orderId, List<String> productIds,List<String> numbers,List<String> specs) {
        log.info("==========添加购物车商品信息start===========");

        List<StoreOrderCartInfoDO> list = new ArrayList<>();
        for (int i = 0;i < productIds.size();i++){
            String newSku = StrUtil.replace(specs.get(i),"|",",");
            StoreProductDO storeProductDO = appStoreProductService.getById(productIds.get(i));

            StoreProductAttrValueDO storeProductAttrValue = storeProductAttrValueService
                    .getOne(Wrappers.<StoreProductAttrValueDO>lambdaQuery()
                            .eq(StoreProductAttrValueDO::getSku, newSku)
                            .eq(StoreProductAttrValueDO::getProductId, productIds.get(i)));
            StoreOrderCartInfoDO info = new StoreOrderCartInfoDO();
            info.setOid(oid);
            info.setOrderId(orderId);
            info.setCartId(0L);
            info.setProductId(Long.valueOf(productIds.get(i)));
            info.setCartInfo("");
            info.setUnique(IdUtil.simpleUUID());
            info.setIsAfterSales(1);
            info.setTitle(storeProductDO.getStoreName());
            info.setImage(storeProductDO.getImage());
            info.setNumber(Integer.valueOf(numbers.get(i)));
            info.setSpec(specs.get(i));
            info.setPrice(storeProductAttrValue.getPrice());
            list.add(info);

        }

        this.saveBatch(list);
    }



}
