package com.niluogege.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.niluogege.server.mapper.EmployeeMapper;
import com.niluogege.server.pojo.Employee;
import com.niluogege.server.pojo.RespBean;
import com.niluogege.server.pojo.RespPageBean;
import com.niluogege.server.service.IEmployeeService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private RabbitTemplate rabbitTemplate;


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

    /**
     * 添加员工
     *
     * @param employee
     * @return
     */
    @Override
    public RespBean addEmp(Employee employee) {
        LocalDate beginContract = employee.getBeginContract();
        LocalDate endContract = employee.getEndContract();

        long days = beginContract.until(endContract, ChronoUnit.DAYS);
        DecimalFormat decimalFormat = new DecimalFormat("##.00");
        employee.setContractTerm(Double.parseDouble(decimalFormat.format(days / 365.00)));

        if (1 == employeeMapper.insert(employee)) {
            //发送消息去发送右键
            rabbitTemplate.convertAndSend("mail.welcome", employee);
            return RespBean.success("添加成功!");

        }
        return RespBean.error("添加失败!");

    }

    /**
     * 获取工号
     */
    @Override
    public RespBean maxWorkId() {
        List<Map<String, Object>> maps = employeeMapper.selectMaps(new QueryWrapper<Employee>().select("max(workID)"));
        return RespBean.success(null, String.format("%08d", Integer.parseInt(maps.get(0).get("max(workID)").toString() + 1)));
    }

    /**
     * 获取员工
     */
    @Override
    public List<Employee> getEmployees(Integer id) {
        return employeeMapper.getEmployees(id);
    }
}
