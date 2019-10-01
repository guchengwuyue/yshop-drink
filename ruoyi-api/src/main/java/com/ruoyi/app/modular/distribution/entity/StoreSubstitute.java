package com.ruoyi.app.modular.distribution.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ruoyi.app.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * 代发货表
 * </p>
 *
 * @author hupeng
 * @since 2019-09-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="StoreSubstitute对象", description="代发货表")
public class StoreSubstitute extends BaseEntity {

    private static final long serialVersionUID = 1L;

@TableId(value = "id", type = IdType.AUTO)
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
