package com.ruoyi.app.common.persistence.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 微信素材表
 * </p>
 *
 * @author hupeng
 * @since 2019-09-05
 */
@TableName("wechat_news_article")
public class WechatNewsArticle extends Model<WechatNewsArticle> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 素材标题
     */
    private String title;
    /**
     * 永久素材显示URL
     */
    @TableField("local_url")
    private String localUrl;
    /**
     * 是否显示封面 0不显示，1 显示
     */
    @TableField("show_cover_pic")
    private Integer showCoverPic;
    /**
     * 作者
     */
    private String author;
    /**
     * 摘要内容
     */
    private String digest;
    /**
     * 图文内容
     */
    private String content;
    /**
     * 图文消息原文地址
     */
    @TableField("content_source_url")
    private String contentSourceUrl;
    /**
     * 创建时间
     */
    @TableField("create_at")
    private Date createAt;
    /**
     * 创建人
     */
    @TableField("create_by")
    private Long createBy;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocalUrl() {
        return localUrl;
    }

    public void setLocalUrl(String localUrl) {
        this.localUrl = localUrl;
    }

    public Integer getShowCoverPic() {
        return showCoverPic;
    }

    public void setShowCoverPic(Integer showCoverPic) {
        this.showCoverPic = showCoverPic;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentSourceUrl() {
        return contentSourceUrl;
    }

    public void setContentSourceUrl(String contentSourceUrl) {
        this.contentSourceUrl = contentSourceUrl;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "WechatNewsArticle{" +
        ", id=" + id +
        ", title=" + title +
        ", localUrl=" + localUrl +
        ", showCoverPic=" + showCoverPic +
        ", author=" + author +
        ", digest=" + digest +
        ", content=" + content +
        ", contentSourceUrl=" + contentSourceUrl +
        ", createAt=" + createAt +
        ", createBy=" + createBy +
        "}";
    }
}
