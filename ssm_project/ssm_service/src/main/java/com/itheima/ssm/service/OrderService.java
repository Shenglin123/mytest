package com.itheima.ssm.service;

import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.domain.Product;
import org.springframework.core.annotation.Order;

import java.util.List;

/**
 * @author 盛林
 * @date 2018/11/28
 */
public interface OrderService {
    List<Orders> findAllOrders(Integer pagenum,Integer pageSize);
    void addOrder(Order order);
    void delete(String[] orders);
    Orders findByid(String id);
    void update(Orders orders);

    void updateStyle(String[] id);

    void updateStyle2(String[] id);
}
