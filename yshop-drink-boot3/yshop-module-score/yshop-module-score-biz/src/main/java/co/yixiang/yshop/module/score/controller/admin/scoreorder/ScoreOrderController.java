package co.yixiang.yshop.module.score.controller.admin.scoreorder;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
import jakarta.validation.*;
import java.util.*;
import java.io.IOException;

import co.yixiang.yshop.framework.common.pojo.PageResult;
import co.yixiang.yshop.framework.common.pojo.CommonResult;
import static co.yixiang.yshop.framework.common.pojo.CommonResult.success;

import co.yixiang.yshop.framework.excel.core.util.ExcelUtils;

import co.yixiang.yshop.module.score.controller.admin.scoreorder.vo.*;
import co.yixiang.yshop.module.score.dal.dataobject.scoreorder.ScoreOrderDO;
import co.yixiang.yshop.module.score.convert.scoreorder.ScoreOrderConvert;
import co.yixiang.yshop.module.score.service.scoreorder.ScoreOrderService;

@Tag(name = "管理后台 - 积分商城订单")
@RestController
@RequestMapping("/score/order")
@Validated
public class ScoreOrderController {

    @Resource
    private ScoreOrderService orderService;


    @PutMapping("/update")
    @Operation(summary = "更新积分商城订单")
    @PreAuthorize("@ss.hasPermission('score:order:update')")
    public CommonResult<Boolean> updateOrder(@Valid @RequestBody ScoreOrderUpdateReqVO updateReqVO) {
        orderService.updateOrder(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除积分商城订单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('score:order:delete')")
    public CommonResult<Boolean> deleteOrder(@RequestParam("id") Long id) {
        orderService.deleteOrder(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得积分商城订单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('score:order:query')")
    public CommonResult<ScoreOrderRespVO> getOrder(@RequestParam("id") Long id) {
        return success(orderService.getOrder(id));
    }

    @GetMapping("/list")
    @Operation(summary = "获得积分商城订单列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('score:order:query')")
    public CommonResult<List<ScoreOrderRespVO>> getOrderList(@RequestParam("ids") Collection<Long> ids) {
        List<ScoreOrderDO> list = orderService.getOrderList(ids);
        return success(ScoreOrderConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得积分商城订单分页")
    @PreAuthorize("@ss.hasPermission('score:order:query')")
    public CommonResult<PageResult<ScoreOrderRespVO>> getOrderPage(@Valid ScoreOrderPageReqVO pageVO) {
        return success(orderService.getOrderPage(pageVO));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出积分商城订单 Excel")
    @PreAuthorize("@ss.hasPermission('score:order:export')")
    public void exportOrderExcel(@Valid ScoreOrderExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ScoreOrderDO> list = orderService.getOrderList(exportReqVO);
        // 导出 Excel
        List<ScoreOrderExcelVO> datas = ScoreOrderConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "积分商城订单.xls", "数据", ScoreOrderExcelVO.class, datas);
    }

    @GetMapping("/take")
    @Operation(summary = "收货")
    @PreAuthorize("@ss.hasPermission('score:order:update')")
    public CommonResult<Boolean> take(@RequestParam("id") Long id) {
        orderService.takeOrder(id);
        return success(true);
    }

}
