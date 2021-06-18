package co.yixiang.app.modular.member.service;




import co.yixiang.app.common.persistence.model.StoreMemberAddress;
import co.yixiang.app.modular.member.service.vo.AddressVO;
import co.yixiang.app.modular.shop.service.vo.PageVO;
import com.baomidou.mybatisplus.extension.service.IService;
import co.yixiang.app.modular.member.service.dto.AddressDTO;

import java.util.List;

public interface IAddressService extends IService<StoreMemberAddress> {

    List<AddressDTO> getList(PageVO pageVO, int userId);

    boolean addAndEdit(AddressVO addressVO, int userId);

    AddressDTO findDefault(int userId);

}
