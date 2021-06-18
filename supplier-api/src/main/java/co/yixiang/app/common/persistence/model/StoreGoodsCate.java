package co.yixiang.app.common.persistence.model;


import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 商城商品分类
 * </p>
 *
 * @author hupeng
 * @since 2019-09-08
 */
@TableName("store_goods_cate")
public class StoreGoodsCate extends Model<StoreGoodsCate> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 上级分类编号
     */
    private Long pid;
    /**
     * 品牌ID
     */
    @TableField("brand_id")
    private Long brandId;
    /**
     * 商品分类名称
     */
    @TableField("cate_title")
    private String cateTitle;
    /**
     * 商品分类
     */
    @TableField("cate_desc")
    private String cateDesc;
    /**
     * 缩略图url
     */
    @TableField("img_url")
    private String imgUrl;
    /**
     * 商品分类排序
     */
    private Long sort;
    /**
     * 商品状态(1有效,0无效)
     */
    private Long status;
    /**
     * 删除状态(1删除,0未删除)
     */
    @TableField("is_deleted")
    private Long isDeleted;
    /**
     * 创建时间
     */
    @TableField("create_at")
    private Date createAt;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getCateTitle() {
        return cateTitle;
    }

    public void setCateTitle(String cateTitle) {
        this.cateTitle = cateTitle;
    }

    public String getCateDesc() {
        return cateDesc;
    }

    public void setCateDesc(String cateDesc) {
        this.cateDesc = cateDesc;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Long isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "StoreGoodsCate{" +
        ", id=" + id +
        ", pid=" + pid +
        ", brandId=" + brandId +
        ", cateTitle=" + cateTitle +
        ", cateDesc=" + cateDesc +
        ", imgUrl=" + imgUrl +
        ", sort=" + sort +
        ", status=" + status +
        ", isDeleted=" + isDeleted +
        ", createAt=" + createAt +
        "}";
    }
}
