package com.ruoyi.shop.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商品的属性(独立)对象 store_goods_attribute
 * 
 * @author hupeng
 * @date 2019-08-24
 */
public class StoreGoodsAttribute extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 属性id */
    private Long attrId;

    /** 属性名称 */
    @Excel(name = "属性名称")
    private String attrName;

    /** 属性分类id(商品模型id) */
    @Excel(name = "属性分类id(商品模型id)")
    private Integer typeId;

    private String typeName;

    /** 0不需要检索 1关键字检索 2范围检索 */
    private Integer attrIndex;

    /** 0唯一属性 1单选属性 2复选属性 */
    private Integer attrType;

    /** 0 手工录入 1从列表中选择 2多行文本框 */
    private Integer attrInputType;

    /** 可选值列表 */
    private String attrValues;

    /** 属性排序 */
    private Integer sort;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setAttrId(Long attrId)
    {
        this.attrId = attrId;
    }

    public Long getAttrId() 
    {
        return attrId;
    }
    public void setAttrName(String attrName) 
    {
        this.attrName = attrName;
    }

    public String getAttrName() 
    {
        return attrName;
    }
    public void setTypeId(Integer typeId) 
    {
        this.typeId = typeId;
    }

    public Integer getTypeId() 
    {
        return typeId;
    }
    public void setAttrIndex(Integer attrIndex) 
    {
        this.attrIndex = attrIndex;
    }

    public Integer getAttrIndex() 
    {
        return attrIndex;
    }
    public void setAttrType(Integer attrType) 
    {
        this.attrType = attrType;
    }

    public Integer getAttrType() 
    {
        return attrType;
    }
    public void setAttrInputType(Integer attrInputType) 
    {
        this.attrInputType = attrInputType;
    }

    public Integer getAttrInputType() 
    {
        return attrInputType;
    }
    public void setAttrValues(String attrValues) 
    {
        this.attrValues = attrValues;
    }

    public String getAttrValues() 
    {
        return attrValues;
    }
    public void setSort(Integer sort) 
    {
        this.sort = sort;
    }

    public Integer getSort() 
    {
        return sort;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("attrId", getAttrId())
            .append("attrName", getAttrName())
            .append("typeId", getTypeId())
            .append("typeName", getTypeName())
            .append("attrIndex", getAttrIndex())
            .append("attrType", getAttrType())
            .append("attrInputType", getAttrInputType())
            .append("attrValues", getAttrValues())
            .append("sort", getSort())
            .toString();
    }
}
