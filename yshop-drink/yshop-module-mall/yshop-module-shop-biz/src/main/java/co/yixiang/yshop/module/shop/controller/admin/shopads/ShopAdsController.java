package co.yixiang.yshop.module.shop.controller.admin.shopads;

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

import co.yixiang.yshop.module.shop.controller.admin.shopads.vo.*;
import co.yixiang.yshop.module.shop.dal.dataobject.shopads.ShopAdsDO;
import co.yixiang.yshop.module.shop.convert.shopads.ShopAdsConvert;
import co.yixiang.yshop.module.shop.service.shopads.ShopAdsService;

@Tag(name = "管理后台 - 广告图管理")
@RestController
@RequestMapping("/shop/ads")
@Validated
public class ShopAdsController {

    @Resource
    private ShopAdsService adsService;

    @PostMapping("/create")
    @Operation(summary = "创建广告图管理")
    @PreAuthorize("@ss.hasPermission('shop:ads:create')")
    public CommonResult<Long> createAds(@Valid @RequestBody ShopAdsCreateReqVO createReqVO) {
        return success(adsService.createAds(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新广告图管理")
    @PreAuthorize("@ss.hasPermission('shop:ads:update')")
    public CommonResult<Boolean> updateAds(@Valid @RequestBody ShopAdsUpdateReqVO updateReqVO) {
        adsService.updateAds(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除广告图管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('shop:ads:delete')")
    public CommonResult<Boolean> deleteAds(@RequestParam("id") Long id) {
        adsService.deleteAds(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得广告图管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('shop:ads:query')")
    public CommonResult<ShopAdsRespVO> getAds(@RequestParam("id") Long id) {
        ShopAdsDO ads = adsService.getAds(id);
        return success(ShopAdsConvert.INSTANCE.convert(ads));
    }

    @GetMapping("/list")
    @Operation(summary = "获得广告图管理列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('shop:ads:query')")
    public CommonResult<List<ShopAdsRespVO>> getAdsList(@RequestParam("ids") Collection<Long> ids) {
        List<ShopAdsDO> list = adsService.getAdsList(ids);
        return success(ShopAdsConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得广告图管理分页")
    @PreAuthorize("@ss.hasPermission('shop:ads:query')")
    public CommonResult<PageResult<ShopAdsRespVO>> getAdsPage(@Valid ShopAdsPageReqVO pageVO) {
        PageResult<ShopAdsDO> pageResult = adsService.getAdsPage(pageVO);
        return success(ShopAdsConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出广告图管理 Excel")
    @PreAuthorize("@ss.hasPermission('shop:ads:export')")
    @OperateLog(type = EXPORT)
    public void exportAdsExcel(@Valid ShopAdsExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ShopAdsDO> list = adsService.getAdsList(exportReqVO);
        // 导出 Excel
        List<ShopAdsExcelVO> datas = ShopAdsConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "广告图管理.xls", "数据", ShopAdsExcelVO.class, datas);
    }

}
