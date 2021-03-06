package com.niluogege.server.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.niluogege.server.pojo.Menu;
import com.niluogege.server.pojo.MenuRole;
import com.niluogege.server.pojo.RespBean;
import com.niluogege.server.pojo.Role;
import com.niluogege.server.service.IMenuRoleService;
import com.niluogege.server.service.IMenuService;
import com.niluogege.server.service.IRoleService;
import com.niluogege.server.service.impl.RoleServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "PermissController")
@RestController
@RequestMapping("/system/basic/permiss")
public class PermissController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IMenuService menuService;
    @Autowired
    private IMenuRoleService menuRoleService;

    @ApiOperation("获取所有角色")
    @GetMapping("/")
    public RespBean getAllRoles() {
        return RespBean.success(roleService.list());
    }

    @ApiOperation("添加角色")
    @PostMapping("/role")
    public RespBean addRole(@RequestBody Role role) {

        String name = role.getName();
        if (!name.startsWith("ROLE_")) {
            role.setName("ROLE_" + name);
        }

        boolean save = roleService.save(role);
        if (save) {
            return RespBean.success("添加成功");
        }

        return RespBean.error("添加失败");
    }

    @ApiOperation("删除角色")
    @DeleteMapping("/role/{id}")
    public RespBean deleteRole(@PathVariable Integer id) {
        if (roleService.removeById(id)) {
            return RespBean.success("删除成功");
        }
        return RespBean.success("删除失败");
    }


    @ApiOperation("获取所有菜单")
    @GetMapping("/menus")
    public List<Menu> getAllMenus() {
        return menuService.getAllMenus();
    }


    @GetMapping("mid/{rid}")
    @ApiOperation("根据角色id查询菜单id")
    public List<Integer> getMidByRid(Integer rid) {
        return menuRoleService.list(new QueryWrapper<MenuRole>().eq("rid", rid))
                .stream()
                .map(MenuRole::getMid)
                .collect(Collectors.toList());
    }


    @ApiOperation("更新角色菜单")
    @PutMapping("/")
    public RespBean updateMenuRole(Integer rid,Integer[] mid){
       return menuRoleService.updateMenuRole(rid,mid);
    }
}
