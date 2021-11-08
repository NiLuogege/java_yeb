package com.niluogege.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.niluogege.server.mapper.MenuRoleMapper;
import com.niluogege.server.pojo.MenuRole;
import com.niluogege.server.pojo.RespBean;
import com.niluogege.server.service.IMenuRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author niluogege
 * @since 2021-10-29
 */
@Service
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements IMenuRoleService {
    @Autowired
    private MenuRoleMapper menuRoleMapper;

    /**
     * 更新角色菜单（为了简单进行如下操作）
     * 1. 删除该角色对应的所有菜单
     */
    @Transactional
    @Override
    public RespBean updateMenuRole(Integer rid, Integer[] mid) {
        menuRoleMapper.delete(new QueryWrapper<MenuRole>().eq("rid", rid));
        if (mid == null || mid.length <= 0) {
            return RespBean.success("更新成功");
        }

        Integer result = menuRoleMapper.insertRecord(rid, mid);
        if (result == mid.length) {
            return RespBean.success("更新成功");
        }

        return RespBean.error("更新失败");
    }
}
