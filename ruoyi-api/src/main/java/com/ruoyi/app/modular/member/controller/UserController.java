package com.ruoyi.app.modular.member.controller;


import com.itmuch.lightsecurity.annotation.PreAuthorize;
import com.itmuch.lightsecurity.jwt.User;
import com.itmuch.lightsecurity.jwt.UserOperator;
import com.ruoyi.app.common.R;
import com.ruoyi.app.common.persistence.model.StoreMember;
import com.ruoyi.app.modular.member.service.IMemberService;
import com.ruoyi.app.modular.member.service.dto.MemberDTO;
import com.ruoyi.app.modular.member.service.impl.AddressServiceImpl;
import com.ruoyi.app.modular.member.service.mapper.MemberMapper;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import com.ruoyi.app.modular.member.service.vo.AddressVO;
import com.ruoyi.app.modular.shop.service.impl.GoodsServiceImpl;
import com.ruoyi.app.modular.shop.service.impl.OrderServiceImpl;
import com.ruoyi.app.modular.shop.service.mapper.GoodsMapper;
import com.ruoyi.app.modular.shop.service.vo.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
//import wiki.xsx.core.handler.StringHandler;
//import wiki.xsx.core.util.RedisUtil;

/**
 * @ClassName 个人中心
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/6/27
 **/

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "个人中心", tags = "个人中心模块", description = "个人中心")
public class UserController {
    private final UserOperator userOperator;
    private final IMemberService memberService;
    private final MemberMapper memberMapper;
    private final AddressServiceImpl addressService;
    private final GoodsServiceImpl goodsService;
    private final GoodsMapper goodsMapper;
    private final OrderServiceImpl orderService;

    @GetMapping(value = "/shop/user-my-info")
    @ApiOperation(value = "我的个人信息",notes = "我的个人信息")
    public R userInfo(){
        //redis测试
        //StringHandler stringHandler = RedisUtil.getStringHandler();
        //stringHandler.set("name","hupeng");
        User user = userOperator.getUser();
        StoreMember member = memberService.getById(user.getId());
        MemberDTO memberDTO = memberMapper.toDto(member);
        return R.success(memberDTO);
    }

    @GetMapping(value = "/shop/user-my-address")
    @ApiOperation(value = "我的地址列表",notes = "我的地址列表")
    public R addressList(@Validated @RequestBody PageVO pageVO){
        int userId = userOperator.getUser().getId();
        return R.success(addressService.getList(pageVO,userId));
    }

    @PostMapping(value = "/shop/user-address-add-edit")
    @ApiOperation(value = "添加或修改地址",notes = "添加或修改地址")
    public R addAndEditAdress(@Validated @RequestBody AddressVO addressVO){
        int userId = userOperator.getUser().getId();
        boolean result = addressService.addAndEdit(addressVO,userId);
        if(result){
            return R.success("操作成功");
        }else{
            return R.error(4000,"操作失败");
        }
    }

    @PostMapping(value = "/shop/user-address-del")
    @ApiOperation(value = "删除地址",notes = "删除地址")
    public R delAddress(@RequestParam(value = "address_id",defaultValue = "0") int addressId){

        boolean result = addressService.removeById(addressId);
        if(result){
            return R.success("操作成功");
        }else{
            return R.error(4000,"操作失败");
        }
    }


    @GetMapping(value = "/shop/user-my-coupons")
    @ApiOperation(value = "我的优惠券",notes = "我的优惠券")
    public R myCoupons(@RequestParam(value = "orderTotalPrice",defaultValue = "0") double orderTotalPrice){
        int userId = userOperator.getUser().getId();
        return R.success(memberService.couponList(userId,orderTotalPrice));
    }

    @GetMapping(value = "/shop/user-my-collects")
    @ApiOperation(value = "我的收藏列表",notes = "我的收藏列表")
    public R myCollect(@Validated @RequestBody PageVO pageVO){
        int userId = userOperator.getUser().getId();
        return R.success(goodsMapper.toDto(goodsService
                .collectGoods(pageVO.getPage(),pageVO.getLimit(),userId)));
    }

    @GetMapping(value = "/shop/user-order-list")
    @ApiOperation(value = "订单列表",notes = "订单列表")
    public R orderList(@RequestParam(value = "status",defaultValue = "0") int status,
                       @RequestParam(value = "page",defaultValue = "1") int page,
                       @RequestParam(value = "limit",defaultValue = "10") int limit){

        int userId = userOperator.getUser().getId();
        return R.success(orderService.orderList(status,userId,page,limit));
    }

    @GetMapping(value = "/shop/user-order-detail")
    @ApiOperation(value = "订单详情",notes = "订单详情")
    public R orderDetail(@RequestParam(value = "order_id",defaultValue = "0") String orderId){
        int userId = userOperator.getUser().getId();

        return R.success(orderService.orderDetail(orderId,userId));
    }

    @PostMapping(value = "/shop/user-order-handle")
    @ApiOperation(value = "订单操作",notes = "订单操作")
    public R handleOrder(@RequestParam(value = "order_id",defaultValue = "0") int orderId,
                         @RequestParam(value = "type",defaultValue = "1") int type){
        int userId = userOperator.getUser().getId();
        orderService.orderHandle(orderId,type,userId);
        return R.success("操作成功");
    }


    @GetMapping(value = "/shop/user-points-money-logs")
    @ApiOperation(value = "获取积分或者余额流水",notes = "获取积分或者余额流水")
    public R pointsMoneyLogs(@RequestParam(value = "type",defaultValue = "1") int type,
                             @RequestParam(value = "page",defaultValue = "1") int page,
                             @RequestParam(value = "limit",defaultValue = "10") int limit){

        int userId = userOperator.getUser().getId();
        return R.success(orderService.pointsMoneyLogs(type,userId,page,limit));

    }






    /**
    @GetMapping(value = "/super")
    @PreAuthorize("hasAllRoles('user','admin')")
    @ApiOperation(value = "超管测试信息",notes = "超管测试信息")
    public R superInfo(){
        return R.success("超级管理员才可以访问的权限");
    }
    **/
}
