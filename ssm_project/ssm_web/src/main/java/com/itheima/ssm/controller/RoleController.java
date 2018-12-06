package com.itheima.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.RoleandPermission;
import com.itheima.ssm.service.PermissionService;
import com.itheima.ssm.service.RoleServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 盛林
 * @date 2018/12/3
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleServiceI roleServiceI;
    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/findAll")
    public String findAll(@RequestParam(name = "pagenum",defaultValue ="1") Integer pagenum,
                          @RequestParam(name ="pageSize",defaultValue = "5") Integer pageSize,Model model) {
        if(pagenum==1){
            List<Role> roles1 = roleServiceI.findAll(pagenum,pageSize);
            if(roles1.size()<pageSize){
                pageSize=roles1.size();
            }
        }
        List<Role> roles = roleServiceI.findAll(pagenum,pageSize);
        PageInfo<Role> pageInfo = new PageInfo(roles);
        model.addAttribute("roleList", pageInfo);
        return "role-list";
    }

    @RequestMapping("/save")
    public String save(Role role) {
        roleServiceI.save(role);
        return "redirect:findAll";
    }
    @RequestMapping("/delete")
    public String delete(String id) {
        roleServiceI.delete(id);
        return "redirect:findAll";
    }

    @RequestMapping("/findById")
    public String findByid(String id, Model model) {
        Role byid = roleServiceI.findByid(id);
        model.addAttribute("role", byid);
        return "role-show";

    }
    @RequestMapping("/findRoleByIdAndAllPermission")
    public String findUserByIdAndAllRole(String id, Model model) {
        List<Permission> all = permissionService.findAll1();
        Role byid = roleServiceI.findByid(id);
        List<Permission> permissions1 = byid.getPermissions();
        for (Permission permission : all) {
            for (Permission permission1 : permissions1) {
                if (permission1.getId().equals(permission.getId())) {
                    permission.setFlag(1);
                }
            }
        }
        model.addAttribute("permissionList", all);
        model.addAttribute("role", id);
        return "role-permission-add";
    }

    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(HttpServletRequest request, String roleId) {
        String[] ids = request.getParameterValues("ids");
        List<RoleandPermission> list = new ArrayList<RoleandPermission>();
        if (ids != null) {
            for (String id : ids) {
                RoleandPermission roleandPermission = new RoleandPermission();
                roleandPermission.setPid(id);
                roleandPermission.setRd(roleId);
                list.add(roleandPermission);
            }
            permissionService.deletePandR(roleId);
            for (RoleandPermission roleandPermission : list) {
                permissionService.addPandR(roleandPermission);
            }
            return "redirect:findAll";
        } else {
            permissionService.deletePandR(roleId);
            return "redirect:findAll";
        }

    }
}
