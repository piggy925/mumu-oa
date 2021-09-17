package com.example.mumuoa.controller;

import cn.hutool.core.date.DateUtil;
import com.example.mumuoa.common.util.R;
import com.example.mumuoa.config.shiro.JwtUtil;
import com.example.mumuoa.service.CheckinService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "签到模块接口")
@RestController
@Slf4j
@RequestMapping("/checkin")
public class CheckinController {
    @Resource
    private CheckinService checkinService;

    @Resource
    private JwtUtil jwtUtil;

    @ApiOperation("查看用户今天是否可以签到")
    @GetMapping("/validCanCheckin")
    public R validCanCheckin(@RequestHeader("token") String token) {
        int userId = jwtUtil.getUserId(token);
        String result = checkinService.validCanChecking(userId, DateUtil.today());
        return R.success(result);
    }
}