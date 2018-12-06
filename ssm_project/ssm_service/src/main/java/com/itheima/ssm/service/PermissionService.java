package com.itheima.ssm.service;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.RoleandPermission;

import java.util.List;

/**
 * @author 盛林
 * @date 2018/12/3
 */
public interface PermissionService {
    List<Permission> findAll(Integer pagenum,Integer pageSize);
    void addPandR(RoleandPermission roleandPermission);
    void deletePandR(String pid);
    void save(Permission permission);
    Permission findById(String id);
    void deleteById(String id);
    List<Permission> findAll1();
}
