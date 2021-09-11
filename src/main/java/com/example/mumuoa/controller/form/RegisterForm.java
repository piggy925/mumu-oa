package com.example.mumuoa.controller.form;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 接收前台注册信息的类.
 */
@Data
@ApiModel
public class RegisterForm {
    //注册码
    @Pattern(regexp = "^[0-9]{6}$", message = "注册码必须是6位数字")
    @NotNull(message = "注册码不能为空")
    private String registerCode;

    //临时授权码
    @NotNull(message = "微信临时授权码不能为空")
    private String code;

    //昵称
    @NotNull(message = "昵称不能为空")
    private String nickname;

    //头像
    @NotNull(message = "头像不能为空")
    private String photo;
}