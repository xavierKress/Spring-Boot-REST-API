package com.example.spring_boot_rest_api.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ClientOrderLoggerAspect {

	private static final Logger logger = LoggerFactory.getLogger(ClientOrderLoggerAspect.class);
	@Before("execution(* com.example.spring_boot_rest_api.component.ClientOrderComponent.securedMethod())")
	public void checkPermissions(JoinPoint joinPoint) {

		logger.debug("Permission check done");
		// if not, throw UnathorizedException
		logger.debug(
				"Method {} executed",
				joinPoint.getSignature()
		);

		logger.debug(
				"with {} argument(s)",
				joinPoint.getArgs()
		);


	}
}
