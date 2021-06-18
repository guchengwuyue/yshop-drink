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
import co.yixiang.shop.domain.StoreCoupon;
import co.yixiang.shop.service.IStoreCouponService;
import co.yixiang.common.core.controller.BaseController;
import co.yixiang.common.core.domain.AjaxResult;
import co.yixiang.common.utils.poi.ExcelUtil;
import co.yixiang.common.core.page.TableDataInfo;

/**
 * 优惠券主Controller
 * 
 * @author hupeng
 * @date 2019-08-29
 */
@Controller
@RequestMapping("/shop/coupon")
public class StoreCouponController extends BaseController
{
    private String prefix = "shop/coupon";

    @Autowired
    private IStoreCouponService storeCouponService;

    @RequiresPermissions("shop:coupon:view")
    @GetMapping()
    public String coupon()
    {
        return prefix + "/coupon";
    }

    /**
     * 查询优惠券主列表
     */
    @RequiresPermissions("shop:coupon:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(StoreCoupon storeCoupon)
    {
        startPage();
        List<StoreCoupon> list = storeCouponService.selectStoreCouponList(storeCoupon);
        return getDataTable(list);
    }

    /**
     * 导出优惠券主列表
     */
    @RequiresPermissions("shop:coupon:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(StoreCoupon storeCoupon)
    {
        List<StoreCoupon> list = storeCouponService.selectStoreCouponList(storeCoupon);
        ExcelUtil<StoreCoupon> util = new ExcelUtil<StoreCoupon>(StoreCoupon.class);
        return util.exportExcel(list, "coupon");
    }

    /**
     * 新增优惠券主
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存优惠券主
     */
    @RequiresPermissions("shop:coupon:add")
    @Log(title = "优惠券主", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(StoreCoupon storeCoupon)
    {
        return toAjax(storeCouponService.insertStoreCoupon(storeCoupon));
    }

    /**
     * 修改优惠券主
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        StoreCoupon storeCoupon = storeCouponService.selectStoreCouponById(id);
        mmap.put("storeCoupon", storeCoupon);
        return prefix + "/edit";
    }

    /**
     * 修改保存优惠券主
     */
    @RequiresPermissions("shop:coupon:edit")
    @Log(title = "优惠券主", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(StoreCoupon storeCoupon)
    {
        return toAjax(storeCouponService.updateStoreCoupon(storeCoupon));
    }

    /**
     * 删除优惠券主
     */
    @RequiresPermissions("shop:coupon:remove")
    @Log(title = "优惠券主", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(storeCouponService.deleteStoreCouponByIds(ids));
    }
}
