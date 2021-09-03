package com.example.mumuoa.controller;

import com.example.mumuoa.common.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("测试接口")
@RequestMapping("/test")
@RestController
public class TestController {
    @ApiOperation("测试方法")
    @GetMapping("/say")
    public R sayHello(@RequestBody String name) {
        return R.success().put("message", "hello world");
    }
}