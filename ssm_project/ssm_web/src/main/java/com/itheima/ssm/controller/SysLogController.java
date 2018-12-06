package com.itheima.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.SysLog;
import com.itheima.ssm.service.ISysLogService;
import com.itheima.ssm.service.ISyslogServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SysLogController {

    @Autowired
    private ISyslogServiceI sysLogService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "pagenum",defaultValue ="1") Integer pagenum,
                                @RequestParam(name ="pageSize",defaultValue = "5") Integer pageSize) throws Exception {
        ModelAndView mv=new ModelAndView();
        if(pagenum==1){
            List<SysLog> sysLogList1= sysLogService.findAll(pagenum,pageSize);
            if(sysLogList1.size()<pageSize){
                pageSize=sysLogList1.size();
            }
        }
       List<SysLog> sysLogList= sysLogService.findAll(pagenum,pageSize);
        PageInfo<SysLog> pageInfo = new PageInfo<SysLog>(sysLogList);
       mv.addObject("sysLogs",pageInfo);
       mv.setViewName("syslog-list");
        return mv;
    }
    @RequestMapping("/delete")
    public String delete(HttpServletRequest request){
        String[] ids = request.getParameterValues("ids");
        sysLogService.delete(ids);
        return "redirect:findAll";
    }
}
