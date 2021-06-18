package co.yixiang.web.controller.shop;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import co.yixiang.common.annotation.Log;
import co.yixiang.common.enums.BusinessType;
import co.yixiang.shop.domain.StoreOrder;
import co.yixiang.shop.service.IStoreOrderService;
import co.yixiang.common.core.controller.BaseController;
import co.yixiang.common.core.domain.AjaxResult;
import co.yixiang.common.utils.poi.ExcelUtil;
import co.yixiang.common.core.page.TableDataInfo;

/**
 * 订单主Controller
 * 
 * @author hupeng
 * @date 2019-08-29
 */
@Controller
@RequestMapping("/shop/order")
public class StoreOrderController extends BaseController
{
    private String prefix = "shop/order";

    @Autowired
    private IStoreOrderService storeOrderService;

    @RequiresPermissions("shop:order:view")
    @GetMapping()
    public String order()
    {
        return prefix + "/order";
    }

    /**
     * 查询订单主列表
     */
    @RequiresPermissions("shop:order:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(StoreOrder storeOrder)
    {
        startPage();
        if(storeOrder.getOrderStatus() != null){
            switch (storeOrder.getOrderStatus()){
                case -1:
                    storeOrder.setDeleted(1);
                    storeOrder.setOrderStatus(0);
                    storeOrder.setPayStatus(0);
                    storeOrder.setShippingStatus(0);
                    break;
                case 0:
                    storeOrder.setDeleted(0);
                    storeOrder.setOrderStatus(0);
                    storeOrder.setPayStatus(0);
                    storeOrder.setShippingStatus(0);
                    break;
                case 1:
                    storeOrder.setDeleted(0);
                    storeOrder.setPayStatus(1);
                    storeOrder.setOrderStatus(0);
                    storeOrder.setShippingStatus(0);
                    break;
                case 2:
                    storeOrder.setDeleted(0);
                    storeOrder.setPayStatus(1);
                    storeOrder.setOrderStatus(0);
                    storeOrder.setShippingStatus(1);
                    break;
                case 3:
                    storeOrder.setDeleted(0);
                    storeOrder.setPayStatus(1);
                    storeOrder.setOrderStatus(0);
                    storeOrder.setShippingStatus(2);
                    break;
                case 4:
                    storeOrder.setDeleted(0);
                    storeOrder.setPayStatus(1);
                    storeOrder.setOrderStatus(0);
                    storeOrder.setShippingStatus(3);
                    break;
                case 5:
                    storeOrder.setDeleted(0);
                    storeOrder.setPayStatus(1);
                    storeOrder.setOrderStatus(1);
                    storeOrder.setShippingStatus(0);
                    break;
                case 6:
                    storeOrder.setDeleted(0);
                    storeOrder.setPayStatus(1);
                    storeOrder.setOrderStatus(2);
                    storeOrder.setShippingStatus(0);
                    break;

            }
        }

        //System.out.println(storeOrder);
        List<StoreOrder> list = storeOrderService.selectStoreOrderList(storeOrder);
        return getDataTable(list);
    }

    /**
     * 导出订单主列表
     */
    @RequiresPermissions("shop:order:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(StoreOrder storeOrder)
    {
        List<StoreOrder> list = storeOrderService.selectStoreOrderList(storeOrder);
        ExcelUtil<StoreOrder> util = new ExcelUtil<StoreOrder>(StoreOrder.class);
        return util.exportExcel(list, "order");
    }

    /**
     * 新增订单主
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存订单主
     */
    @RequiresPermissions("shop:order:add")
    @Log(title = "订单主", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(StoreOrder storeOrder)
    {
        return toAjax(storeOrderService.insertStoreOrder(storeOrder));
    }

    /**
     * 修改订单主
     */
    @GetMapping("/edit/{orderId}")
    public String edit(@PathVariable("orderId") Integer orderId, ModelMap mmap)
    {
        StoreOrder storeOrder = storeOrderService.selectStoreOrderById(orderId);
        mmap.put("storeOrder", storeOrder);
        return prefix + "/edit";
    }

    /**
     * 修改保存订单主
     */
    @RequiresPermissions("shop:order:edit")
    @Log(title = "订单主", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated StoreOrder storeOrder)
    {
        return toAjax(storeOrderService.updateStoreOrder(storeOrder));
    }

    /**
     * 删除订单主
     */
    @RequiresPermissions("shop:order:remove")
    @Log(title = "订单主", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(storeOrderService.deleteStoreOrderByIds(ids));
    }
}
