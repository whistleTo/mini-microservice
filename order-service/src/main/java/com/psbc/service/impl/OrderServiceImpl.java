package com.psbc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.psbc.entity.Order;
import com.psbc.mapper.OrderMapper;
import com.psbc.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
}