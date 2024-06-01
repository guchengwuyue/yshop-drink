package co.yixiang.yshop.module.product.service.storeproductreply;

import co.yixiang.yshop.framework.common.pojo.PageResult;
import co.yixiang.yshop.module.product.controller.admin.storeproductreply.vo.StoreProductReplyPageReqVO;
import co.yixiang.yshop.module.product.controller.admin.storeproductreply.vo.StoreProductReplyUpdateReqVO;
import co.yixiang.yshop.module.product.controller.app.product.vo.AppStoreProductReplyQueryVo;
import co.yixiang.yshop.module.product.dal.dataobject.storeproductreply.StoreProductReplyDO;

import jakarta.validation.Valid;

/**
 * 评论 Service 接口
 *
 * @author yshop
 */
public interface StoreProductReplyService {


    /**
     * 更新评论
     *
     * @param updateReqVO 更新信息
     */
    void updateStoreProductReply(@Valid StoreProductReplyUpdateReqVO updateReqVO);

    /**
     * 删除评论
     *
     * @param id 编号
     */
    void deleteStoreProductReply(Long id);

    /**
     * 获得评论
     *
     * @param id 编号
     * @return 评论
     */
    StoreProductReplyDO getStoreProductReply(Long id);


    /**
     * 获得评论分页
     *
     * @param pageReqVO 分页查询
     * @return 评论分页
     */
    PageResult<AppStoreProductReplyQueryVo> getStoreProductReplyPage(StoreProductReplyPageReqVO pageReqVO);



}
