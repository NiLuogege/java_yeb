package com.niluogege.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.niluogege.server.mapper.MenuMapper;
import com.niluogege.server.pojo.Menu;
import com.niluogege.server.service.IMenuService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author niluogege
 * @since 2021-10-29
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

}
