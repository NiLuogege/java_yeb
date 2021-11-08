package com.niluogege.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.niluogege.server.pojo.MenuRole;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author niluogege
 * @since 2021-10-29
 */
public interface MenuRoleMapper extends BaseMapper<MenuRole> {

    Integer insertRecord(@Param("rid")Integer rid, @Param("mids") Integer[] mid);
}
