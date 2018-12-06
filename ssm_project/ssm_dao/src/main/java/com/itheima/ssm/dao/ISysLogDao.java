package com.itheima.ssm.dao;

import com.itheima.ssm.domain.SysLog;

import java.util.List;

/**
 * @author 盛林
 * @date 2018/12/4
 */
public interface ISysLogDao {
    List<SysLog> findAll();
   void save(SysLog sysLog);

    void delete(String id);
}
