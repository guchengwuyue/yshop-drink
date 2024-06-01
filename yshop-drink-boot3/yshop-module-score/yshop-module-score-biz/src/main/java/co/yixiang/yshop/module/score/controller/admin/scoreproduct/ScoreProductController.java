package co.yixiang.yshop.module.score.controller.admin.scoreproduct;

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

import co.yixiang.yshop.module.score.controller.admin.scoreproduct.vo.*;
import co.yixiang.yshop.module.score.dal.dataobject.scoreproduct.ScoreProductDO;
import co.yixiang.yshop.module.score.convert.scoreproduct.ScoreProductConvert;
import co.yixiang.yshop.module.score.service.scoreproduct.ScoreProductService;

@Tag(name = "管理后台 - 积分产品")
@RestController
@RequestMapping("/score/product")
@Validated
public class ScoreProductController {

    @Resource
    private ScoreProductService productService;

    @PostMapping("/create")
    @Operation(summary = "创建积分产品")
    @PreAuthorize("@ss.hasPermission('score:product:create')")
    public CommonResult<Long> createProduct(@Valid @RequestBody ScoreProductCreateReqVO createReqVO) {
        return success(productService.createProduct(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新积分产品")
    @PreAuthorize("@ss.hasPermission('score:product:update')")
    public CommonResult<Boolean> updateProduct(@Valid @RequestBody ScoreProductUpdateReqVO updateReqVO) {
        productService.updateProduct(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除积分产品")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('score:product:delete')")
    public CommonResult<Boolean> deleteProduct(@RequestParam("id") Long id) {
        productService.deleteProduct(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得积分产品")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('score:product:query')")
    public CommonResult<ScoreProductRespVO> getProduct(@RequestParam("id") Long id) {
        ScoreProductDO product = productService.getProduct(id);
        return success(ScoreProductConvert.INSTANCE.convert(product));
    }

    @GetMapping("/list")
    @Operation(summary = "获得积分产品列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('score:product:query')")
    public CommonResult<List<ScoreProductRespVO>> getProductList(@RequestParam("ids") Collection<Long> ids) {
        List<ScoreProductDO> list = productService.getProductList(ids);
        return success(ScoreProductConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得积分产品分页")
    @PreAuthorize("@ss.hasPermission('score:product:query')")
    public CommonResult<PageResult<ScoreProductRespVO>> getProductPage(@Valid ScoreProductPageReqVO pageVO) {
        PageResult<ScoreProductDO> pageResult = productService.getProductPage(pageVO);
        return success(ScoreProductConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出积分产品 Excel")
    @PreAuthorize("@ss.hasPermission('score:product:export')")
    public void exportProductExcel(@Valid ScoreProductExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ScoreProductDO> list = productService.getProductList(exportReqVO);
        // 导出 Excel
        List<ScoreProductExcelVO> datas = ScoreProductConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "积分产品.xls", "数据", ScoreProductExcelVO.class, datas);
    }

}
