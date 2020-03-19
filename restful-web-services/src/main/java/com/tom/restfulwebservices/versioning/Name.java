package com.tom.restfulwebservices.versioning;

public class Name {
	private String firstName;
	private String lastName;
	
	/*
	 * Step 11a - Versioning
	 * -------------------------------------------------
	 */
	
	public Name() {

	}
	
	public Name(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
}
