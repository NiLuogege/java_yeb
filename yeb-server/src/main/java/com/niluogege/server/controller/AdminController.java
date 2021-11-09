package com.niluogege.server.controller;


import com.niluogege.server.pojo.Admin;
import com.niluogege.server.pojo.RespBean;
import com.niluogege.server.pojo.Role;
import com.niluogege.server.service.IAdminService;
import com.niluogege.server.service.IRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private IRoleService roleService;

    @ApiOperation("通过用户id 获取对应角色（权限）")
    @GetMapping("/getRoles")
    public RespBean getRoles(Integer adminId) {
        List<Role> roles = adminService.getRoles(adminId);
        return RespBean.success(roles);
    }


    @ApiOperation("获取所有角色")
    @GetMapping("/roles")
    public RespBean getAllRoles(){
        return RespBean.success(roleService.list());
    }

    @GetMapping("/")
    @ApiOperation("根据 名字 模糊查询管理员 列表")
    public List<Admin> getAllAdmins(String keyword) {
        return adminService.getAllAdmins(keyword);
    }

    @ApiOperation("更新管理员")
    @PutMapping("/")
    public RespBean updateAdmin(@RequestBody Admin admin) {
        if (adminService.updateById(admin)) {
            return RespBean.success("更新成功");
        }

        return RespBean.error("更新失败");
    }

    @ApiOperation("删除操作员")
    @DeleteMapping("/{id}")
    public RespBean deleteAdmin(@PathVariable("id") String id1) {
        if (adminService.removeById(id1)) {
            return RespBean.success("删除成功");
        }

        return RespBean.error("删除失败");
    }

    @PutMapping("/role")
    @ApiOperation("修改用户角色")
    public  RespBean updateAdminRole(Integer amdinId , Integer[] rids){
        return adminService.updateAdminRole(amdinId,rids);
    }
}
