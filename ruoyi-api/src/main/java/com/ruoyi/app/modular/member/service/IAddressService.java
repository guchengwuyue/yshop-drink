package com.ruoyi.app.modular.member.service;




import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.app.common.persistence.model.StoreMemberAddress;
import com.ruoyi.app.modular.member.service.dto.AddressDTO;
import com.ruoyi.app.modular.member.service.vo.AddressVO;
import com.ruoyi.app.modular.shop.service.vo.PageVO;

import java.util.List;

public interface IAddressService extends IService<StoreMemberAddress> {

    List<AddressDTO> getList(PageVO pageVO,int userId);

    boolean addAndEdit(AddressVO addressVO,int userId);

    AddressDTO findDefault(int userId);

}
