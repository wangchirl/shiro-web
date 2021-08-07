package com.shadow.shiro.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Data
@Accessors(chain = true)
public class LoginUserDTO {

    @NotBlank(message = "用户名不能空")
    private String username;

    @NotBlank(message = "密码不能空")
    private String password;

    @NotBlank(message = "验证码不能空")
    private String captcha;

}
