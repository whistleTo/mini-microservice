package com.psbc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.psbc.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}