package co.yixiang.yshop.module.member.service.useraddress;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import co.yixiang.yshop.framework.common.enums.ShopCommonEnum;
import co.yixiang.yshop.module.member.controller.app.address.param.AppAddressParam;
import co.yixiang.yshop.module.member.controller.app.address.vo.AppUserAddressQueryVo;
import co.yixiang.yshop.module.member.convert.useraddress.UserAddressConvert;
import co.yixiang.yshop.module.member.dal.dataobject.useraddress.UserAddressDO;
import co.yixiang.yshop.module.member.dal.mysql.useraddress.UserAddressMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


import java.util.List;

import static co.yixiang.yshop.framework.common.exception.util.ServiceExceptionUtil.exception;
import static co.yixiang.yshop.module.member.enums.ErrorCodeConstants.USER_ADDRESS_NOT_EXISTS;
import static co.yixiang.yshop.module.member.enums.ErrorCodeConstants.USER_ADDRESS_PARAM_NOT_EXISTS;

/**
 * 用户地址 Service 实现类
 *
 * @author yshop
 */
@Service
@Validated
public class AppUserAddressServiceImpl extends ServiceImpl<UserAddressMapper,UserAddressDO> implements AppUserAddressService {

    @Resource
    private UserAddressMapper userAddressMapper;

    /**
     * 添加或者修改地址
     * @param uid uid
     * @param param AddressParam
     * @return id 地址id
     */
    @Override
    public Long addAndEdit(Long uid, AppAddressParam param){
        if(ShopCommonEnum.ENABLE_1.getValue().equals(param.getIsDefault())){
            userAddressMapper.update(UserAddressDO.builder().isDefault(ShopCommonEnum.DEFAULT_0.getValue()).build(),
                    new LambdaQueryWrapper<UserAddressDO>().eq(UserAddressDO::getUid,uid));
        }
        UserAddressDO userAddress = UserAddressDO.builder()
                .address(param.getAddress())
                .detail(param.getDetail())
                .uid(uid)
                .isDefault(param.getIsDefault())
                .phone(param.getPhone())
                .postCode(param.getPostCode())
                .realName(param.getRealName())
                .latitude(param.getLatitude())
                .longitude(param.getLongitude())
                .build();
        if(StrUtil.isBlank(param.getId())){
            this.save(userAddress);
        }else{
            userAddress.setId(Long.valueOf(param.getId()));
            this.updateById(userAddress);
        }

        return userAddress.getId();
    }

    /**
     * 设置默认地址
     * @param uid uid
     * @param addressId 地址id
     */
    @Override
    public void setDefault(Long uid,String addressId){
        if(StrUtil.isBlank(addressId) || !NumberUtil.isNumber(addressId)){
            throw exception(USER_ADDRESS_PARAM_NOT_EXISTS);
        }
        UserAddressDO address = new UserAddressDO();
        address.setIsDefault(ShopCommonEnum.DEFAULT_0.getValue());
        userAddressMapper.update(address,
                new LambdaQueryWrapper<UserAddressDO>().eq(UserAddressDO::getUid,uid));

        UserAddressDO userAddress = new UserAddressDO();
        userAddress.setIsDefault(ShopCommonEnum.DEFAULT_1.getValue());
        userAddress.setId(Long.valueOf(addressId));
        userAddressMapper.updateById(userAddress);
    }


    /**
     * 获取用户地址
     * @param uid uid
     * @param page page
     * @param limit limit
     * @return List
     */
    @Override
    public List<AppUserAddressQueryVo> getList(Long uid, int page, int limit){
        Page<UserAddressDO> pageModel = new Page<>(page, limit);
        IPage<UserAddressDO> pageList = this.lambdaQuery().eq(UserAddressDO::getUid,uid).page(pageModel);
        return UserAddressConvert.INSTANCE.convertList02(pageList.getRecords());
    }


}
