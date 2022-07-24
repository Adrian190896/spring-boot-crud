package com.oz.common;

import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseCustom {
	
	public String message;
	public String error;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}

}
