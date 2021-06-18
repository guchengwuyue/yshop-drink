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
import co.yixiang.shop.domain.StoreGoodsAttribute;
import co.yixiang.shop.service.IStoreGoodsAttributeService;
import co.yixiang.common.core.controller.BaseController;
import co.yixiang.common.core.domain.AjaxResult;
import co.yixiang.common.utils.poi.ExcelUtil;
import co.yixiang.common.core.page.TableDataInfo;

/**
 * 商品的属性(独立)Controller
 * 
 * @author hupeng
 * @date 2019-08-24
 */
@Controller
@RequestMapping("/shop/attribute")
public class StoreGoodsAttributeController extends BaseController
{
    private String prefix = "shop/attribute";

    @Autowired
    private IStoreGoodsAttributeService storeGoodsAttributeService;

    @RequiresPermissions("shop:attribute:view")
    @GetMapping()
    public String attribute()
    {
        return prefix + "/attribute";
    }

    /**
     * 查询商品的属性(独立)列表
     */
    @RequiresPermissions("shop:attribute:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(StoreGoodsAttribute storeGoodsAttribute)
    {
        startPage();
        List<StoreGoodsAttribute> list = storeGoodsAttributeService.selectStoreGoodsAttributeList(storeGoodsAttribute);
        return getDataTable(list);
    }

    /**
     * 导出商品的属性(独立)列表
     */
    @RequiresPermissions("shop:attribute:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(StoreGoodsAttribute storeGoodsAttribute)
    {
        List<StoreGoodsAttribute> list = storeGoodsAttributeService.selectStoreGoodsAttributeList(storeGoodsAttribute);
        ExcelUtil<StoreGoodsAttribute> util = new ExcelUtil<StoreGoodsAttribute>(StoreGoodsAttribute.class);
        return util.exportExcel(list, "attribute");
    }

    /**
     * 新增商品的属性(独立)
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存商品的属性(独立)
     */
    @RequiresPermissions("shop:attribute:add")
    @Log(title = "商品的属性(独立)", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(StoreGoodsAttribute storeGoodsAttribute)
    {
        return toAjax(storeGoodsAttributeService.insertStoreGoodsAttribute(storeGoodsAttribute));
    }

    /**
     * 修改商品的属性(独立)
     */
    @GetMapping("/edit/{attrId}")
    public String edit(@PathVariable("attrId") Long attrId, ModelMap mmap)
    {
        StoreGoodsAttribute storeGoodsAttribute = storeGoodsAttributeService.selectStoreGoodsAttributeById(attrId);
        mmap.put("storeGoodsAttribute", storeGoodsAttribute);
        return prefix + "/edit";
    }

    /**
     * 修改保存商品的属性(独立)
     */
    @RequiresPermissions("shop:attribute:edit")
    @Log(title = "商品的属性(独立)", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(StoreGoodsAttribute storeGoodsAttribute)
    {
        return toAjax(storeGoodsAttributeService.updateStoreGoodsAttribute(storeGoodsAttribute));
    }

    /**
     * 删除商品的属性(独立)
     */
    @RequiresPermissions("shop:attribute:remove")
    @Log(title = "商品的属性(独立)", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(storeGoodsAttributeService.deleteStoreGoodsAttributeByIds(ids));
    }
}
