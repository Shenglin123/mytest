package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Orders;
import org.aspectj.weaver.ast.Or;
import org.springframework.core.annotation.Order;

import java.util.List;

/**
 * @author 盛林
 * @date 2018/11/28
 */
public interface IordersDao {
List<Orders> findAllOrder();
    void addProduct(Order order);
    void delete(String id);
    Orders findByid(String id);
    void deleteTravellerOrder(String id);
    void update(Orders orders);
    void updateStyle(Orders orders);
}
