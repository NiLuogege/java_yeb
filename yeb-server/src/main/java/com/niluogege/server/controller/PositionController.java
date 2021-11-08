package com.niluogege.server.controller;


import com.niluogege.server.pojo.Position;
import com.niluogege.server.pojo.RespBean;
import com.niluogege.server.service.IPositionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;

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
    @GetMapping("/")
    public RespBean getAllPositions() {
        return RespBean.success(positionService.list());
    }

    @ApiOperation("添加职位")
    @PostMapping("/")
    public RespBean addPosition(@RequestBody Position position){
        if (position.getName().isEmpty()){
            return RespBean.error("名称非法");
        }
        position.setCreateDate(LocalDateTime.now());
        boolean saved = positionService.save(position);
        if (saved){
            return RespBean.success("添加成功");
        }

        return RespBean.error("添加失败");
    }

    @ApiOperation("更新职位")
    @PutMapping("/")
    public RespBean updatePosition(@RequestBody Position position){
       if (positionService.updateById(position)){
           return RespBean.success("更新成功");
       }

       return RespBean.error("更新失败");
    }


    @ApiOperation("删除职位信息")
    @DeleteMapping("/{id}")
    public RespBean deletePosition(@PathVariable Integer id){
        if (positionService.removeById(id)){
            return RespBean.success("删除成功");
        }

        return RespBean.error("删除失败");
    }


    @ApiOperation("批量删除职位信息")
    @DeleteMapping("/")
    public RespBean deletePositionByIds(Integer[] ids){
        if (positionService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("删除成功");
        }

        return RespBean.error("删除失败");
    }

}
