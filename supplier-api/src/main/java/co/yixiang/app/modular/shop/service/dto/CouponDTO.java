package co.yixiang.app.modular.shop.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName CouponDTO
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/9/14
 **/

@Data
public class CouponDTO implements Serializable {
    private int id;
    private String name;
    private Double money;
    private Double condition;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date useEndTime;
}
