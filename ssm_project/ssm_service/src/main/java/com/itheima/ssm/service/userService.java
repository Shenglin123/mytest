package com.itheima.ssm.service;

import com.itheima.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @author 盛林
 * @date 2018/12/2
 */
public interface userService extends UserDetailsService{
    List<UserInfo> findAll(Integer pagenum,Integer pageSize) throws Exception;

    void save(UserInfo userInfo) throws Exception;

    UserInfo findById(String id) throws Exception;

    void delete(String id);

    void update(UserInfo userInfo);

    UserInfo findByUsername(String username);
}
