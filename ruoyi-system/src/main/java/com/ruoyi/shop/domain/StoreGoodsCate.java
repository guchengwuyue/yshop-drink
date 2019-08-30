package com.ruoyi.shop.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.TreeEntity;
import java.util.Date;

/**
 * 商城商品分类对象 store_goods_cate
 * 
 * @author hupeng
 * @date 2019-08-22
 */
public class StoreGoodsCate extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Long id;

    /** 上级分类编号 */
    @Excel(name = "上级分类编号")
    private Long pid;

    /** 品牌ID */
    private Long brandId;

    /** 商品分类名称 */
    @Excel(name = "商品分类名称")
    private String cateTitle;

    /** 商品描述 */
    @Excel(name = "商品描述")
    private String cateDesc;

    /** 缩略图url */
    private String imgUrl;

    /** 商品分类排序 */
    @Excel(name = "商品分类排序")
    private Long sort;

    /** 分类状态 */
    @Excel(name = "分类状态")
    private Integer status;

    /** 删除状态(1删除,0未删除) */
    private Integer isDeleted;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createAt;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setPid(Long pid) 
    {
        this.pid = pid;
    }

    public Long getPid() 
    {
        return pid;
    }
    public void setBrandId(Long brandId) 
    {
        this.brandId = brandId;
    }

    public Long getBrandId() 
    {
        return brandId;
    }
    public void setCateTitle(String cateTitle) 
    {
        this.cateTitle = cateTitle;
    }

    public String getCateTitle() 
    {
        return cateTitle;
    }
    public void setCateDesc(String cateDesc) 
    {
        this.cateDesc = cateDesc;
    }

    public String getCateDesc() 
    {
        return cateDesc;
    }
    public void setImgUrl(String imgUrl) 
    {
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() 
    {
        return imgUrl;
    }
    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setIsDeleted(Integer isDeleted) 
    {
        this.isDeleted = isDeleted;
    }

    public Integer getIsDeleted() 
    {
        return isDeleted;
    }
    public void setCreateAt(Date createAt) 
    {
        this.createAt = createAt;
    }

    public Date getCreateAt() 
    {
        return createAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("pid", getPid())
            .append("brandId", getBrandId())
            .append("cateTitle", getCateTitle())
            .append("cateDesc", getCateDesc())
            .append("imgUrl", getImgUrl())
            .append("sort", getSort())
            .append("status", getStatus())
            .append("isDeleted", getIsDeleted())
            .append("createAt", getCreateAt())
            .toString();
    }
}
