package com.lky.ssm.service;

import com.lky.ssm.domain.SysLog;

import java.util.List;

/**
 * @auther likeyu
 * @create 2019-07-30-11:09
 **/
public interface ISysLogService {

    void save(SysLog sysLog) throws Exception;

    List<SysLog> findAll() throws Exception;
}
