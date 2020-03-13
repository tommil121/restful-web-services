package com.tom.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

//Controller - handle http requests

@RestController
public class HelloWorldController {
	
	// Step 6c - Continuing with Internationalization
	// --------------------------------------------------------------------
	@Autowired
	private MessageSource messageSource;
	
	/**Using HTTP GET METHOD
	//to URI path - /hello-world
	//using method - "Hello World"
	*/
	
	//Step 1a -- Using @RequestMapping
	//One way to use get method and map our helloworld method to the get
	//can be done using @RequestMapping
	//----------------------------------------------------------------------
	//@RequestMapping(method=RequestMethod.GET, path="/hello-world")
	//@GetMapping("/hello-world")
	//public String helloWorld() {
	//	return "Hello World";
	//}
	
	//Step 1b -- Using @GetMapping
	//Another way is to us @GetMapping, which bypasses the need to 
	//add the parameters needed in @RequestMapping
	//
	//returns:
	//	Hello World
	//----------------------------------------------------------------------
	@GetMapping("/hello-world")
	public String helloWorld() {
		return "Hello World";
	}
	
	//Step 1c -- Do the same as above but using a java bean (HelloWorldBean.class)
	//Return a bean instead of a string
	//returns:
	//	{
	//		"message": "Hello World"
	//	}
	//----------------------------------------------------------------------
	@GetMapping("/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World");
	}
	
	//Step 1d -- Enhance HelloWorld Method with path parameter (@PathVariable)
	//ie) @GetMapping("/hello-world/path-variable/{name}")
	//so now when we pass method a name, it will return Hello World and the name back
	//
	//returns:
	//	{
	//		"message": "Hello World, Tom"
	//	}
	//----------------------------------------------------------------------
	@GetMapping("/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World, %s", name));
	}
	
	//Step 6a - Learning about Internationalized (1 18 n)
	//----------------------------------------------------------------------
	// customize the Good Morning message based on country of origin
	// of request (ex) if france, then return "Bon Jour"
	//@GetMapping("/hello-world-internationalized")
	//public String helloWorldInternationalized() {
		//return "Good Morning";
	//}
	
	// Step 6c - Updates helloWorldInternationalized() from Step 6a
	//----------------------------------------------------------------------
	// to use messageSource
	// Learning about Internationalized (1 18 n)
	// customize the Good Morning message based on country of origin
	// of request (ex) if france, then return "Bon Jour"
	//
	// If a locale is not passed it will use the default locale (US)
	// which was set in the messages.proporties file
	//
	// @RequestHeader
	// Annotation which indicates that a method parameter should be bound to 
	// a web request header. 
	
	//@GetMapping("/hello-world-internationalized")
	//public String helloWorldInternationalized(@RequestHeader(
		//	name = "Accept-Language", required=false) Locale locale) {
		//return messageSource.getMessage("good.morning.message", null, locale);
	//}
	
	// Step 6d -- improving helloWorldInternationalized() (done in Step 6c) further
	// -------------------------------------------------------------------------
	// Here we use LocalContextHolder to get Locale rather than the "locale"
	// variable coming from Locale class
	
	@GetMapping("/hello-world-internationalized")
	public String helloWorldInternationalized() {
		return messageSource.getMessage("good.morning.message", null, 
									LocaleContextHolder.getLocale());
	}
}
