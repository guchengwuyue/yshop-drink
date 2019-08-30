package com.ruoyi.web.controller.shop;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.shop.domain.StoreSpec;
import com.ruoyi.shop.service.IStoreSpecService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商品规格(独立)Controller
 * 
 * @author hupeng
 * @date 2019-08-25
 */
@Controller
@RequestMapping("/shop/spec")
public class StoreSpecController extends BaseController
{
    private String prefix = "shop/spec";

    @Autowired
    private IStoreSpecService storeSpecService;

    @RequiresPermissions("shop:spec:view")
    @GetMapping()
    public String spec()
    {
        return prefix + "/spec";
    }

    /**
     * 查询商品规格(独立)列表
     */
    @RequiresPermissions("shop:spec:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(StoreSpec storeSpec)
    {
        startPage();
        List<StoreSpec> list = storeSpecService.selectStoreSpecList(storeSpec);
        return getDataTable(list);
    }

    /**
     * 导出商品规格(独立)列表
     */
    @RequiresPermissions("shop:spec:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(StoreSpec storeSpec)
    {
        List<StoreSpec> list = storeSpecService.selectStoreSpecList(storeSpec);
        ExcelUtil<StoreSpec> util = new ExcelUtil<StoreSpec>(StoreSpec.class);
        return util.exportExcel(list, "spec");
    }

    /**
     * 新增商品规格(独立)
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存商品规格(独立)
     */
    @RequiresPermissions("shop:spec:add")
    @Log(title = "商品规格(独立)", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(StoreSpec storeSpec)
    {
        return toAjax(storeSpecService.insertStoreSpec(storeSpec));
    }

    /**
     * 修改商品规格(独立)
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        StoreSpec storeSpec = storeSpecService.selectStoreSpecById(id);
        mmap.put("storeSpec", storeSpec);
        return prefix + "/edit";
    }

    /**
     * 修改保存商品规格(独立)
     */
    @RequiresPermissions("shop:spec:edit")
    @Log(title = "商品规格(独立)", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(StoreSpec storeSpec)
    {
        return toAjax(storeSpecService.updateStoreSpec(storeSpec));
    }

    /**
     * 删除商品规格(独立)
     */
    @RequiresPermissions("shop:spec:remove")
    @Log(title = "商品规格(独立)", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(storeSpecService.deleteStoreSpecByIds(ids));
    }
}
