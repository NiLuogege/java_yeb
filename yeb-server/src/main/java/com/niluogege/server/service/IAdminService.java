package com.niluogege.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.niluogege.server.pojo.Admin;
import com.niluogege.server.pojo.RespBean;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author niluogege
 * @since 2021-10-29
 */
public interface IAdminService extends IService<Admin> {


    /**
     * 登录之后返回token
     * @param code
     * @param username
     * @param password
     * @return
     */
    RespBean login( String username, String password,String code, HttpServletRequest request);


    /**
     * 通过 username 获取用户信息
     */
    Admin getAdminByUserName(String username);
}
