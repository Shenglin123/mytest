package com.itheima.ssm.service;

import com.github.pagehelper.PageHelper;
import com.itheima.ssm.dao.ISysLogDao;
import com.itheima.ssm.domain.SysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 盛林
 * @date 2018/12/4
 */
@Transactional
@Service
public class ISysLogService implements ISyslogServiceI {
    @Autowired
    private ISysLogDao iSysLogDao;
    public void save(SysLog sysLog) {
    iSysLogDao.save(sysLog);
    }

    @Override
    public void delete(String[] ids) {
        for (String id : ids) {
            iSysLogDao.delete(id);
        }
    }

    public List<SysLog> findAll(Integer pagenum,Integer pageSize) {
        PageHelper.startPage(pagenum,pageSize);
        List<SysLog> all = iSysLogDao.findAll();
        return all;
    }
}
