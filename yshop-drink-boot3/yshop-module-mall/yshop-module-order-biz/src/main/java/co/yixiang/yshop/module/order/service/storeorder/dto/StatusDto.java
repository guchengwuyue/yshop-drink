package co.yixiang.yshop.module.order.service.storeorder.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName StatusDto
 * @Author hupeng <610796224@qq.com>
 * @Date 2024/1/19
 **/
@Data
public class StatusDto implements Serializable {
    private String yClass;
    private String msg;
    private String payType;
    private String title;
    private String type;
}
