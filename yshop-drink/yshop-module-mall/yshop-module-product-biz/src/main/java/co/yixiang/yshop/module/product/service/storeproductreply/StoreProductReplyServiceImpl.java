package co.yixiang.yshop.module.product.service.storeproductreply;

import cn.hutool.core.util.StrUtil;
import co.yixiang.yshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import co.yixiang.yshop.module.product.controller.app.product.vo.AppStoreProductReplyQueryVo;
import co.yixiang.yshop.module.product.dal.dataobject.storeproduct.StoreProductDO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import co.yixiang.yshop.module.product.controller.admin.storeproductreply.vo.*;
import co.yixiang.yshop.module.product.dal.dataobject.storeproductreply.StoreProductReplyDO;
import co.yixiang.yshop.framework.common.pojo.PageResult;

import co.yixiang.yshop.module.product.convert.storeproductreply.StoreProductReplyConvert;
import co.yixiang.yshop.module.product.dal.mysql.storeproductreply.StoreProductReplyMapper;

import static co.yixiang.yshop.framework.common.exception.util.ServiceExceptionUtil.exception;
import static co.yixiang.yshop.module.product.enums.ErrorCodeConstants.*;

/**
 * 评论 Service 实现类
 *
 * @author yshop
 */
@Service
@Validated
public class StoreProductReplyServiceImpl implements StoreProductReplyService {

    @Resource
    private StoreProductReplyMapper storeProductReplyMapper;


    @Override
    public void updateStoreProductReply(StoreProductReplyUpdateReqVO updateReqVO) {
        // 校验存在
        validateStoreProductReplyExists(updateReqVO.getId());
        // 更新
        StoreProductReplyDO updateObj = StoreProductReplyConvert.INSTANCE.convert(updateReqVO);
        storeProductReplyMapper.updateById(updateObj);
    }

    @Override
    public void deleteStoreProductReply(Long id) {
        // 校验存在
        validateStoreProductReplyExists(id);
        // 删除
        storeProductReplyMapper.deleteById(id);
    }

    private void validateStoreProductReplyExists(Long id) {
        if (storeProductReplyMapper.selectById(id) == null) {
            throw exception(STORE_PRODUCT_REPLY_NOT_EXISTS);
        }
    }

    @Override
    public StoreProductReplyDO getStoreProductReply(Long id) {
        return storeProductReplyMapper.selectById(id);
    }

    @Override
    public PageResult<AppStoreProductReplyQueryVo> getStoreProductReplyPage(StoreProductReplyPageReqVO pageReqVO) {
                Page<StoreProductReplyDO> pageModel = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        List<AppStoreProductReplyQueryVo> list = storeProductReplyMapper
                .allReplyList(pageModel,pageReqVO.getNickname());
        return new PageResult<>(list, storeProductReplyMapper.allReplyListCount(pageReqVO.getNickname()));
    }


}
