package com.tom.restfulwebservices.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.tom.restfulwebservices.user.UserNotFoundException;

/*
 * Step 2g (part 2)- Create a custom exception response 
 * as a standard across organization
 * ------------------------------------------------------------------------
 */

/* @ControllerAdvice
 * Specialization of @Component for classes that declare @ExceptionHandler, 
 * @InitBinder, or @ModelAttribute methods to be shared across multiple 
 * @Controller classes. 
 */

/*
 * @RestController
 * A convenience annotation that is itself annotated with @Controller and 
 * @ResponseBody. Types that carry this annotation are treated as 
 * controllers where @RequestMapping methods assume @ResponseBody 
 * semantics by default. 
 */

/*
 * ResponseEntityExceptionHandler - A convenient base class for @ControllerAdvice 
 * classes that wish to provide centralized exception handling across all 
 * @RequestMapping methods through @ExceptionHandler methods. 
 */

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	
	//When an exception happens, we want to create a new instance of
	//our specific bean
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {	
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), 
				request.getDescription(false));		
		return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// next method is customized specifically to handle UserNotFoundExceptions 
	// will return the 404 Not Found message, rather than 500 internal server error (above method)
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {	
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), 
				request.getDescription(false));		
		return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	// Step 4c - continuing handling validation
	// -----------------------------------------------------------------------------
	// When invalid data is submitted, return exception with "Bad Request"
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, 
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation Failed", 
				ex.getBindingResult().toString());		
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
}
