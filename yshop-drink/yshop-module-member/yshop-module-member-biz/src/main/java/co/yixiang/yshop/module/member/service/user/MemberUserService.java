package co.yixiang.yshop.module.member.service.user;

import co.yixiang.yshop.framework.common.validation.Mobile;
import co.yixiang.yshop.module.member.controller.app.user.vo.AppUserQueryVo;
import co.yixiang.yshop.module.member.controller.app.user.vo.AppUserUpdateMobileReqVO;
import co.yixiang.yshop.module.member.dal.dataobject.user.MemberUserDO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

/**
 * 会员用户 Service 接口
 *
 * @author yshop
 */
public interface MemberUserService extends IService<MemberUserDO> {


    /**
     * 通过手机查询用户
     *
     * @param mobile 手机
     * @return 用户对象
     */
    MemberUserDO getUserByMobile(String mobile);

    /**
     * 基于用户昵称，模糊匹配用户列表
     *
     * @param nickname 用户昵称，模糊匹配
     * @return 用户信息的列表
     */
    List<MemberUserDO> getUserListByNickname(String nickname);

    /**
     * 基于手机号创建用户。
     * 如果用户已经存在，则直接进行返回
     *
     * @param mobile 手机号
     * @param registerIp 注册 IP
     * @return 用户对象
     */
    MemberUserDO createUserIfAbsent(@Mobile String mobile, String registerIp,String from);

    /**
     * 更新用户的最后登陆信息
     *
     * @param id 用户编号
     * @param loginIp 登陆 IP
     */
    void updateUserLogin(Long id, String loginIp);

    /**
     * 通过用户 ID 查询用户
     *
     * @param id 用户ID
     * @return 用户对象信息
     */
    MemberUserDO getUser(Long id);

    /**
     * 通过用户 ID 查询用户
     *
     * @param id 用户ID
     * @return 用户对象信息
     */
    AppUserQueryVo getAppUser(Long id);


    /**
     * 减去用户余额
     * @param uid uid
     * @param payPrice 金额
     */
    void decPrice(Long uid, BigDecimal payPrice);

    /**
     * 减去用户积分
     * @param uid uid
     * @param score 积分
     */
    void decScore(Long uid, Integer score);


    /**
     * 增加购买次数
     * @param uid uid
     */
    void incPayCount(Long uid);

    /**
     * 通过用户 ID 查询用户们
     *
     * @param ids 用户 ID
     * @return 用户对象信息数组
     */
    List<MemberUserDO> getUserList(Collection<Long> ids);

    /**
     * 修改用户昵称
     * @param userId 用户id
     * @param nickname 用户新昵称
     */
    void updateUserNickname(Long userId, String nickname,String birthday, Integer gender,
                            String avatar,
                            String mobile);

    /**
     * 修改用户头像
     * @param userId 用户id
     * @param inputStream 头像文件
     * @return 头像url
     */
    String updateUserAvatar(Long userId, InputStream inputStream) throws Exception;

    /**
     * 修改手机
     * @param userId 用户id
     * @param reqVO 请求实体
     */
    void updateUserMobile(Long userId, AppUserUpdateMobileReqVO reqVO);

    /**
     * 判断密码是否匹配
     *
     * @param rawPassword 未加密的密码
     * @param encodedPassword 加密后的密码
     * @return 是否匹配
     */
    boolean isPasswordMatch(String rawPassword, String encodedPassword);

    /**
     * 更新用户余额
     * @param uid y用户id
     * @param price 金额
     */
    void incMoney(Long uid,BigDecimal price);



}
