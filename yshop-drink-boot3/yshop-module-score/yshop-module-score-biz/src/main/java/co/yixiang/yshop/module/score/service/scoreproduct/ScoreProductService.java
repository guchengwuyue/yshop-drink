package co.yixiang.yshop.module.score.service.scoreproduct;

import java.util.*;
import jakarta.validation.*;
import co.yixiang.yshop.module.score.controller.admin.scoreproduct.vo.*;
import co.yixiang.yshop.module.score.dal.dataobject.scoreproduct.ScoreProductDO;
import co.yixiang.yshop.framework.common.pojo.PageResult;

/**
 * 积分产品 Service 接口
 *
 * @author yshop
 */
public interface ScoreProductService {

    /**
     * 创建积分产品
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createProduct(@Valid ScoreProductCreateReqVO createReqVO);

    /**
     * 更新积分产品
     *
     * @param updateReqVO 更新信息
     */
    void updateProduct(@Valid ScoreProductUpdateReqVO updateReqVO);

    /**
     * 删除积分产品
     *
     * @param id 编号
     */
    void deleteProduct(Long id);

    /**
     * 获得积分产品
     *
     * @param id 编号
     * @return 积分产品
     */
    ScoreProductDO getProduct(Long id);

    /**
     * 获得积分产品列表
     *
     * @param ids 编号
     * @return 积分产品列表
     */
    List<ScoreProductDO> getProductList(Collection<Long> ids);

    /**
     * 获得积分产品分页
     *
     * @param pageReqVO 分页查询
     * @return 积分产品分页
     */
    PageResult<ScoreProductDO> getProductPage(ScoreProductPageReqVO pageReqVO);

    /**
     * 获得积分产品列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 积分产品列表
     */
    List<ScoreProductDO> getProductList(ScoreProductExportReqVO exportReqVO);

}
