package com.kyle.a_system;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

/**
 * 用来定义一个日志切面来对记录controller的输入和输出
 * @author kyle
 *
 */
@Component
@Aspect
public class ControllerLoggerAspect {
	
	private static Logger logger = LoggerFactory.getLogger(ControllerLoggerAspect.class);
	
    // 定义一个切入点表达式
    @Pointcut("execution(* com.kyle.*.*.controller.*Controller.*(..))")
    public void pointCut(){}

    @Around(value="pointCut()")
    public Object around(ProceedingJoinPoint pjd) throws Throwable{
    	
        Object result = null;
        Signature signature = pjd.getSignature();
        String classname = signature.getDeclaringTypeName();
        String methodName = signature.getName();
        
        //方法执行前
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(classname).append(":").append(methodName).append("] ");
        sb.append("入参: ");
        sb.append(JSON.toJSON(pjd.getArgs()));
        
        try{
        	logger.info(sb.toString());
            result = pjd.proceed();
        } catch(Throwable e){
          throw e;
        }
        
        //后置通知
        StringBuilder sb2 = new StringBuilder();
        sb2.append("[").append(classname).append(":").append(methodName).append("] ");
        sb2.append("返回: ");
        sb2.append(result);
        logger.info(sb2.toString());
        
        return result;
    }
}