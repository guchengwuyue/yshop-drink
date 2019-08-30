package com.ruoyi.app.modular.member.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.app.common.persistence.dao.StoreMemberDao;
import com.ruoyi.app.common.persistence.model.StoreMember;
import com.ruoyi.app.modular.member.service.IMemberService;
import com.ruoyi.app.modular.member.service.dto.MemberDTO;
import com.ruoyi.app.modular.member.service.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MemberServiceImpl extends ServiceImpl<StoreMemberDao,StoreMember>  implements IMemberService {

    @Autowired
    private StoreMemberDao storeMemberMapper;

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public StoreMember login(String openid) {
        // 根据登陆账号获取数据库信息


        StoreMember result = storeMemberMapper
                .selectOne(new QueryWrapper<StoreMember>().lambda()
                        .eq(StoreMember::getOpenid, openid));

        return  result;
        //return null;
    }

    @Override
    public List<MemberDTO> memeberList() {
        //storeMemberMapper.selectList(null);
        return  memberMapper.toDto(storeMemberMapper.selectList(null));
        //return null;
    }
}
