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
import com.ruoyi.shop.domain.StoreGoodsType;
import com.ruoyi.shop.service.IStoreGoodsTypeService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商品类型(商品模型)Controller
 * 
 * @author hupeng
 * @date 2019-08-24
 */
@Controller
@RequestMapping("/shop/type")
public class StoreGoodsTypeController extends BaseController
{
    private String prefix = "shop/type";

    @Autowired
    private IStoreGoodsTypeService storeGoodsTypeService;

    @RequiresPermissions("shop:type:view")
    @GetMapping()
    public String type()
    {
        return prefix + "/type";
    }

    /**
     * 查询商品类型(商品模型)列表
     */
    @RequiresPermissions("shop:type:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(StoreGoodsType storeGoodsType)
    {
        startPage();
        List<StoreGoodsType> list = storeGoodsTypeService.selectStoreGoodsTypeList(storeGoodsType);
        return getDataTable(list);
    }

    /**
     * 导出商品类型(商品模型)列表
     */
    @RequiresPermissions("shop:type:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(StoreGoodsType storeGoodsType)
    {
        List<StoreGoodsType> list = storeGoodsTypeService.selectStoreGoodsTypeList(storeGoodsType);
        ExcelUtil<StoreGoodsType> util = new ExcelUtil<StoreGoodsType>(StoreGoodsType.class);
        return util.exportExcel(list, "type");
    }

    /**
     * 新增商品类型(商品模型)
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存商品类型(商品模型)
     */
    @RequiresPermissions("shop:type:add")
    @Log(title = "商品类型(商品模型)", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(StoreGoodsType storeGoodsType)
    {
        return toAjax(storeGoodsTypeService.insertStoreGoodsType(storeGoodsType));
    }

    /**
     * 修改商品类型(商品模型)
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        StoreGoodsType storeGoodsType = storeGoodsTypeService.selectStoreGoodsTypeById(id);
        mmap.put("storeGoodsType", storeGoodsType);
        return prefix + "/edit";
    }

    /**
     * 修改保存商品类型(商品模型)
     */
    @RequiresPermissions("shop:type:edit")
    @Log(title = "商品类型(商品模型)", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(StoreGoodsType storeGoodsType)
    {
        return toAjax(storeGoodsTypeService.updateStoreGoodsType(storeGoodsType));
    }

    /**
     * 删除商品类型(商品模型)
     */
    @RequiresPermissions("shop:type:remove")
    @Log(title = "商品类型(商品模型)", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(storeGoodsTypeService.deleteStoreGoodsTypeByIds(ids));
    }
}
