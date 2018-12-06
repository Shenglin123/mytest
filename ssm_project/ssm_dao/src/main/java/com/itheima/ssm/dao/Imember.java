package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Member;

import java.util.List;

/**
 * @author 盛林
 * @date 2018/12/1
 */
public interface Imember {
    List<Member> findByid(String id);
}
