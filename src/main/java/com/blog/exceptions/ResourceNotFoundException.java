package com.blog.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {

	private String resourceName;
	private String filedName;
	private long filedValue;
	public ResourceNotFoundException(String resourceName, String filedName, long filedValue) {
		super(String.format("%s not found with %s :%s",resourceName,filedName,filedValue));
		this.resourceName = resourceName;
		this.filedName = filedName;
		this.filedValue = filedValue;
	}
	
	
	
	
}
