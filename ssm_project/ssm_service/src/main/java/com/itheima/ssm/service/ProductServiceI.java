package com.itheima.ssm.service;

import com.github.pagehelper.PageHelper;
import com.itheima.ssm.dao.IProductDao;
import com.itheima.ssm.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 盛林
 * @date 2018/11/28
 */
@Transactional
@Service
public class ProductServiceI implements ProductService {
    @Autowired
    private IProductDao iProductDao;
    public List<Product> findAll(Integer pagenum,Integer pageSize,String pname) {
        PageHelper.startPage(pagenum,pageSize);
        return iProductDao.findAll(pname);
    }


    public void addProduct(Product product) {
        iProductDao.addProduct(product);
    }


    public void deletes(String[] cbs) {
        for (String cb : cbs) {
            iProductDao.delete(cb);
        }
    }

    public Product findByid(String id) {
          return iProductDao.findByid(id);
    }

    public void update(Product product) {
        iProductDao.update(product);
    }

    public void updateStyle(String[] id) {
        for (String s : id) {
            Product byid = iProductDao.findByid(s);
            byid.setProductStatus(1);
            iProductDao.updateStyle(byid);
        }

    }
    public void updateStyle2(String[] id) {
        for (String s : id) {
            Product byid = iProductDao.findByid(s);
            byid.setProductStatus(0);
            iProductDao.updateStyle(byid);
        }
    }
}
