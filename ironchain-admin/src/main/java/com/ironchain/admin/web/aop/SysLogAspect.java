package com.ironchain.admin.web.aop;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ironchain.admin.security.SecurityKit;
import com.ironchain.common.annotation.SysLog;
import com.ironchain.common.base.BaseController;
import com.ironchain.common.dao.SystemLogDao;
import com.ironchain.common.domain.SystemLog;

@Aspect
//@Component
@Order(5)
public class SysLogAspect {
	
	@Autowired
	private SystemLogDao systemLogDao;
	
	@Pointcut("execution(public * com.ironchain.admin.modules..*.*(..))")
	public void logPointCut() { 
		
	}
	
	@Before("logPointCut()")
	public void saveSysLog(JoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		
		SystemLog systemLog = new SystemLog();
		SysLog syslogAnnot = method.getAnnotation(SysLog.class);
		if(syslogAnnot != null){
			if(syslogAnnot.ignore())
				return;
			//注解上的描述 
			systemLog.setOperation(syslogAnnot.value());
		}
		systemLog.setSystem("admin");
		//请求的方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		systemLog.setMethod(className + "." + methodName + "()");
		//获取request
		HttpServletRequest request = ((ServletRequestAttributes) 
				RequestContextHolder.getRequestAttributes()).getRequest();
		//请求uri
		systemLog.setUri(request.getRequestURI());
		//设置IP地址
		systemLog.setIp(BaseController.getRemoteAddr(request));
		//用户名
		systemLog.setUsername(SecurityKit.getCurrentUser() != null?
				SecurityKit.getCurrentUser().getLoginName() : null);
		//请求时间
		systemLog.setCreateTime(new Date());
		//保存系统日志
		systemLogDao.save(systemLog);
	}
}
