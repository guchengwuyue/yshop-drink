package co.yixiang.yshop.module.product.dal.mysql.storeproductreply;

import java.util.*;

import co.yixiang.yshop.framework.common.pojo.PageResult;
import co.yixiang.yshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import co.yixiang.yshop.framework.mybatis.core.mapper.BaseMapperX;
import co.yixiang.yshop.module.product.controller.app.product.vo.AppStoreProductReplyQueryVo;
import co.yixiang.yshop.module.product.dal.dataobject.storeproductreply.StoreProductReplyDO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import co.yixiang.yshop.module.product.controller.admin.storeproductreply.vo.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 评论 Mapper
 *
 * @author yshop
 */
@Mapper
public interface StoreProductReplyMapper extends BaseMapperX<StoreProductReplyDO> {

    default PageResult<StoreProductReplyDO> selectPage(StoreProductReplyPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<StoreProductReplyDO>()
                .eqIfPresent(StoreProductReplyDO::getUid, reqVO.getUid())
                .eqIfPresent(StoreProductReplyDO::getOid, reqVO.getOid())
                .eqIfPresent(StoreProductReplyDO::getUnique, reqVO.getUnique())
                .eqIfPresent(StoreProductReplyDO::getProductId, reqVO.getProductId())
                .eqIfPresent(StoreProductReplyDO::getReplyType, reqVO.getReplyType())
                .eqIfPresent(StoreProductReplyDO::getProductScore, reqVO.getProductScore())
                .eqIfPresent(StoreProductReplyDO::getServiceScore, reqVO.getServiceScore())
                .eqIfPresent(StoreProductReplyDO::getComment, reqVO.getComment())
                .eqIfPresent(StoreProductReplyDO::getPics, reqVO.getPics())
                .betweenIfPresent(StoreProductReplyDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(StoreProductReplyDO::getMerchantReplyContent, reqVO.getMerchantReplyContent())
                .betweenIfPresent(StoreProductReplyDO::getMerchantReplyTime, reqVO.getMerchantReplyTime())
                .eqIfPresent(StoreProductReplyDO::getIsReply, reqVO.getIsReply())
                .orderByDesc(StoreProductReplyDO::getId));
    }



    @Select("select A.product_score as productScore,A.service_score as serviceScore," +
            "A.comment,A.merchant_reply_content as merchantReplyContent," +
            "A.merchant_reply_time as merchantReplyTime,A.pics as pictures,A.create_Time as createTime," +
            "B.nickname,B.avatar,C.cart_info as cartInfo" +
            " from yshop_store_product_reply A left join yshop_user B " +
            "on A.uid = B.id left join yshop_store_order_cart_info C on A.`unique` = C.`unique`" +
            " where A.product_id=#{productId} and A.deleted=0 " +
            "order by A.create_Time DESC limit 1")
    AppStoreProductReplyQueryVo getReply(long productId);

    @Select("<script>select A.product_score as productScore,A.service_score as serviceScore," +
            "A.comment,A.merchant_reply_content as merchantReplyContent," +
            "A.merchant_reply_time as merchantReplyTime,A.pics as pictures,A.create_Time as createTime," +
            "B.nickname,B.avatar,C.cart_info as cartInfo" +
            " from yshop_store_product_reply A left join yshop_user B " +
            "on A.uid = B.id left join yshop_store_order_cart_info C on A.`unique` = C.`unique`" +
            " where A.product_id=#{productId} and A.deleted=0 " +
            "<if test='type == 1'>and A.product_score = 5</if>" +
            "<if test='type == 2'>and A.product_score &lt; 5 and A.product_score &gt; 2</if>" +
            "<if test='type == 3'>and A.product_score &lt; 2</if>"+
            " order by A.create_Time DESC</script>")
    List<AppStoreProductReplyQueryVo> selectReplyList(Page page, @Param("productId") long productId, @Param("type") int type);

    @Select("<script>select A.id, A.product_score as productScore,A.service_score as serviceScore," +
            "A.comment,A.merchant_reply_content as merchantReplyContent," +
            "A.merchant_reply_time as merchantReplyTime,A.pics as pictures,A.create_Time as createTime," +
            "B.nickname,B.avatar,C.cart_info as cartInfo" +
            " from yshop_store_product_reply A left join yshop_user B " +
            "on A.uid = B.id left join yshop_store_order_cart_info C on A.`unique` = C.`unique`" +
            " where A.deleted=0 " +
            "<if test =\"nickname !=''\">and B.nickname = #{nickname}</if>" +
            " order by A.create_Time DESC</script>")
    List<AppStoreProductReplyQueryVo> allReplyList(Page page, @Param("nickname") String nickname);

    @Select("<script>select count(*)" +
            " from yshop_store_product_reply A left join yshop_user B " +
            "on A.uid = B.id left join yshop_store_order_cart_info C on A.`unique` = C.`unique`" +
            " where A.deleted=0 " +
            "<if test =\"nickname !=''\">and B.nickname = #{nickname}</if>" +
            " order by A.create_Time DESC</script>")
   Long allReplyListCount(@Param("nickname") String nickname);

  // <if test ="nickname !=''">

}
