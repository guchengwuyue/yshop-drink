package co.yixiang.yshop.module.product.service.storeproductreply;

import co.yixiang.yshop.module.product.controller.app.product.vo.AppReplyCountVo;
import co.yixiang.yshop.module.product.controller.app.product.vo.AppStoreProductReplyQueryVo;
import co.yixiang.yshop.module.product.dal.dataobject.storeproductreply.StoreProductReplyDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 评论 Service 接口
 *
 * @author yshop
 */
public interface AppStoreProductReplyService extends IService<StoreProductReplyDO> {

    /**
     * 获取评价数量
     * @param productId
     * @return
     */
    Long productReplyCount(long productId);

    /**
     * 获取单条评价
     * @param productId 商品di
     * @return YxStoreProductReplyQueryVo
     */
    AppStoreProductReplyQueryVo getReply(long productId);

    /**
     * 好评比例
     * @param productId
     * @return
     */
    String replyPer(long productId);

    /**
     * 返回当前商品评价数量
     * @param unique
     * @return
     */
    Long replyCount(String unique);

    /**
     * 获取评价列表
     * @param productId 商品id
     * @param type 0-全部 1-好评 2-中评 3-差评
     * @param page page
     * @param limit limit
     * @return list
     */
    List<AppStoreProductReplyQueryVo> getReplyList(long productId, int type, int page, int limit);

    /**
     * 评价数据
     * @param productId 商品id
     * @return ReplyCountVO
     */
    AppReplyCountVo getReplyCount(long productId);

}
