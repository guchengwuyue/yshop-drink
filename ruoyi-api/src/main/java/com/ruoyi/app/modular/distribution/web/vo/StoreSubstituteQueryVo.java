package com.ruoyi.app.distribution.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

import java.util.Date;

/**
 * <p>
 * 代发货表 查询结果对象
 * </p>
 *
 * @author hupeng
 * @date 2019-09-30
 */
@Data
@ApiModel(value="StoreSubstituteQueryVo对象", description="代发货表查询参数")
public class StoreSubstituteQueryVo implements Serializable{
    private static final long serialVersionUID = 1L;

private Long id;

@ApiModelProperty(value = "用户id")
private Integer userId;

@ApiModelProperty(value = "收货人姓名")
private String username;

@ApiModelProperty(value = "收货手机号")
private String phone;

@ApiModelProperty(value = "城市信息")
private String cityInfo;

@ApiModelProperty(value = "收货详细地址")
private String address;

@ApiModelProperty(value = "产品明细")
private String productInfo;

@ApiModelProperty(value = "创建时间")
private Integer createTime;

private Date updatedAt;

}