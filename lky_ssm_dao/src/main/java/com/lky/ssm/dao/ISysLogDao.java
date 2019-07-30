package com.lky.ssm.dao;

import com.lky.ssm.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @auther likeyu
 * @create 2019-07-30-11:11
 **/
public interface ISysLogDao {

    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void save(SysLog sysLog);

    @Select("select * from sysLog")
    List<SysLog> findAll() throws Exception;
}
