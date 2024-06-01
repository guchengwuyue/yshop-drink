package co.yixiang.yshop.module.member.service.user;

import cn.hutool.core.util.NumberUtil;
import co.yixiang.yshop.framework.common.enums.ShopCommonEnum;
import co.yixiang.yshop.module.member.enums.BillDetailEnum;
import co.yixiang.yshop.module.member.service.userbill.UserBillService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.*;
import co.yixiang.yshop.module.member.controller.admin.user.vo.*;
import co.yixiang.yshop.module.member.dal.dataobject.user.MemberUserDO;
import co.yixiang.yshop.framework.common.pojo.PageResult;

import co.yixiang.yshop.module.member.convert.user.UserConvert;
import co.yixiang.yshop.module.member.dal.mysql.user.MemberUserMapper;

import static co.yixiang.yshop.framework.common.exception.util.ServiceExceptionUtil.exception;
import static co.yixiang.yshop.module.member.enums.ErrorCodeConstants.*;

/**
 * 用户 Service 实现类
 *
 * @author yshop
 */
@Service
@Validated
public class UserServiceImpl implements UserService {

    @Resource
    private MemberUserMapper userMapper;
    @Resource
    private UserBillService userBillService;

    @Override
    public Long createUser(UserCreateReqVO createReqVO) {
        // 插入
        MemberUserDO user = UserConvert.INSTANCE.convert(createReqVO);
        userMapper.insert(user);
        // 返回
        return user.getId();
    }

    @Override
    public void updateUser(UserUpdateReqVO updateReqVO) {
        // 校验存在
        validateUserExists(updateReqVO.getId());
        // 更新
        MemberUserDO updateObj = UserConvert.INSTANCE.convert(updateReqVO);
        userMapper.updateById(updateObj);
    }

    /**
     * 更新金额与积分
     *
     * @param updateReqVO 更新信息
     */
    @Override
    public void updateMony(UserUpdateMoneyReqVO updateReqVO){
        MemberUserDO memberUserDO = userMapper.selectById(updateReqVO.getId());
        double newMoney = 0d;
        String mark = "";
        if(ShopCommonEnum.ADD_1.getValue().equals(updateReqVO.getPtype())){
            mark = "系统增加了"+updateReqVO.getMoney()+"余额";
            newMoney = NumberUtil.add(memberUserDO.getNowMoney(),new BigDecimal(updateReqVO.getMoney())).doubleValue();
            userBillService.income(memberUserDO.getId(),"系统增加余额", BillDetailEnum.CATEGORY_1.getValue(),
                    BillDetailEnum.TYPE_6.getValue(),Double.valueOf(updateReqVO.getMoney()),newMoney, mark,"");
        }else{
            mark = "系统扣除了"+updateReqVO.getMoney()+"余额";
            newMoney = NumberUtil.sub(memberUserDO.getNowMoney(),new BigDecimal(updateReqVO.getMoney())).doubleValue();
            if(newMoney < 0) {
                newMoney = 0d;
            }
            userBillService.expend(memberUserDO.getId(), "系统减少余额",
                    BillDetailEnum.CATEGORY_1.getValue(),
                    BillDetailEnum.TYPE_7.getValue(),
                    Double.valueOf(updateReqVO.getMoney()), newMoney, mark);
        }
        double newIntegral = 0d;
        if(ShopCommonEnum.ADD_1.getValue().equals(updateReqVO.getItype())){
            mark = "系统增加了"+updateReqVO.getIntegral()+"积分";
            newIntegral = NumberUtil.add(memberUserDO.getIntegral(),new BigDecimal(updateReqVO.getIntegral())).doubleValue();
            userBillService.income(memberUserDO.getId(),"系统增加积分", BillDetailEnum.CATEGORY_2.getValue(),
                    BillDetailEnum.TYPE_6.getValue(),Double.valueOf(updateReqVO.getIntegral()),newIntegral, mark,"");
        }else{
            mark = "系统扣除了"+updateReqVO.getIntegral()+"积分";
            newIntegral = NumberUtil.sub(memberUserDO.getIntegral(),new BigDecimal(updateReqVO.getIntegral())).doubleValue();
            if(newIntegral < 0) {
                newIntegral = 0d;
            }
            userBillService.expend(memberUserDO.getId(), "系统减少积分",
                    BillDetailEnum.CATEGORY_2.getValue(),
                    BillDetailEnum.TYPE_7.getValue(),
                    Double.valueOf(updateReqVO.getIntegral()), newIntegral, mark);
        }
        memberUserDO.setIntegral(BigDecimal.valueOf(newIntegral));
        memberUserDO.setNowMoney(BigDecimal.valueOf(newMoney));
        userMapper.updateById(memberUserDO);

    }


    @Override
    public void deleteUser(Long id) {
        // 校验存在
        validateUserExists(id);
        // 删除
        userMapper.deleteById(id);
    }

    private void validateUserExists(Long id) {
        if (userMapper.selectById(id) == null) {
            throw exception(USER_NOT_EXISTS);
        }
    }

    @Override
    public MemberUserDO getUser(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public List<MemberUserDO> getUserList(Collection<Long> ids) {
        return userMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<MemberUserDO> getUserPage(UserPageReqVO pageReqVO) {
        return userMapper.selectPage(pageReqVO);
    }

    @Override
    public List<MemberUserDO> getUserList(UserExportReqVO exportReqVO) {
        return userMapper.selectList(exportReqVO);
    }

}
