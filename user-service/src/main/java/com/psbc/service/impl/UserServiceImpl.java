package com.psbc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.psbc.entity.User;
import com.psbc.mapper.UserMapper;
import com.psbc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}