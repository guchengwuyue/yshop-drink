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
 * 商品主表
 * </p>
 *
 * @author hupeng
 * @since 2019-09-05
 */
@TableName("store_goods")
public class StoreGoods extends Model<StoreGoods> {

    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    @TableId(value = "goods_id", type = IdType.AUTO)
    private Integer goodsId;
    /**
     * 分类id
     */
    @TableField("cate_id")
    private Integer cateId;
    /**
     * 扩展分类id
     */
    @TableField("extend_cat_id")
    private Integer extendCatId;
    /**
     * 商品编号
     */
    @TableField("goods_sn")
    private String goodsSn;
    /**
     * 商品名称
     */
    @TableField("goods_name")
    private String goodsName;
    /**
     * 点击数
     */
    @TableField("click_count")
    private Integer clickCount;
    /**
     * 品牌id
     */
    @TableField("brand_id")
    private Integer brandId;
    /**
     * 库存数量
     */
    @TableField("store_count")
    private Integer storeCount;
    /**
     * 商品评论数
     */
    @TableField("comment_count")
    private Integer commentCount;
    /**
     * 商品重量克为单位
     */
    private Integer weight;
    /**
     * 商品体积。单位立方米
     */
    private Double volume;
    /**
     * 市场价(零售价)
     */
    @TableField("market_price")
    private BigDecimal marketPrice;
    /**
     * 本店价(供货价)
     */
    @TableField("shop_price")
    private BigDecimal shopPrice;
    /**
     * 商品成本价
     */
    @TableField("cost_price")
    private BigDecimal costPrice;
    /**
     * 价格阶梯
     */
    @TableField("price_ladder")
    private String priceLadder;
    /**
     * 商品关键词
     */
    private String keywords;
    /**
     * 商品简单描述
     */
    @TableField("goods_remark")
    private String goodsRemark;
    /**
     * 商品详细描述
     */
    @TableField("goods_content")
    private String goodsContent;
    /**
     * 手机端商品详情
     */
    @TableField("mobile_content")
    private String mobileContent;
    /**
     * 商品上传原始图(主图)
     */
    @TableField("goods_logo")
    private String goodsLogo;
    /**
     * 是否为虚拟商品 1是，0否
     */
    @TableField("is_virtual")
    private Integer isVirtual;
    /**
     * 虚拟商品有效期
     */
    @TableField("virtual_indate")
    private Integer virtualIndate;
    /**
     * 虚拟商品购买上限
     */
    @TableField("virtual_limit")
    private Integer virtualLimit;
    /**
     * 是否允许过期退款， 1是，0否
     */
    @TableField("virtual_refund")
    private Integer virtualRefund;
    /**
     * 是否上架 0-未上架 1-销售中
     */
    @TableField("is_on_sale")
    private Integer isOnSale;
    /**
     * 是否包邮0否1是
     */
    @TableField("is_free_shipping")
    private Integer isFreeShipping;
    /**
     * 商品上架时间
     */
    @TableField("on_time")
    private Integer onTime;
    /**
     * 商品排序
     */
    private Integer sort;
    /**
     * 是否推荐0-未推荐 1-已推荐
     */
    @TableField("is_recommend")
    private Integer isRecommend;
    /**
     * 是否新品0-不是 1-是
     */
    @TableField("is_new")
    private Integer isNew;
    /**
     * 是否热卖
     */
    @TableField("is_hot")
    private Integer isHot;
    /**
     * 最后更新时间
     */
    @TableField("last_update")
    private Integer lastUpdate;
    /**
     * 商品所属类型id，取值表goods_type的id
     */
    @TableField("goods_type")
    private Integer goodsType;
    /**
     * 商品规格类型，取值表goods_type的id
     */
    @TableField("spec_type")
    private Integer specType;
    /**
     * 购买商品赠送积分
     */
    @TableField("give_integral")
    private Integer giveIntegral;
    /**
     * 积分兑换：0不参与积分兑换，积分和现金的兑换比例见后台配置
     */
    @TableField("exchange_integral")
    private Integer exchangeIntegral;
    /**
     * 供货商ID
     */
    @TableField("suppliers_id")
    private Integer suppliersId;
    /**
     * 商品销量
     */
    @TableField("sales_sum")
    private Integer salesSum;
    /**
     * 0默认1抢购2团购3优惠促销4预售5虚拟(5其实没用)6拼团
     */
    @TableField("prom_type")
    private Integer promType;
    /**
     * 优惠活动id
     */
    @TableField("prom_id")
    private Integer promId;
    /**
     * 佣金用于分销分成
     */
    private BigDecimal commission;
    /**
     * SPU
     */
    private String spu;
    /**
     * SKU
     */
    private String sku;
    /**
     * 运费模板ID
     */
    @TableField("template_id")
    private Integer templateId;
    /**
     * 视频
     */
    @TableField("goods_video")
    private String goodsVideo;


    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getCateId() {
        return cateId;
    }

    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }

    public Integer getExtendCatId() {
        return extendCatId;
    }

    public void setExtendCatId(Integer extendCatId) {
        this.extendCatId = extendCatId;
    }

    public String getGoodsSn() {
        return goodsSn;
    }

    public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getClickCount() {
        return clickCount;
    }

    public void setClickCount(Integer clickCount) {
        this.clickCount = clickCount;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getStoreCount() {
        return storeCount;
    }

    public void setStoreCount(Integer storeCount) {
        this.storeCount = storeCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public BigDecimal getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(BigDecimal shopPrice) {
        this.shopPrice = shopPrice;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public String getPriceLadder() {
        return priceLadder;
    }

    public void setPriceLadder(String priceLadder) {
        this.priceLadder = priceLadder;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getGoodsRemark() {
        return goodsRemark;
    }

    public void setGoodsRemark(String goodsRemark) {
        this.goodsRemark = goodsRemark;
    }

    public String getGoodsContent() {
        return goodsContent;
    }

    public void setGoodsContent(String goodsContent) {
        this.goodsContent = goodsContent;
    }

    public String getMobileContent() {
        return mobileContent;
    }

    public void setMobileContent(String mobileContent) {
        this.mobileContent = mobileContent;
    }

    public String getGoodsLogo() {
        return goodsLogo;
    }

    public void setGoodsLogo(String goodsLogo) {
        this.goodsLogo = goodsLogo;
    }

    public Integer getIsVirtual() {
        return isVirtual;
    }

    public void setIsVirtual(Integer isVirtual) {
        this.isVirtual = isVirtual;
    }

    public Integer getVirtualIndate() {
        return virtualIndate;
    }

    public void setVirtualIndate(Integer virtualIndate) {
        this.virtualIndate = virtualIndate;
    }

    public Integer getVirtualLimit() {
        return virtualLimit;
    }

    public void setVirtualLimit(Integer virtualLimit) {
        this.virtualLimit = virtualLimit;
    }

    public Integer getVirtualRefund() {
        return virtualRefund;
    }

    public void setVirtualRefund(Integer virtualRefund) {
        this.virtualRefund = virtualRefund;
    }

    public Integer getIsOnSale() {
        return isOnSale;
    }

    public void setIsOnSale(Integer isOnSale) {
        this.isOnSale = isOnSale;
    }

    public Integer getIsFreeShipping() {
        return isFreeShipping;
    }

    public void setIsFreeShipping(Integer isFreeShipping) {
        this.isFreeShipping = isFreeShipping;
    }

    public Integer getOnTime() {
        return onTime;
    }

    public void setOnTime(Integer onTime) {
        this.onTime = onTime;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Integer isRecommend) {
        this.isRecommend = isRecommend;
    }

    public Integer getIsNew() {
        return isNew;
    }

    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }

    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    public Integer getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Integer lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Integer getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }

    public Integer getSpecType() {
        return specType;
    }

    public void setSpecType(Integer specType) {
        this.specType = specType;
    }

    public Integer getGiveIntegral() {
        return giveIntegral;
    }

    public void setGiveIntegral(Integer giveIntegral) {
        this.giveIntegral = giveIntegral;
    }

    public Integer getExchangeIntegral() {
        return exchangeIntegral;
    }

    public void setExchangeIntegral(Integer exchangeIntegral) {
        this.exchangeIntegral = exchangeIntegral;
    }

    public Integer getSuppliersId() {
        return suppliersId;
    }

    public void setSuppliersId(Integer suppliersId) {
        this.suppliersId = suppliersId;
    }

    public Integer getSalesSum() {
        return salesSum;
    }

    public void setSalesSum(Integer salesSum) {
        this.salesSum = salesSum;
    }

    public Integer getPromType() {
        return promType;
    }

    public void setPromType(Integer promType) {
        this.promType = promType;
    }

    public Integer getPromId() {
        return promId;
    }

    public void setPromId(Integer promId) {
        this.promId = promId;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public String getSpu() {
        return spu;
    }

    public void setSpu(String spu) {
        this.spu = spu;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public String getGoodsVideo() {
        return goodsVideo;
    }

    public void setGoodsVideo(String goodsVideo) {
        this.goodsVideo = goodsVideo;
    }

    @Override
    protected Serializable pkVal() {
        return this.goodsId;
    }

    @Override
    public String toString() {
        return "StoreGoods{" +
        ", goodsId=" + goodsId +
        ", cateId=" + cateId +
        ", extendCatId=" + extendCatId +
        ", goodsSn=" + goodsSn +
        ", goodsName=" + goodsName +
        ", clickCount=" + clickCount +
        ", brandId=" + brandId +
        ", storeCount=" + storeCount +
        ", commentCount=" + commentCount +
        ", weight=" + weight +
        ", volume=" + volume +
        ", marketPrice=" + marketPrice +
        ", shopPrice=" + shopPrice +
        ", costPrice=" + costPrice +
        ", priceLadder=" + priceLadder +
        ", keywords=" + keywords +
        ", goodsRemark=" + goodsRemark +
        ", goodsContent=" + goodsContent +
        ", mobileContent=" + mobileContent +
        ", goodsLogo=" + goodsLogo +
        ", isVirtual=" + isVirtual +
        ", virtualIndate=" + virtualIndate +
        ", virtualLimit=" + virtualLimit +
        ", virtualRefund=" + virtualRefund +
        ", isOnSale=" + isOnSale +
        ", isFreeShipping=" + isFreeShipping +
        ", onTime=" + onTime +
        ", sort=" + sort +
        ", isRecommend=" + isRecommend +
        ", isNew=" + isNew +
        ", isHot=" + isHot +
        ", lastUpdate=" + lastUpdate +
        ", goodsType=" + goodsType +
        ", specType=" + specType +
        ", giveIntegral=" + giveIntegral +
        ", exchangeIntegral=" + exchangeIntegral +
        ", suppliersId=" + suppliersId +
        ", salesSum=" + salesSum +
        ", promType=" + promType +
        ", promId=" + promId +
        ", commission=" + commission +
        ", spu=" + spu +
        ", sku=" + sku +
        ", templateId=" + templateId +
        ", goodsVideo=" + goodsVideo +
        "}";
    }
}
