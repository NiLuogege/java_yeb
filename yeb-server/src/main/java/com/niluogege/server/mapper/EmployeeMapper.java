package com.niluogege.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.niluogege.server.pojo.Employee;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author niluogege
 * @since 2021-10-29
 */
public interface EmployeeMapper extends BaseMapper<Employee> {

    /**
     * 获取员工列表，带分页和筛选
     *
     * @param page
     * @param employee
     * @param deginDateScope
     */
    IPage<Employee> getEmployeeByPage(@Param("page") Page<Employee> page,
                                      @Param("employee") Employee employee,
                                      @Param("deginDateScope") LocalDate[] deginDateScope);
}
