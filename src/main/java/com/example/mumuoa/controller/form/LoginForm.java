package com.example.mumuoa.controller.form;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;

@ApiModel
@Data
public class LoginForm {
    @NotNull(message = "授权码不能为空")
    private String code;
}