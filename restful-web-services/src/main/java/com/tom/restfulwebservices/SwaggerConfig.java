package com.tom.restfulwebservices;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
 * @Configuration
 * Indicates that a class declares one or more @Bean methods and 
 * may be processed by the Spring container to generate bean definitions 
 * and service requests for those beans at runtime
 */

@Configuration
//@EnableSwagger2   // @EnableSwagger2 -- need this to enable swagger
public class SwaggerConfig {
	
	/*
	 * Step 8b - Configuring Swagger
	 * ---------------------------------------------------------------
	 */
	
	/*
	 * @Bean
	 * Indicates that a method produces a bean to be managed by the 
	 * Spring container. 
	 */
	
	/*
	 * Docket
	 * A builder which is intended to be the primary interface into 
	 * the swagger-springmvc framework. Provides sensible defaults and 
	 * convenience methods for configuration
	 */
	
	//@Bean
	//public Docket api() {
		//return new Docket(DocumentationType.SWAGGER_2);
	//}
	
}
