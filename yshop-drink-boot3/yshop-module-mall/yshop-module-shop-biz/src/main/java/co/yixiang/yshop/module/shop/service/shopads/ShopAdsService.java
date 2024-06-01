package co.yixiang.yshop.module.shop.service.shopads;

import java.util.*;
import jakarta.validation.*;
import co.yixiang.yshop.module.shop.controller.admin.shopads.vo.*;
import co.yixiang.yshop.module.shop.dal.dataobject.shopads.ShopAdsDO;
import co.yixiang.yshop.framework.common.pojo.PageResult;

/**
 * 广告图管理 Service 接口
 *
 * @author yshop
 */
public interface ShopAdsService {

    /**
     * 创建广告图管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAds(@Valid ShopAdsCreateReqVO createReqVO);

    /**
     * 更新广告图管理
     *
     * @param updateReqVO 更新信息
     */
    void updateAds(@Valid ShopAdsUpdateReqVO updateReqVO);

    /**
     * 删除广告图管理
     *
     * @param id 编号
     */
    void deleteAds(Long id);

    /**
     * 获得广告图管理
     *
     * @param id 编号
     * @return 广告图管理
     */
    ShopAdsDO getAds(Long id);

    /**
     * 获得广告图管理列表
     *
     * @param ids 编号
     * @return 广告图管理列表
     */
    List<ShopAdsDO> getAdsList(Collection<Long> ids);

    /**
     * 获得广告图管理分页
     *
     * @param pageReqVO 分页查询
     * @return 广告图管理分页
     */
    PageResult<ShopAdsDO> getAdsPage(ShopAdsPageReqVO pageReqVO);

    /**
     * 获得广告图管理列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 广告图管理列表
     */
    List<ShopAdsDO> getAdsList(ShopAdsExportReqVO exportReqVO);

}
