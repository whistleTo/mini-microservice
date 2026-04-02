package com.psbc.feign;

import com.psbc.entity.User;
import com.psbc.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("user-service")
public interface UserFeignClient {

    @GetMapping("/user/get/{id}")
    Result<User> getUserById(@PathVariable("id") Long id);
}