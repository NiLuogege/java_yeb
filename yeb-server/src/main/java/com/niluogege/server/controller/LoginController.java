package com.niluogege.server.controller;

import com.niluogege.server.pojo.Admin;
import com.niluogege.server.pojo.AdminLoginParam;
import com.niluogege.server.pojo.RespBean;
import com.niluogege.server.pojo.Role;
import com.niluogege.server.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = "LoginController")
@RestController
public class LoginController {

    @Autowired
    private IAdminService adminService;

    @ApiOperation(value = "登录之后返回token")
    @PostMapping("/login")
    public RespBean login(@RequestBody AdminLoginParam adminLoginParam, HttpServletRequest request) {
        return adminService.login(adminLoginParam.getUsername(), adminLoginParam.getPassword(), adminLoginParam.getCode(), request);
    }

    @ApiOperation("获取用户信息")
    @GetMapping("/getAdminInfo")
    public RespBean getAdminInfo(Integer adminId) {
        Admin admin = adminService.getById(adminId);
        List<Role> roles = adminService.getRoles(adminId);
        admin.setRoles(roles);
        return RespBean.success(admin);
    }

}
