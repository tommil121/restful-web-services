package com.tom.restfulwebservices;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

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
@EnableSwagger2   // @EnableSwagger2 -- need this to enable swagger
public class SwaggerConfig {
	
	/*
	 * Step 8c - Enhancing Swagger with API info
	 * ---------------------------------------------------------------
	 */
	public static final Contact DEFAULT_CONTACT = new Contact(
			"Tom Miller", "http://www.tom.com", "tom@gmail.com");
	
	public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
			"Awesome API Title", "Awesome API Description", "1.0"
			,"urn:tos", DEFAULT_CONTACT, "Apache 2.0", 
			"http://www.apache.org/licenses/LICENSE-2.0");
	
	private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = 
			new HashSet<String>(Arrays.asList("application/json",
					"application/xml"));


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
	
	@Bean
	public Docket api() {
		
		/*
		 * Step 8c - Enhancing Swagger with API info
		 * ---------------------------------------------------------------
		 */
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(DEFAULT_API_INFO)
				.produces(DEFAULT_PRODUCES_AND_CONSUMES)
				.consumes(DEFAULT_PRODUCES_AND_CONSUMES);
	}
	
}
