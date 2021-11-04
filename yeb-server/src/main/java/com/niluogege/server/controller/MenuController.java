package com.niluogege.server.controller;


import com.niluogege.server.pojo.Menu;
import com.niluogege.server.pojo.RespBean;
import com.niluogege.server.service.IAdminService;
import com.niluogege.server.service.IMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
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
@RequestMapping("/menu")
@Api(tags = "MenuController")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation("通过 用户id 获取菜单")
    @GetMapping("/getMenusByAdminId")
    public RespBean getMenusByAdminId(Integer adminId) {

        BoundValueOperations<String, List<Menu>> menuOperations = redisTemplate.boundValueOps("menus_" + adminId);
        List<Menu> menus = (List<Menu>) menuOperations.get();
        System.out.println("isEmpty="+CollectionUtils.isEmpty(menus));
        if (CollectionUtils.isEmpty(menus)) {
            menus = menuService.getMenusByAdminId(adminId);
            menuOperations.set(menus);
        }
        return RespBean.success(menus);
    }

}
