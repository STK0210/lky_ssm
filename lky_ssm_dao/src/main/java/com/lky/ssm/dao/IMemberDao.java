package com.lky.ssm.dao;

import com.lky.ssm.domain.Member;
import org.apache.ibatis.annotations.Select;

/**
 * @auther likeyu
 * @create 2019-07-26-20:56
 **/
public interface IMemberDao {

    @Select("select * from member where id =#{id}")
    Member findById(String id) throws Exception;

}
