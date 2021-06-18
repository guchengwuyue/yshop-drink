package co.yixiang.shop.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import co.yixiang.common.annotation.Excel;
import co.yixiang.common.core.domain.BaseEntity;

/**
 * 商品主对象 store_goods
 * 
 * @author hupeng
 * @date 2019-08-25
 */
public class StoreGoods extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商品id */
    private Integer goodsId;

    /** 分类id */
    @Excel(name = "分类id")
    private Long cateId;

    private String brandName;

    private String cateName;

    //private

    /** 扩展分类id */
    private Long extendCatId;

    /** 商品编号 */
    @Excel(name = "商品编号")
    private String goodsSn;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String goodsName;

    /** 点击数 */
    private Integer clickCount;

    /** 品牌id */
    @Excel(name = "品牌id")
    private Integer brandId;

    /** 库存数量 */
    @Excel(name = "库存数量")
    private Integer storeCount;

    /** 商品评论数 */
    private Integer commentCount;

    /** 商品重量克为单位 */
    private Long weight;

    /** 商品体积。单位立方米 */
    private Double volume;

    /** 市场价(零售价) */
    @Excel(name = "市场价(零售价)")
    private Double marketPrice;

    /** 本店价(供货价) */
    @Excel(name = "本店价(供货价)")
    private Double shopPrice;

    /** 商品成本价 */
    private Double costPrice;

    /** 价格阶梯 */
    private String priceLadder;

    /** 商品关键词 */
    private String keywords;

    /** 商品简单描述 */
    private String goodsRemark;

    /** 商品详细描述 */
    private String goodsContent;

    /** 手机端商品详情 */
    private String mobileContent;

    /** 商品上传原始图(主图) */
    private String goodsLogo;

    /** 是否为虚拟商品 1是，0否 */
    private Integer isVirtual;

    /** 虚拟商品有效期 */
    private Long virtualIndate;

    /** 虚拟商品购买上限 */
    private Integer virtualLimit;

    /** 是否允许过期退款， 1是，0否 */
    private Integer virtualRefund;

    /** 是否上架 0-未上架 1-销售中 */
    @Excel(name = "是否上架 0-未上架 1-销售中")
    private Integer isOnSale;

    /** 是否包邮0否1是 */
    private Integer isFreeShipping;

    /** 商品上架时间 */
    private Integer onTime;

    /** 商品排序 */
    @Excel(name = "商品排序")
    private Integer sort;

    /** 是否推荐0-未推荐 1-已推荐 */
    @Excel(name = "是否推荐0-未推荐 1-已推荐")
    private Integer isRecommend;

    /** 是否新品0-不是 1-是 */
    @Excel(name = "是否新品0-不是 1-是")
    private Integer isNew;

    /** 是否热卖 */
    private Integer isHot;

    /** 最后更新时间 */
    private Integer lastUpdate;

    /** 商品所属类型id，取值表goods_type的id */
    private Integer goodsType;

    /** 商品规格类型，取值表goods_type的id */
    private Integer specType;

    /** 购买商品赠送积分 */
    private Integer giveIntegral;

    /** 积分兑换：0不参与积分兑换，积分和现金的兑换比例见后台配置 */
    private Integer exchangeIntegral;

    /** 供货商ID */
    private Integer suppliersId;

    /** 商品销量 */
    private Long salesSum;

    /** 0默认1抢购2团购3优惠促销4预售5虚拟(5其实没用)6拼团 */
    private Integer promType;

    /** 优惠活动id */
    private Long promId;

    /** 佣金用于分销分成 */
    private Double commission;

    /** SPU */
    private String spu;

    /** SKU */
    private String sku;

    /** 运费模板ID */
    private Long templateId;

    /** 视频 */
    private String goodsVideo;

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setGoodsId(Integer goodsId)
    {
        this.goodsId = goodsId;
    }

    public Integer getGoodsId() 
    {
        return goodsId;
    }
    public void setCateId(Long cateId) 
    {
        this.cateId = cateId;
    }

    public Long getCateId() 
    {
        return cateId;
    }
    public void setExtendCatId(Long extendCatId) 
    {
        this.extendCatId = extendCatId;
    }

    public Long getExtendCatId() 
    {
        return extendCatId;
    }
    public void setGoodsSn(String goodsSn) 
    {
        this.goodsSn = goodsSn;
    }

    public String getGoodsSn() 
    {
        return goodsSn;
    }
    public void setGoodsName(String goodsName) 
    {
        this.goodsName = goodsName;
    }

    public String getGoodsName() 
    {
        return goodsName;
    }
    public void setClickCount(Integer clickCount) 
    {
        this.clickCount = clickCount;
    }

    public Integer getClickCount() 
    {
        return clickCount;
    }
    public void setBrandId(Integer brandId) 
    {
        this.brandId = brandId;
    }

    public Integer getBrandId() 
    {
        return brandId;
    }
    public void setStoreCount(Integer storeCount) 
    {
        this.storeCount = storeCount;
    }

    public Integer getStoreCount() 
    {
        return storeCount;
    }
    public void setCommentCount(Integer commentCount) 
    {
        this.commentCount = commentCount;
    }

    public Integer getCommentCount() 
    {
        return commentCount;
    }
    public void setWeight(Long weight) 
    {
        this.weight = weight;
    }

    public Long getWeight() 
    {
        return weight;
    }
    public void setVolume(Double volume) 
    {
        this.volume = volume;
    }

    public Double getVolume() 
    {
        return volume;
    }
    public void setMarketPrice(Double marketPrice) 
    {
        this.marketPrice = marketPrice;
    }

    public Double getMarketPrice() 
    {
        return marketPrice;
    }
    public void setShopPrice(Double shopPrice) 
    {
        this.shopPrice = shopPrice;
    }

    public Double getShopPrice() 
    {
        return shopPrice;
    }
    public void setCostPrice(Double costPrice) 
    {
        this.costPrice = costPrice;
    }

    public Double getCostPrice() 
    {
        return costPrice;
    }
    public void setPriceLadder(String priceLadder) 
    {
        this.priceLadder = priceLadder;
    }

    public String getPriceLadder() 
    {
        return priceLadder;
    }
    public void setKeywords(String keywords) 
    {
        this.keywords = keywords;
    }

    public String getKeywords() 
    {
        return keywords;
    }
    public void setGoodsRemark(String goodsRemark) 
    {
        this.goodsRemark = goodsRemark;
    }

    public String getGoodsRemark() 
    {
        return goodsRemark;
    }
    public void setGoodsContent(String goodsContent) 
    {
        this.goodsContent = goodsContent;
    }

    public String getGoodsContent() 
    {
        return goodsContent;
    }
    public void setMobileContent(String mobileContent) 
    {
        this.mobileContent = mobileContent;
    }

    public String getMobileContent() 
    {
        return mobileContent;
    }
    public void setGoodsLogo(String goodsLogo) 
    {
        this.goodsLogo = goodsLogo;
    }

    public String getGoodsLogo() 
    {
        return goodsLogo;
    }
    public void setIsVirtual(Integer isVirtual) 
    {
        this.isVirtual = isVirtual;
    }

    public Integer getIsVirtual() 
    {
        return isVirtual;
    }
    public void setVirtualIndate(Long virtualIndate) 
    {
        this.virtualIndate = virtualIndate;
    }

    public Long getVirtualIndate() 
    {
        return virtualIndate;
    }
    public void setVirtualLimit(Integer virtualLimit) 
    {
        this.virtualLimit = virtualLimit;
    }

    public Integer getVirtualLimit() 
    {
        return virtualLimit;
    }
    public void setVirtualRefund(Integer virtualRefund) 
    {
        this.virtualRefund = virtualRefund;
    }

    public Integer getVirtualRefund() 
    {
        return virtualRefund;
    }
    public void setIsOnSale(Integer isOnSale) 
    {
        this.isOnSale = isOnSale;
    }

    public Integer getIsOnSale() 
    {
        return isOnSale;
    }
    public void setIsFreeShipping(Integer isFreeShipping) 
    {
        this.isFreeShipping = isFreeShipping;
    }

    public Integer getIsFreeShipping() 
    {
        return isFreeShipping;
    }
    public void setOnTime(Integer onTime) 
    {
        this.onTime = onTime;
    }

    public Integer getOnTime() 
    {
        return onTime;
    }
    public void setSort(Integer sort) 
    {
        this.sort = sort;
    }

    public Integer getSort() 
    {
        return sort;
    }
    public void setIsRecommend(Integer isRecommend) 
    {
        this.isRecommend = isRecommend;
    }

    public Integer getIsRecommend() 
    {
        return isRecommend;
    }
    public void setIsNew(Integer isNew) 
    {
        this.isNew = isNew;
    }

    public Integer getIsNew() 
    {
        return isNew;
    }
    public void setIsHot(Integer isHot) 
    {
        this.isHot = isHot;
    }

    public Integer getIsHot() 
    {
        return isHot;
    }
    public void setLastUpdate(Integer lastUpdate) 
    {
        this.lastUpdate = lastUpdate;
    }

    public Integer getLastUpdate() 
    {
        return lastUpdate;
    }
    public void setGoodsType(Integer goodsType) 
    {
        this.goodsType = goodsType;
    }

    public Integer getGoodsType() 
    {
        return goodsType;
    }
    public void setSpecType(Integer specType) 
    {
        this.specType = specType;
    }

    public Integer getSpecType() 
    {
        return specType;
    }
    public void setGiveIntegral(Integer giveIntegral) 
    {
        this.giveIntegral = giveIntegral;
    }

    public Integer getGiveIntegral() 
    {
        return giveIntegral;
    }
    public void setExchangeIntegral(Integer exchangeIntegral) 
    {
        this.exchangeIntegral = exchangeIntegral;
    }

    public Integer getExchangeIntegral() 
    {
        return exchangeIntegral;
    }
    public void setSuppliersId(Integer suppliersId) 
    {
        this.suppliersId = suppliersId;
    }

    public Integer getSuppliersId() 
    {
        return suppliersId;
    }
    public void setSalesSum(Long salesSum) 
    {
        this.salesSum = salesSum;
    }

    public Long getSalesSum() 
    {
        return salesSum;
    }
    public void setPromType(Integer promType) 
    {
        this.promType = promType;
    }

    public Integer getPromType() 
    {
        return promType;
    }
    public void setPromId(Long promId) 
    {
        this.promId = promId;
    }

    public Long getPromId() 
    {
        return promId;
    }
    public void setCommission(Double commission) 
    {
        this.commission = commission;
    }

    public Double getCommission() 
    {
        return commission;
    }
    public void setSpu(String spu) 
    {
        this.spu = spu;
    }

    public String getSpu() 
    {
        return spu;
    }
    public void setSku(String sku) 
    {
        this.sku = sku;
    }

    public String getSku() 
    {
        return sku;
    }
    public void setTemplateId(Long templateId) 
    {
        this.templateId = templateId;
    }

    public Long getTemplateId() 
    {
        return templateId;
    }
    public void setGoodsVideo(String goodsVideo) 
    {
        this.goodsVideo = goodsVideo;
    }

    public String getGoodsVideo() 
    {
        return goodsVideo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("goodsId", getGoodsId())
            .append("cateId", getCateId())
            .append("extendCatId", getExtendCatId())
            .append("goodsSn", getGoodsSn())
            .append("goodsName", getGoodsName())
            .append("clickCount", getClickCount())
            .append("brandId", getBrandId())
            .append("storeCount", getStoreCount())
            .append("commentCount", getCommentCount())
            .append("weight", getWeight())
            .append("volume", getVolume())
            .append("marketPrice", getMarketPrice())
            .append("shopPrice", getShopPrice())
            .append("costPrice", getCostPrice())
            .append("priceLadder", getPriceLadder())
            .append("keywords", getKeywords())
            .append("goodsRemark", getGoodsRemark())
            .append("goodsContent", getGoodsContent())
            .append("mobileContent", getMobileContent())
            .append("goodsLogo", getGoodsLogo())
            .append("isVirtual", getIsVirtual())
            .append("virtualIndate", getVirtualIndate())
            .append("virtualLimit", getVirtualLimit())
            .append("virtualRefund", getVirtualRefund())
            .append("isOnSale", getIsOnSale())
            .append("isFreeShipping", getIsFreeShipping())
            .append("onTime", getOnTime())
            .append("sort", getSort())
            .append("isRecommend", getIsRecommend())
            .append("isNew", getIsNew())
            .append("isHot", getIsHot())
            .append("lastUpdate", getLastUpdate())
            .append("goodsType", getGoodsType())
            .append("specType", getSpecType())
            .append("giveIntegral", getGiveIntegral())
            .append("exchangeIntegral", getExchangeIntegral())
            .append("suppliersId", getSuppliersId())
            .append("salesSum", getSalesSum())
            .append("promType", getPromType())
            .append("promId", getPromId())
            .append("commission", getCommission())
            .append("spu", getSpu())
            .append("sku", getSku())
            .append("templateId", getTemplateId())
            .append("goodsVideo", getGoodsVideo())
            .toString();
    }
}
