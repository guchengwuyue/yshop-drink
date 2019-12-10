package com.ruoyi.shop.domain;

import com.ruoyi.common.enums.OrderStatus;
import com.ruoyi.common.utils.OrderUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * 订单主对象 store_order
 * 
 * @author hupeng
 * @date 2019-08-29
 */
public class StoreOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单id */
    private Integer orderId;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String orderSn;

    /** 用户id */
    @Excel(name = "用户id")
    private Integer userId;

    /** 订单状态 0-未确认 1-已确认(已收货） 2-已评价 */
    @Excel(name = "订单状态 0-未确认 1-已确认(已收货） 2-已评价")
    private Integer orderStatus;

    /** 发货状态 0-未发货 1-已发货 2-退货中 3-退货完成 */
    @Excel(name = "发货状态 0-未发货 1-已发货 2-退货中 3-退货完成")
    private Integer shippingStatus;



    /** 支付状态 0-未支付 1-已支付 */
    @Excel(name = "支付状态 0-未支付 1-已支付")
    private Integer payStatus;

    /** 收货人 */
    @Excel(name = "收货人")
    private String consignee;

    /** 国家 */
    @Excel(name = "国家")
    private Long country;

    /** 省份 */
    @Excel(name = "省份")
    private Long province;

    /** 城市 */
    @Excel(name = "城市")
    private Long city;

    /** 县区 */
    @Excel(name = "县区")
    private Long district;

    /** 乡镇 */
    @Excel(name = "乡镇")
    private Long twon;

    /** 地区地址 */
    @Excel(name = "地区地址")
    private String cityInfo;

    /** 地址详细地址 */
    @Excel(name = "地址详细地址")
    private String address;

    /** 邮政编码 */
    private String zipcode;

    /** 手机 */
    @Excel(name = "手机")
    private String mobile;

    /** 邮件 */
    private String email;

    /** 收货方式 1-物流 2-快递 3-自取 */
    private Integer receiveType;

    /** 物流code 单号 */
    @NotBlank(message = "物流单号不能为空")
    private String shippingCode;

    /** 物流名称 */
    @NotBlank(message = "物流公司不能为空")
    private String shippingName;

    /** 支付code */
    private String payCode;

    /** 支付方式1-微信2-余额3-转账 */
    @Excel(name = "支付方式1-微信2-余额3-转账")
    private Integer payMethod;

    /** 支付方式为转账的转账id */
    private Integer rechargeId;

    /** 支付方式名称 */
    private String payName;

    /** 发票抬头 */
    private String invoiceTitle;

    /** 纳税人识别号 */
    private String taxpayer;

    /** 商品总价 */
    @Excel(name = "商品总价")
    private Double goodsPrice;

    /** 邮费 */
    private Double shippingPrice;

    /** 使用余额 */
    private Double userMoney;

    /** 优惠券抵扣 */
    @Excel(name = "优惠券抵扣")
    private Double couponPrice;

    /** 使用积分 */
    private Integer integral;

    /** 使用积分抵多少钱 */
    private Double integralMoney;

    /** 应付款金额 */
    @Excel(name = "应付款金额")
    private Double orderAmount;

    /** 订单总价 */
    @Excel(name = "订单总价")
    private Double totalAmount;

    /** 下单时间 */
    @Excel(name = "下单时间")
    private Date addTime;

    /** 最后新发货时间 */
    @Excel(name = "最后新发货时间")
    private Long shippingTime;

    /** 收货确认时间 */
    @Excel(name = "收货确认时间")
    private Integer confirmTime;

    /** 支付时间 */
    @Excel(name = "支付时间")
    private Integer payTime;

    /** 第三方平台交易流水号 */
    private String transactionId;

    /** 活动ID */
    private Long promId;

    /** 订单类型：0普通订单4预售订单5虚拟订单6拼团订单 */
    private Integer promType;

    /** 活动id */
    private Integer orderPromId;

    /** 活动优惠金额 */
    private Double orderPromAmount;

    /** 价格调整 */
    private Double discount;

    /** 用户备注 */
    private String userNote;

    /** 管理员备注 */
    private String adminNote;

    /** 父单单号 */
    private String parentSn;

    /** 是否已分成0未分成1已分成 */
    private Integer isDistribut;

    /** 订金 */
    private Double paidMoney;

    /** 删除|取消时间 */
    private Long deletedTime;

    /** 用户假删除标识,1:删除,0未删除 */
    private Integer deleted;

    /** 管理后台修改变动的价格 */
    @Excel(name = "管理后台修改变动的价格")
    private String editMoney;

    private String orderStatusStr;

    private StoreMember storeMember;

    private List<StoreOrderGoods> storeOrderGoods;

    public List<StoreOrderGoods> getStoreOrderGoods() {
        return storeOrderGoods;
    }

    public void setStoreOrderGoods(List<StoreOrderGoods> storeOrderGoods) {
        this.storeOrderGoods = storeOrderGoods;
    }

    public StoreMember getStoreMember() {
        return storeMember;
    }

    public void setStoreMember(StoreMember storeMember) {
        this.storeMember = storeMember;
    }

    public String getOrderStatusStr() {

        if(payStatus != null || shippingStatus != null || orderStatus != null || deleted != null){
            int status = OrderUtils.orderStatus(payStatus,shippingStatus,orderStatus,deleted);
            return OrderStatus.getValue(status).getDesc();
        }
        return "==";
    }

    public void setOrderStatusStr(String orderStatusStr) {
        this.orderStatusStr = orderStatusStr;
    }

    public void setOrderId(Integer orderId)
    {
        this.orderId = orderId;
    }

    public Integer getOrderId() 
    {
        return orderId;
    }
    public void setOrderSn(String orderSn) 
    {
        this.orderSn = orderSn;
    }

    public String getOrderSn() 
    {
        return orderSn;
    }
    public void setUserId(Integer userId) 
    {
        this.userId = userId;
    }

    public Integer getUserId() 
    {
        return userId;
    }
    public void setOrderStatus(Integer orderStatus) 
    {
        this.orderStatus = orderStatus;
    }

    public Integer getOrderStatus() 
    {
        return orderStatus;
    }
    public void setShippingStatus(Integer shippingStatus) 
    {
        this.shippingStatus = shippingStatus;
    }

    public Integer getShippingStatus() 
    {
        return shippingStatus;
    }
    public void setPayStatus(Integer payStatus) 
    {
        this.payStatus = payStatus;
    }

    public Integer getPayStatus() 
    {
        return payStatus;
    }
    public void setConsignee(String consignee) 
    {
        this.consignee = consignee;
    }

    public String getConsignee() 
    {
        return consignee;
    }
    public void setCountry(Long country) 
    {
        this.country = country;
    }

    public Long getCountry() 
    {
        return country;
    }
    public void setProvince(Long province) 
    {
        this.province = province;
    }

    public Long getProvince() 
    {
        return province;
    }
    public void setCity(Long city) 
    {
        this.city = city;
    }

    public Long getCity() 
    {
        return city;
    }
    public void setDistrict(Long district) 
    {
        this.district = district;
    }

    public Long getDistrict() 
    {
        return district;
    }
    public void setTwon(Long twon) 
    {
        this.twon = twon;
    }

    public Long getTwon() 
    {
        return twon;
    }
    public void setCityInfo(String cityInfo) 
    {
        this.cityInfo = cityInfo;
    }

    public String getCityInfo() 
    {
        return cityInfo;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setZipcode(String zipcode) 
    {
        this.zipcode = zipcode;
    }

    public String getZipcode() 
    {
        return zipcode;
    }
    public void setMobile(String mobile) 
    {
        this.mobile = mobile;
    }

    public String getMobile() 
    {
        return mobile;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setReceiveType(Integer receiveType) 
    {
        this.receiveType = receiveType;
    }

    public Integer getReceiveType() 
    {
        return receiveType;
    }
    public void setShippingCode(String shippingCode) 
    {
        this.shippingCode = shippingCode;
    }

    public String getShippingCode() 
    {
        return shippingCode;
    }
    public void setShippingName(String shippingName) 
    {
        this.shippingName = shippingName;
    }

    public String getShippingName() 
    {
        return shippingName;
    }
    public void setPayCode(String payCode) 
    {
        this.payCode = payCode;
    }

    public String getPayCode() 
    {
        return payCode;
    }
    public void setPayMethod(Integer payMethod) 
    {
        this.payMethod = payMethod;
    }

    public Integer getPayMethod() 
    {
        return payMethod;
    }
    public void setRechargeId(Integer rechargeId) 
    {
        this.rechargeId = rechargeId;
    }

    public Integer getRechargeId() 
    {
        return rechargeId;
    }
    public void setPayName(String payName) 
    {
        this.payName = payName;
    }

    public String getPayName() 
    {
        return payName;
    }
    public void setInvoiceTitle(String invoiceTitle) 
    {
        this.invoiceTitle = invoiceTitle;
    }

    public String getInvoiceTitle() 
    {
        return invoiceTitle;
    }
    public void setTaxpayer(String taxpayer) 
    {
        this.taxpayer = taxpayer;
    }

    public String getTaxpayer() 
    {
        return taxpayer;
    }
    public void setGoodsPrice(Double goodsPrice) 
    {
        this.goodsPrice = goodsPrice;
    }

    public Double getGoodsPrice() 
    {
        return goodsPrice;
    }
    public void setShippingPrice(Double shippingPrice) 
    {
        this.shippingPrice = shippingPrice;
    }

    public Double getShippingPrice() 
    {
        return shippingPrice;
    }
    public void setUserMoney(Double userMoney) 
    {
        this.userMoney = userMoney;
    }

    public Double getUserMoney() 
    {
        return userMoney;
    }
    public void setCouponPrice(Double couponPrice) 
    {
        this.couponPrice = couponPrice;
    }

    public Double getCouponPrice() 
    {
        return couponPrice;
    }
    public void setIntegral(Integer integral) 
    {
        this.integral = integral;
    }

    public Integer getIntegral() 
    {
        return integral;
    }
    public void setIntegralMoney(Double integralMoney) 
    {
        this.integralMoney = integralMoney;
    }

    public Double getIntegralMoney() 
    {
        return integralMoney;
    }
    public void setOrderAmount(Double orderAmount) 
    {
        this.orderAmount = orderAmount;
    }

    public Double getOrderAmount() 
    {
        return orderAmount;
    }
    public void setTotalAmount(Double totalAmount) 
    {
        this.totalAmount = totalAmount;
    }

    public Double getTotalAmount() 
    {
        return totalAmount;
    }
    public void setAddTime(Date addTime)
    {
        this.addTime = addTime;
    }

    public Date getAddTime()
    {
        return addTime;
    }
    public void setShippingTime(Long shippingTime) 
    {
        this.shippingTime = shippingTime;
    }

    public Long getShippingTime() 
    {
        return shippingTime;
    }
    public void setConfirmTime(Integer confirmTime) 
    {
        this.confirmTime = confirmTime;
    }

    public Integer getConfirmTime() 
    {
        return confirmTime;
    }
    public void setPayTime(Integer payTime) 
    {
        this.payTime = payTime;
    }

    public Integer getPayTime() 
    {
        return payTime;
    }
    public void setTransactionId(String transactionId) 
    {
        this.transactionId = transactionId;
    }

    public String getTransactionId() 
    {
        return transactionId;
    }
    public void setPromId(Long promId) 
    {
        this.promId = promId;
    }

    public Long getPromId() 
    {
        return promId;
    }
    public void setPromType(Integer promType) 
    {
        this.promType = promType;
    }

    public Integer getPromType() 
    {
        return promType;
    }
    public void setOrderPromId(Integer orderPromId) 
    {
        this.orderPromId = orderPromId;
    }

    public Integer getOrderPromId() 
    {
        return orderPromId;
    }
    public void setOrderPromAmount(Double orderPromAmount) 
    {
        this.orderPromAmount = orderPromAmount;
    }

    public Double getOrderPromAmount() 
    {
        return orderPromAmount;
    }
    public void setDiscount(Double discount) 
    {
        this.discount = discount;
    }

    public Double getDiscount() 
    {
        return discount;
    }
    public void setUserNote(String userNote) 
    {
        this.userNote = userNote;
    }

    public String getUserNote() 
    {
        return userNote;
    }
    public void setAdminNote(String adminNote) 
    {
        this.adminNote = adminNote;
    }

    public String getAdminNote() 
    {
        return adminNote;
    }
    public void setParentSn(String parentSn) 
    {
        this.parentSn = parentSn;
    }

    public String getParentSn() 
    {
        return parentSn;
    }
    public void setIsDistribut(Integer isDistribut) 
    {
        this.isDistribut = isDistribut;
    }

    public Integer getIsDistribut() 
    {
        return isDistribut;
    }
    public void setPaidMoney(Double paidMoney) 
    {
        this.paidMoney = paidMoney;
    }

    public Double getPaidMoney() 
    {
        return paidMoney;
    }
    public void setDeletedTime(Long deletedTime) 
    {
        this.deletedTime = deletedTime;
    }

    public Long getDeletedTime() 
    {
        return deletedTime;
    }
    public void setDeleted(Integer deleted) 
    {
        this.deleted = deleted;
    }

    public Integer getDeleted() 
    {
        return deleted;
    }
    public void setEditMoney(String editMoney) 
    {
        this.editMoney = editMoney;
    }

    public String getEditMoney() 
    {
        return editMoney;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("orderId", getOrderId())
            .append("orderSn", getOrderSn())
            .append("userId", getUserId())
            .append("orderStatus", getOrderStatus())
            .append("shippingStatus", getShippingStatus())
            .append("payStatus", getPayStatus())
            .append("consignee", getConsignee())
            .append("country", getCountry())
            .append("province", getProvince())
            .append("city", getCity())
            .append("district", getDistrict())
            .append("twon", getTwon())
            .append("cityInfo", getCityInfo())
            .append("address", getAddress())
            .append("zipcode", getZipcode())
            .append("mobile", getMobile())
            .append("email", getEmail())
            .append("receiveType", getReceiveType())
            .append("shippingCode", getShippingCode())
            .append("shippingName", getShippingName())
            .append("payCode", getPayCode())
            .append("payMethod", getPayMethod())
            .append("rechargeId", getRechargeId())
            .append("payName", getPayName())
            .append("invoiceTitle", getInvoiceTitle())
            .append("taxpayer", getTaxpayer())
            .append("goodsPrice", getGoodsPrice())
            .append("shippingPrice", getShippingPrice())
            .append("userMoney", getUserMoney())
            .append("couponPrice", getCouponPrice())
            .append("integral", getIntegral())
            .append("integralMoney", getIntegralMoney())
            .append("orderAmount", getOrderAmount())
            .append("totalAmount", getTotalAmount())
            .append("addTime", getAddTime())
            .append("shippingTime", getShippingTime())
            .append("confirmTime", getConfirmTime())
            .append("payTime", getPayTime())
            .append("transactionId", getTransactionId())
            .append("promId", getPromId())
            .append("promType", getPromType())
            .append("orderPromId", getOrderPromId())
            .append("orderPromAmount", getOrderPromAmount())
            .append("discount", getDiscount())
            .append("userNote", getUserNote())
            .append("adminNote", getAdminNote())
            .append("parentSn", getParentSn())
            .append("isDistribut", getIsDistribut())
            .append("paidMoney", getPaidMoney())
            .append("deletedTime", getDeletedTime())
            .append("deleted", getDeleted())
            .append("editMoney", getEditMoney())
            .toString();
    }
}
