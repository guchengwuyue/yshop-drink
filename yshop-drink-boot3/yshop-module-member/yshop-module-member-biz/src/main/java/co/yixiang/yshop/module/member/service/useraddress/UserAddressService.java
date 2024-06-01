package co.yixiang.yshop.module.member.service.useraddress;

import java.util.*;
import co.yixiang.yshop.module.member.controller.admin.useraddress.vo.*;
import co.yixiang.yshop.module.member.dal.dataobject.useraddress.UserAddressDO;
import co.yixiang.yshop.framework.common.pojo.PageResult;
import jakarta.validation.Valid;

/**
 * 用户地址 Service 接口
 *
 * @author yshop
 */
public interface UserAddressService {

    /**
     * 创建用户地址
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createUserAddress(@Valid UserAddressCreateReqVO createReqVO);

    /**
     * 更新用户地址
     *
     * @param updateReqVO 更新信息
     */
    void updateUserAddress(@Valid UserAddressUpdateReqVO updateReqVO);

    /**
     * 删除用户地址
     *
     * @param id 编号
     */
    void deleteUserAddress(Long id);

    /**
     * 获得用户地址
     *
     * @param id 编号
     * @return 用户地址
     */
    UserAddressDO getUserAddress(Long id);

    /**
     * 获得用户地址列表
     *
     * @param ids 编号
     * @return 用户地址列表
     */
    List<UserAddressDO> getUserAddressList(Collection<Long> ids);

    /**
     * 获得用户地址分页
     *
     * @param pageReqVO 分页查询
     * @return 用户地址分页
     */
    PageResult<UserAddressDO> getUserAddressPage(UserAddressPageReqVO pageReqVO);

    /**
     * 获得用户地址列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 用户地址列表
     */
    List<UserAddressDO> getUserAddressList(UserAddressExportReqVO exportReqVO);

}
