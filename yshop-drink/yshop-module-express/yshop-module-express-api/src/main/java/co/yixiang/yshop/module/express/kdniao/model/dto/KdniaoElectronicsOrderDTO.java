package co.yixiang.yshop.module.express.kdniao.model.dto;

import lombok.*;

/**
 * 电子面单 DTO
 *
 * @author yshop
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class KdniaoElectronicsOrderDTO extends KdniaoApiBaseDTO {

    //用户
    private Long memberID;

    //订单编号
    private String orderCode;

    //快递公司编码
    private String shipperCode;

    //快递单号
    private String logisticCode;

    //其他费用
    private Double otherCost;

    /**
     * 运费支付方式:1=现付,2=到付,3=月结,4=第三方付(仅SF支持)
     */
    private Integer paytype;
    /**
     * 线下网点客户号
     */
    private String customerName;
    /**
     * 线下网点密码
     */
    private String customerPwd;
    /**
     * 网点名称
     */
    private String sendSite;
    /**
     * 网点快递员
     */
    private String sendStaff;

    //快递运费
    private Double cost;
    /**
     * 月结编号
     */
    private String monthCode;
    /**
     * 是否通知揽件:0=通知揽件,1=不通知揽件
     */
    private Integer isNotice;
    /**
     * 是否返回电子面单模板:0=不返回,1=返回
     */
    private Integer isReturnTemp;
    /**
     * 是否需要短信提醒:0=否,1=是
     */
    private Integer isSendMessage;
    /**
     * 模板尺寸
     */
    private String templateSize;
    /**
     * 签回单操作要求(如：签名、盖章、身份证复印件等)
     */
    private String operateRequire;

    /**
     * 上门揽件开始时间
     */
    private Integer startDate;
    /**
     * 上门揽件结束时间
     */
    private Integer endDate;
    /**
     * 备注
     */
    private String remark;
    /**
     * 快递类型:1=标准快件
     */
    private Integer expType;
    /**
     * 是否要签回单:0=否,1=是
     */
    private Integer isReturnSignBill;
    /**
     * 发件人公司
     */
    private String company;
    /**
     * 发件人省
     */
    private String provinceName;
    /**
     * 发件人市
     */
    private String cityName;
    /**
     * 发件人区
     */
    private String expAreaName;
    /**
     * 发件人详细地址
     */
    private String address;
    /**
     * 发件人姓名
     */
    private String name;
    /**
     * 发件人电话
     */
    private String tel;
    /**
     * 发件人手机号码
     */
    private String mobile;
    /**
     * 发件地邮编
     */
    private String postCode;


    /**
     * 收件人公司
     */
    private String receiverCompany;
    /**
     * 收件人省
     */
    private String receiverProvinceName;
    /**
     * 收件人市
     */
    private String receiverCityName;
    /**
     * 收件人区
     */
    private String receiverExpAreaName;
    /**
     * 收件人详细地址
     */
    private String receiverAddress;
    /**
     * 收件人姓名
     */
    private String receiverName;
    /**
     * 收件人电话
     */
    private String receiverTel;
    /**
     * 收件人手机号码
     */
    private String receiverMobile;
    /**
     * 收件地邮编
     */
    private String receiverPostCode;

    /**
     * 重量
     */
    private Double weight;

    /**
     * 重量
     */
    private Integer quantity;

    /**
     * 体积
     *
     */
    private Double volume;




}
