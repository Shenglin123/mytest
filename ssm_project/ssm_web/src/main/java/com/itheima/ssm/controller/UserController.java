package com.itheima.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.RoleandUser;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.RoleServiceI;
import com.itheima.ssm.service.userService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 盛林
 * @date 2018/12/2
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private userService userService1;
    @Autowired
    private RoleServiceI roleServiceI;

    //查询指定id的用户
    @RequestMapping("/findById")
    public ModelAndView findById(String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService1.findById(id);
        mv.addObject("user", userInfo);
        mv.setViewName("user-show");
        return mv;
    }

    //用户添加
    @RequestMapping("/save")
    public String save(UserInfo userInfo) throws Exception {
        userService1.save(userInfo);
        return "redirect:findAll";
    }
    @RolesAllowed("ADMIN")
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "pagenum",defaultValue ="1") Integer pagenum,
                                @RequestParam(name ="pageSize",defaultValue = "5") Integer pageSize) throws Exception {
        ModelAndView mv = new ModelAndView();
        if(pagenum==1){
            List<UserInfo> userList1 = userService1.findAll(pagenum,pageSize);
            if(userList1.size()<pageSize){
                pageSize=userList1.size();
            }
        }
        List<UserInfo> userList = userService1.findAll(pagenum,pageSize);
        PageInfo<UserInfo> pageInfo = new PageInfo<UserInfo>(userList);
        mv.addObject("userList",pageInfo);
        mv.setViewName("user-list");
        return mv;
    }

    @RequestMapping("/delete")
    public String delete(String id) throws Exception {
        userService1.delete(id);
        return "redirect:findAll";
    }

    @RequestMapping("/update")
    public String update(String id, Model model) throws Exception {
        UserInfo userInfo = userService1.findById(id);
        model.addAttribute("userinfo", userInfo);
        return "user-update";
    }
    @RequestMapping("/updatepsd")
    public String updatepsd(String username,Model model){
      UserInfo userInfo  =  userService1.findByUsername(username);
      model.addAttribute("userinfo",userInfo);
      return "user-update";
    }

    @RequestMapping("/update1")
    public String update1(UserInfo userInfo) {
        userService1.update(userInfo);
        return "redirect:findAll";
    }
    @RequestMapping("/findUserByIdAndAllRole")
    public String findUserByIdAndAllRole(String id, Model model) throws Exception {
        List<Role> all = roleServiceI.findAll1();
        UserInfo byId = userService1.findById(id);
        List<Role> roles = byId.getRoles();
        for (Role role : all) {
            for (Role role1 : roles) {
                if(role1.getId().equals(role.getId())){
                    role.setFlag(1);
                }
            }
        }
        model.addAttribute("roleList", all);
        model.addAttribute("user", id);
        return "user-role-add";
    }

    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(HttpServletRequest request, String userId) {
        String[] ids = request.getParameterValues("ids");
        List<RoleandUser> list = new ArrayList<RoleandUser>();
        if (ids != null) {
            for (String id : ids) {
                RoleandUser roleandUser = new RoleandUser();
                roleandUser.setRid(id);
                roleandUser.setUid(userId);
                list.add(roleandUser);
            }
            roleServiceI.deleteAll(userId);
            for (RoleandUser roleandUser : list) {
                roleServiceI.addUserRole(roleandUser);
            }
            return "redirect:findAll";
        }else {
                roleServiceI.deleteAll(userId);
            return "redirect:findAll";
        }
    }
}