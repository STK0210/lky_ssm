package com.lky.ssm.service;

import com.lky.ssm.domain.Permission;
import com.lky.ssm.domain.Role;
import com.lky.ssm.domain.UserInfo;

import java.util.List;

/**
 * @auther likeyu
 * @create 2019-07-28-13:32
 **/
public interface IRoleService {

    List<Role> findAll() throws Exception;

    void save(Role role) throws Exception;

    Role findById(String roleId) throws Exception;

    List<Permission> findOtherPermissions(String roleId) throws Exception;

    void addPermissionToRole(String roleId, String[] permissionIds) throws Exception;
}
