package com.tipikae.safetynetalerts.logging;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * An implementation HandlerInterceptor for intercepting and logging requests.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class CustomRequestLoggingInterceptor implements HandlerInterceptor {
	
	private static final Logger LOGGER = LogManager.getLogger("CustomRequestLoggingInterceptor");

	/**
	 * {@inheritDoc}
	 * @return boolean
	 */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	String method = request.getMethod();
    	StringBuffer requestURL = request.getRequestURL();
    	String query = request.getQueryString();
        LOGGER.info("preHandle => Method: {}, Request URL: {}, Parameters: {}", method, requestURL, query);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    	int status = response.getStatus();
        LOGGER.info("afterCompletion => Response Status: {}", status);
    }
}
