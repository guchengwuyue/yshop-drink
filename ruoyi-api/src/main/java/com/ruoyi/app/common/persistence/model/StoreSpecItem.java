package com.ruoyi.app.common.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 规格项
 * </p>
 *
 * @author hupeng
 * @since 2019-09-07
 */
@TableName("store_spec_item")
public class StoreSpecItem extends Model<StoreSpecItem> {

    private static final long serialVersionUID = 1L;

    /**
     * 规格项id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 规格id
     */
    @TableField("spec_id")
    private Integer specId;
    /**
     * 规格项
     */
    private String item;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSpecId() {
        return specId;
    }

    public void setSpecId(Integer specId) {
        this.specId = specId;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "StoreSpecItem{" +
        ", id=" + id +
        ", specId=" + specId +
        ", item=" + item +
        "}";
    }
}
