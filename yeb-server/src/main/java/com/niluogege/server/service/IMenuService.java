package com.niluogege.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.niluogege.server.pojo.Menu;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author niluogege
 * @since 2021-10-29
 */
public interface IMenuService extends IService<Menu> {


    /**
     * 通过 用户id 获取菜单
     */
    List<Menu> getMenusByAdminId(Integer adminId);


    /**
     * 获取角色对应的菜单列表
     * @return
     */
    public List<Menu> getAllMenuWithRole();

    /**
     * 获取所有菜单
     */
    List<Menu> getAllMenus();
}
