package com.example.mumuoa.controller;

import com.example.mumuoa.common.util.R;
import com.example.mumuoa.config.shiro.JwtUtil;
import com.example.mumuoa.controller.form.LoginForm;
import com.example.mumuoa.controller.form.RegisterForm;
import com.example.mumuoa.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Api(tags = "用户模块")
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private RedisTemplate redisTemplate;

    @Value("${mumuoa.jwt.cache-expire}")
    private int cacheExpire;

    @ApiOperation("用户注册")
    @PostMapping("/register")
    private R register(@Valid @RequestBody RegisterForm form) {
        int userId = userService.registerUser(form.getRegisterCode(), form.getCode(), form.getNickname(), form.getPhoto());
        String token = jwtUtil.createToken(userId);
        Set<String> permissions = userService.searchUserPermissions(userId);
        saveTokenCache(token, userId);
        return R.success("用户注册成功").put("token", token).put("permission", permissions);
    }

    private void saveTokenCache(String token, int userId) {
        redisTemplate.opsForValue().set(token, userId, cacheExpire, TimeUnit.DAYS);
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    private R login(@Valid @RequestBody LoginForm form) {
        Integer userId = userService.login(form.getCode());
        String token = jwtUtil.createToken(userId);
        Set<String> permissions = userService.searchUserPermissions(userId);
        saveTokenCache(token, userId);
        return R.success("登录成功").put("token", token).put("permission", permissions);
    }
}