package co.yixiang.web.controller.shop;

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
import co.yixiang.common.annotation.Log;
import co.yixiang.common.enums.BusinessType;
import co.yixiang.shop.domain.StoreGoodsBrand;
import co.yixiang.shop.service.IStoreGoodsBrandService;
import co.yixiang.common.core.controller.BaseController;
import co.yixiang.common.core.domain.AjaxResult;
import co.yixiang.common.utils.poi.ExcelUtil;
import co.yixiang.common.core.page.TableDataInfo;

/**
 * 产品品牌Controller
 * 
 * @author hupeng
 * @date 2019-08-23
 */
@Controller
@RequestMapping("/shop/brand")
public class StoreGoodsBrandController extends BaseController
{
    private String prefix = "shop/brand";

    @Autowired
    private IStoreGoodsBrandService storeGoodsBrandService;

    @RequiresPermissions("shop:brand:view")
    @GetMapping()
    public String brand()
    {
        return prefix + "/brand";
    }

    /**
     * 查询产品品牌列表
     */
    @RequiresPermissions("shop:brand:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(StoreGoodsBrand storeGoodsBrand)
    {
        startPage();
        List<StoreGoodsBrand> list = storeGoodsBrandService.selectStoreGoodsBrandList(storeGoodsBrand);
        return getDataTable(list);
    }

    /**
     * 导出产品品牌列表
     */
    @RequiresPermissions("shop:brand:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(StoreGoodsBrand storeGoodsBrand)
    {
        List<StoreGoodsBrand> list = storeGoodsBrandService.selectStoreGoodsBrandList(storeGoodsBrand);
        ExcelUtil<StoreGoodsBrand> util = new ExcelUtil<StoreGoodsBrand>(StoreGoodsBrand.class);
        return util.exportExcel(list, "brand");
    }

    /**
     * 新增产品品牌
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存产品品牌
     */
    @RequiresPermissions("shop:brand:add")
    @Log(title = "产品品牌", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(StoreGoodsBrand storeGoodsBrand)
    {
        return toAjax(storeGoodsBrandService.insertStoreGoodsBrand(storeGoodsBrand));
    }

    /**
     * 修改产品品牌
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        StoreGoodsBrand storeGoodsBrand = storeGoodsBrandService.selectStoreGoodsBrandById(id);
        mmap.put("storeGoodsBrand", storeGoodsBrand);
        return prefix + "/edit";
    }

    /**
     * 修改保存产品品牌
     */
    @RequiresPermissions("shop:brand:edit")
    @Log(title = "产品品牌", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(StoreGoodsBrand storeGoodsBrand)
    {
        return toAjax(storeGoodsBrandService.updateStoreGoodsBrand(storeGoodsBrand));
    }

    /**
     * 删除产品品牌
     */
    @RequiresPermissions("shop:brand:remove")
    @Log(title = "产品品牌", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(storeGoodsBrandService.deleteStoreGoodsBrandByIds(ids));
    }
}
