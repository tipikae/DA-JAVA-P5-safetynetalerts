package com.tipikae.safetynetalerts.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * An aspect to log every request.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
@Aspect
public class ErrorRequestLogger {

	private static final Logger LOGGER = LogManager.getLogger("RequestLogger");
	
	@Pointcut("execution(com.tipikae.safetynetalerts.exception.ControllerException.new(int, String))")
	public void logErrorsRequests() {}
	
	@Before("logErrorsRequests()")
	public void logErrors(JoinPoint jp){
		String[] args = (String[]) jp.getArgs();
		LOGGER.error("Error code: " + args[0] + ": " + args[1]);
	}
}
