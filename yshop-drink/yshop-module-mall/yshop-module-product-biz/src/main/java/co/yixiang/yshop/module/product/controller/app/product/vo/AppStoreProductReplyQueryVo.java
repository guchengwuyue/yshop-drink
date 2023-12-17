package co.yixiang.yshop.module.product.controller.app.product.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 评论表 查询结果对象
 * </p>
 *
 * @author hupeng
 * @date 2023-6-12
 */
@Data
@Schema(description = "用户 APP - 评论表vo")
public class AppStoreProductReplyQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "评论ID", required = true)
    private Long id;

    @Schema(description = "产品id", required = true)
    private Long productId;

    @Schema(description = "某种商品类型(普通商品、秒杀商品）", required = true)
    private String replyType;

    @Schema(description = "商品分数", required = true)
    private Integer productScore;

    @Schema(description = "服务分数", required = true)
    private Integer serviceScore;

    @Schema(description = "评论内容", required = true)
    private String comment;

    @Schema(description = "评论图片", required = true)
    private String[] pics;

    @Schema(description = "评论图片,字符串", required = true)
    private String pictures;

    @Schema(description = "管理员回复内容", required = true)
    private String merchantReplyContent;

    @Schema(description = "管理员回复时间", required = true)
    private Date merchantReplyTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @Schema(description = "发布时间", required = true)
    private Date createTime;

    @Schema(description = "评价星星数", required = true)
    private String star;

    @Schema(description = "用户昵称", required = true)
    private String nickname;

    @Schema(description = "用户头像", required = true)
    private String avatar;

    @Schema(description = "商品sku", required = true)
    private String sku;

    @JsonIgnore
    private String cartInfo;


}
