package co.yixiang.yshop.module.member.service.user;

import java.util.*;
import javax.validation.*;
import co.yixiang.yshop.module.member.controller.admin.user.vo.*;
import co.yixiang.yshop.module.member.dal.dataobject.user.MemberUserDO;
import co.yixiang.yshop.framework.common.pojo.PageResult;

/**
 * 用户 Service 接口
 *
 * @author yshop
 */
public interface UserService {

    /**
     * 创建用户
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createUser(@Valid UserCreateReqVO createReqVO);

    /**
     * 更新用户
     *
     * @param updateReqVO 更新信息
     */
    void updateUser(@Valid UserUpdateReqVO updateReqVO);

    /**
     * 更新金额与积分
     *
     * @param updateReqVO 更新信息
     */
    void updateMony(@Valid UserUpdateMoneyReqVO updateReqVO);

    /**
     * 删除用户
     *
     * @param id 编号
     */
    void deleteUser(Long id);

    /**
     * 获得用户
     *
     * @param id 编号
     * @return 用户
     */
    MemberUserDO getUser(Long id);

    /**
     * 获得用户列表
     *
     * @param ids 编号
     * @return 用户列表
     */
    List<MemberUserDO> getUserList(Collection<Long> ids);

    /**
     * 获得用户分页
     *
     * @param pageReqVO 分页查询
     * @return 用户分页
     */
    PageResult<MemberUserDO> getUserPage(UserPageReqVO pageReqVO);

    /**
     * 获得用户列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 用户列表
     */
    List<MemberUserDO> getUserList(UserExportReqVO exportReqVO);

}
