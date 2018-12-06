package com.itheima.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Product;
import com.itheima.ssm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

/**
 * @author 盛林
 * @date 2018/11/28
 */
@Controller
@RequestMapping("/product")
public class ProductController {
@Autowired
    private ProductService productService;

@RequestMapping("/findAll")
public String findAll(@RequestParam(name = "pagenum",defaultValue ="1") Integer pagenum,
                      @RequestParam(name ="pageSize",defaultValue = "5") Integer pageSize,Model model,String pname){

    if(pagenum==1){
        List<Product> list1= productService.findAll(pagenum,pageSize,pname);
        if(list1.size()<pageSize){
            pageSize=list1.size();
        }
    }
    List<Product> list = productService.findAll(pagenum,pageSize,pname);
    PageInfo<Product> pageInfo = new PageInfo(list);
    model.addAttribute("productList",pageInfo);
    model.addAttribute("pname",pname);
    return "product-list";
}
@RequestMapping("/save")
    public String save(Product product){
    productService.addProduct(product);
    return "redirect:findAll";
}
@RequestMapping("/update1")
public String update1(Product product,Integer pagenum,Integer pageSize){
    productService.update(product);
    return "redirect:findAll?pagenum="+pagenum+"&pageSize="+pageSize;
}

@RequestMapping("/delProduct")
public String delUser(String[] id){
    productService.deletes(id);
    return "redirect:findAll";
}
@RequestMapping("/update")
    public String update(String id,Model model,Integer pagenum,Integer pageSize){
    Product byid = productService.findByid(id);
    model.addAttribute("product",byid);
    PageInfo pageInfo = new PageInfo();
    pageInfo.setPageNum(pagenum);
    pageInfo.setPageSize(pageSize);
    model.addAttribute("pageinfo",pageInfo);
    return "product-update";
}
@RequestMapping("/updateSytle")
    public String updateSytle(Integer pagenum,Integer pageSize,String[] id){
   productService.updateStyle(id);
    return "redirect:findAll?pagenum="+pagenum+"&pageSize="+pageSize;
}
@RequestMapping("/updateSytle2")
    public String updateSytle2(Integer pagenum,Integer pageSize,String[] id){
        productService.updateStyle2(id);
        return "redirect:findAll?pagenum="+pagenum+"&pageSize="+pageSize;
    }
}
