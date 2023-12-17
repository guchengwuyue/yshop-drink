package co.yixiang.yshop.module.shop.controller.admin.material;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import co.yixiang.yshop.framework.common.pojo.PageResult;
import co.yixiang.yshop.framework.common.pojo.CommonResult;
import static co.yixiang.yshop.framework.common.pojo.CommonResult.success;

import co.yixiang.yshop.framework.excel.core.util.ExcelUtils;

import co.yixiang.yshop.framework.operatelog.core.annotations.OperateLog;
import static co.yixiang.yshop.framework.operatelog.core.enums.OperateTypeEnum.*;

import co.yixiang.yshop.module.shop.controller.admin.material.vo.*;
import co.yixiang.yshop.module.shop.dal.dataobject.material.MaterialDO;
import co.yixiang.yshop.module.shop.convert.material.MaterialConvert;
import co.yixiang.yshop.module.shop.service.material.MaterialService;

@Tag(name = "管理后台 - 素材库")
@RestController
@RequestMapping("/shop/material")
@Validated
public class MaterialController {

    @Resource
    private MaterialService materialService;

    @PostMapping("/create")
    @Operation(summary = "创建素材库")
    @PreAuthorize("@ss.hasPermission('shop:material:create')")
    public CommonResult<Long> createMaterial(@Valid @RequestBody MaterialCreateReqVO createReqVO) {
        return success(materialService.createMaterial(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新素材库")
    @PreAuthorize("@ss.hasPermission('shop:material:update')")
    public CommonResult<Boolean> updateMaterial(@Valid @RequestBody MaterialUpdateReqVO updateReqVO) {
        materialService.updateMaterial(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除素材库")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('shop:material:delete')")
    public CommonResult<Boolean> deleteMaterial(@RequestParam("id") Long id) {
        materialService.deleteMaterial(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得素材库")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('shop:material:query')")
    public CommonResult<MaterialRespVO> getMaterial(@RequestParam("id") Long id) {
        MaterialDO material = materialService.getMaterial(id);
        return success(MaterialConvert.INSTANCE.convert(material));
    }

    @GetMapping("/list")
    @Operation(summary = "获得素材库列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('shop:material:query')")
    public CommonResult<List<MaterialRespVO>> getMaterialList(@RequestParam("ids") Collection<Long> ids) {
        List<MaterialDO> list = materialService.getMaterialList(ids);
        return success(MaterialConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得素材库分页")
    @PreAuthorize("@ss.hasPermission('shop:material:query')")
    public CommonResult<PageResult<MaterialRespVO>> getMaterialPage(@Valid MaterialPageReqVO pageVO) {
        PageResult<MaterialDO> pageResult = materialService.getMaterialPage(pageVO);
        return success(MaterialConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出素材库 Excel")
    @PreAuthorize("@ss.hasPermission('shop:material:export')")
    @OperateLog(type = EXPORT)
    public void exportMaterialExcel(@Valid MaterialExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<MaterialDO> list = materialService.getMaterialList(exportReqVO);
        // 导出 Excel
        List<MaterialExcelVO> datas = MaterialConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "素材库.xls", "数据", MaterialExcelVO.class, datas);
    }

}
