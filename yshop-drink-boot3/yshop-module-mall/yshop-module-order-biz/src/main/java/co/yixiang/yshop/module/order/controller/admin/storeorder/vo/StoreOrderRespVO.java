package co.yixiang.yshop.module.order.controller.admin.storeorder.vo;

import co.yixiang.yshop.module.member.controller.admin.user.vo.UserRespVO;
import co.yixiang.yshop.module.order.dal.dataobject.storeordercartinfo.StoreOrderCartInfoDO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 订单 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StoreOrderRespVO extends StoreOrderBaseVO {

    @Schema(description = "订单ID", required = true, example = "31716")
    private Long id;

    @Schema(description = "添加时间", required = true)
    private LocalDateTime createTime;

    @Schema(description = "用户信息", required = true)
    private UserRespVO userRespVO;

    @Schema(description = "商品信息", required = true)
    private List<StoreOrderCartInfoDO> storeOrderCartInfoDOList;

    @Schema(description = "订单状态", required = true)
    private String StatusStr;



}
