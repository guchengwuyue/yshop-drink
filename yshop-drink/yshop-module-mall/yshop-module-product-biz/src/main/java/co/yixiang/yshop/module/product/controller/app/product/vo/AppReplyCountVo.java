package co.yixiang.yshop.module.product.controller.app.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

/**
 * @ClassName ReplyCount
 * @Author hupeng <610796224@qq.com>
 * @Date 2023/7/9
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppReplyCountVo implements Serializable {

    @Schema(description = "总的评论数", required = true)
    private Long sumCount;

    @Schema(description = "好评数", required = true)
    private Long goodCount;

    @Schema(description = "中评数", required = true)
    private Long inCount;

    @Schema(description = "差评数", required = true)
    private Long poorCount;

    @Schema(description = "好评率", required = true)
    private String replyChance;

    @Schema(description = "好评星星数", required = true)
    private String replySstar;

}
