package com.niluogege.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.niluogege.server.mapper.RoleMapper;
import com.niluogege.server.pojo.Role;
import com.niluogege.server.service.IRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author niluogege
 * @since 2021-10-29
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
