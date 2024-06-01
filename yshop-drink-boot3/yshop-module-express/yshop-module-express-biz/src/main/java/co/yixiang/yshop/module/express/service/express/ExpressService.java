package co.yixiang.yshop.module.express.service.express;

import co.yixiang.yshop.framework.common.pojo.PageResult;
import co.yixiang.yshop.module.express.controller.admin.express.vo.ExpressCreateReqVO;
import co.yixiang.yshop.module.express.controller.admin.express.vo.ExpressExportReqVO;
import co.yixiang.yshop.module.express.controller.admin.express.vo.ExpressPageReqVO;
import co.yixiang.yshop.module.express.controller.admin.express.vo.ExpressUpdateReqVO;
import co.yixiang.yshop.module.express.dal.dataobject.express.ExpressDO;


import jakarta.validation.Valid;
import java.util.List;

/**
 * 快递公司 Service 接口
 *
 * @author yshop
 */
public interface ExpressService {

    /**
     * 创建快递公司
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createExpress(@Valid ExpressCreateReqVO createReqVO);

    /**
     * 更新快递公司
     *
     * @param updateReqVO 更新信息
     */
    void updateExpress(@Valid ExpressUpdateReqVO updateReqVO);

    /**
     * 删除快递公司
     *
     * @param id 编号
     */
    void deleteExpress(Integer id);

    /**
     * 获得快递公司
     *
     * @param id 编号
     * @return 快递公司
     */
    ExpressDO getExpress(Integer id);

    /**
     * 获得快递公司列表
     *
     * @return 快递公司列表
     */
    List<ExpressDO> getExpressList();

    /**
     * 获得快递公司分页
     *
     * @param pageReqVO 分页查询
     * @return 快递公司分页
     */
    PageResult<ExpressDO> getExpressPage(ExpressPageReqVO pageReqVO);

    /**
     * 获得快递公司列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 快递公司列表
     */
    List<ExpressDO> getExpressList(ExpressExportReqVO exportReqVO);

}
