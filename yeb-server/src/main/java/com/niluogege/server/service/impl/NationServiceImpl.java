package com.niluogege.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.niluogege.server.mapper.NationMapper;
import com.niluogege.server.pojo.Nation;
import com.niluogege.server.service.INationService;
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
public class NationServiceImpl extends ServiceImpl<NationMapper, Nation> implements INationService {

}
