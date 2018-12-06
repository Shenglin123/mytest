package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Traveller;

import java.util.List;

/**
 * @author 盛林
 * @date 2018/12/1
 */
public interface Itraveller {
    List<Traveller>  findByid(String id);
}
