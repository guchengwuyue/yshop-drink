package co.yixiang.yshop.module.express.kdniao.util;

import co.yixiang.yshop.framework.common.exception.ErrorCode;
import co.yixiang.yshop.framework.common.exception.util.ServiceExceptionUtil;
import co.yixiang.yshop.module.express.kdniao.model.dto.KdniaoApiDTO;
import co.yixiang.yshop.module.express.kdniao.model.dto.KdniaoElectronicsOrderDTO;
import co.yixiang.yshop.module.express.kdniao.model.dto.KdniaoElectronicsOrderGoodsDTO;
import co.yixiang.yshop.module.express.kdniao.model.vo.KdniaoApiVO;
import co.yixiang.yshop.module.express.kdniao.model.vo.KdniaoOrderVO;
import com.alibaba.fastjson.JSON;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p> 快递鸟工具类 </p>
 *
 * @author hupeng
 * @date 2023/7/219
 */
@Slf4j
public class KdniaoUtil {
    //查询物流当url
    private final String KDNIAO_LOGISTIC_QUERY = "https://api.kdniao.com/Ebusiness/EbusinessOrderHandle.aspx";
    //电子面单url
    private final String KDNIAO_ELECT_QUERY = "https://api.kdniao.com/api/EOrderService";

    /**
     * 快递查询接口
     *
     * @param queryDTO 请求参数
     * @return 物流信息
     */
    public static KdniaoApiVO getLogisticInfo(KdniaoApiDTO queryDTO){
        KdniaoApiVO kdniaoApiVO = new KdniaoUtil().getLogisticBase(queryDTO);
        if (kdniaoApiVO.getSuccess() == "false"){
           throw ServiceExceptionUtil.exception(new ErrorCode(999999,kdniaoApiVO.getReason()));
        }

        kdniaoApiVO.handleData();
        return kdniaoApiVO;
    }

    /**
     * 获取电子面单信息
     * @param queryDTO
     * @param kdniaoElectronicsOrderGoodsDTOList
     * @return
     */
    public static KdniaoOrderVO getOrderInfo(KdniaoElectronicsOrderDTO queryDTO,
                                             List<KdniaoElectronicsOrderGoodsDTO> kdniaoElectronicsOrderGoodsDTOList) {
        KdniaoOrderVO kdniaoOrderVO = new KdniaoUtil().getEleCtBase(queryDTO,kdniaoElectronicsOrderGoodsDTOList);
        //todo 由于目前快递鸟订单打印需要申请当地营业网店账号 所有目前这个没法测试 如果有其他用户有可以测试反馈给我们官方
        if (kdniaoOrderVO.getSuccess() == "false"){
            log.error(kdniaoOrderVO.getReason());
            throw ServiceExceptionUtil.exception(new ErrorCode(999999,kdniaoOrderVO.getReason()));
        }

        return kdniaoOrderVO;
    }

    /**
     * 快递查询接口
     *
     * @param queryDTO 请求参数
     * @return 物流信息
     */
    @SneakyThrows(Exception.class)
    private KdniaoApiVO getLogisticBase(KdniaoApiDTO queryDTO) {
        String EBusinessID = queryDTO.getEBusinessID();
        String ApiKey = queryDTO.getApiKey();
        String shipperCode = queryDTO.getShipperCode();
        String logisticCode = queryDTO.getLogisticCode();

        // 组装应用级参数
        Map<String, String> requestParamMap = new HashMap<>();
        requestParamMap.put("shipperCode", shipperCode);
        requestParamMap.put("LogisticCode", logisticCode);
        String RequestData = JSON.toJSONString(requestParamMap);
        // 组装系统级参数
        Map<String, String> params =  new HashMap<>();
        params.put("RequestData", this.urlEncoder(RequestData, "UTF-8"));
        params.put("EBusinessID", EBusinessID);
        if(queryDTO.getIsFree()) {
            params.put("RequestType", "1002");//免费1002 收费8001
        }else{
            params.put("RequestType", "8001");
        }
        String dataSign = this.encrypt(RequestData, ApiKey, "UTF-8");
        params.put("DataSign", this.urlEncoder(dataSign, "UTF-8"));
        params.put("DataType", "2");
        // 以form表单形式提交post请求，post请求体中包含了应用级参数和系统级参数
        String resultJson = this.sendPost(KDNIAO_LOGISTIC_QUERY, params);
        return JSON.parseObject(resultJson, KdniaoApiVO.class);
    }

    /**
     * 快递查询接口
     *
     * @param queryDTO 请求参数
     * @return 物流信息
     */
    @SneakyThrows(Exception.class)
    private KdniaoOrderVO getEleCtBase(KdniaoElectronicsOrderDTO queryDTO,
                                       List<KdniaoElectronicsOrderGoodsDTO> kdniaoElectronicsOrderGoodsDTOList) {
        String EBusinessID = queryDTO.getEBusinessID();
        String ApiKey = queryDTO.getApiKey();


        Map<String, Object> requestParamMap = this.doMap(queryDTO,kdniaoElectronicsOrderGoodsDTOList);
        System.out.println("map:"+requestParamMap);
        String RequestData = JSON.toJSONString(requestParamMap);
        // 组装系统级参数
        Map<String, String> params = new HashMap<>();
        params.put("RequestData", this.urlEncoder(RequestData, "UTF-8"));
        params.put("EBusinessID", EBusinessID);
        params.put("RequestType", "1007");
        String dataSign = this.encrypt(RequestData, ApiKey, "UTF-8");
        params.put("DataSign", this.urlEncoder(dataSign, "UTF-8"));
        params.put("DataType", "2");

        String resultJson = this.sendPost(KDNIAO_ELECT_QUERY, params);
        return JSON.parseObject(resultJson, KdniaoOrderVO.class);
    }

    //组合数据
    private Map<String, Object> doMap(KdniaoElectronicsOrderDTO queryDTO,
                                      List<KdniaoElectronicsOrderGoodsDTO> kdniaoElectronicsOrderGoodsDTOList){

        // 组装应用级参数
        Map<String, Object> requestParamMap = new HashMap<>();
        requestParamMap.put("MemberID", queryDTO.getMemberID());
        requestParamMap.put("OrderCode", queryDTO.getOrderCode());
        requestParamMap.put("ShipperCode", queryDTO.getShipperCode());
        requestParamMap.put("LogisticCode", queryDTO.getLogisticCode());
        requestParamMap.put("CustomerName", queryDTO.getCustomerName());
        requestParamMap.put("CustomerPwd", queryDTO.getCustomerPwd());
        requestParamMap.put("SendSite", queryDTO.getSendSite());

        requestParamMap.put("PayType", queryDTO.getPaytype());
        requestParamMap.put("MonthCode", queryDTO.getMonthCode());
        requestParamMap.put("IsReturnSignBill", queryDTO.getIsReturnSignBill());
        requestParamMap.put("OperateRequire", queryDTO.getOperateRequire());
        requestParamMap.put("ExpType", queryDTO.getExpType());
        requestParamMap.put("Cost", queryDTO.getCost());

        requestParamMap.put("OtherCost", queryDTO.getOtherCost());

        Map<String, Object> senderMap = new HashMap<>();
        senderMap.put("Name", queryDTO.getName());
        senderMap.put("Tel", queryDTO.getTel());
        senderMap.put("Company", queryDTO.getCompany());
        senderMap.put("Mobile", queryDTO.getMobile());
        senderMap.put("PostCode", queryDTO.getPostCode());
        senderMap.put("ProvinceName", queryDTO.getProvinceName());
        senderMap.put("CityName", queryDTO.getCityName());
        senderMap.put("ExpAreaName", queryDTO.getExpAreaName());
        senderMap.put("Address", queryDTO.getAddress());
        requestParamMap.put("Sender", senderMap);

        Map<String, Object> receiverMap = new HashMap<>();
        receiverMap.put("Name", queryDTO.getReceiverName());
        receiverMap.put("Tel", queryDTO.getReceiverTel());
        receiverMap.put("Company", queryDTO.getReceiverCompany());
        receiverMap.put("Mobile", queryDTO.getReceiverMobile());
        receiverMap.put("PostCode", queryDTO.getReceiverPostCode());
        receiverMap.put("ProvinceName", queryDTO.getReceiverProvinceName());
        receiverMap.put("CityName", queryDTO.getReceiverCityName());
        receiverMap.put("ExpAreaName", queryDTO.getReceiverExpAreaName());
        receiverMap.put("Address", queryDTO.getReceiverAddress());
        requestParamMap.put("Receiver", receiverMap);

        List<Map<String, Object>> commodityMapList = new ArrayList<>();
        for (KdniaoElectronicsOrderGoodsDTO kdniaoElectronicsOrderGoodsDTO : kdniaoElectronicsOrderGoodsDTOList) {
            Map<String, Object> commodityMap = new HashMap<>();
            commodityMap.put("GoodsName", kdniaoElectronicsOrderGoodsDTO.getGoodsName());
            commodityMap.put("GoodsCode", kdniaoElectronicsOrderGoodsDTO.getGoodsCode());
            commodityMap.put("Goodsquantity", kdniaoElectronicsOrderGoodsDTO.getGoodsQuantity());
            commodityMap.put("GoodsPrice", kdniaoElectronicsOrderGoodsDTO.getGoodsPrice());
            commodityMap.put("GoodsWeight", kdniaoElectronicsOrderGoodsDTO.getGoodsWeight());
            commodityMap.put("GoodsVol", kdniaoElectronicsOrderGoodsDTO.getGoodsVol());
            commodityMap.put("GoodsDesc", kdniaoElectronicsOrderGoodsDTO.getGoodsDesc());

            commodityMapList.add(commodityMap);
        }

        requestParamMap.put("Commodity", commodityMapList);

        requestParamMap.put("IsNotice", queryDTO.getIsNotice());
        requestParamMap.put("StartDate", queryDTO.getStartDate());
        requestParamMap.put("EndDate", queryDTO.getEndDate());
        requestParamMap.put("Weight", queryDTO.getWeight());
        requestParamMap.put("Quantity", queryDTO.getQuantity());
        requestParamMap.put("Volume", queryDTO.getVolume());
        requestParamMap.put("IsReturnPrintTemplate", queryDTO.getIsReturnTemp());
        requestParamMap.put("Remark", queryDTO.getRemark());

        return requestParamMap;

    }


    /**
     * MD5加密
     * str 内容
     * charset 编码方式
     *
     * @throws Exception
     */
    @SuppressWarnings("unused")
    private String MD5(String str, String charset) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes(charset));
        byte[] result = md.digest();
        StringBuffer sb = new StringBuffer(32);
        for (int i = 0; i < result.length; i++) {
            int val = result[i] & 0xff;
            if (val <= 0xf) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(val));
        }
        return sb.toString().toLowerCase();
    }

    /**
     * base64编码
     * str 内容
     * charset 编码方式
     *
     * @throws UnsupportedEncodingException
     */
    private String base64(String str, String charset) throws UnsupportedEncodingException {
        String encoded = Base64.encode(str.getBytes(charset));
        return encoded;
    }

    @SuppressWarnings("unused")
    private String urlEncoder(String str, String charset) throws UnsupportedEncodingException {
        String result = URLEncoder.encode(str, charset);
        return result;
    }

    /**
     * 电商Sign签名生成
     * content 内容
     * keyValue ApiKey
     * charset 编码方式
     *
     * @return DataSign签名
     * @throws UnsupportedEncodingException ,Exception
     */
    @SuppressWarnings("unused")
    private String encrypt(String content, String keyValue, String charset) throws UnsupportedEncodingException, Exception {
        if (keyValue != null) {
            return base64(MD5(content + keyValue, charset), charset);
        }
        return base64(MD5(content, charset), charset);
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * url 发送请求的 URL
     * params 请求的参数集合
     *
     * @return 远程资源的响应结果
     */
    @SuppressWarnings("unused")
    private String sendPost(String url, Map<String, String> params) {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            URL realUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // POST方法
            conn.setRequestMethod("POST");
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.connect();
            // 获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            // 发送请求参数
            if (params != null) {
                StringBuilder param = new StringBuilder();
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    if (param.length() > 0) {
                        param.append("&");
                    }
                    param.append(entry.getKey());
                    param.append("=");
                    param.append(entry.getValue());
                }
                log.info("[快递鸟] 请求参数: [{}]", param);
                out.write(param.toString());
            }
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result.toString();
    }

}
