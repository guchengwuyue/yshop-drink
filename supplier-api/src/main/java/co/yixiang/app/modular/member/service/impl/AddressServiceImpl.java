package co.yixiang.app.modular.member.service.impl;



import cn.hutool.core.util.StrUtil;
import co.yixiang.app.common.persistence.dao.StoreMemberAddressMapper;
import co.yixiang.app.common.persistence.model.StoreMemberAddress;
import co.yixiang.app.modular.member.service.IAddressService;
import co.yixiang.app.modular.member.service.vo.AddressVO;
import co.yixiang.app.modular.shop.service.vo.PageVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import co.yixiang.app.modular.member.service.dto.AddressDTO;
import co.yixiang.app.modular.member.service.mapper.AddressMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AddressServiceImpl extends ServiceImpl<StoreMemberAddressMapper, StoreMemberAddress>  implements IAddressService {

    private final AddressMapper addressMapper;

    /**
     * 地址列表
     * @param pageVO
     * @param userId
     * @return
     */
    @Override
    public List<AddressDTO> getList(PageVO pageVO, int userId) {

        QueryWrapper<StoreMemberAddress> wrapper = new QueryWrapper<>();
        wrapper.eq("mid",userId);
        Page<StoreMemberAddress> pageModel = new Page<>(pageVO.getPage(), pageVO.getLimit());

        IPage<StoreMemberAddress> pageList = baseMapper.selectPage(pageModel,wrapper);

        return addressMapper.toDto(pageList.getRecords());
    }

    /**
     * 添加或者修改
     * @param addressVO
     * @param userId
     * @return
     */
    @Override
    public boolean addAndEdit(AddressVO addressVO, int userId) {
        StoreMemberAddress storeMemberAddress = new StoreMemberAddress();
        storeMemberAddress.setCityInfo(addressVO.getCityInfo());
        storeMemberAddress.setPhone(addressVO.getPhone());
        storeMemberAddress.setAddress(addressVO.getAddress());
        storeMemberAddress.setUsername(addressVO.getUsername());
        int count = 0;
        if(StrUtil.isEmpty(addressVO.getId())){
            if(findDefault(userId) != null){
                storeMemberAddress.setIsDefault(0);
            }else{
                storeMemberAddress.setIsDefault(1);
            }
            storeMemberAddress.setMid(Integer.valueOf(userId).longValue());
            count = baseMapper.insert(storeMemberAddress);
            if(count > 0) return true;
        }else{
            storeMemberAddress.setId(Long.valueOf(addressVO.getId()));
            count = baseMapper.updateById(storeMemberAddress);
            if(count > 0) return true;
        }
        return false;
    }

    @Override
    public AddressDTO findDefault(int userId) {
        QueryWrapper<StoreMemberAddress> wrapper = new QueryWrapper<>();
        wrapper.eq("mid",userId).eq("is_default",1);

        return addressMapper.toDto(baseMapper.selectOne(wrapper));
    }
}
