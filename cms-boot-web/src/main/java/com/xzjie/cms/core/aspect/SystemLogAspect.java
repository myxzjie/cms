package com.xzjie.cms.core.aspect;

import com.xzjie.cms.core.annotation.Log;
import com.xzjie.cms.core.event.SystemLogEvent;
import com.xzjie.cms.core.utils.RequestHolder;
import com.xzjie.cms.core.utils.SecurityUtils;
import com.xzjie.cms.system.auth.dto.LoginDto;
import com.xzjie.cms.log.model.SystemLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;

@Aspect
@Component
public class SystemLogAspect {
    private ThreadLocal<SystemLog> currentSystemLogThreadLocal = new ThreadLocal<>();
    private ThreadLocal<Long> currentTime = new ThreadLocal<>();
    @Autowired
    private ApplicationContext context;

    /**
     * 配置切入点
     */
    @Pointcut("@annotation(com.xzjie.cms.core.annotation.Log)")
    public void logPointcut() {
        // 该方法无方法体,主要为了让同类中其他方法使用此切入点
    }

    /***
     * 拦截控制层的操作日志
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Before(value = "logPointcut()")
    public void beforeLog(JoinPoint joinPoint) throws Throwable {
        SystemLog systemLog = new SystemLog();
        currentSystemLogThreadLocal.set(systemLog);
        currentTime.set(Instant.now().toEpochMilli());
//        Object result = joinPoint.proceed();
        HttpServletRequest request = RequestHolder.getHttpServletRequest();

        systemLog.setUsername(SecurityUtils.getUsername());
        systemLog.setUrl(request.getRequestURI());
        systemLog.setStartTime(LocalDateTime.now());
        systemLog.setRequestIp(RequestHolder.getIp());
        systemLog.setBrowser(RequestHolder.getBrowser());


        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Log log = method.getAnnotation(Log.class);

        // 类名
        systemLog.setName(log.value());
        systemLog.setDescription(log.descrption());
        systemLog.setMethod(joinPoint.getTarget().getClass() + "." + signature.getName() + "()");

        systemLog.setFinishTime(LocalDateTime.now());

        //访问目标方法的参数 可动态改变参数值
        Object[] args = joinPoint.getArgs();
        // 参数
        systemLog.setParams(Arrays.toString(args));

        if ("login".equals(log.value())) {
            try {
                LoginDto loginRequest = (LoginDto) args[0];
                systemLog.setUsername(loginRequest.getUsername());
                systemLog.setParams("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Around("logPointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();
        return result;
    }

    /**
     * 返回通知
     *
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(returning = "ret", pointcut = "logPointcut()")
    public void doAfterReturning(Map<String, Object> ret) {
        SystemLog systemLog = currentSystemLogThreadLocal.get();
        // 处理完请求，返回内容
//        R r = Convert.convert(R.class, ret);
        if (ret.get("code").equals(0)) {
//            // 正常返回
            systemLog.setType(1);
            systemLog.setLevel("INFO");
        } else {
            systemLog.setType(2);
            systemLog.setLevel("ERROR");
        }

        this.consumingTime(systemLog);
        // 发布事件
        context.publishEvent(new SystemLogEvent(systemLog));
        this.remove();
    }

    /**
     * 异常通知
     *
     * @param e
     */
    @AfterThrowing(pointcut = "logPointcut()", throwing = "e")
    public void doAfterThrowable(Throwable e) {
        SystemLog systemLog = currentSystemLogThreadLocal.get();
        // 异常
        systemLog.setType(2);
        systemLog.setLevel("ERROR");
        // 异常对象
        systemLog.setExceptionDetail(e.getMessage());
        // 异常信息
//        systemLog.setExDesc(e.getMessage());
        this.consumingTime(systemLog);
        // 发布事件
        context.publishEvent(new SystemLogEvent(systemLog));
        this.remove();
    }

    /**
     * 耗时
     *
     * @param systemLog
     */
    private void consumingTime(SystemLog systemLog) {
        long endTime = Instant.now().toEpochMilli();
        systemLog.setTime(endTime - currentTime.get());
        currentTime.remove();
    }

    private void remove() {
        currentSystemLogThreadLocal.remove();
    }

}
