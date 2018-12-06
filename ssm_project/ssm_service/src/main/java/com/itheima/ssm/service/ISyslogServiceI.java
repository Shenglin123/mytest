package com.itheima.ssm.service;

import com.itheima.ssm.domain.SysLog;

import java.util.List;

/**
 * @author 盛林
 * @date 2018/12/4
 */
public interface ISyslogServiceI {
    List<SysLog> findAll(Integer pagenum, Integer pageSize);
    void save(SysLog sysLog);

    void delete(String[] ids);
}
