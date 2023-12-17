package co.yixiang.yshop.module.member.service.userbill;

import co.yixiang.yshop.framework.common.pojo.PageResult;
import co.yixiang.yshop.module.member.controller.admin.userbill.vo.UserBillPageReqVO;
import co.yixiang.yshop.module.member.controller.app.user.vo.AppUserBillVO;
import co.yixiang.yshop.module.member.dal.dataobject.userbill.UserBillDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 用户账单 Service 接口
 *
 * @author yshop
 */
public interface UserBillService extends IService<UserBillDO> {


    /**
     * 获得用户账单分页
     *
     * @param pageReqVO 分页查询
     * @return 用户账单分页
     */
    PageResult<UserBillDO> getUserBillPage(UserBillPageReqVO pageReqVO);

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
    void expend(Long uid,String title,String category,String type,double number,double balance,String mark);

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
    void income(Long uid,String title,String category,String type,double number,
                double balance,String mark,String linkid);

    void income(Long uid,String title,String category,String type,double number,
                double balance,String mark,String linkid,String extendField);

    /**
     * 获取用户账单
     * @param uid
     * @param cate 0余额 1-积分
     * @param type 状态,0全部  1消费 2充值 3退款
     * @param page
     * @param limit
     * @return
     */
    List<AppUserBillVO> getBillList(Long uid, int cate, int type,int page, int limit);


}
