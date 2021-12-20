package com.tipikae.safetynetalerts.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@Aspect
public class RequestLogger {

	@Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
	public void getAction() {
	}
	
	@Before("getAction()")
    public void beforeGetAction(JoinPoint joinPoint) {
		Class<? extends Object> clazz = joinPoint.getTarget().getClass();
        Logger logger = LoggerFactory.getLogger(clazz);
        
        String url = getRequestUrl(joinPoint, clazz);
        String payload = getPayload(joinPoint);
        logger.info("GET " + url + " Payload " + payload);
	}
	
	@After("getAction()")
    public void afterGetAction(JoinPoint joinPoint) {
		Class<? extends Object> clazz = joinPoint.getTarget().getClass();
        Logger logger = LoggerFactory.getLogger(clazz);
        
		logger.info("after getAction OK");
	}

    private String getRequestUrl(JoinPoint joinPoint, Class<? extends Object> clazz) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        GetMapping methodGetMapping = method.getAnnotation(GetMapping.class);
        System.out.println(
        		"class=" + clazz.toString() + 
        		", methodSignature=" + methodSignature.toString() + 
        		", method=" + method.toString() + 
        		", methodGetMapping=" + methodGetMapping);
        return getGetUrl(methodGetMapping);
    }

    private String getPayload(JoinPoint joinPoint) {
        CodeSignature signature = (CodeSignature) joinPoint.getSignature();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < joinPoint.getArgs().length; i++) {
            String parameterName = signature.getParameterNames()[i];
            builder.append(parameterName);
            builder.append(": ");
            builder.append(joinPoint.getArgs()[i].toString());
            builder.append(", ");
        }
        return builder.toString();
    }

    private String getGetUrl(GetMapping getMapping) {
        String endpoint = getUrl(getMapping.value());

        return endpoint;
    }

    private String getUrl(String[] urls) {
        if (urls.length == 0) return "";
        else return urls[0];
    }
}
