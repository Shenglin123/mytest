package com.itheima.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.domain.Product;
import com.itheima.ssm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 盛林
 * @date 2018/11/28
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrderService orderService;
    @RequestMapping("/findAll")
    public String findAll(@RequestParam(name = "pagenum",defaultValue ="1") Integer pagenum,
                          @RequestParam(name ="pageSize",defaultValue = "5") Integer pageSize ,Model model){
        if(pagenum==1){
            List<Orders> list1 = orderService.findAllOrders(pagenum,pageSize);
            if(list1.size()<pageSize){
                pageSize=list1.size();
            }
        }
        List<Orders> list = orderService.findAllOrders(pagenum,pageSize);
        PageInfo<Orders> pageInfo = new PageInfo(list);
        model.addAttribute("ordersList",pageInfo);
        return "orders-list";
    }
    @RequestMapping("/delOrder")
    public String delete(String[] ids){
        orderService.delete(ids);
        return "redirect:findAll";
    }
    @RequestMapping("/findById")
    public String findByid(String id, Model model){
        Orders byid = orderService.findByid(id);
        model.addAttribute("orders",byid);
        return "orders-show";
    }
    @RequestMapping("/updateById")
    public String updateByid(String id,Model model,Integer pagenum,Integer pageSize){
        Orders order = orderService.findByid(id);
        model.addAttribute("orders",order);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNum(pagenum);
        pageInfo.setPageSize(pageSize);
        model.addAttribute("pageinfo",pageInfo);
        return "orders-update";
    }
    @RequestMapping("/update1")
    public String update1(Orders orders,Integer pagenum,Integer pageSize){
           orderService.update(orders);
        return "redirect:findAll?pagenum="+pagenum+"&pageSize="+pageSize;
    }
    @RequestMapping("/updateSytle")
    public String updateSytle(Integer pagenum,Integer pageSize,String[] ids){
       orderService.updateStyle(ids);
        return "redirect:findAll?pagenum="+pagenum+"&pageSize="+pageSize;
    }
    @RequestMapping("/updateSytle2")
    public String updateSytle2(Integer pagenum,Integer pageSize,String[] ids){
        orderService.updateStyle2(ids);
        return "redirect:findAll?pagenum="+pagenum+"&pageSize="+pageSize;
    }
}

