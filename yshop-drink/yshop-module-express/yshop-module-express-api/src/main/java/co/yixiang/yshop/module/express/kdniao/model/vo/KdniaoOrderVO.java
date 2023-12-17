package co.yixiang.yshop.module.express.kdniao.model.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * <p>
 * 快递鸟-面单-响应参数
 * </p>
 * @author hupeng
 * @date 2023/7/22
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class KdniaoOrderVO {
    //返回编码
    private String ResultCode;

    //失败原因
    private String Reason;

    //成功与否 true/false
    private String Success;

    //面单打印模板内容(html格式)
    private String PrintTemplate;

    //唯一标识
    private String UniquerRequestNumber;

    //子单数量
    private Integer SubCount;

    //子单单号
    private String SubOrders;

    //子单模板内容(html格式)
    private String SubPrintTemplates;

    //签回单模板内容
    private String SignBillPrintTemplate;

    //订单信息
    private OrderInfo Order;



    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderInfo {

        //订单编号
        private String OrderCode;

        //快递公司编码
        private String ShipperCode;

        //快递单号
        private String LogisticCode;

        //大头笔
        private String MarkDestination;

        //签回单单号
        private String SignWaybillCode;


        //始发地区域编码
        private String OriginCode;

        //始发地/始发网点
        private String OriginName;

        //目的地区域编码
        private String DestinatioCode;

        //目的地/到达网点
        private String DestinatioName;

        //分拣编码
        private String SortingCode;


        //集包编码
        private String PackageCode;

        //集包地
        private String PackageName;

        //目的地分拨
        private String DestinationAllocationCentre;

        //配送产品类型
        private Integer TransType;

        //运输方式(用于自行设计京东模板)：
        //0：陆运
        //1：航空
        private Integer TransportType;

        //自行设计模板用
        private String ShipperInfo;
    }


}
