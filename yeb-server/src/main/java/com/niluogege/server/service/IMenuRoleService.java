package com.niluogege.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.niluogege.server.pojo.MenuRole;
import com.niluogege.server.pojo.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author niluogege
 * @since 2021-10-29
 */
public interface IMenuRoleService extends IService<MenuRole> {

    RespBean updateMenuRole(Integer rid, Integer[] mid);
}
