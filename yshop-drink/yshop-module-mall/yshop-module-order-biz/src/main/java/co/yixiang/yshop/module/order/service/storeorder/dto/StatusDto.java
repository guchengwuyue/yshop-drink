package co.yixiang.yshop.module.order.service.storeorder.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName StatusDto
 * @Author hupeng <610796224@qq.com>
 * @Date 2023/6/19
 **/
@Data
public class StatusDto implements Serializable {
    private String _class;
    private String _msg;
    private String _payType;
    private String _title;
    private String _type;
}
