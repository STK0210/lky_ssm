package com.lky.ssm.service;

import com.lky.ssm.domain.Role;
import com.lky.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @auther likeyu
 * @create 2019-07-28-13:32
 **/
public interface IUserService extends UserDetailsService {

    List<UserInfo> findAll() throws Exception;

    void save(UserInfo userInfo) throws Exception;

    UserInfo findById(String id) throws Exception;

    List<Role> findOtherRole(String userId) throws Exception;

    void addRoleToUser(String userId, String[] roleIds) throws Exception;
}
