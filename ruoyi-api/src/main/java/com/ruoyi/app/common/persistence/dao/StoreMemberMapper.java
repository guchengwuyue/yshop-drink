package com.ruoyi.app.common.persistence.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.app.common.persistence.model.StoreMember;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface StoreMemberMapper extends BaseMapper<StoreMember> {
    @Update("update store_member set user_money=user_money-#{money} where id=#{userId}")
    int decUserMony(@Param("userId") int userId,@Param("money") double money);
}
