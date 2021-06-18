package co.yixiang.app.modular.member.service;




import co.yixiang.app.common.persistence.model.StoreMember;
import co.yixiang.app.modular.shop.service.dto.CouponDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import co.yixiang.app.common.persistence.model.StoreMember;
import co.yixiang.app.modular.member.service.dto.MemberDTO;
import co.yixiang.app.modular.shop.service.dto.CouponDTO;

import java.util.List;

public interface IMemberService extends IService<StoreMember> {
    StoreMember login(String openid);

    List<MemberDTO> memeberList();

    List<CouponDTO> couponList(int userId, double orderTotalPrice);

}
