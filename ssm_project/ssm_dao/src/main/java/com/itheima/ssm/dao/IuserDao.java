package com.itheima.ssm.dao;

import com.itheima.ssm.domain.UserInfo;

import java.util.List;

/**
 * @author 盛林
 * @date 2018/12/2
 */
public interface IuserDao {
     UserInfo findByUsername(String username);
    void save(UserInfo userInfo);
    List<UserInfo> findAll();
    UserInfo findById(String id);
    void delete(String id);
    void deleteuser(String id);
    void update(UserInfo userInfo);
}
