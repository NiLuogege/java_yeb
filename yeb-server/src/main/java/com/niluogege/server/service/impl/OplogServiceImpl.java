package com.niluogege.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.niluogege.server.mapper.OplogMapper;
import com.niluogege.server.pojo.Oplog;
import com.niluogege.server.service.IOplogService;
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
public class OplogServiceImpl extends ServiceImpl<OplogMapper, Oplog> implements IOplogService {

}
