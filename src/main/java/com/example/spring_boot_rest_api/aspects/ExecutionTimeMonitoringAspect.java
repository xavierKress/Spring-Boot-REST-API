package com.example.spring_boot_rest_api.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecutionTimeMonitoringAspect {

	private static final Logger logger = LoggerFactory.getLogger(ExecutionTimeMonitoringAspect.class);
	@Pointcut("@annotation(ExecutionTimeMonitoring)")
	public void proceedExecutionTimeMonitoring() {
	}

	@Before("proceedExecutionTimeMonitoring()")
	public void beforPointCut(JoinPoint joinPoint) {
		this.logMethodInformation(joinPoint);
		logger.debug("before method : {}", System.currentTimeMillis());
	}

	@After("proceedExecutionTimeMonitoring()")
	public void afterPointCut(JoinPoint joinPoint) {
		this.logMethodInformation(joinPoint);
		logger.debug("after method : {}", System.currentTimeMillis());
	}

	private void logMethodInformation(JoinPoint joinPoint){
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();

		logger.debug("full method description: {}", signature.getMethod());
		logger.debug("method name: {}", signature.getMethod().getName());
		logger.debug("declaring type: {}", signature.getDeclaringType());

		ExecutionTimeMonitoring executionTimeMonitoring = signature.getMethod().getAnnotation(ExecutionTimeMonitoring.class);
		logger.debug("operation value: {}", executionTimeMonitoring.operation());
	}
}
