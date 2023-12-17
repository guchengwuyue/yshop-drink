package co.yixiang.yshop.module.product.controller.app.category;

import co.yixiang.yshop.framework.common.pojo.CommonResult;
import co.yixiang.yshop.module.product.controller.app.category.vo.AppCategoryRespVO;
import co.yixiang.yshop.module.product.convert.category.ProductCategoryConvert;
import co.yixiang.yshop.module.product.dal.dataobject.category.ProductCategoryDO;
import co.yixiang.yshop.module.product.service.category.ProductCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;

import static co.yixiang.yshop.framework.common.pojo.CommonResult.success;

@Tag(name = "用户 APP - 商品分类")
@RestController
@RequestMapping("/product/category")
@Validated
public class AppCategoryController {

    @Resource
    private ProductCategoryService categoryService;

//    @GetMapping("/list")
//    @Operation(summary = "获得商品分类列表")
//    public CommonResult<List<AppCategoryRespVO>> getProductCategoryList() {
//        List<ProductCategoryDO> list = categoryService.getEnableCategoryList();
//        list.sort(Comparator.comparing(ProductCategoryDO::getSort));
//        return success(ProductCategoryConvert.INSTANCE.convertList03(list));
//    }

}
