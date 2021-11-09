package com.niluogege.server.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.niluogege.server.mapper.EmployeeMapper;
import com.niluogege.server.pojo.Employee;
import com.niluogege.server.pojo.RespPageBean;
import com.niluogege.server.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author niluogege
 * @since 2021-10-29
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {


    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 获取员工列表，带分页和筛选
     *
     * @param currentPage
     * @param size
     * @param employee
     * @param deginDateScope
     * @return
     */
    @Override
    public RespPageBean getEmployeeByPage(Integer currentPage, Integer size, Employee employee, LocalDate[] deginDateScope) {
        //开启分页
        Page<Employee> page = new Page<>(currentPage, size);
        IPage<Employee> employeePage = employeeMapper.getEmployeeByPage(page, employee, deginDateScope);
        RespPageBean respPageBean = new RespPageBean(employeePage.getRecords(), employeePage.getTotal());
        return respPageBean;
    }
}
