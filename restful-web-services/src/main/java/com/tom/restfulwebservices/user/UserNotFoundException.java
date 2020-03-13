package com.tom.restfulwebservices.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * @ResponseStatus(HttpStatus.NOT_FOUND)
 * Specifies the type of status error returned on exception so it is more
 * accurate to what the issue is (the user id does not exist)
 * 
 * Now rather than returning a 500 Internal Server Error prior
 * to adding in @ResponseStatus(HttpStatus.NOT_FOUND)
 * Status on failure now returns 404 Not Found
 * 
 * The @ResponseStatus(HttpStatus.NOT_FOUND) provides this
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
	
	// Step 2f (enhances retrieveUser() from Step 2c to 
	// handle exceptions of user not found
	// -------------------------------------------------------------
	public UserNotFoundException(String message) {
		super(message);
	}

}
