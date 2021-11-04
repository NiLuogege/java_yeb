package com.niluogege.server.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author niluogege
 * @since 2021-10-29
 */
@RestController
@RequestMapping("/")
public class DemoController {

    @GetMapping("hello")
    public String hello(){
        return "dasfasf";
    }


    @GetMapping("/employee/advanced/hello")
    public String helloBig(){
        return "/employee/advanced/hello";
    }

}
