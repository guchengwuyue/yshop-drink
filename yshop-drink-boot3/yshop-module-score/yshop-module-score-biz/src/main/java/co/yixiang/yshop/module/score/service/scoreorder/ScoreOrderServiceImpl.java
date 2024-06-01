package co.yixiang.yshop.module.score.service.scoreorder;

import co.yixiang.yshop.framework.common.enums.ShopCommonEnum;
import co.yixiang.yshop.module.member.controller.admin.user.vo.UserRespVO;
import co.yixiang.yshop.module.member.convert.user.UserConvert;
import co.yixiang.yshop.module.member.dal.dataobject.user.MemberUserDO;
import co.yixiang.yshop.module.member.dal.mysql.user.MemberUserMapper;
import co.yixiang.yshop.module.score.convert.scoreproduct.ScoreProductConvert;
import co.yixiang.yshop.module.score.dal.dataobject.scoreproduct.ScoreProductDO;
import co.yixiang.yshop.module.score.dal.mysql.scoreproduct.ScoreProductMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import co.yixiang.yshop.module.score.controller.admin.scoreorder.vo.*;
import co.yixiang.yshop.module.score.dal.dataobject.scoreorder.ScoreOrderDO;
import co.yixiang.yshop.framework.common.pojo.PageResult;

import co.yixiang.yshop.module.score.convert.scoreorder.ScoreOrderConvert;
import co.yixiang.yshop.module.score.dal.mysql.scoreorder.ScoreOrderMapper;

import static co.yixiang.yshop.framework.common.exception.util.ServiceExceptionUtil.exception;
import static co.yixiang.yshop.module.score.enums.ErrorCodeConstants.*;

/**
 * 积分商城订单 Service 实现类
 *
 * @author yshop
 */
@Service
@Validated
public class ScoreOrderServiceImpl implements ScoreOrderService {

    @Resource
    private ScoreOrderMapper orderMapper;
    @Resource
    private ScoreProductMapper scoreProductMapper;
    @Resource
    private MemberUserMapper memberUserMapper;

    @Override
    public Long createOrder(ScoreOrderCreateReqVO createReqVO) {
        // 插入
        ScoreOrderDO order = ScoreOrderConvert.INSTANCE.convert(createReqVO);
        orderMapper.insert(order);
        // 返回
        return order.getId();
    }

    @Override
    public void updateOrder(ScoreOrderUpdateReqVO updateReqVO) {
        // 校验存在
        validateOrderExists(updateReqVO.getId());
        // 更新
        ScoreOrderDO updateObj = ScoreOrderConvert.INSTANCE.convert(updateReqVO);
        updateObj.setHaveDelivered(ShopCommonEnum.DEFAULT_1.getValue());
        orderMapper.updateById(updateObj);
    }

    @Override
    public void deleteOrder(Long id) {
        // 校验存在
        validateOrderExists(id);
        // 删除
        orderMapper.deleteById(id);
    }

    @Override
    public void takeOrder(Long id) {
        // 校验存在
        ScoreOrderDO scoreOrderDO = orderMapper.selectById(id);
        if (scoreOrderDO == null) {
            throw exception(ORDER_NOT_EXISTS);
        }
        scoreOrderDO.setHaveReceived(ShopCommonEnum.DEFAULT_1.getValue());
        orderMapper.updateById(scoreOrderDO);

    }

    private void validateOrderExists(Long id) {
        if (orderMapper.selectById(id) == null) {
            throw exception(ORDER_NOT_EXISTS);
        }
    }

    @Override
    public ScoreOrderRespVO getOrder(Long id) {
        ScoreOrderDO scoreOrderDO = orderMapper.selectById(id);
        ScoreOrderRespVO scoreOrderRespVO = ScoreOrderConvert.INSTANCE.convert(scoreOrderDO);
        ScoreProductDO scoreProductDO = scoreProductMapper.selectById(scoreOrderRespVO.getProductId());
        scoreOrderRespVO.setScoreProductRespVO(ScoreProductConvert.INSTANCE.convert(scoreProductDO));
        MemberUserDO memberUserDO = memberUserMapper.selectById(scoreOrderRespVO.getUid());
        UserRespVO  userRespVO = UserConvert.INSTANCE.convert4(memberUserDO);
        scoreOrderRespVO.setUserRespVO(userRespVO);
        return scoreOrderRespVO;
    }

    @Override
    public List<ScoreOrderDO> getOrderList(Collection<Long> ids) {
        return orderMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ScoreOrderRespVO> getOrderPage(ScoreOrderPageReqVO pageReqVO) {
        PageResult<ScoreOrderDO> pageResult = orderMapper.selectPage(pageReqVO);
        PageResult<ScoreOrderRespVO> scoreOrderRespVOPageResult = ScoreOrderConvert.INSTANCE.convertPage(pageResult);
        for (ScoreOrderRespVO scoreOrderRespVO : scoreOrderRespVOPageResult.getList()) {
            ScoreProductDO scoreProductDO = scoreProductMapper.selectById(scoreOrderRespVO.getProductId());
            scoreOrderRespVO.setScoreProductRespVO(ScoreProductConvert.INSTANCE.convert(scoreProductDO));
            MemberUserDO memberUserDO = memberUserMapper.selectById(scoreOrderRespVO.getUid());
            UserRespVO  userRespVO = UserConvert.INSTANCE.convert4(memberUserDO);
            scoreOrderRespVO.setUserRespVO(userRespVO);
        }
        return scoreOrderRespVOPageResult;
    }

    @Override
    public List<ScoreOrderDO> getOrderList(ScoreOrderExportReqVO exportReqVO) {
        return orderMapper.selectList(exportReqVO);
    }

}
