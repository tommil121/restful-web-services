package com.tom.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * Step 10a - Static Filtering
 * ---------------------------------------------------------------
 * @JsonIgnore
 * Marker annotation that indicates that the logical property that the 
 * accessor (field, getter/setter method or Creator parameter 
 * [of JsonCreator-annotated constructor or factory method]) is to 
 * be ignored by introspection-based serialization and deserialization 
 * functionality. 
 */

/*
 * want to send field1 and 2 but not 3
 */

/*
 * Another way to do this is with @JsonIgnoreProperties
 */

//@JsonIgnoreProperties(value= {"field1"})
public class SomeBean {
	private String field1;
	private String field2;
	
	@JsonIgnore
	private String field3;
	
	
	public SomeBean(String field1, String field2, String field3) {
		super();
		this.field1 = field1;
		this.field2 = field2;
		this.field3 = field3;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}

	@Override
	public String toString() {
		return "SomeBean [field1=" + field1 + ", field2=" + field2 + ", field3=" + field3 + "]";
	}
	
	
}
