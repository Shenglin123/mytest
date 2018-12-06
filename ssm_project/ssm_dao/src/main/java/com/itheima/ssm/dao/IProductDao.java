package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Product;

import java.util.List;

/**
 * @author 盛林
 * @date 2018/11/28
 */
public interface IProductDao {
   List<Product> findAll(String name);
   void addProduct(Product product);
   void delete(String id);
   Product findByid(String id);
   void update(Product product);
   void updateStyle(Product product);
}
