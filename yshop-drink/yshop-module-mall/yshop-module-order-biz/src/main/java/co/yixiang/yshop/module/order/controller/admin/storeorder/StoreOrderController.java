package co.yixiang.yshop.module.order.controller.admin.storeorder;

import co.yixiang.yshop.framework.common.pojo.CommonResult;
import co.yixiang.yshop.framework.common.pojo.PageResult;
import co.yixiang.yshop.framework.excel.core.util.ExcelUtils;
import co.yixiang.yshop.framework.operatelog.core.annotations.OperateLog;
import co.yixiang.yshop.module.member.service.userbill.UserBillService;
import co.yixiang.yshop.module.order.controller.admin.storeorder.vo.*;
import co.yixiang.yshop.module.order.convert.storeorder.StoreOrderConvert;
import co.yixiang.yshop.module.order.dal.dataobject.storeorder.StoreOrderDO;
import co.yixiang.yshop.module.order.dal.dataobject.storeorderstatus.StoreOrderStatusDO;
import co.yixiang.yshop.module.order.dal.redis.order.AsyncCountRedisDAO;
import co.yixiang.yshop.module.order.service.storeorder.AsyncStoreOrderService;
import co.yixiang.yshop.module.order.service.storeorder.StoreOrderService;
import co.yixiang.yshop.module.order.service.storeorder.dto.OrderTimeDataDto;
import co.yixiang.yshop.module.order.service.storeorderstatus.StoreOrderStatusService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static co.yixiang.yshop.framework.common.pojo.CommonResult.success;
import static co.yixiang.yshop.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

@Tag(name = "管理后台 - 订单")
@RestController
@RequestMapping("/order/store-order")
@Validated
public class StoreOrderController {

    @Resource
    private StoreOrderService storeOrderService;
    @Resource
    private StoreOrderStatusService storeOrderStatusService;
    @Resource
    private AsyncCountRedisDAO asyncCountRedisDAO;
    @Resource
    private AsyncStoreOrderService asyncStoreOrderService;


    @PostMapping("/create")
    @Operation(summary = "创建订单")
    @PreAuthorize("@ss.hasPermission('order:store-order:create')")
    public CommonResult<Long> createStoreOrder(@Valid @RequestBody StoreOrderCreateReqVO createReqVO) {
        return success(storeOrderService.createStoreOrder(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新订单")
    @PreAuthorize("@ss.hasPermission('order:store-order:update')")
    public CommonResult<Boolean> updateStoreOrder(@Valid @RequestBody StoreOrderUpdateReqVO updateReqVO) {
        storeOrderService.updateStoreOrder(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除订单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('order:store-order:delete')")
    public CommonResult<Boolean> deleteStoreOrder(@RequestParam("id") Long id) {
        storeOrderService.deleteStoreOrder(id);
        return success(true);
    }

    @GetMapping("/pay")
    @Operation(summary = "订单线下支付")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('order:store-order:delete')")
    public CommonResult<Boolean> payStoreOrder(@RequestParam("id") Long id) {
        storeOrderService.payStoreOrder(id);
        return success(true);
    }

    @GetMapping("/take")
    @Operation(summary = "确认收货")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('order:store-order:delete')")
    public CommonResult<Boolean> takeStoreOrder(@RequestParam("id") Long id) {
        storeOrderService.takeStoreOrder(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得订单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('order:store-order:query')")
    public CommonResult<StoreOrderRespVO> getStoreOrder(@RequestParam("id") Long id) {
        return success(storeOrderService.getStoreOrder(id));
    }

    @GetMapping("/list")
    @Operation(summary = "获得订单列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('order:store-order:query')")
    public CommonResult<List<StoreOrderRespVO>> getStoreOrderList(@RequestParam("ids") Collection<Long> ids) {
        List<StoreOrderDO> list = storeOrderService.getStoreOrderList(ids);
        return success(StoreOrderConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得订单分页")
    @PreAuthorize("@ss.hasPermission('order:store-order:query')")
    public CommonResult<PageResult<StoreOrderRespVO>> getStoreOrderPage(@Valid StoreOrderPageReqVO pageVO) {
        return success(storeOrderService.getStoreOrderPage(pageVO));
    }

    @GetMapping("/record-list")
    @Operation(summary = "获得订单记录列表")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('order:store-order:query')")
    public CommonResult<List<StoreOrderStatusDO>> getStoreOrderRecordList(@RequestParam("id") Long id) {
        List<StoreOrderStatusDO> list = storeOrderStatusService.list(new LambdaQueryWrapper<StoreOrderStatusDO>()
                .eq(StoreOrderStatusDO::getOid,id));
        return success(list);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出订单 Excel")
    @PreAuthorize("@ss.hasPermission('order:store-order:export')")
    @OperateLog(type = EXPORT)
    public void exportStoreOrderExcel(@Valid StoreOrderExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<StoreOrderDO> list = storeOrderService.getStoreOrderList(exportReqVO);
        // 导出 Excel
        List<StoreOrderExcelVO> datas = StoreOrderConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "订单.xls", "数据", StoreOrderExcelVO.class, datas);
    }



    @GetMapping("/count")
    @Operation(summary = "获得订单统计")
    public CommonResult<OrderTimeDataDto> getStoreOrderCount() {
        asyncStoreOrderService.getOrderTimeData();
        return success(asyncCountRedisDAO.get());
    }


    @Operation(summary = "退款")
    @PostMapping(value = "/refund")
    @PreAuthorize("@ss.hasPermission('order:store-order:update')")
    public CommonResult<Boolean> refund(@Validated @RequestBody StoreOrderRefundVO updateReqVO) {
        storeOrderService.orderRefund(updateReqVO.getId(),updateReqVO.getPayPrice(), 0, null);
        return success(true);
    }


}
