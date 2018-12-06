package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.RoleandPermission;

import java.util.List;

/**
 * @author 盛林
 * @date 2018/12/3
 */
public interface Ipermission {
    List<Permission> findByid(String id);
    List<Permission> findAll();
   void addPandR(RoleandPermission roleandPermission);
   void deletePandR(String pid);
    void save(Permission permission);
    Permission findById(String id);
    void delete(String id);
    void deleteRandP(String id);
}
