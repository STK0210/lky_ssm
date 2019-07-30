package com.lky.ssm.controller;

import com.lky.ssm.domain.SysLog;
import com.lky.ssm.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @auther likeyu
 * @create 2019-07-30-10:30
 **/
@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISysLogService sysLogService;

    private Date visitTime;//开始时间
    private Class clazz;//访问的类
    private Method method;//访问的方法


    //前置通知，获取开始时间，执行的的类，执行的方法
    @Before("execution(* com.lky.ssm.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();
        clazz = jp.getTarget().getClass();//获得当前访问的具体的类对象
        String methodName = jp.getSignature().getName();//获取到访问的方法名
        Object[] args = jp.getArgs();

        //获取具体执行的方法的对象
        if (args == null || args.length == 0) {
            method = clazz.getMethod(methodName);//只能获取无参方法
        } else {
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            clazz.getMethod(methodName, classArgs);
        }

    }

    //后置通知
    @After("execution(* com.lky.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        long time = new Date().getTime() - visitTime.getTime();//获取访问的时长

        //获取url
        String url = "";

        if (clazz != null && method != null && clazz != LogAop.class) {
            //获取类上的@RequestMapping("/orders")
            RequestMapping ClassAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (ClassAnnotation != null) {
                String[] classValue = ClassAnnotation.value();
                //获取方法上的@Reque
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0] + methodValue[0];
                    //获取访问的ip地址
                    String ip = request.getRemoteAddr();

                    //获取当前操作的用户,通过springSecurity
                    SecurityContext context = SecurityContextHolder.getContext();//从上下文中获取了当前登录的用户
                    User user = (User) context.getAuthentication().getPrincipal();
                    String username = user.getUsername();


                    //将日志相关信息封装到sysLog对象中
                    SysLog sysLog = new SysLog();
                    sysLog.setExecutionTime(time);
                    sysLog.setIp(ip);
                    sysLog.setMethod("[类名]" + clazz.getName() + "  [方法名]" + method.getName());
                    sysLog.setUrl(url);
                    sysLog.setUsername(username);
                    sysLog.setVisitTime(visitTime);

                    //调用Service去完成记录存入数据库
                    sysLogService.save(sysLog);
                }
            }
        }
    }

}
