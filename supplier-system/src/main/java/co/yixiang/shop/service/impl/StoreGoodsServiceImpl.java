package co.yixiang.shop.service.impl;

import java.util.List;
import java.util.Map;

import co.yixiang.shop.domain.StoreGoods;
import co.yixiang.shop.domain.StoreGoodsAttr;
import co.yixiang.shop.domain.StoreSpecGoodsPrice;
import co.yixiang.shop.mapper.StoreGoodsBrandMapper;
import co.yixiang.shop.mapper.StoreGoodsMapper;
import co.yixiang.shop.mapper.StoreSpecGoodsPriceMapper;
import co.yixiang.shop.service.IStoreGoodsAttrService;
import co.yixiang.shop.service.IStoreGoodsService;
import co.yixiang.shop.service.IStoreSpecGoodsPriceService;
import co.yixiang.shop.domain.StoreGoodsAttr;
import co.yixiang.shop.domain.StoreSpecGoodsPrice;
import co.yixiang.shop.mapper.StoreGoodsBrandMapper;
import co.yixiang.shop.mapper.StoreSpecGoodsPriceMapper;
import co.yixiang.shop.service.IStoreGoodsAttrService;
import co.yixiang.shop.service.IStoreSpecGoodsPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.yixiang.shop.mapper.StoreGoodsMapper;
import co.yixiang.shop.domain.StoreGoods;
import co.yixiang.shop.service.IStoreGoodsService;
import co.yixiang.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商品主Service业务层处理
 * 
 * @author hupeng
 * @date 2019-08-25
 */
@Service
public class StoreGoodsServiceImpl implements IStoreGoodsService
{
    @Autowired
    private StoreGoodsMapper storeGoodsMapper;

    @Autowired
    private StoreGoodsBrandMapper storeGoodsBrandMapper;

    @Autowired
    private StoreSpecGoodsPriceMapper storeSpecGoodsPriceMapper;

    @Autowired
    private IStoreSpecGoodsPriceService storeSpecGoodsPriceService;

    @Autowired
    protected IStoreGoodsAttrService storeGoodsAttrService;

    @Override
    @Transactional
    public int saveSpecAttr(Map<String,Map<String,String>> specDataMap,
                            Map<String,String> attrDataMap,String goodsId,String goodsType) {
        //新增或者更新规格
        StoreSpecGoodsPrice storeSpecGoodsPrice = new StoreSpecGoodsPrice();
        StoreSpecGoodsPrice goodsPriceDomain = new StoreSpecGoodsPrice();
        storeSpecGoodsPrice.setGoodsId(Long.valueOf(goodsId));
        goodsPriceDomain.setGoodsId(Long.valueOf(goodsId));
        storeSpecGoodsPriceMapper.delStoreSpecGoodsPriceByGoodsId(Integer.valueOf(goodsId));

        for(Map.Entry<String, Map<String,String>> entry : specDataMap.entrySet()){
            String mapKey = entry.getKey();
            Map map = entry.getValue();
            storeSpecGoodsPrice.setKey(mapKey);
//            List<StoreSpecGoodsPrice> storeSpecGoodsPrices = storeSpecGoodsPriceService
//                    .selectStoreSpecGoodsPriceList(storeSpecGoodsPrice);

            goodsPriceDomain.setKey(mapKey);
            goodsPriceDomain.setPrice(Double.valueOf(map.get("price").toString()));
            goodsPriceDomain.setMarketPrice(Double.valueOf(map.get("market_price").toString()));
            goodsPriceDomain.setStoreCount(Long.valueOf(map.get("store_count").toString()));
            goodsPriceDomain.setKeyName(map.get("key_name").toString());
            storeSpecGoodsPriceService.insertStoreSpecGoodsPrice(goodsPriceDomain);

//            if(storeSpecGoodsPrices.isEmpty()){
//                goodsPriceDomain.setKeyName(map.get("key_name").toString());
//                storeSpecGoodsPriceService.insertStoreSpecGoodsPrice(goodsPriceDomain);
//            }else{
//                goodsPriceDomain.setItemId(storeSpecGoodsPrices.get(0).getItemId());
//                storeSpecGoodsPriceService.updateStoreSpecGoodsPrice(goodsPriceDomain);
//            }
            //System.out.println(mapKey+":"+map);

        }

        //新增或者更新属性
        StoreGoodsAttr goodsAttr = new StoreGoodsAttr();
        StoreGoodsAttr goodsAttrDomain = new StoreGoodsAttr();
        goodsAttr.setGoodsId(Integer.valueOf(goodsId));
        goodsAttrDomain.setGoodsId(Integer.valueOf(goodsId));
        for (Map.Entry<String, String> entry : attrDataMap.entrySet()) {
            String attrId = entry.getKey();
            String attrValue = entry.getValue();
            goodsAttr.setAttrId(Integer.valueOf(attrId));
            List<StoreGoodsAttr> attrList = storeGoodsAttrService
                    .selectStoreGoodsAttrList(goodsAttr);
            goodsAttrDomain.setAttrValue(attrValue);
            goodsAttrDomain.setAttrId(Integer.valueOf(attrId));
            if(attrList.isEmpty()){
                storeGoodsAttrService.insertStoreGoodsAttr(goodsAttrDomain);
            }else{
                goodsAttrDomain.setGoodsAttrId(attrList.get(0).getGoodsAttrId());
                storeGoodsAttrService.updateStoreGoodsAttr(goodsAttrDomain);
            }

        }

        //商品更新模型
        StoreGoods storeGoods = new StoreGoods();
        storeGoods.setGoodsId(Integer.valueOf(goodsId));
        storeGoods.setGoodsType(Integer.valueOf(goodsType));
        this.updateStoreGoods(storeGoods);

        return 1;
    }

    @Override
    public String getGoodsItems(Long goodsId) {
        return storeSpecGoodsPriceMapper.selectStoreSpecGoodsPriceItemStr(goodsId);
    }

    /**
     * 查询商品主
     * 
     * @param goodsId 商品主ID
     * @return 商品主
     */
    @Override
    public StoreGoods selectStoreGoodsById(Integer goodsId)
    {
        return storeGoodsMapper.selectStoreGoodsById(goodsId);
    }

    /**
     * 查询商品主列表
     * 
     * @param storeGoods 商品
     * @return 商品主
     */
    @Override
    public List<StoreGoods> selectStoreGoodsList(StoreGoods storeGoods)
    {
        List<StoreGoods> storeGoodsList = storeGoodsMapper.selectStoreGoodsList(storeGoods);
        for (StoreGoods goods : storeGoodsList){
            String brandName = storeGoodsBrandMapper
                    .selectStoreGoodsBrandById(goods.getBrandId().longValue()).getBrandTitle();
            goods.setBrandName(brandName);
        }
        return storeGoodsList;
    }

    /**
     * 新增商品主
     * 
     * @param storeGoods 商品主
     * @return 结果
     */
    @Override
    public int insertStoreGoods(StoreGoods storeGoods)
    {
        return storeGoodsMapper.insertStoreGoods(storeGoods);
    }

    /**
     * 修改商品主
     * 
     * @param storeGoods 商品主
     * @return 结果
     */
    @Override
    public int updateStoreGoods(StoreGoods storeGoods)
    {
        return storeGoodsMapper.updateStoreGoods(storeGoods);
    }

    /**
     * 删除商品主对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteStoreGoodsByIds(String ids)
    {
        return storeGoodsMapper.deleteStoreGoodsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除商品主信息
     * 
     * @param goodsId 商品主ID
     * @return 结果
     */
    public int deleteStoreGoodsById(Integer goodsId)
    {
        return storeGoodsMapper.deleteStoreGoodsById(goodsId);
    }
}
