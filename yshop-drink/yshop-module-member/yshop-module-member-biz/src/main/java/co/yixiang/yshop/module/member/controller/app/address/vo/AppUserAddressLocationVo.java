package co.yixiang.yshop.module.member.controller.app.address.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 用户地址表 查询结果对象
 * </p>
 *
 * @author hupeng
 * @date 2023-6-28
 */
@Data
@Schema(description = "用户地址表查询参数")
public class AppUserAddressLocationVo implements Serializable {
    private static final long serialVersionUID = 1L;


    private double lat;


    private double lng;


    private double lat2;


    private double lng2;



}
