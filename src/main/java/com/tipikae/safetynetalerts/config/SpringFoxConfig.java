package com.tipikae.safetynetalerts.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * A configuration class for Swagger2
 * @author tipikae
 * @version 1.0
 *
 */
@Configuration
public class SpringFoxConfig {   

	/**
	 * Api method.
	 * @return Docket
	 */
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)
    			.select()
    			.apis(RequestHandlerSelectors.basePackage("com.tipikae.safetynetalerts"))           
    			.paths(PathSelectors.any())           
    			.build();
    }
}
