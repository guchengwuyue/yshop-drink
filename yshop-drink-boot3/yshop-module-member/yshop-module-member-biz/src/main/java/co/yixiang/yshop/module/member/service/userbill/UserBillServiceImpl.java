package co.yixiang.yshop.module.member.service.userbill;

import co.yixiang.yshop.framework.common.enums.ShopCommonEnum;
import co.yixiang.yshop.framework.common.pojo.PageResult;
import co.yixiang.yshop.module.member.controller.admin.userbill.vo.UserBillPageReqVO;
import co.yixiang.yshop.module.member.controller.app.user.vo.AppUserBillVO;
import co.yixiang.yshop.module.member.convert.userbill.UserBillConvert;
import co.yixiang.yshop.module.member.dal.dataobject.userbill.UserBillDO;
import co.yixiang.yshop.module.member.dal.mysql.userbill.UserBillMapper;
import co.yixiang.yshop.module.member.enums.BillDetailEnum;
import co.yixiang.yshop.module.member.enums.BillEnum;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.List;

/**
 * 用户账单 Service 实现类
 *
 * @author yshop
 */
@Service
@Validated
public class UserBillServiceImpl extends ServiceImpl<UserBillMapper, UserBillDO> implements UserBillService {

    @Resource
    private UserBillMapper userBillMapper;

    @Override
    public PageResult<UserBillDO> getUserBillPage(UserBillPageReqVO pageReqVO) {
        return userBillMapper.selectPage(pageReqVO);
    }


    /**
     * 增加支出流水
     * @param uid uid
     * @param title 账单标题
     * @param category 明细种类
     * @param type 明细类型
     * @param number 明细数字
     * @param balance 剩余
     * @param mark 备注
     */
    @Override
    public void expend(Long uid,String title,String category,String type,double number,double balance,String mark){
        UserBillDO userBill = UserBillDO.builder()
                .uid(uid)
                .title(title)
                .category(category)
                .type(type)
                .number(BigDecimal.valueOf(number))
                .balance(BigDecimal.valueOf(balance))
                .mark(mark)
                .pm(BillEnum.PM_0.getValue())
                .build();

        userBillMapper.insert(userBill);
    }

    /**
     * 增加收入/支入流水
     * @param uid uid
     * @param title 账单标题
     * @param category 明细种类
     * @param type 明细类型
     * @param number 明细数字
     * @param balance 剩余
     * @param mark 备注
     * @param linkid 关联id
     */
    @Override
    public void income(Long uid,String title,String category,String type,double number,
                       double balance,String mark,String linkid){
        UserBillDO userBill = UserBillDO.builder()
                .uid(uid)
                .title(title)
                .category(category)
                .type(type)
                .number(BigDecimal.valueOf(number))
                .balance(BigDecimal.valueOf(balance))
                .mark(mark)
                .pm(BillEnum.PM_1.getValue())
                .linkId(linkid)
                .build();

        userBillMapper.insert(userBill);
    }

    @Override
    public void income(Long uid, String title, String category, String type, double number, double balance,
                       String mark, String linkid, String extendField) {

        UserBillDO userBill = UserBillDO.builder()
                .uid(uid)
                .title(title)
                .category(category)
                .type(type)
                .number(BigDecimal.valueOf(number))
                .balance(BigDecimal.valueOf(balance))
                .mark(mark)
                .pm(BillEnum.PM_1.getValue())
                .linkId(linkid)
                .extendField(extendField)
                .status(ShopCommonEnum.IS_STATUS_1.getValue())
                .build();

        userBillMapper.insert(userBill);

    }

    /**
     * 获取用户账单
     * @param uid
     * @param cate 0余额 1-积分
     * @param type 状态,0全部  1消费 2充值 3退款
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<AppUserBillVO> getBillList(Long uid,int cate, int type, int page, int limit) {
        LambdaQueryWrapper<UserBillDO> wrapper = new LambdaQueryWrapper<>();
        if(cate == 1){
            wrapper.eq(UserBillDO::getCategory,BillDetailEnum.CATEGORY_2.getValue());
        }else{
            wrapper.eq(UserBillDO::getCategory,BillDetailEnum.CATEGORY_1.getValue());
        }
        wrapper.eq(UserBillDO::getUid, uid)
                .eq(UserBillDO::getStatus,ShopCommonEnum.IS_STATUS_1.getValue())
                //.eq(UserBillDO::getCategory,BillDetailEnum.CATEGORY_1.getValue())
                .orderByDesc(UserBillDO::getId);
        switch (type){
            case 1:
                wrapper.eq(UserBillDO::getType, BillDetailEnum.TYPE_3.getValue());
                break;
            case 2:
                wrapper.eq(UserBillDO::getType, BillDetailEnum.TYPE_1.getValue());
                break;
            case 3:
                wrapper.eq(UserBillDO::getType, BillDetailEnum.TYPE_5.getValue());
                break;
            default:

        }
        Page<UserBillDO> pageModel = new Page<>(page, limit);
        IPage<UserBillDO> pageList = userBillMapper.selectPage(pageModel, wrapper);
        return UserBillConvert.INSTANCE.convertList02(pageList.getRecords());
    }


}
