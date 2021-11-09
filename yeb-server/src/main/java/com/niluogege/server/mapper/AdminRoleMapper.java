package com.niluogege.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.niluogege.server.pojo.AdminRole;
import com.niluogege.server.pojo.RespBean;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author niluogege
 * @since 2021-10-29
 */
public interface AdminRoleMapper extends BaseMapper<AdminRole> {

    /**
     * 修改用户角色
     */
    Integer addRole(@Param("amdinId") Integer amdinId, @Param("rids")Integer[] rids);
}
