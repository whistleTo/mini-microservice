package com.psbc.controller;

import com.psbc.annotation.Cost;
import com.psbc.entity.Order;
import com.psbc.entity.User;
import com.psbc.feign.UserFeignClient;
import com.psbc.result.Result;
import com.psbc.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @Cost
    @GetMapping("/get/alone/{id}")
    public Order getOrder(@PathVariable("id") Long id) {
        return orderService.getById(id);
    }

    private final UserFeignClient userFeignClient;

    @Cost
    @GetMapping("/get/{id}")
    public Map<String, Object> getOrderWithUser(@PathVariable Long id) {
        Order order = orderService.getById(id);
        Result<User> userResult = userFeignClient.getUserById(order.getUserId());

        Map<String, Object> result = new HashMap<>();
        result.put("order", order);
        result.put("user", userResult.getData());
        return result;
    }
}