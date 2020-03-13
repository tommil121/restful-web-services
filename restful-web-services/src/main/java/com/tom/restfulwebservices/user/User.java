package com.tom.restfulwebservices.user;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

public class User {
	
	//Step 2a - create a User bean
	//-------------------------------------------------------------
	
	private Integer id;
	
	/*
	 * Step 4b - Enhancing create method() with validation
	 * ----------------------------------------------------
	 * @Size
	 * The annotated element size must be between the specified boundaries (included). 
	 * 
	 * @Past
	 * The annotated element must be an instant, date or time in the past. 
	 */
	
	@Size(min = 2, message="Name should have 2 atleast 2 characters")
	private String name;
	
	@Past
	private Date birthDate;
	
	
	//no arg constructor
	protected User() {
		// no arg constructor was needed in former versions
		// no longer needed but here for reference
	}
	
	public User(Integer id, String name, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}
	
	
}
