package co.yixiang.yshop.module.coupon.service.couponuser;

import java.util.*;
import javax.validation.*;
import co.yixiang.yshop.module.coupon.controller.admin.couponuser.vo.*;
import co.yixiang.yshop.module.coupon.dal.dataobject.couponuser.CouponUserDO;
import co.yixiang.yshop.framework.common.pojo.PageResult;

/**
 * 用户领的优惠券 Service 接口
 *
 * @author yshop
 */
public interface CouponUserService {

    /**
     * 创建用户领的优惠券
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createUser(@Valid CouponUserCreateReqVO createReqVO);

    /**
     * 更新用户领的优惠券
     *
     * @param updateReqVO 更新信息
     */
    void updateUser(@Valid CouponUserUpdateReqVO updateReqVO);

    /**
     * 删除用户领的优惠券
     *
     * @param id 编号
     */
    void deleteUser(Integer id);

    /**
     * 获得用户领的优惠券
     *
     * @param id 编号
     * @return 用户领的优惠券
     */
    CouponUserDO getUser(Integer id);

    /**
     * 获得用户领的优惠券列表
     *
     * @param id 编号
     * @return 用户领的优惠券列表
     */
    List<CouponUserDO> getUserList(Integer id);

    /**
     * 获得用户领的优惠券分页
     *
     * @param pageReqVO 分页查询
     * @return 用户领的优惠券分页
     */
    PageResult<CouponUserDO> getUserPage(CouponUserPageReqVO pageReqVO);

    /**
     * 获得用户领的优惠券列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 用户领的优惠券列表
     */
    List<CouponUserDO> getUserList(CouponUserExportReqVO exportReqVO);

}
