package com.tipikae.safetynetalerts.logging;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class CustomRequestLoggingInterceptor implements HandlerInterceptor {
	
	private static final Logger LOGGER = LogManager.getLogger("CustomRequestLoggingInterceptor");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	StringBuffer requestURL = request.getRequestURL();
        LOGGER.info("preHandle => Request URL: {}", requestURL);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    	int status = response.getStatus();
        LOGGER.info("afterCompletion => Response Status: {}", status);
    }
}
