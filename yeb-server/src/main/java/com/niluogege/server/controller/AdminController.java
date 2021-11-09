package com.niluogege.server.controller;


import com.niluogege.server.pojo.Admin;
import com.niluogege.server.pojo.RespBean;
import com.niluogege.server.pojo.Role;
import com.niluogege.server.service.IAdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author niluogege
 * @since 2021-10-29
 */
@RestController
@RequestMapping("/system/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @ApiOperation("通过用户id 获取对应角色（权限）")
    @GetMapping("/getRoles")
    public RespBean getRoles(Integer adminId) {
        List<Role> roles = adminService.getRoles(adminId);
        return RespBean.success(roles);
    }

    @GetMapping("/")
    @ApiOperation("根据 名字 模糊查询管理员 列表")
    public List<Admin> getAllAdmins(String keyword){
        return  adminService.getAllAdmins(keyword);
    }
}
