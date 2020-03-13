package com.tom.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

/*
 * @Component - Indicates that an annotated class is a "component". Such classes are 
 * considered as candidates for auto-detection when using annotation-based 
 * configuration and classpath scanning. 
 */

@Component
public class UserDaoService {

	//Step 2b - create a User Dao Service to talk to database
	// For this step, our database is a static Array List to hold data
	//-------------------------------------------------------------
	
	public static List<User> users = new ArrayList<>();
	
	private static int usersCount = 3;
	
	static {  // for Step 2b - we are using a static Array List to hold our data
		users.add(new User(1, "Adam", new Date()));
		users.add(new User(2, "Eve", new Date()));
		users.add(new User(3, "Jack", new Date()));
	}
	
	// list all users
	public List<User> findAll(){
		return users;
	}
	
	//add user to user list
	public User save(User user) {
		if (user.getId()==null) {
			user.setId(++usersCount);
		}
		users.add(user);
		return user;
	}
	
	//find 1 user in the users list
	public User findOne(int id) {
		for (User user : users) {
			if (user.getId()==id) {
				return user;
			}
		}
		return null;
	}
	
	// Step 3a - delete 1 user in the users list
	// ------------------------------------------------
	public User deleteById(int id) {
		Iterator<User> iterator = users.iterator();
		while (iterator.hasNext()) {
			User user = iterator.next();
			if (user.getId()==id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}
	
}
