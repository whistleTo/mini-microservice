package com.psbc.controller;

import com.psbc.annotation.Cost;
import com.psbc.entity.User;
import com.psbc.result.Result;
import com.psbc.service.UserService;
import com.psbc.util.RedisCache;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final RedisCache redisCache;

    private static final String CACHE_KEY_USER = "user:info:";

    @Cost
    @GetMapping("/get/{id}")
    public Result<?> getUserById(@PathVariable Long id) {
        String key = CACHE_KEY_USER + id;

        // 1. 查缓存（原样拿出来，不偷偷处理）
        Object cacheObj = redisCache.get(key);

        // 情况1：缓存里是我们的空值标记 → 直接返回，不查库！
        // ==============================================
        if (RedisCache.EMPTY_TAG.equals(cacheObj)) {
            return Result.fail(401,"曾将查过，用户不存在");
        }
        // ==============================================
        // 情况2：缓存里有正常用户 → 直接返回
        // ==============================================
        if (cacheObj != null) {
            log.info("缓存命中，直接返回，用户ID：{}", id);
            return Result.success((User) cacheObj);
        }

        // 2. Redis 没有，查数据库
        log.info("缓存未命中，查询数据库，用户ID：{}", id);
        User dbUser = userService.getById(id);


        // 3. 存入 Redis
        // 正常数据 & 空数据都能存，自动处理null，防缓存穿透
        redisCache.set(key, dbUser, 30, TimeUnit.MINUTES);
        log.info("数据已存入Redis，用户ID：{}", id);



        return dbUser == null ? Result.fail(400,"首次查询并且用户不存在") : Result.success(dbUser);
    }
}