package com.tipikae.safetynetalerts.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.tipikae.safetynetalerts.logging.CustomRequestLoggingInterceptor;

/**
 * An implementation of WebMvcConfigurer.
 * @author tipikae
 * @version 1.0
 *
 */
@EnableWebMvc
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

	@Autowired
	private CustomRequestLoggingInterceptor interceptor;

	/**
	 * Add custom interceptors.
	 */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor);
    }
}
