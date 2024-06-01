package co.yixiang.yshop.module.order.controller.app.order.vo;


import co.yixiang.yshop.module.member.controller.app.user.vo.AppUserQueryVo;
import co.yixiang.yshop.module.member.dal.dataobject.useraddress.UserAddressDO;
import co.yixiang.yshop.module.order.service.storeorder.dto.PriceGroupDto;
import co.yixiang.yshop.module.product.controller.app.cart.vo.AppStoreCartQueryVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName ConfirmOrderVo
 * @Author hupeng <610796224@qq.com>
 * @Date 2023/6/18
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "用户 APP - 订单确认参数参数")
public class AppConfirmOrderVo implements Serializable {
    //地址信息
    private UserAddressDO addressInfo;

    //砍价id
    private Integer bargainId;


    private Integer combinationId;

    //优惠券减
    private Boolean deduction;

    private Boolean enableIntegral;

    private Double enableIntegralNum;

    //积分抵扣
    private Integer integralRatio;

    private String orderKey;

    private PriceGroupDto priceGroup;

    private Integer seckillId;

    //店铺自提
    private Integer storeSelfMention;


   // private StoreCouponUserVo usableCoupon;

    private AppUserQueryVo userInfo;



}
