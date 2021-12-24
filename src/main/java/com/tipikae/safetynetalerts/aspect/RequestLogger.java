package com.tipikae.safetynetalerts.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * An aspect to log every request.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
@Aspect
public class RequestLogger {

	private static final Logger LOGGER = LogManager.getLogger("RequestLogger");
	
	@Before("com.tipikae.safetynetalerts.exception.ControllerException(*))")
	public void loggingException(JoinPoint joinPoint){
		String[] args = (String[]) joinPoint.getArgs();
		LOGGER.error("Error code: " + args[0] + ": " + args[1]);
	}
}
