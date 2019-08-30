package com.ruoyi.app.common.persistence.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 订单主表
 * </p>
 *
 * @author hupeng
 * @since 2019-06-26
 */
@TableName("store_order")
public class StoreOrder extends Model<StoreOrder> {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer orderId;
    /**
     * 店铺ID
     */
    @TableField("store_id")
    private Integer storeId;
    /**
     * 订单编号
     */
    @TableField("order_sn")
    private String orderSn;
    /**
     * 用户id
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * 订单状态 0-未确认 1-已确认(已收货） 2-已评价
     */
    @TableField("order_status")
    private Integer orderStatus;
    /**
     * 发货状态 0-未发货 1-已发货 2-退货中 3-退货完成
     */
    @TableField("shipping_status")
    private Integer shippingStatus;
    /**
     * 支付状态 0-未支付 1-已支付
     */
    @TableField("pay_status")
    private Integer payStatus;
    /**
     * 收货人
     */
    private String consignee;
    /**
     * 国家
     */
    private Integer country;
    /**
     * 省份
     */
    private Integer province;
    /**
     * 城市
     */
    private Integer city;
    /**
     * 县区
     */
    private Integer district;
    /**
     * 乡镇
     */
    private Integer twon;
    /**
     * 地区地址
     */
    @TableField("city_info")
    private String cityInfo;
    /**
     * 地址详细地址
     */
    private String address;
    /**
     * 邮政编码
     */
    private String zipcode;
    /**
     * 手机
     */
    private String mobile;
    /**
     * 邮件
     */
    private String email;
    /**
     * 收货方式 1-物流/快递 2-自取
     */
    @TableField("receive_type")
    private Integer receiveType;
    /**
     * 物流code 单号
     */
    @TableField("shipping_code")
    private String shippingCode;
    /**
     * 物流名称
     */
    @TableField("shipping_name")
    private String shippingName;
    /**
     * 支付code
     */
    @TableField("pay_code")
    private String payCode;
    /**
     * 支付方式1-微信2-余额3-转账
     */
    @TableField("pay_method")
    private Integer payMethod;
    /**
     * 支付方式为转账的转账id
     */
    @TableField("recharge_id")
    private Integer rechargeId;
    /**
     * 支付方式名称
     */
    @TableField("pay_name")
    private String payName;
    /**
     * 发票抬头
     */
    @TableField("invoice_title")
    private String invoiceTitle;
    /**
     * 纳税人识别号
     */
    private String taxpayer;
    /**
     * 商品总价
     */
    @TableField("goods_price")
    private BigDecimal goodsPrice;
    /**
     * 邮费
     */
    @TableField("shipping_price")
    private BigDecimal shippingPrice;
    /**
     * 使用余额
     */
    @TableField("user_money")
    private BigDecimal userMoney;
    /**
     * 优惠券抵扣
     */
    @TableField("coupon_price")
    private BigDecimal couponPrice;
    /**
     * 红包金额
     */
    @TableField("red_price")
    private BigDecimal redPrice;
    @TableField("red_id")
    private Integer redId;
    /**
     * 使用积分
     */
    private Integer integral;
    /**
     * 使用积分抵多少钱
     */
    @TableField("integral_money")
    private BigDecimal integralMoney;
    /**
     * 应付款金额
     */
    @TableField("order_amount")
    private BigDecimal orderAmount;
    /**
     * 订单总价
     */
    @TableField("total_amount")
    private BigDecimal totalAmount;
    /**
     * 下单时间
     */
    @TableField("add_time")
    private Integer addTime;
    /**
     * 最后新发货时间
     */
    @TableField("shipping_time")
    private Integer shippingTime;
    /**
     * 收货确认时间
     */
    @TableField("confirm_time")
    private Integer confirmTime;
    /**
     * 支付时间
     */
    @TableField("pay_time")
    private Integer payTime;
    /**
     * 第三方平台交易流水号
     */
    @TableField("transaction_id")
    private String transactionId;
    /**
     * 活动ID
     */
    @TableField("prom_id")
    private Integer promId;
    /**
     * 订单类型：0普通订单4预售订单5虚拟订单6拼团订单
     */
    @TableField("prom_type")
    private Integer promType;
    /**
     * 活动id
     */
    @TableField("order_prom_id")
    private Integer orderPromId;
    /**
     * 活动优惠金额
     */
    @TableField("order_prom_amount")
    private BigDecimal orderPromAmount;
    @TableField("edit_money")
    private String editMoney;
    /**
     * 价格调整(会员折扣金额)
     */
    private BigDecimal discount;
    /**
     * 用户备注
     */
    @TableField("user_note")
    private String userNote;
    /**
     * 管理员备注
     */
    @TableField("admin_note")
    private String adminNote;
    /**
     * 父单单号
     */
    @TableField("parent_sn")
    private String parentSn;
    /**
     * 0-普通订单 1-未分成 2-已分成
     */
    @TableField("is_distribut")
    private Integer isDistribut;
    /**
     * 订金
     */
    @TableField("paid_money")
    private BigDecimal paidMoney;
    /**
     * 删除|取消时间
     */
    @TableField("deleted_time")
    private Integer deletedTime;
    /**
     * 用户假删除标识,1:删除,0未删除
     */
    private Integer deleted;
    /**
     * 0未结算1已结算
     */
    @TableField("is_checkout")
    private Integer isCheckout;
    /**
     * 平台奖励的积分
     */
    @TableField("give_points")
    private Integer givePoints;
    /**
     * 唯一兑换码
     */
    @TableField("unique_number")
    private Long uniqueNumber;
    /**
     * 店铺类型1订门票2农家乐3找民宿4补给箱5-旗舰店
     */
    @TableField("store_type")
    private Integer storeType;
    /**
     * 唯一二维码
     */
    @TableField("unique_qrcode")
    private String uniqueQrcode;


    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(Integer shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public Integer getCountry() {
        return country;
    }

    public void setCountry(Integer country) {
        this.country = country;
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getDistrict() {
        return district;
    }

    public void setDistrict(Integer district) {
        this.district = district;
    }

    public Integer getTwon() {
        return twon;
    }

    public void setTwon(Integer twon) {
        this.twon = twon;
    }

    public String getCityInfo() {
        return cityInfo;
    }

    public void setCityInfo(String cityInfo) {
        this.cityInfo = cityInfo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getReceiveType() {
        return receiveType;
    }

    public void setReceiveType(Integer receiveType) {
        this.receiveType = receiveType;
    }

    public String getShippingCode() {
        return shippingCode;
    }

    public void setShippingCode(String shippingCode) {
        this.shippingCode = shippingCode;
    }

    public String getShippingName() {
        return shippingName;
    }

    public void setShippingName(String shippingName) {
        this.shippingName = shippingName;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public Integer getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }

    public Integer getRechargeId() {
        return rechargeId;
    }

    public void setRechargeId(Integer rechargeId) {
        this.rechargeId = rechargeId;
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public String getTaxpayer() {
        return taxpayer;
    }

    public void setTaxpayer(String taxpayer) {
        this.taxpayer = taxpayer;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public BigDecimal getShippingPrice() {
        return shippingPrice;
    }

    public void setShippingPrice(BigDecimal shippingPrice) {
        this.shippingPrice = shippingPrice;
    }

    public BigDecimal getUserMoney() {
        return userMoney;
    }

    public void setUserMoney(BigDecimal userMoney) {
        this.userMoney = userMoney;
    }

    public BigDecimal getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(BigDecimal couponPrice) {
        this.couponPrice = couponPrice;
    }

    public BigDecimal getRedPrice() {
        return redPrice;
    }

    public void setRedPrice(BigDecimal redPrice) {
        this.redPrice = redPrice;
    }

    public Integer getRedId() {
        return redId;
    }

    public void setRedId(Integer redId) {
        this.redId = redId;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public BigDecimal getIntegralMoney() {
        return integralMoney;
    }

    public void setIntegralMoney(BigDecimal integralMoney) {
        this.integralMoney = integralMoney;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getAddTime() {
        return addTime;
    }

    public void setAddTime(Integer addTime) {
        this.addTime = addTime;
    }

    public Integer getShippingTime() {
        return shippingTime;
    }

    public void setShippingTime(Integer shippingTime) {
        this.shippingTime = shippingTime;
    }

    public Integer getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Integer confirmTime) {
        this.confirmTime = confirmTime;
    }

    public Integer getPayTime() {
        return payTime;
    }

    public void setPayTime(Integer payTime) {
        this.payTime = payTime;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getPromId() {
        return promId;
    }

    public void setPromId(Integer promId) {
        this.promId = promId;
    }

    public Integer getPromType() {
        return promType;
    }

    public void setPromType(Integer promType) {
        this.promType = promType;
    }

    public Integer getOrderPromId() {
        return orderPromId;
    }

    public void setOrderPromId(Integer orderPromId) {
        this.orderPromId = orderPromId;
    }

    public BigDecimal getOrderPromAmount() {
        return orderPromAmount;
    }

    public void setOrderPromAmount(BigDecimal orderPromAmount) {
        this.orderPromAmount = orderPromAmount;
    }

    public String getEditMoney() {
        return editMoney;
    }

    public void setEditMoney(String editMoney) {
        this.editMoney = editMoney;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getUserNote() {
        return userNote;
    }

    public void setUserNote(String userNote) {
        this.userNote = userNote;
    }

    public String getAdminNote() {
        return adminNote;
    }

    public void setAdminNote(String adminNote) {
        this.adminNote = adminNote;
    }

    public String getParentSn() {
        return parentSn;
    }

    public void setParentSn(String parentSn) {
        this.parentSn = parentSn;
    }

    public Integer getIsDistribut() {
        return isDistribut;
    }

    public void setIsDistribut(Integer isDistribut) {
        this.isDistribut = isDistribut;
    }

    public BigDecimal getPaidMoney() {
        return paidMoney;
    }

    public void setPaidMoney(BigDecimal paidMoney) {
        this.paidMoney = paidMoney;
    }

    public Integer getDeletedTime() {
        return deletedTime;
    }

    public void setDeletedTime(Integer deletedTime) {
        this.deletedTime = deletedTime;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Integer getIsCheckout() {
        return isCheckout;
    }

    public void setIsCheckout(Integer isCheckout) {
        this.isCheckout = isCheckout;
    }

    public Integer getGivePoints() {
        return givePoints;
    }

    public void setGivePoints(Integer givePoints) {
        this.givePoints = givePoints;
    }

    public Long getUniqueNumber() {
        return uniqueNumber;
    }

    public void setUniqueNumber(Long uniqueNumber) {
        this.uniqueNumber = uniqueNumber;
    }

    public Integer getStoreType() {
        return storeType;
    }

    public void setStoreType(Integer storeType) {
        this.storeType = storeType;
    }

    public String getUniqueQrcode() {
        return uniqueQrcode;
    }

    public void setUniqueQrcode(String uniqueQrcode) {
        this.uniqueQrcode = uniqueQrcode;
    }

    @Override
    protected Serializable pkVal() {
        return this.orderId;
    }

    @Override
    public String toString() {
        return "StoreOrder{" +
        ", orderId=" + orderId +
        ", storeId=" + storeId +
        ", orderSn=" + orderSn +
        ", userId=" + userId +
        ", orderStatus=" + orderStatus +
        ", shippingStatus=" + shippingStatus +
        ", payStatus=" + payStatus +
        ", consignee=" + consignee +
        ", country=" + country +
        ", province=" + province +
        ", city=" + city +
        ", district=" + district +
        ", twon=" + twon +
        ", cityInfo=" + cityInfo +
        ", address=" + address +
        ", zipcode=" + zipcode +
        ", mobile=" + mobile +
        ", email=" + email +
        ", receiveType=" + receiveType +
        ", shippingCode=" + shippingCode +
        ", shippingName=" + shippingName +
        ", payCode=" + payCode +
        ", payMethod=" + payMethod +
        ", rechargeId=" + rechargeId +
        ", payName=" + payName +
        ", invoiceTitle=" + invoiceTitle +
        ", taxpayer=" + taxpayer +
        ", goodsPrice=" + goodsPrice +
        ", shippingPrice=" + shippingPrice +
        ", userMoney=" + userMoney +
        ", couponPrice=" + couponPrice +
        ", redPrice=" + redPrice +
        ", redId=" + redId +
        ", integral=" + integral +
        ", integralMoney=" + integralMoney +
        ", orderAmount=" + orderAmount +
        ", totalAmount=" + totalAmount +
        ", addTime=" + addTime +
        ", shippingTime=" + shippingTime +
        ", confirmTime=" + confirmTime +
        ", payTime=" + payTime +
        ", transactionId=" + transactionId +
        ", promId=" + promId +
        ", promType=" + promType +
        ", orderPromId=" + orderPromId +
        ", orderPromAmount=" + orderPromAmount +
        ", editMoney=" + editMoney +
        ", discount=" + discount +
        ", userNote=" + userNote +
        ", adminNote=" + adminNote +
        ", parentSn=" + parentSn +
        ", isDistribut=" + isDistribut +
        ", paidMoney=" + paidMoney +
        ", deletedTime=" + deletedTime +
        ", deleted=" + deleted +
        ", isCheckout=" + isCheckout +
        ", givePoints=" + givePoints +
        ", uniqueNumber=" + uniqueNumber +
        ", storeType=" + storeType +
        ", uniqueQrcode=" + uniqueQrcode +
        "}";
    }
}
