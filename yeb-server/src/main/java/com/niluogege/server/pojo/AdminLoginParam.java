package com.niluogege.server.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户登录实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AdminLogin对象",description = "")
public class AdminLoginParam {

    @ApiModelProperty(value = "用户名",required = true)
    private String username;

    @ApiModelProperty(value = "密码",required = true)
    private String password;

    @ApiModelProperty(value = "验证码",required = true)
    private String code;

}
