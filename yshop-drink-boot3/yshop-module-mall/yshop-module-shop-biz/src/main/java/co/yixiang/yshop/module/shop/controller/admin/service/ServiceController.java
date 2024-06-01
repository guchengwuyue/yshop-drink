package co.yixiang.yshop.module.shop.controller.admin.service;

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

import co.yixiang.yshop.module.shop.controller.admin.service.vo.*;
import co.yixiang.yshop.module.shop.dal.dataobject.service.ServiceDO;
import co.yixiang.yshop.module.shop.convert.service.ServiceConvert;
import co.yixiang.yshop.module.shop.service.service.ServiceService;

@Tag(name = "管理后台 - 我的服务")
@RestController
@RequestMapping("/shop/service")
@Validated
public class ServiceController {

    @Resource
    private ServiceService serviceService;

    @PostMapping("/create")
    @Operation(summary = "创建我的服务")
    @PreAuthorize("@ss.hasPermission('shop:service:create')")
    public CommonResult<Long> createService(@Valid @RequestBody ServiceCreateReqVO createReqVO) {
        return success(serviceService.createService(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新我的服务")
    @PreAuthorize("@ss.hasPermission('shop:service:update')")
    public CommonResult<Boolean> updateService(@Valid @RequestBody ServiceUpdateReqVO updateReqVO) {
        serviceService.updateService(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除我的服务")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('shop:service:delete')")
    public CommonResult<Boolean> deleteService(@RequestParam("id") Long id) {
        serviceService.deleteService(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得我的服务")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('shop:service:query')")
    public CommonResult<ServiceRespVO> getService(@RequestParam("id") Long id) {
        ServiceDO service = serviceService.getService(id);
        return success(ServiceConvert.INSTANCE.convert(service));
    }

    @GetMapping("/list")
    @Operation(summary = "获得我的服务列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('shop:service:query')")
    public CommonResult<List<ServiceRespVO>> getServiceList(@RequestParam("ids") Collection<Long> ids) {
        List<ServiceDO> list = serviceService.getServiceList(ids);
        return success(ServiceConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得我的服务分页")
    @PreAuthorize("@ss.hasPermission('shop:service:query')")
    public CommonResult<PageResult<ServiceRespVO>> getServicePage(@Valid ServicePageReqVO pageVO) {
        PageResult<ServiceDO> pageResult = serviceService.getServicePage(pageVO);
        return success(ServiceConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出我的服务 Excel")
    @PreAuthorize("@ss.hasPermission('shop:service:export')")
    public void exportServiceExcel(@Valid ServiceExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ServiceDO> list = serviceService.getServiceList(exportReqVO);
        // 导出 Excel
        List<ServiceExcelVO> datas = ServiceConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "我的服务.xls", "数据", ServiceExcelVO.class, datas);
    }

}
