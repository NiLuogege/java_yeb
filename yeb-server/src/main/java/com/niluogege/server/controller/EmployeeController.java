package com.niluogege.server.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.niluogege.server.pojo.Employee;
import com.niluogege.server.pojo.Nation;
import com.niluogege.server.pojo.RespBean;
import com.niluogege.server.pojo.RespPageBean;
import com.niluogege.server.service.IEmployeeService;
import com.niluogege.server.service.INationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
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


        return employeeService.getEmployeeByPage(currentPage, size, employee, deginDateScope);

    }

    @ApiOperation("添加员工")
    @PostMapping("/")
    public RespBean addEmp(@RequestBody Employee employee) {
        return employeeService.addEmp(employee);
    }


    @ApiOperation("获取工号")
    @GetMapping("/maxWorkId")
    public RespBean maxWorkId() {
        return employeeService.maxWorkId();
    }

    /**
     * 添加 produces = "application/octet-stream" 标识以 流的形式返回
     */
    @ApiOperation("导出员工")
    @GetMapping(value = "/download", produces = "application/octet-stream")
    public void downloadEmployee(Integer id, HttpServletResponse response) {
        List<Employee> employees = employeeService.getEmployees(id);

        ExportParams exportParams = new ExportParams("员工表", "员工表", ExcelType.HSSF);
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, Employee.class, employees);
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
