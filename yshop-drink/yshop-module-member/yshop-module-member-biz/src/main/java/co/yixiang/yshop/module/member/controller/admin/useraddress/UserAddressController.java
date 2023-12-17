package co.yixiang.yshop.module.member.controller.admin.useraddress;

import co.yixiang.yshop.framework.common.pojo.CommonResult;
import co.yixiang.yshop.framework.common.pojo.PageResult;
import co.yixiang.yshop.module.member.controller.admin.useraddress.vo.UserAddressCreateReqVO;
import co.yixiang.yshop.module.member.controller.admin.useraddress.vo.UserAddressPageReqVO;
import co.yixiang.yshop.module.member.controller.admin.useraddress.vo.UserAddressRespVO;
import co.yixiang.yshop.module.member.controller.admin.useraddress.vo.UserAddressUpdateReqVO;
import co.yixiang.yshop.module.member.convert.useraddress.UserAddressConvert;
import co.yixiang.yshop.module.member.dal.dataobject.useraddress.UserAddressDO;
import co.yixiang.yshop.module.member.service.useraddress.UserAddressService;
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

@Tag(name = "管理后台 - 用户地址")
@RestController
@RequestMapping("/member/user-address")
@Validated
public class UserAddressController {

    @Resource
    private UserAddressService userAddressService;

    @PostMapping("/create")
    @Operation(summary = "创建用户地址")
    @PreAuthorize("@ss.hasPermission('member:user-address:create')")
    public CommonResult<Long> createUserAddress(@Valid @RequestBody UserAddressCreateReqVO createReqVO) {
        return success(userAddressService.createUserAddress(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新用户地址")
    @PreAuthorize("@ss.hasPermission('member:user-address:update')")
    public CommonResult<Boolean> updateUserAddress(@Valid @RequestBody UserAddressUpdateReqVO updateReqVO) {
        userAddressService.updateUserAddress(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除用户地址")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('member:user-address:delete')")
    public CommonResult<Boolean> deleteUserAddress(@RequestParam("id") Long id) {
        userAddressService.deleteUserAddress(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得用户地址")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('member:user-address:query')")
    public CommonResult<UserAddressRespVO> getUserAddress(@RequestParam("id") Long id) {
        UserAddressDO userAddress = userAddressService.getUserAddress(id);
        return success(UserAddressConvert.INSTANCE.convert(userAddress));
    }

    @GetMapping("/list")
    @Operation(summary = "获得用户地址列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('member:user-address:query')")
    public CommonResult<List<UserAddressRespVO>> getUserAddressList(@RequestParam("ids") Collection<Long> ids) {
        List<UserAddressDO> list = userAddressService.getUserAddressList(ids);
        return success(UserAddressConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得用户地址分页")
    @PreAuthorize("@ss.hasPermission('member:user-address:query')")
    public CommonResult<PageResult<UserAddressRespVO>> getUserAddressPage(@Valid UserAddressPageReqVO pageVO) {
        PageResult<UserAddressDO> pageResult = userAddressService.getUserAddressPage(pageVO);
        return success(UserAddressConvert.INSTANCE.convertPage(pageResult));
    }



}
