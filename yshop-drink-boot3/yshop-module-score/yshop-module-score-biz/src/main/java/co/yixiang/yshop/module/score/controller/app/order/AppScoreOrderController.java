package co.yixiang.yshop.module.score.controller.app.order;

import co.yixiang.yshop.framework.common.pojo.CommonResult;
import co.yixiang.yshop.framework.security.core.annotations.PreAuthenticated;
import co.yixiang.yshop.module.score.controller.app.order.param.AppScoreOrderParam;
import co.yixiang.yshop.module.score.controller.app.order.vo.AppScoreOrderVO;
import co.yixiang.yshop.module.score.controller.app.product.param.AppScoreProductQueryParam;
import co.yixiang.yshop.module.score.controller.app.product.vo.AppScoreProductVO;
import co.yixiang.yshop.module.score.service.scoreorder.AppScoreOrderService;
import co.yixiang.yshop.module.score.service.scoreproduct.AppScoreProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

import static co.yixiang.yshop.framework.common.pojo.CommonResult.success;
import static co.yixiang.yshop.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

@Tag(name = "用户 APP - 积分订单")
@RestController
@RequestMapping("/score-order")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AppScoreOrderController {

    private final AppScoreOrderService appScoreOrderService;

    /**
     * 提交积分订单
     */
    @PreAuthenticated
    @PostMapping("/submit")
    @Operation(summary = "提交积分订单")
    public CommonResult<Boolean> submit(@RequestBody @Valid AppScoreOrderParam appScoreOrderParam){
        Long uid = getLoginUserId();
        appScoreOrderService.submit(uid,appScoreOrderParam);
        return success(true);
    }

    /**
     * 订单列表
     */
    @PreAuthenticated
    @GetMapping("/list")
    @Operation(summary = "订单列表")
    @Parameters({
            @Parameter(name = "type", description = "状态,-1全部 默认为0待发货 1待收货  2已完成",
                    required = true, example = "1"),
            @Parameter(name = "page", description = "页码,默认为1",
                    required = true, example = "1"),
            @Parameter(name = "limit", description = "页大小,默认为10",
                    required = true, example = "10      ")
    })
    public CommonResult<List<AppScoreOrderVO>> orderList(@RequestParam(value = "type", defaultValue = "0") int type,
                                                         @RequestParam(value = "page", defaultValue = "1") int page,
                                                         @RequestParam(value = "limit", defaultValue = "10") int limit) {
        Long uid = getLoginUserId();
        return success(appScoreOrderService.orderList(uid, type, page, limit));
    }

    /**
     * 订单详情
     */
    @PreAuthenticated
    @GetMapping("/detail")
    @Operation(summary = "订单详情")
    @Parameters({
            @Parameter(name = "id", description = "id",
                    required = true, example = "1")
    })
    public CommonResult<AppScoreOrderVO> orderList(@RequestParam(value = "id", defaultValue = "0") Long id) {
        Long uid = getLoginUserId();
        return success(appScoreOrderService.orderDetail(uid, id));
    }


    /**
     * 收货
     */
    @PreAuthenticated
    @GetMapping("/take")
    @Operation(summary = "收货")
    public CommonResult<Boolean> take(@RequestParam(value = "id", defaultValue = "0") Long id){
        Long uid = getLoginUserId();
        appScoreOrderService.take(uid,id);
        return success(true);
    }






}
