package com.ruoyi.app.common.persistence.model;


import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 商品规格关联价格表
 * </p>
 *
 * @author hupeng
 * @since 2019-09-07
 */
@TableName("store_spec_goods_price")
public class StoreSpecGoodsPrice extends Model<StoreSpecGoodsPrice> {

    private static final long serialVersionUID = 1L;

    /**
     * 规格商品id
     */
    @TableId(value = "item_id", type = IdType.AUTO)
    private Long itemId;
    /**
     * 商品id
     */
    @TableField("goods_id")
    private Integer goodsId;
    /**
     * 规格键名
     */
    @TableField("`key`")
    private String key;
    /**
     * 规格键名中文
     */
    @TableField("key_name")
    private String keyName;
    /**
     * 价格(供货价格)
     */
    private BigDecimal price;
    /**
     * 零售价格
     */
    @TableField("market_price")
    private Double marketPrice;
    /**
     * 库存数量
     */
    @TableField("store_count")
    private Integer storeCount;
    /**
     * 商品条形码
     */
    @TableField("bar_code")
    private String barCode;
    /**
     * SKU
     */
    private String sku;
    /**
     * 规格商品主图
     */
    @TableField("spec_img")
    private String specImg;
    /**
     * 活动id
     */
    @TableField("prom_id")
    private Integer promId;
    /**
     * 参加活动类型
     */
    @TableField("prom_type")
    private Integer promType;


    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Integer getStoreCount() {
        return storeCount;
    }

    public void setStoreCount(Integer storeCount) {
        this.storeCount = storeCount;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getSpecImg() {
        return specImg;
    }

    public void setSpecImg(String specImg) {
        this.specImg = specImg;
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

    @Override
    protected Serializable pkVal() {
        return this.itemId;
    }

    @Override
    public String toString() {
        return "StoreSpecGoodsPrice{" +
        ", itemId=" + itemId +
        ", goodsId=" + goodsId +
        ", key=" + key +
        ", keyName=" + keyName +
        ", price=" + price +
        ", marketPrice=" + marketPrice +
        ", storeCount=" + storeCount +
        ", barCode=" + barCode +
        ", sku=" + sku +
        ", specImg=" + specImg +
        ", promId=" + promId +
        ", promType=" + promType +
        "}";
    }
}
