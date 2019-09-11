package com.ruoyi.app.common.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 首页轮播广告
 * </p>
 *
 * @author hupeng
 * @since 2019-09-05
 */
@TableName("store_ads")
public class StoreAds extends Model<StoreAds> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 视频url
     */
    @TableField("video_url")
    private String videoUrl;

    /**
     * 轮播图片地址
     */
    private String url;



    /**
     * 类型1-视频 2-图片
     */
    private Integer type;



    /**
     * 序号
     */
    private Integer sort;


    /**
     * 创建时间
     */
    @TableField("create_time")
    private Long createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "StoreAds{" +
        ", id=" + id +
        ", videoUrl=" + videoUrl +
        ", url=" + url +
        ", type=" + type +
        ", sort=" + sort +
        ", createTime=" + createTime +
        "}";
    }
}
