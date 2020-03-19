package com.tom.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

/*
 * @RestController
 * A convenience annotation that is itself annotated with @Controller and 
 * @ResponseBody. Types that carry this annotation are treated as 
 * controllers where @RequestMapping methods assume @ResponseBody 
 * semantics by default. 

 */
@RestController
public class FilteringController {
	
	/*
	 * Step 10a - Static Filtering
	 * ---------------------------------------------------------------
	 */
	
	/*
	 * want to send field1 and 2 but not 3
	 */
	@GetMapping("/filtering")
	public SomeBean retrieveSomeBean() {
		return new SomeBean("value1", "value2", "value3");
	}
	
	/*
	 * want to send field1 and 2 but not 3
	 */
	@GetMapping("/filtering-list")
	public List<SomeBean> retrieveListOfSomeBeans() {
		return Arrays.asList(new SomeBean("value1", "value2", "value3"),
				new SomeBean("value12", "value22", "value32"));
	}
	
	/*
	 * Step 10b - Dynamic Filtering
	 * ---------------------------------------------------------------
	 */
	/*
	 * only want to send field 1 and field 2 but not field 3
	 */
	@GetMapping("/dynamic-filtering")
	public MappingJacksonValue retrieveAnotherBean() {
		AnotherBean anotherBean = new AnotherBean(
				"value1", "value2", "value3");
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.
				filterOutAllExcept("field1", "field2");
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("AnotherBeanFilter", filter);
		
		MappingJacksonValue mapping = new MappingJacksonValue(anotherBean);
		
		mapping.setFilters(filters);
		
		return mapping;
	}
	
	/*
	 * want to send field2 and 3 but not 1
	 */
	@GetMapping("/dynamic-filtering-list")
	public MappingJacksonValue retrieveListOfAnotherBeans() {
		List<AnotherBean> list = Arrays.asList(new AnotherBean("value1", "value2", "value3"),
				new AnotherBean("value12", "value22", "value32"));
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.
				filterOutAllExcept("field1", "field2");
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("AnotherBeanFilter", filter);
		
		MappingJacksonValue mapping = new MappingJacksonValue(list);
		
		mapping.setFilters(filters);
		
		return mapping;
	}
}
