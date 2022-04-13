package com.example.demo.resouces.exception;

import java.io.Serializable;

public class StandardError implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private Long temestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;
	
	
	public StandardError(Long temestamp, Integer status, String error, String message, String path) {
		super();
		this.temestamp = temestamp;
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}


	public Long getTemestamp() {
		return temestamp;
	}


	public void setTemestamp(Long temestamp) {
		this.temestamp = temestamp;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getError() {
		return error;
	}


	public void setError(String error) {
		this.error = error;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}
	
	
	
	
	
}
