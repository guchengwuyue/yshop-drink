package co.yixiang.yshop.module.express.kdniao.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * <p>
 * 快递鸟-物流-查询base参数
 * </p>
 *
 * @author hupeng
 * @date 2023/7/21
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
//快递鸟-物流-查询base参数
public class KdniaoApiBaseDTO {

    //用户ID
    private String eBusinessID;

    //API key
    private String apiKey;

   //请求url
    //https://api.kdniao.com/Ebusiness/EbusinessOrderHandle.aspx
    private String reqURL;

    private Boolean isFree;

}
