package com.itheima.ssm.service;

import com.github.pagehelper.PageHelper;
import com.itheima.ssm.dao.IRoleDao;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.RoleandUser;
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
public class RoleService implements RoleServiceI {
    @Autowired
    private IRoleDao iRoleDao;
    @Override
    public List<Role> findAll(Integer pagenum,Integer pageSize) {
        PageHelper.startPage(pagenum,pageSize);
        List<Role> roles = iRoleDao.findAll();
        return roles;
    }

    @Override
    public void save(Role role) {
        iRoleDao.save(role);
    }
    @Override
    public void delete(String id) {
        iRoleDao.deleterole(id);
        iRoleDao.deletep(id);
        iRoleDao.delete(id);
    }

    @Override
    public Role findByid(String id) {
        Role byid = iRoleDao.findByid(id);
        return byid;
    }

    @Override
    public Role findRoleByUserId(String id) {
        return iRoleDao.findRoleByUserId(id);
    }

    @Override
    public void addUserRole(RoleandUser roleandUser) {
        iRoleDao.addUserRole(roleandUser);
    }

    @Override
    public void deleteAll(String uid) {
        iRoleDao.deleteAll(uid);
    }

    @Override
    public List<Role> findAll1() {
        return iRoleDao.findAll();
    }


}
