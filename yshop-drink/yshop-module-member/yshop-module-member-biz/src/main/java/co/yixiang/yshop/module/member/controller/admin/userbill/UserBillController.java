package co.yixiang.yshop.module.member.controller.admin.userbill;

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

import co.yixiang.yshop.framework.operatelog.core.annotations.OperateLog;
import static co.yixiang.yshop.framework.operatelog.core.enums.OperateTypeEnum.*;

import co.yixiang.yshop.module.member.controller.admin.userbill.vo.*;
import co.yixiang.yshop.module.member.dal.dataobject.userbill.UserBillDO;
import co.yixiang.yshop.module.member.convert.userbill.UserBillConvert;
import co.yixiang.yshop.module.member.service.userbill.UserBillService;

@Tag(name = "管理后台 - 用户账单")
@RestController
@RequestMapping("/member/user-bill")
@Validated
public class UserBillController {

    @Resource
    private UserBillService userBillService;


    @GetMapping("/page")
    @Operation(summary = "获得用户账单分页")
    @PreAuthorize("@ss.hasPermission('member:user-bill:query')")
    public CommonResult<PageResult<UserBillRespVO>> getUserBillPage(@Valid UserBillPageReqVO pageVO) {
        PageResult<UserBillDO> pageResult = userBillService.getUserBillPage(pageVO);
        return success(UserBillConvert.INSTANCE.convertPage(pageResult));
    }







}
