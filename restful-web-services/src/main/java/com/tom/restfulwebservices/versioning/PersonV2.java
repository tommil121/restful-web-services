package com.tom.restfulwebservices.versioning;

public class PersonV2 {
	
	/*
	 * Step 11a - Versioning
	 * -------------------------------------------------
	 */
	
	private Name name;
	

	public PersonV2() {
	}
	
	public PersonV2(Name name) {
		super();
		this.name = name;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}
	
	
}
