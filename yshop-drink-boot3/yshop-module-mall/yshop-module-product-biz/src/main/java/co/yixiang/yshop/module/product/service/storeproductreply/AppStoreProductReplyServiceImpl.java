package co.yixiang.yshop.module.product.service.storeproductreply;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import co.yixiang.yshop.framework.common.constant.ShopConstants;
import co.yixiang.yshop.module.product.controller.app.cart.vo.AppStoreCartQueryVo;
import co.yixiang.yshop.module.product.controller.app.product.vo.AppReplyCountVo;
import co.yixiang.yshop.module.product.controller.app.product.vo.AppStoreProductReplyQueryVo;
import co.yixiang.yshop.module.product.dal.dataobject.storeproductreply.StoreProductReplyDO;
import co.yixiang.yshop.module.product.dal.mysql.storeproductreply.StoreProductReplyMapper;
import co.yixiang.yshop.module.product.enums.ProductConstants;
import co.yixiang.yshop.module.product.enums.product.ScoreEnum;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 评论 Service 实现类
 *
 * @author yshop
 */
@Service
@Validated
public class AppStoreProductReplyServiceImpl extends ServiceImpl<StoreProductReplyMapper, StoreProductReplyDO> implements AppStoreProductReplyService {

    @Resource
    private StoreProductReplyMapper storeProductReplyMapper;

    /**
     * 获取评价数量
     * @param productId
     * @return
     */
    @Override
    public Long productReplyCount(long productId) {

        return this.baseMapper.selectCount(Wrappers.<StoreProductReplyDO>lambdaQuery()
                .eq(StoreProductReplyDO::getProductId,productId));

    }

    /**
     * 获取单条评价
     * @param productId 商品di
     * @return YxStoreProductReplyQueryVo
     */
    @Override
    public AppStoreProductReplyQueryVo getReply(long productId) {
        AppStoreProductReplyQueryVo vo = this.baseMapper.getReply(productId);
        if(ObjectUtil.isNotNull(vo)){
            return handleReply(vo);
        }
        return null;
    }

    /**
     * 好评比例
     * @param productId 商品id
     * @return %
     */
    @Override
    public String replyPer(long productId) {
        LambdaQueryWrapper<StoreProductReplyDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StoreProductReplyDO::getProductId,productId)
                .eq(StoreProductReplyDO::getProductScore, ScoreEnum.DEFAULT_5.getValue());
        Long productScoreCount = this.baseMapper.selectCount(wrapper);
        Long count = productReplyCount(productId);
        if(count > 0){
            return ""+NumberUtil.round(NumberUtil.mul(NumberUtil.div(productScoreCount,count),100),2);
        }

        return ShopConstants.YSHOP_ZERO;
    }


    /**
     * 返回当前商品评价数量
     * @param unique
     * @return
     */
    @Override
    public Long replyCount(String unique) {
        LambdaQueryWrapper<StoreProductReplyDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StoreProductReplyDO::getUnique,unique);
        return this.baseMapper.selectCount(wrapper);
    }

    /**
     * 获取评价列表
     * @param productId 商品id
     * @param type 0-全部 1-好评 2-中评 3-差评
     * @param page page
     * @param limit limit
     * @return list
     */
    @Override
    public List<AppStoreProductReplyQueryVo> getReplyList(long productId, int type, int page, int limit) {
        List<AppStoreProductReplyQueryVo> newList = new ArrayList<>();
        Page<StoreProductReplyDO> pageModel = new Page<>(page, limit);
        List<AppStoreProductReplyQueryVo> list = this.baseMapper
                .selectReplyList(pageModel,productId,type);
        List<AppStoreProductReplyQueryVo> list1 = list.stream().map(i ->{
            AppStoreProductReplyQueryVo vo = new AppStoreProductReplyQueryVo();
            BeanUtils.copyProperties(i,vo);
            if(i.getPictures().contains(",")){
                vo.setPics(i.getPictures().split(","));
            }
            return vo;
        }).collect(Collectors.toList());
        for (AppStoreProductReplyQueryVo queryVo : list1) {
            newList.add(handleReply(queryVo));
        }
        return newList;
    }


    /**
     * 评价数据
     * @param productId 商品id
     * @return ReplyCountVO
     */
    @Override
    public AppReplyCountVo getReplyCount(long productId) {
        Long sumCount = productReplyCount(productId);

        if(sumCount == 0) {
            return new AppReplyCountVo();
        }

        //好评
        Long goodCount = this.baseMapper.selectCount(Wrappers.<StoreProductReplyDO>lambdaQuery()
                .eq(StoreProductReplyDO::getProductId,productId)
                .eq(StoreProductReplyDO::getProductScore,ScoreEnum.DEFAULT_5.getValue()));

        //中评
        Long inCount = this.baseMapper.selectCount(Wrappers.<StoreProductReplyDO>lambdaQuery()
                .eq(StoreProductReplyDO::getProductId,productId)
                .lt(StoreProductReplyDO::getProductScore,ScoreEnum.DEFAULT_5.getValue())
                .gt(StoreProductReplyDO::getProductScore,ScoreEnum.DEFAULT_2.getValue()));

        //差评
        Long poorCount = this.baseMapper.selectCount(Wrappers.<StoreProductReplyDO>lambdaQuery()
                .eq(StoreProductReplyDO::getProductId,productId)
                .lt(StoreProductReplyDO::getProductScore,ScoreEnum.DEFAULT_2.getValue()));

        //好评率
        String replyChance = ""+NumberUtil.round(NumberUtil.mul(NumberUtil.div(goodCount,sumCount),100),2);
        String replyStar = ""+NumberUtil.round(NumberUtil.mul(NumberUtil.div(goodCount,sumCount),5),2);

        return AppReplyCountVo.builder()
                .sumCount(sumCount)
                .goodCount(goodCount)
                .inCount(inCount)
                .poorCount(poorCount)
                .replyChance(replyChance)
                .replySstar(replyStar)
                .build();

    }

    /**
     * 处理评价
     * @param replyQueryVo replyQueryVo
     * @return YxStoreProductReplyQueryVo
     */
    private AppStoreProductReplyQueryVo handleReply(AppStoreProductReplyQueryVo replyQueryVo) {
        AppStoreCartQueryVo cartInfo = JSONObject.parseObject(replyQueryVo.getCartInfo()
                ,AppStoreCartQueryVo.class);
        if(ObjectUtil.isNotNull(cartInfo)){
            if(ObjectUtil.isNotNull(cartInfo.getProductInfo())){
                if(ObjectUtil.isNotNull(cartInfo.getProductInfo().getAttrInfo())){
                    replyQueryVo.setSku(cartInfo.getProductInfo().getAttrInfo().getSku());
                }
            }
        }

        BigDecimal star = NumberUtil.add(replyQueryVo.getProductScore(),
                replyQueryVo.getServiceScore());

        star = NumberUtil.div(star,2);

        replyQueryVo.setStar(String.valueOf(star.intValue()));

        if(StrUtil.isEmpty(replyQueryVo.getComment())){
            replyQueryVo.setComment(ProductConstants.NO_COMMENT_CONTENT);
        }

        return replyQueryVo;
    }


}
