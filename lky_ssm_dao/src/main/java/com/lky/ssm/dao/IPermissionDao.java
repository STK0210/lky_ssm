package com.lky.ssm.dao;

import com.lky.ssm.domain.Permission;
import com.lky.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @auther likeyu
 * @create 2019-07-28-13:39
 **/
public interface IPermissionDao {

    @Select("select * from permission where id in (select permissionId from role_permission where roleId = #{id})")
    List<Permission> findPermissionByRoleId(String id) throws Exception;

    @Select("select * from permission")
    List<Permission> findAll() throws Exception;

    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void save(Permission permission) throws Exception;
}
