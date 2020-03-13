package com.tom.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/* @RestController
 * A convenience annotation that is itself annotated with @Controller and 
 * @ResponseBody. Types that carry this annotation are treated as controllers where 
   @RequestMapping methods assume @ResponseBody semantics by default. 
 */

@RestController
public class UserController {

	/*
	 * Step 2c - Creating the User Controller
	 * -------------------------------------------------------------
	 */
	
	/*
	 * @Autowired
	 * Marks a constructor, field, setter method, or config method 
	 * as to be autowired by Spring's dependency injection facilities.
	 */
	
	@Autowired
	private UserDaoService service;
	
	
	//retrieveAllUsers  (GET /users)
	@GetMapping("/users") 
	public List<User> retrieveAllUsers(){
		return service.findAll();
	}
	
	// Step 2c (continued)
	// -------------------------------------------------------------
	//retrieveUser(int id)    (GET /users/{id})
	//@PathVariable allows us to map the id parameter to the path to find its resource
	//@GetMapping("/users/{id}") 
	//public User retrieveUser(@PathVariable int id) {
		//return service.findOne(id);
	//}
	
	// Step 2f (enhances retrieveUser() from Step 2c to handle 
	// exceptions of user not found 
	// -------------------------------------------------------------
	//@GetMapping("/users/{id}") 
	//public User retrieveUser(@PathVariable int id) {
		//User user = service.findOne(id);
		
		//if (user==null)
			//throw new UserNotFoundException("id-" + id);
		
		//return user;
	//}
	
	// Step 5a - working with HATEOAS (enhances retrieveUser() from Step 2f	
	// ------------------------------------------------------------
	// HATEOAS allows us to add links to our response from our resource very easily
	// Instead of just returning the data for 1 user, HATEOAS allows us
	// to also return a LINK to all of the users
	//
	// @EntityModel - A simple EntityModel wrapping a domain object and 
	// adding links to it.
	// 
	// retrieveUser()
	//	returns:
	/*
	 * {
	 * 		"id": 1,
	 * 		"name": "Adam",
	 * 		"birthDate": "2020-03-11T15:48:08.984+0000",
	 * 		"_links": {
	 * 			"all-users": {
	 * 			"href": "http://localhost:8080/users"
	 * 			}	
	 * 		}
	 * }
	 */
	
	/*@GetMapping("/users/{id}") 
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		User user = service.findOne(id);
		
		if (user==null)
			throw new UserNotFoundException("id-" + id);
		
		// the link to "all-users" is SERVER_PATH + "/users"
		// retrieveAllUsers
		EntityModel<User> model = new EntityModel<>(user);
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		model.add(linkTo.withRel("all-users"));

		return model;
	}*/
	
	@GetMapping("/users/{id}")
	public Resource<User> retrieveUser(@PathVariable int id) {
		User user = service.findOne(id);
		
		if (user==null)
			throw new UserNotFoundException("id-" + id);
		
		Resource<User> resource = new Resource<User>(user);
		
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		
		resource.add(linkTo.withRel("all-users"));
		
		return resource;
	}
	
	/*
	 * Step 3b - Map to the deleteById method in UserDaoService
	 * -------------------------------------------------------------
	 * @DeleteMapping
	 * Annotation for mapping HTTP DELETE requests onto specific handler 
	 * methods. Specifically, @DeleteMapping is a composed annotation that 
	 * acts as a shortcut for @RequestMapping(method = RequestMethod.DELETE).
	 * 
	 * if the resource is deleted successfully, 200 status created is retured
	 * else user not found exception is returned
	 */
	@DeleteMapping("/users/{id}") 
	public void deleteUser(@PathVariable int id) {
		User user = service.deleteById(id);
		
		if (user==null)
			throw new UserNotFoundException("id-" + id);
	}
	
	/*
	 * Step 2d - Create a method that creates a new user
	 * -------------------------------------------------------------
	 * CREATED
	 * input - details of user
	 * output - CREATED & Return the created URI of the created user
	 * 
	 * @PostMapping -- Maps to a User object
	 * 
	 * @RequestBody -- whatever is passed in body of request gets 
	 * mapped to the user object
	 * 
	 * How do we post it?  Postman (A rest client)
	 */
	
	/**@PostMapping("/users")      // (Maps to a User object)
	public ResponseEntity<Object> createUser(@RequestBody User user) {   
		User savedUser = service.save(user);
		
		// Step 2e - respond with created message on creation (best practice)
		// and return /user/4 (uri of newly created user)
		// ------------------------------------------------------------------
		// /user/{id} -----> savedUsed.getId()
		
		URI location = ServletUriComponentsBuilder   // @ServletUriComponentsBuilder -- UriComponentsBuilder with additional static factory methods to create links based on the current HttpServletRequest.
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId()).toUri();    // This sets a 201 Created status in postman after post request
		
		return ResponseEntity.created(location).build();   // in Headers of response in Postman -- a location of http://localhost:8080/users/4 is returned after post request
	} */
	
	/*
	 * Using a put request in postman, format = raw, json
	 * 
	 * sending :
	 * 
	 *  {
    		"name": "Tom",
    		"birthDate": "2000-03-10T21:40:39.664+0000"
		}
		
	 * We are able to add a new user
	 * We dont include id, as the UserDaoService.save() method
	 * auto increments id number on addition of a new user
	 */
	
	/*
	 * Step 4a - Enhancing create method() with validation
	 * ---------------------------------------------------------------------
	 * Enhancing what we did in step 2d through 2e
	 * 
	 * @Valid
	 * Marks a property, method parameter or method return type for validation 
	 * cascading. Constraints defined on the object and its properties are be 
	 * validated when the property, method parameter or method return type is 
	 * validated. 

	 */
	@PostMapping("/users")      // (Maps to a User object)
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {   
		User savedUser = service.save(user);
		
		// Step 2e - respond with created message on creation (best practice)
		// and return /user/4 (uri of newly created user)
		// ------------------------------------------------------------------
		// /user/{id} -----> savedUsed.getId()
		
		URI location = ServletUriComponentsBuilder   // @ServletUriComponentsBuilder -- UriComponentsBuilder with additional static factory methods to create links based on the current HttpServletRequest.
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId()).toUri();    // This sets a 201 Created status in postman after post request
		
		return ResponseEntity.created(location).build();   // in Headers of response in Postman -- a location of http://localhost:8080/users/4 is returned after post request
	}
}
