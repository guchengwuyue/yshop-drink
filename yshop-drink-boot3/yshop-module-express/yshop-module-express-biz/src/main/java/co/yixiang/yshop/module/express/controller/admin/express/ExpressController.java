package co.yixiang.yshop.module.express.controller.admin.express;

import co.yixiang.yshop.framework.common.pojo.CommonResult;
import co.yixiang.yshop.framework.common.pojo.PageResult;
import co.yixiang.yshop.framework.excel.core.util.ExcelUtils;
import co.yixiang.yshop.module.express.controller.admin.express.vo.*;
import co.yixiang.yshop.module.express.convert.express.ExpressConvert;
import co.yixiang.yshop.module.express.dal.dataobject.express.ExpressDO;
import co.yixiang.yshop.module.express.dal.redis.express.ExpressRedisDAO;
import co.yixiang.yshop.module.express.kdniao.model.dto.KdniaoApiBaseDTO;
import co.yixiang.yshop.module.express.kdniao.model.dto.KdniaoApiDTO;
import co.yixiang.yshop.module.express.kdniao.model.vo.KdniaoApiVO;
import co.yixiang.yshop.module.express.kdniao.util.KdniaoUtil;
import co.yixiang.yshop.module.express.service.express.ExpressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.io.IOException;
import java.util.List;

import static co.yixiang.yshop.framework.common.pojo.CommonResult.success;


@Tag(name = "管理后台 - 快递公司")
@RestController
@RequestMapping("/order/express")
@Validated
public class ExpressController {

    @Resource
    private ExpressService expressService;
    @Resource
    private ExpressRedisDAO expressRedisDAO;

    @PostMapping("/create")
    @Operation(summary = "创建快递公司")
    @PreAuthorize("@ss.hasPermission('order:express:create')")
    public CommonResult<Integer> createExpress(@Valid @RequestBody ExpressCreateReqVO createReqVO) {
        return success(expressService.createExpress(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新快递公司")
    @PreAuthorize("@ss.hasPermission('order:express:update')")
    public CommonResult<Boolean> updateExpress(@Valid @RequestBody ExpressUpdateReqVO updateReqVO) {
        expressService.updateExpress(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除快递公司")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('order:express:delete')")
    public CommonResult<Boolean> deleteExpress(@RequestParam("id") Integer id) {
        expressService.deleteExpress(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得快递公司")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('order:express:query')")
    public CommonResult<ExpressRespVO> getExpress(@RequestParam("id") Integer id) {
        ExpressDO express = expressService.getExpress(id);
        return success(ExpressConvert.INSTANCE.convert(express));
    }

    @GetMapping("/list")
    @Operation(summary = "获得快递公司列表")
    @PreAuthorize("@ss.hasPermission('order:express:query')")
    public CommonResult<List<ExpressRespVO>> getExpressList() {
        List<ExpressDO> list = expressService.getExpressList();
        return success(ExpressConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得快递公司分页")
    @PreAuthorize("@ss.hasPermission('order:express:query')")
    public CommonResult<PageResult<ExpressRespVO>> getExpressPage(@Valid ExpressPageReqVO pageVO) {
        PageResult<ExpressDO> pageResult = expressService.getExpressPage(pageVO);
        return success(ExpressConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出快递公司 Excel")
    @PreAuthorize("@ss.hasPermission('order:express:export')")
    public void exportExpressExcel(@Valid ExpressExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ExpressDO> list = expressService.getExpressList(exportReqVO);
        // 导出 Excel
        List<ExpressExcelVO> datas = ExpressConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "快递公司.xls", "数据", ExpressExcelVO.class, datas);
    }

    @GetMapping("/set")
    @Operation(summary = "获得快递鸟配置")
    public CommonResult<KdniaoApiBaseDTO> getExpressSet() {
        return success(expressRedisDAO.get());
    }

    @PostMapping("/set")
    @Operation(summary = "快递鸟配置")
    public CommonResult<Boolean> postExpressSet(@RequestBody KdniaoApiBaseDTO kdniaoApiBaseDTO) {
        expressRedisDAO.set(kdniaoApiBaseDTO);
        return success(true);
    }

    @GetMapping("/getLogistic")
    @Parameters({
            @Parameter(name = "shipperCode", description = "快递公司编码", required = true),
            @Parameter(name = "logisticCode", description = "快递单号", required = true),
    })
    @Operation(summary = "查询物流")
    public CommonResult<KdniaoApiVO> getLogistic(@RequestParam(value = "shipperCode") String shipperCode,
                                                 @RequestParam("logisticCode") String logisticCode) {
        KdniaoApiBaseDTO kdniaoApiBaseDTO = expressRedisDAO.get();
        KdniaoApiDTO params = new KdniaoApiDTO();
        params.setLogisticCode(logisticCode);
        params.setShipperCode(shipperCode);
        params.setApiKey(kdniaoApiBaseDTO.getApiKey());
        params.setEBusinessID(kdniaoApiBaseDTO.getEBusinessID());
        params.setIsFree(kdniaoApiBaseDTO.getIsFree());
        //此处注意 这个地方分收费与免费当 目前用当免费免费当只支持圆通 申通 百世 如果是收费当 改里面RequestType参数 函数里有说明
        return success(KdniaoUtil.getLogisticInfo(params));
    }



}
