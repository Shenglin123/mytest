package com.itheima.ssm.service;

import com.itheima.ssm.domain.Product;

import java.util.List;

/**
 * @author 盛林
 * @date 2018/11/28
 */
public interface ProductService {
    List<Product> findAll(Integer pagenum,Integer pageSize,String pname);
    void addProduct(Product product);
    void deletes(String[] cbs);
    Product findByid(String id);
    void update(Product product);
    void updateStyle(String[] id);
    void updateStyle2(String[] id);
}
