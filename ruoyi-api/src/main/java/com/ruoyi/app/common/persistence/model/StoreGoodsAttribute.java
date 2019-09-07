package com.ruoyi.app.common.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 商品的属性表(独立)
 * </p>
 *
 * @author hupeng
 * @since 2019-09-07
 */
@TableName("store_goods_attribute")
public class StoreGoodsAttribute extends Model<StoreGoodsAttribute> {

    private static final long serialVersionUID = 1L;

    /**
     * 属性id
     */
    @TableId(value = "attr_id", type = IdType.AUTO)
    private Integer attrId;
    /**
     * 属性名称
     */
    @TableField("attr_name")
    private String attrName;
    /**
     * 属性分类id(商品模型id)
     */
    @TableField("type_id")
    private Integer typeId;
    /**
     * 0不需要检索 1关键字检索 2范围检索
     */
    @TableField("attr_index")
    private Integer attrIndex;
    /**
     * 0唯一属性 1单选属性 2复选属性
     */
    @TableField("attr_type")
    private Integer attrType;
    /**
     *  0 手工录入 1从列表中选择 2多行文本框
     */
    @TableField("attr_input_type")
    private Integer attrInputType;
    /**
     * 可选值列表
     */
    @TableField("attr_values")
    private String attrValues;
    /**
     * 属性排序
     */
    private Integer sort;


    public Integer getAttrId() {
        return attrId;
    }

    public void setAttrId(Integer attrId) {
        this.attrId = attrId;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getAttrIndex() {
        return attrIndex;
    }

    public void setAttrIndex(Integer attrIndex) {
        this.attrIndex = attrIndex;
    }

    public Integer getAttrType() {
        return attrType;
    }

    public void setAttrType(Integer attrType) {
        this.attrType = attrType;
    }

    public Integer getAttrInputType() {
        return attrInputType;
    }

    public void setAttrInputType(Integer attrInputType) {
        this.attrInputType = attrInputType;
    }

    public String getAttrValues() {
        return attrValues;
    }

    public void setAttrValues(String attrValues) {
        this.attrValues = attrValues;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    protected Serializable pkVal() {
        return this.attrId;
    }

    @Override
    public String toString() {
        return "StoreGoodsAttribute{" +
        ", attrId=" + attrId +
        ", attrName=" + attrName +
        ", typeId=" + typeId +
        ", attrIndex=" + attrIndex +
        ", attrType=" + attrType +
        ", attrInputType=" + attrInputType +
        ", attrValues=" + attrValues +
        ", sort=" + sort +
        "}";
    }
}
