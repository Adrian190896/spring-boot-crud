package com.oz.model.human;

import io.swagger.annotations.ApiModelProperty;

public class HumanModel {
	
	@ApiModelProperty(notes = "Human name", example = "Jose") 
	private String name;
	@ApiModelProperty(notes = "Human last name", example = "Anastacio") 
	private String lastName;
	@ApiModelProperty(notes = "Human age", example = "22") 
	private Integer age;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	public HumanModel() {

	}
	
	public HumanModel(String name, String lastName, Integer age) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.age = age;
	}

	
}
