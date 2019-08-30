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
import com.ruoyi.shop.domain.StoreMember;
import com.ruoyi.shop.service.IStoreMemberService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商城会员信息Controller
 * 
 * @author hupeng
 * @date 2019-08-30
 */
@Controller
@RequestMapping("/shop/member")
public class StoreMemberController extends BaseController
{
    private String prefix = "shop/member";

    @Autowired
    private IStoreMemberService storeMemberService;

    @RequiresPermissions("shop:member:view")
    @GetMapping()
    public String member()
    {
        return prefix + "/member";
    }

    /**
     * 查询商城会员信息列表
     */
    @RequiresPermissions("shop:member:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(StoreMember storeMember)
    {
        startPage();
        List<StoreMember> list = storeMemberService.selectStoreMemberList(storeMember);
        return getDataTable(list);
    }

    /**
     * 导出商城会员信息列表
     */
    @RequiresPermissions("shop:member:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(StoreMember storeMember)
    {
        List<StoreMember> list = storeMemberService.selectStoreMemberList(storeMember);
        ExcelUtil<StoreMember> util = new ExcelUtil<StoreMember>(StoreMember.class);
        return util.exportExcel(list, "member");
    }

    /**
     * 新增商城会员信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存商城会员信息
     */
    @RequiresPermissions("shop:member:add")
    @Log(title = "商城会员信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(StoreMember storeMember)
    {
        return toAjax(storeMemberService.insertStoreMember(storeMember));
    }

    /**
     * 修改商城会员信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        StoreMember storeMember = storeMemberService.selectStoreMemberById(id);
        mmap.put("storeMember", storeMember);
        return prefix + "/edit";
    }

    /**
     * 修改保存商城会员信息
     */
    @RequiresPermissions("shop:member:edit")
    @Log(title = "商城会员信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(StoreMember storeMember)
    {
        return toAjax(storeMemberService.updateStoreMember(storeMember));
    }

    /**
     * 删除商城会员信息
     */
    @RequiresPermissions("shop:member:remove")
    @Log(title = "商城会员信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(storeMemberService.deleteStoreMemberByIds(ids));
    }
}
