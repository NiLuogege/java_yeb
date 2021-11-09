package com.niluogege.server.controller;


import com.niluogege.server.pojo.Employee;
import com.niluogege.server.pojo.Nation;
import com.niluogege.server.pojo.RespPageBean;
import com.niluogege.server.service.IEmployeeService;
import com.niluogege.server.service.INationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author niluogege
 * @since 2021-10-29
 */
@Api(tags = "EmployeeControllerf")
@RestController
@RequestMapping("/employee/basic")
public class EmployeeController {

    @Autowired
    private INationService nationService;

    @Autowired
    private IEmployeeService employeeService;

    @ApiOperation(value = "获取所有民族")
    @GetMapping("/nations")
    public List<Nation> getAllNations() {
        return nationService.list();
    }

    @ApiOperation("获取员工列表，带分页和筛选")
    @GetMapping("/")
    public RespPageBean getEmployeeByPage(@RequestParam(defaultValue = "1") Integer currentPage,
                                          @RequestParam(defaultValue = "10") Integer size,
                                          Employee employee,
                                          LocalDate[] deginDateScope) {


        return employeeService.getEmployeeByPage(currentPage,size,employee,deginDateScope);

    }

}
