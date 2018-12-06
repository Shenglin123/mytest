package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.RoleandUser;

import java.util.List;

/**
 * @author 盛林
 * @date 2018/12/2
 */
public interface IRoleDao {
  Role findRoleByUserId(String id);
  List<Role> findAll();
  void save(Role role);
  void delete(String id);
  void deleterole(String id);
    Role findByid(String id);
    void deletep(String id);
    void addUserRole(RoleandUser roleandUser);
    void deleteAll(String uid);
}
