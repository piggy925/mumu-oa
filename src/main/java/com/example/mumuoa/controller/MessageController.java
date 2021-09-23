package com.example.mumuoa.controller;


import com.example.mumuoa.common.util.R;
import com.example.mumuoa.config.shiro.JwtUtil;
import com.example.mumuoa.controller.form.DeleteMessageRefByIdForm;
import com.example.mumuoa.controller.form.SearchMessageByIdForm;
import com.example.mumuoa.controller.form.SearchMessageByPageForm;
import com.example.mumuoa.controller.form.UpdateUnreadMessageForm;
import com.example.mumuoa.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/message")
@Api(tags = "消息模块接口")
public class MessageController {
    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private MessageService messageService;

    @ApiOperation("获取分页消息列表")
    @PostMapping("/searchMessageByPage")
    public R searchMessageByPage(@Valid @RequestBody SearchMessageByPageForm form, @RequestHeader("token") String token) {
        int userId = jwtUtil.getUserId(token);
        int page = form.getPage();
        Integer length = form.getLength();
        long start = (page - 1) * length;
        List<HashMap> list = messageService.searchMessageByPage(userId, start, length);
        return R.success().put("result", list);
    }

    @ApiOperation("根据ID查询消息详情 ")
    @PostMapping("/searchMessageById")
    public R searchMessageById(@Valid @RequestBody SearchMessageByIdForm form) {
        HashMap message = messageService.searchMessageById(form.getId());
        return R.success().put("result", message);
    }

    @ApiOperation("更新未读消息为已读")
    @PostMapping("/updateUnreadMessage")
    public R updateUnreadMessage(@Valid @RequestBody UpdateUnreadMessageForm form) {
        long count = messageService.updateUnreadMessage(form.getId());
        return R.success().put("result", count == 1);
    }

    @ApiOperation("删除消息")
    @PostMapping("/deleteMessageRefById")
    public R deleteMessageRefById(@Valid @RequestBody DeleteMessageRefByIdForm form) {
        long count = messageService.deleteMessageRefById(form.getId());
        return R.success().put("result", count == 1);
    }
}