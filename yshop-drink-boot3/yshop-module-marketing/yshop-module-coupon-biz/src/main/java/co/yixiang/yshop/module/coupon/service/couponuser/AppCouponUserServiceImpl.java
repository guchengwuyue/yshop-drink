package co.yixiang.yshop.module.coupon.service.couponuser;

import co.yixiang.yshop.framework.common.enums.ShopCommonEnum;
import co.yixiang.yshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import co.yixiang.yshop.module.coupon.controller.app.coupon.vo.AppMyCouponVO;
import co.yixiang.yshop.module.coupon.convert.couponuser.CouponUserConvert;
import co.yixiang.yshop.module.coupon.dal.dataobject.couponuser.CouponUserDO;
import co.yixiang.yshop.module.coupon.dal.mysql.couponuser.CouponUserMapper;
import co.yixiang.yshop.module.coupon.enums.CouponStatusEnum;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户领的优惠券 Service 实现类
 *
 * @author yshop
 */
@Service
@Validated
public class AppCouponUserServiceImpl extends ServiceImpl<CouponUserMapper, CouponUserDO> implements AppCouponUserService {

    @Resource
    private CouponUserMapper userMapper;



    /**
     * 获取我的优惠券列表
     * @param shopId 店铺id
     * @param page
     * @param pagesize
     * @return
     */
    @Override
    public List<AppMyCouponVO> getList(Long uid, Long shopId, int type, int page, int pagesize) {
        LocalDateTime nowTime = LocalDateTime.now();
        Page<CouponUserDO> pageModel = new Page<>(page, pagesize);
        LambdaQueryWrapperX<CouponUserDO> wrapper = new LambdaQueryWrapperX<>();
        switch (CouponStatusEnum.toType(type)) {
            case STATUS_0:
                wrapper.eq(CouponUserDO::getStatus,CouponStatusEnum.STATUS_0.getValue())
                        .lt(CouponUserDO::getStartTime,nowTime)
                        .gt(CouponUserDO::getEndTime,nowTime);
                break;
            case STATUS_1:
                wrapper.eq(CouponUserDO::getStatus,CouponStatusEnum.STATUS_1.getValue())
                        .lt(CouponUserDO::getStartTime,nowTime)
                        .gt(CouponUserDO::getEndTime,nowTime);
                break;
            case STATUS_2:
                wrapper.lt(CouponUserDO::getEndTime,nowTime);
                break;
            default:
                log.warn("为了遵循阿里巴巴规范");
        }
        wrapper.eqIfPresent(CouponUserDO::getShopId, shopId)
                .eq(CouponUserDO::getUserId,uid);
        IPage<CouponUserDO> pageList = this.baseMapper.selectPage(pageModel, wrapper);
        return CouponUserConvert.INSTANCE.convertList03(pageList.getRecords());
    }


}
