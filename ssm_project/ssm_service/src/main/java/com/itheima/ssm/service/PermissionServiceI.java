package com.itheima.ssm.service;

import com.github.pagehelper.PageHelper;
import com.itheima.ssm.dao.Ipermission;
import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.RoleandPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 盛林
 * @date 2018/12/3
 */
@Transactional
@Service
public class PermissionServiceI implements PermissionService {
    @Autowired
    private Ipermission ipermission;
    @Override
    public List<Permission> findAll(Integer pagenum,Integer pageSize) {
        PageHelper.startPage(pagenum,pageSize);
        List<Permission> all = ipermission.findAll();
        return all;
    }

    @Override
    public void addPandR(RoleandPermission roleandPermission) {
        ipermission.addPandR(roleandPermission);
    }

    @Override
    public void deletePandR(String rid) {
   ipermission.deletePandR(rid);
    }

    @Override
    public void save(Permission permission) {
        ipermission.save(permission);
    }

    @Override
    public Permission findById(String id) {
        Permission byId = ipermission.findById(id);
        return byId;
    }

    @Override
    public void deleteById(String id) {
        ipermission.deleteRandP(id);
           ipermission.delete(id);
    }

    @Override
    public List<Permission> findAll1() {
        return ipermission.findAll();
    }
}
