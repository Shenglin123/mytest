package com.itheima.ssm.service;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.RoleandUser;

import java.util.List;

/**
 * @author 盛林
 * @date 2018/12/3
 */
public interface RoleServiceI {
    List<Role> findAll(Integer pagenum,Integer pageSize);

    void save(Role role);

    void delete(String id);

    Role findByid(String id);
    Role findRoleByUserId(String id);
    void addUserRole(RoleandUser roleandUser);
    void deleteAll(String uid);

    List<Role> findAll1();
}
