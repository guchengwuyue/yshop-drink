package co.yixiang.yshop.module.coupon.service.coupon;

import co.yixiang.yshop.framework.common.pojo.PageResult;
import co.yixiang.yshop.module.coupon.controller.admin.coupon.vo.CouponCreateReqVO;
import co.yixiang.yshop.module.coupon.controller.admin.coupon.vo.CouponExportReqVO;
import co.yixiang.yshop.module.coupon.controller.admin.coupon.vo.CouponPageReqVO;
import co.yixiang.yshop.module.coupon.controller.admin.coupon.vo.CouponUpdateReqVO;
import co.yixiang.yshop.module.coupon.controller.app.coupon.vo.AppCouponVO;
import co.yixiang.yshop.module.coupon.controller.app.coupon.vo.AppMyCouponVO;
import co.yixiang.yshop.module.coupon.dal.dataobject.coupon.CouponDO;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * 优惠券 Service 接口
 *
 * @author yshop
 */
public interface AppCouponService extends IService<CouponDO> {

    /**
     * 获取未被领取优惠券
     * @param shopId 店铺id
     * @param page
     * @param pagesize
     * @return
     */
    List<AppCouponVO> getNotList(Long uid, Long shopId, int page, int pagesize);

    /**
     * 领取优惠券
     * @param uid 用户ID
     * @param id  优惠券ID
     * @param code 兑换码
     */
    void receive(Long uid,Long id,String code);

}
