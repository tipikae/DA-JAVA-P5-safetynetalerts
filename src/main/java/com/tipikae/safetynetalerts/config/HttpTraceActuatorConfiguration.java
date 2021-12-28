package com.tipikae.safetynetalerts.config;

import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * A configuration for HttpTrace actuator.
 * @author tipikae
 * @version 1.0
 *
 */
@Configuration
public class HttpTraceActuatorConfiguration {

	/**
	 * Set the HttpTrace repository to memory.
	 * @return HttpTraceRepository
	 */
	 @Bean
	 public HttpTraceRepository httpTraceRepository() {
	     return new InMemoryHttpTraceRepository();
	 }

}
