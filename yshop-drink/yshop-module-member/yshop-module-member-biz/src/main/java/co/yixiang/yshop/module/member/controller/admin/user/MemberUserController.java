package co.yixiang.yshop.module.member.controller.admin.user;

import co.yixiang.yshop.framework.common.pojo.CommonResult;
import co.yixiang.yshop.framework.common.pojo.PageResult;
import co.yixiang.yshop.module.member.controller.admin.user.vo.*;
import co.yixiang.yshop.module.member.convert.user.UserConvert;
import co.yixiang.yshop.module.member.dal.dataobject.user.MemberUserDO;
import co.yixiang.yshop.module.member.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

import static co.yixiang.yshop.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 用户")
@RestController
@RequestMapping("/member/user")
@Validated
public class MemberUserController {

    @Resource
    private UserService userService;

    @PostMapping("/create")
    @Operation(summary = "创建用户")
    @PreAuthorize("@ss.hasPermission('member:user:create')")
    public CommonResult<Long> createUser(@Valid @RequestBody UserCreateReqVO createReqVO) {
        return success(userService.createUser(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新用户")
    @PreAuthorize("@ss.hasPermission('member:user:update')")
    public CommonResult<Boolean> updateUser(@Valid @RequestBody UserUpdateReqVO updateReqVO) {
        userService.updateUser(updateReqVO);
        return success(true);
    }

    @PutMapping("/updateMony")
    @Operation(summary = "更新用户余额与积分")
    @PreAuthorize("@ss.hasPermission('member:user:update')")
    public CommonResult<Boolean> updateMony(@Valid @RequestBody UserUpdateMoneyReqVO updateReqVO) {
        userService.updateMony(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除用户")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('member:user:delete')")
    public CommonResult<Boolean> deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得用户")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('member:user:query')")
    public CommonResult<UserRespVO> getUser(@RequestParam("id") Long id) {
        MemberUserDO user = userService.getUser(id);
        return success(UserConvert.INSTANCE.convert(user,true));
    }

    @GetMapping("/list")
    @Operation(summary = "获得用户列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('member:user:query')")
    public CommonResult<List<UserRespVO>> getUserList(@RequestParam("ids") Collection<Long> ids) {
        List<MemberUserDO> list = userService.getUserList(ids);
        return success(UserConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得用户分页")
    @PreAuthorize("@ss.hasPermission('member:user:query')")
    public CommonResult<PageResult<UserRespVO>> getUserPage(@Valid UserPageReqVO pageVO) {
        PageResult<MemberUserDO> pageResult = userService.getUserPage(pageVO);
        return success(UserConvert.INSTANCE.convertPage(pageResult));
    }



}
