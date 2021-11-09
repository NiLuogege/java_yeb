package com.niluogege.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.niluogege.server.pojo.Employee;
import com.niluogege.server.pojo.RespPageBean;

import java.time.LocalDate;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author niluogege
 * @since 2021-10-29
 */
public interface IEmployeeService extends IService<Employee> {

    /**
     * 获取员工列表，带分页和筛选
     * @param currentPage
     * @param size
     * @param employee
     * @param deginDateScope
     * @return
     */
    RespPageBean getEmployeeByPage(Integer currentPage, Integer size, Employee employee, LocalDate[] deginDateScope);
}
