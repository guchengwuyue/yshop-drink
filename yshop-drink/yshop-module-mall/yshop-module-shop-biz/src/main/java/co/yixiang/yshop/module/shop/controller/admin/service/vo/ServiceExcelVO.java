package co.yixiang.yshop.module.shop.controller.admin.service.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 我的服务 Excel VO
 *
 * @author yshop
 */
@Data
public class ServiceExcelVO {

    @ExcelProperty("id")
    private Long id;

    @ExcelProperty("标题")
    private String name;

    @ExcelProperty("图标")
    private String image;

    @ExcelProperty("类型:pages=页面,miniprogram=跳转小程序,menu=菜单,content=内容,call=电话")
    private String type;

    @ExcelProperty("详情")
    private String content;

    @ExcelProperty("父级id")
    private Integer pid;

    @ExcelProperty("小程序app_id")
    private String appId;

    @ExcelProperty("页面路径")
    private String pages;

    @ExcelProperty("电话")
    private String phone;

    @ExcelProperty("权重")
    private Integer weigh;

    @ExcelProperty("状态:0=下架,1=上架")
    private Integer status;

    @ExcelProperty("添加时间")
    private LocalDateTime createTime;

}
