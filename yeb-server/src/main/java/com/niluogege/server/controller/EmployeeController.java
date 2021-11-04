package com.niluogege.server.controller;


import com.niluogege.server.pojo.Nation;
import com.niluogege.server.service.INationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author niluogege
 * @since 2021-10-29
 */
@Api(tags = "EmployeeController")
@RestController
@RequestMapping("/employee/basic")
public class EmployeeController {

    @Autowired
    private INationService nationService;

    @ApiOperation(value = "获取所有民族")
    @GetMapping("/nations")
    public List<Nation> getAllNations(){
        return nationService.list();
    }

}
