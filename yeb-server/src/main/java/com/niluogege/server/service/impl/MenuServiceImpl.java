package com.niluogege.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.niluogege.server.mapper.MenuMapper;
import com.niluogege.server.pojo.Menu;
import com.niluogege.server.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author niluogege
 * @since 2021-10-29
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 通过 用户id 获取菜单
     * @param adminId
     */
    @Override
    public List<Menu> getMenusByAdminId(Integer adminId) {
      return   menuMapper.getMenusByAdminId(adminId);
    }

    /**
     * 获取角色对应的菜单列表
     * @return
     */
    @Override
    public List<Menu> getAllMenuWithRole() {
        return menuMapper.getAllMenuWithRole();
    }


    @Override
    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }


}
