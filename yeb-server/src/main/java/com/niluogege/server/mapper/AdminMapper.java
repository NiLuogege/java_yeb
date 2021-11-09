package com.niluogege.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.niluogege.server.pojo.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author niluogege
 * @since 2021-10-29
 */
public interface AdminMapper extends BaseMapper<Admin> {

    /**
     * 根据 名字 模糊查询管理员 列表
     */
    List<Admin> getAllAdmins(@Param("name") String keyword);
}
