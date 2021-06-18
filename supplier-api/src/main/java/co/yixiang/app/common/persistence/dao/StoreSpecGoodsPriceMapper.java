package co.yixiang.app.common.persistence.dao;

import co.yixiang.app.common.persistence.model.StoreSpecGoodsPrice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import co.yixiang.app.modular.shop.service.dto.ItemDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品规格关联价格表 Mapper 接口
 * </p>
 *
 * @author hupeng
 * @since 2019-09-07
 */
public interface StoreSpecGoodsPriceMapper extends BaseMapper<StoreSpecGoodsPrice> {

    @Select("select GROUP_CONCAT(`key` ORDER BY store_count desc SEPARATOR '_') as keystr" +
            " from store_spec_goods_price where goods_id=#{goodsId}")
    String goodsSpecKey(int goodsId);

    @Select("<script> SELECT a.name,b.id,b.spec_id as specId,b.item FROM store_spec AS a " +
            "INNER JOIN store_spec_item AS b ON a.id = b.spec_id " +
            "WHERE b.id IN " +
            "<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'> #{item} </foreach>" +
            "ORDER BY b.id </script>")
    List<ItemDTO> goodsSpec(List<String> keys);

    @Update("update store_spec_goods_price set store_count=store_count-#{goodsNum} " +
            "where goods_id=#{goodsId} and `key`=#{specKey}")
    int decCount(@Param("goodsId") int goodsId,
                    @Param("specKey") String specKey,@Param("goodsNum") int goodsNum);

}
