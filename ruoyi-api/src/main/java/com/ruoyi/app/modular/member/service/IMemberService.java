package com.ruoyi.app.modular.member.service;




import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.app.common.persistence.model.StoreMember;
import com.ruoyi.app.modular.member.service.dto.MemberDTO;

import java.util.List;

public interface IMemberService extends IService<StoreMember> {
    StoreMember login(String openid);

    List<MemberDTO> memeberList();
}
