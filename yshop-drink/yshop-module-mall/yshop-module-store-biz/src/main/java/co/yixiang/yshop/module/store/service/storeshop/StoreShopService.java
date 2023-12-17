package co.yixiang.yshop.module.store.service.storeshop;

import java.util.*;
import javax.validation.*;
import co.yixiang.yshop.module.store.controller.admin.storeshop.vo.*;
import co.yixiang.yshop.module.store.dal.dataobject.storeshop.StoreShopDO;
import co.yixiang.yshop.framework.common.pojo.PageResult;

/**
 * 门店管理 Service 接口
 *
 * @author yshop
 */
public interface StoreShopService {

    /**
     * 创建门店管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createShop(@Valid StoreShopCreateReqVO createReqVO);

    /**
     * 更新门店管理
     *
     * @param updateReqVO 更新信息
     */
    void updateShop(@Valid StoreShopUpdateReqVO updateReqVO);

    /**
     * 删除门店管理
     *
     * @param id 编号
     */
    void deleteShop(Long id);

    /**
     * 获得门店管理
     *
     * @param id 编号
     * @return 门店管理
     */
    StoreShopDO getShop(Long id);

    /**
     * 获得门店管理列表
     *
     * @return 门店管理列表
     */
    List<StoreShopDO> getShopList();

    /**
     * 获得门店管理分页
     *
     * @param pageReqVO 分页查询
     * @return 门店管理分页
     */
    PageResult<StoreShopDO> getShopPage(StoreShopPageReqVO pageReqVO);

    /**
     * 获得门店管理列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 门店管理列表
     */
    List<StoreShopDO> getShopList(StoreShopExportReqVO exportReqVO);

}
