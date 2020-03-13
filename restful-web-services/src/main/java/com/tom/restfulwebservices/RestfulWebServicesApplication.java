package com.tom.restfulwebservices;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@SpringBootApplication
public class RestfulWebServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServicesApplication.class, args);
	}
	
	//Step 6a - Learning about Internationalized (1 18 n)
	//----------------------------------------------------------------------
	// customize the Good Morning message based on country of origin
	// of request (ex) if france, then return "Bon Jour"
	
	// This step also consists of creation of properties file in
	// src/main/resources/messages.properties
	
	/*
	 * LocaleResolver - Interface for web-based locale resolution strategies 
	 * that allows for both locale resolution via the request and locale 
	 * modification via request and response. 
	 * 
	 */
	@Bean
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        localeResolver.setDefaultLocale(Locale.US);
        return localeResolver;
    }
	
	// Step 6b - Continuing Learning about Internationalized (1 18 n)
	// ----------------------------------------------------------------------
	// utilizing the message.properties and message_fr.properties files
	// for internationalization of Good Morning greeting based on location
	/*
	 * ResourceBundleMessageSource 
	 * org.springframework.context.MessageSource implementation that accesses 
	 * resource bundles using specified basenames. This class relies on the 
	 * underlying JDK's java.util.ResourceBundle implementation, in combination 
	 * with the JDK's standard message parsing provided by java.text.MessageFormat. 
	 */
	
	//@Bean
	//public ResourceBundleMessageSource messageSource() {
		//ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		//messageSource.setBasename("messages");
		//return messageSource;
	//}
	
	// Step 6e - further simplifying messageSource() created in Step 6b
	// -----------------------------------------------------------------------
	// We can add a line of code in application.properties file which allow
	// us to not have to use the messageSource() method above (Step 6b)
	// go to application.properties file and see line
	// spring.messages.basename=messages
	
	// Step 7a - See pom.xml file (Content Negotiation) USING XML AND JSON
	// ------------------------------------------------------------------------
	//		<!-- Step 7a Adding Dependency to utilize XML -->
	//      <!-- This is an example of Content Negotiation -->
	//      <!-- Can now deal with both JSON and XML -->
	//
	//      <dependency>
	//      	<groupId>com.fasterxml.jackson.dataformat</groupId>
	//      	<artifactId>jackson-dataformat-xml</artifactId>
	//      </dependency>
	
	// Step 8a - Using Swagger - See pom.xml file 
	// -------------------------------------------------------------------------
	//		<!-- Step 8a Utilizing Swagger -->
	//		<dependency>
	//			<groupId>io.springfox</groupId>
	//			<artifactId>springfox-swagger2</artifactId>
	//			<version>2.6.1</version>
	//		</dependency>
	//		<dependency>
	//			<groupId>io.springfox</groupId>
	//			<artifactId>springfox-swagger-ui</artifactId>
	//			<version>2.6.1</version>
	//		</dependency>
}
