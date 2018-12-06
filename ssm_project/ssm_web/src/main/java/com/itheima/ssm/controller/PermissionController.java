package com.itheima.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;
    @RequestMapping("/deletePermission")
    public String deletePermission(String id) throws Exception {
        permissionService.deleteById(id);
        return "redirect:findAll";
    }

    @RequestMapping("/findById")
    public ModelAndView findById(String id) throws Exception {
      Permission permission=  permissionService.findById(id);
      ModelAndView mv=new ModelAndView();
      mv.setViewName("permission-show");
      mv.addObject("permission",permission);
      return mv;
    }

    @RequestMapping("/save")
    public String save(Permission permission) throws Exception {
        permissionService.save(permission);
        return "redirect:findAll";
    }
    @RequestMapping("/findAll")
    public String findAll(@RequestParam(name = "pagenum",defaultValue ="1") Integer pagenum,
                          @RequestParam(name ="pageSize",defaultValue = "5") Integer pageSize, Model model) throws Exception {
        if(pagenum==1){
            List<Permission> permissionList = permissionService.findAll(pagenum,pageSize);
            if(permissionList.size()<pageSize){
                pageSize=permissionList.size();
            }
        }
        List<Permission> permissionList = permissionService.findAll(pagenum,pageSize);
        PageInfo<Permission> pageInfo = new PageInfo(permissionList);
        model.addAttribute("permissionList",pageInfo);
        return "permission-list";
    }
}
