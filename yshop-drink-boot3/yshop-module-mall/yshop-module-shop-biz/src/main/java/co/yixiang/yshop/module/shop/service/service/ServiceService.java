package co.yixiang.yshop.module.shop.service.service;

import java.util.*;
import jakarta.validation.*;
import co.yixiang.yshop.module.shop.controller.admin.service.vo.*;
import co.yixiang.yshop.module.shop.dal.dataobject.service.ServiceDO;
import co.yixiang.yshop.framework.common.pojo.PageResult;

/**
 * 我的服务 Service 接口
 *
 * @author yshop
 */
public interface ServiceService {

    /**
     * 创建我的服务
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createService(@Valid ServiceCreateReqVO createReqVO);

    /**
     * 更新我的服务
     *
     * @param updateReqVO 更新信息
     */
    void updateService(@Valid ServiceUpdateReqVO updateReqVO);

    /**
     * 删除我的服务
     *
     * @param id 编号
     */
    void deleteService(Long id);

    /**
     * 获得我的服务
     *
     * @param id 编号
     * @return 我的服务
     */
    ServiceDO getService(Long id);

    /**
     * 获得我的服务列表
     *
     * @param ids 编号
     * @return 我的服务列表
     */
    List<ServiceDO> getServiceList(Collection<Long> ids);

    /**
     * 获得我的服务分页
     *
     * @param pageReqVO 分页查询
     * @return 我的服务分页
     */
    PageResult<ServiceDO> getServicePage(ServicePageReqVO pageReqVO);

    /**
     * 获得我的服务列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 我的服务列表
     */
    List<ServiceDO> getServiceList(ServiceExportReqVO exportReqVO);

}
