package com.itheima.ssm.service;

import com.github.pagehelper.PageHelper;
import com.itheima.ssm.dao.IordersDao;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 盛林
 * @date 2018/11/28
 */
@Transactional
@Service
public class OrderServiceI implements OrderService {
    @Autowired
    private IordersDao iordersDao;
    public List<Orders> findAllOrders(Integer pagenum,Integer pageSize) {
        PageHelper.startPage(pagenum,pageSize);
        return iordersDao.findAllOrder();
    }

    public void addOrder(Order order) {

    }

    public void delete(String[] orders) {
        for (String order : orders) {
            iordersDao.deleteTravellerOrder(order);
            iordersDao.delete(order);
        }
    }

    public Orders findByid(String id) {
        return iordersDao.findByid(id);
    }

    public void update(Orders orders) {
     iordersDao.update(orders);
    }

    public void updateStyle(String[] id) {
        for (String s : id) {
            Orders orders = iordersDao.findByid(s);
            orders.setOrderStatus(1);
            iordersDao.updateStyle(orders);
        }
    }

    public void updateStyle2(String[] id) {
        for (String s : id) {
            Orders orders = iordersDao.findByid(s);
            orders.setOrderStatus(0);
            iordersDao.updateStyle(orders);
        }
    }

}
