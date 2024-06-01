package co.yixiang.yshop.module.product.controller.admin.storeproductreply;

import co.yixiang.yshop.framework.common.pojo.CommonResult;
import co.yixiang.yshop.framework.common.pojo.PageResult;
import co.yixiang.yshop.module.product.controller.admin.storeproductreply.vo.StoreProductReplyPageReqVO;
import co.yixiang.yshop.module.product.controller.admin.storeproductreply.vo.StoreProductReplyRespVO;
import co.yixiang.yshop.module.product.controller.admin.storeproductreply.vo.StoreProductReplyUpdateReqVO;
import co.yixiang.yshop.module.product.controller.app.product.vo.AppStoreProductReplyQueryVo;
import co.yixiang.yshop.module.product.convert.storeproductreply.StoreProductReplyConvert;
import co.yixiang.yshop.module.product.dal.dataobject.storeproductreply.StoreProductReplyDO;
import co.yixiang.yshop.module.product.service.storeproductreply.StoreProductReplyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static co.yixiang.yshop.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 评论")
@RestController
@RequestMapping("/product/store-product-reply")
@Validated
public class StoreProductReplyController {

    @Resource
    private StoreProductReplyService storeProductReplyService;


    @PutMapping("/update")
    @Operation(summary = "更新评论")
    @PreAuthorize("@ss.hasPermission('product:store-product-reply:update')")
    public CommonResult<Boolean> updateStoreProductReply(@Valid @RequestBody StoreProductReplyUpdateReqVO updateReqVO) {
        storeProductReplyService.updateStoreProductReply(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除评论")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('product:store-product-reply:delete')")
    public CommonResult<Boolean> deleteStoreProductReply(@RequestParam("id") Long id) {
        storeProductReplyService.deleteStoreProductReply(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得评论")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('product:store-product-reply:query')")
    public CommonResult<StoreProductReplyRespVO> getStoreProductReply(@RequestParam("id") Long id) {
        StoreProductReplyDO storeProductReply = storeProductReplyService.getStoreProductReply(id);
        return success(StoreProductReplyConvert.INSTANCE.convert(storeProductReply));
    }


    @GetMapping("/page")
    @Operation(summary = "获得评论分页")
    @PreAuthorize("@ss.hasPermission('product:store-product-reply:query')")
    public CommonResult<PageResult<AppStoreProductReplyQueryVo>> getStoreProductReplyPage(@Valid StoreProductReplyPageReqVO pageVO) {
        PageResult<AppStoreProductReplyQueryVo> pageResult = storeProductReplyService.getStoreProductReplyPage(pageVO);
        return success(pageResult);
    }



}
