package com.niluogege.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.niluogege.server.pojo.Role;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author niluogege
 * @since 2021-10-29
 */
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 通过用户id 获取对应角色（权限）
     */
    List<Role> getRoles(Integer adminId);
}
