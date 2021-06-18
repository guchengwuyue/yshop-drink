package co.yixiang.app.common.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 商品规格表(独立)
 * </p>
 *
 * @author hupeng
 * @since 2019-09-07
 */
@TableName("store_spec")
public class StoreSpec extends Model<StoreSpec> {

    private static final long serialVersionUID = 1L;

    /**
     * 规格表
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 规格类型
     */
    @TableField("type_id")
    private Integer typeId;
    /**
     * 规格名称
     */
    private String name;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 是否需要检索：1是，0否
     */
    @TableField("search_index")
    private Integer searchIndex;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getSearchIndex() {
        return searchIndex;
    }

    public void setSearchIndex(Integer searchIndex) {
        this.searchIndex = searchIndex;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "StoreSpec{" +
        ", id=" + id +
        ", typeId=" + typeId +
        ", name=" + name +
        ", sort=" + sort +
        ", searchIndex=" + searchIndex +
        "}";
    }
}
