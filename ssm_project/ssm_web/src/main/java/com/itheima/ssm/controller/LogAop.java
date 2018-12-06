package com.itheima.ssm.controller;
import com.itheima.ssm.domain.SysLog;
import com.itheima.ssm.service.ISyslogServiceI;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
@Component
@Aspect
public class LogAop {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ISyslogServiceI sysLogService;

    private Date visitTime; //开始时间
    private Class clazz; //访问的类
    private Method method;//访问的方法

    //前置通知  主要是获取开始时间，执行的类是哪一个，执行的是哪一个方法
    @Before("execution(* com.itheima.ssm.controller.*.*(..))")
    public void doBefore() throws NoSuchMethodException {
        visitTime = new Date();//当前时间就是开始访问的时间
    }

    //后置通知
    @After("execution(* com.itheima.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        clazz = jp.getTarget().getClass(); //具体要访问的类
        String methodName = jp.getSignature().getName(); //获取访问的方法的名称
        Object[] args = jp.getArgs();//获取访问的方法的参数
        //获取具体执行的方法的Method对象
        if (clazz != SysLogController.class) {
           RequestMapping requestMapping = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
           if(requestMapping!=null){
            String[] value1 = requestMapping.value();
            String s2 = value1[0];
            if (args == null || args.length == 0) {
                method = clazz.getMethod(methodName); //只能获取无参数的方法
            } else {
                Class[] classArgs = new Class[args.length];
                for (int i = 0; i < args.length; i++) {
                    if(args[i]==null){
                        classArgs[i] = new String().getClass();
                    }else if(args[i] instanceof HttpServletRequest){
                        classArgs[i] = HttpServletRequest.class;
                    }
                   else if (args[i] instanceof BindingAwareModelMap) {
                        classArgs[i] = Model.class;
                    } else {
                        classArgs[i] = args[i].getClass();
                    }
                }
                method = clazz.getMethod(methodName, classArgs);
            }
            long time = new Date().getTime() - visitTime.getTime(); //获取访问的时长
            RequestMapping annotation = method.getAnnotation(RequestMapping.class);
            if (annotation != null) {
                String[] value = annotation.value();
                String s = value[0];
                String ip = request.getRemoteAddr();
                SecurityContext context = SecurityContextHolder.getContext();//从上下文中获了当前登录的用户
                User user = (User) context.getAuthentication().getPrincipal();
                String username = user.getUsername();
                String url = s2 + s;
                SysLog sysLog = new SysLog();
                sysLog.setExecutionTime(time); //执行时长
                sysLog.setIp(ip);
                sysLog.setMethod(s);
                sysLog.setUrl(url);
                sysLog.setUsername(username);
                sysLog.setVisitTime(visitTime);
                //调用Service完成操作
                sysLogService.save(sysLog);
            }
            }
        }
    }
}
