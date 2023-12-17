package co.yixiang.yshop.module.express.controller.app.express;

import co.yixiang.yshop.framework.common.pojo.CommonResult;
import co.yixiang.yshop.module.express.dal.redis.express.ExpressRedisDAO;
import co.yixiang.yshop.module.express.kdniao.model.dto.KdniaoApiBaseDTO;
import co.yixiang.yshop.module.express.kdniao.model.dto.KdniaoApiDTO;
import co.yixiang.yshop.module.express.kdniao.model.vo.KdniaoApiVO;
import co.yixiang.yshop.module.express.kdniao.util.KdniaoUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static co.yixiang.yshop.framework.common.pojo.CommonResult.success;

@Tag(name = "app - 查询快递")
@RestController
@RequestMapping("/express")
@Validated
public class AppExpressController {

    @Resource
    private ExpressRedisDAO expressRedisDAO;



    @GetMapping("/getLogistic")
    @Parameters({
            @Parameter(name = "shipperCode", description = "快递公司编码", required = true),
            @Parameter(name = "logisticCode", description = "快递单号", required = true),
    })
    @Operation(summary = "查询物流")
    public CommonResult<KdniaoApiVO> getLogistic(@RequestParam(value = "shipperCode") String shipperCode,
                                                 @RequestParam("logisticCode") String logisticCode) {
        KdniaoApiBaseDTO kdniaoApiBaseDTO = expressRedisDAO.get();
        KdniaoApiDTO params = new KdniaoApiDTO();
        params.setLogisticCode(logisticCode);
        params.setShipperCode(shipperCode);
        params.setApiKey(kdniaoApiBaseDTO.getApiKey());
        params.setEBusinessID(kdniaoApiBaseDTO.getEBusinessID());
        params.setIsFree(kdniaoApiBaseDTO.getIsFree());
        //此处注意 这个地方分收费与免费当 目前用当免费免费当只支持圆通 申通 百世 如果是收费当 改里面RequestType参数 函数里有说明
        return success(KdniaoUtil.getLogisticInfo(params));
    }



}
