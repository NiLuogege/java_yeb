package com.niluogege.server.controller;


import com.niluogege.server.pojo.RespBean;
import com.niluogege.server.service.IPositionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author niluogege
 * @since 2021-10-29
 */
@RestController
@RequestMapping("/system/basic/pos")
public class PositionController {

    @Autowired
    private IPositionService positionService;

    @ApiOperation("获取所有职位")
    @GetMapping("/getAllPositions")
    public RespBean getAllPositions() {
        return RespBean.success(positionService.list());
    }

}
