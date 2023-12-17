package co.yixiang.yshop.module.member.service.useraddress;

import co.yixiang.yshop.module.member.controller.app.address.param.AppAddressParam;
import co.yixiang.yshop.module.member.controller.app.address.vo.AppUserAddressQueryVo;
import co.yixiang.yshop.module.member.dal.dataobject.useraddress.UserAddressDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 用户地址 Service 接口
 *
 * @author yshop
 */
public interface AppUserAddressService extends IService<UserAddressDO> {

    /**
     * 添加或者修改地址
     * @param uid uid
     * @param param AddressParam
     */
    Long addAndEdit(Long uid, AppAddressParam param);


    /**
     * 设置默认地址
     * @param uid uid
     * @param addressId 地址id
     */
    void setDefault(Long uid,String addressId);

    /**
     * 获取用户地址
     * @param uid uid
     * @param page page
     * @param limit limit
     * @return List
     */
    List<AppUserAddressQueryVo> getList(Long uid, int page, int limit);

}
