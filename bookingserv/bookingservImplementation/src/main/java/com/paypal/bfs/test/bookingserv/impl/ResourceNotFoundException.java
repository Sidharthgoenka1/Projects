package com.paypal.bfs.test.bookingserv.impl;

public class ResourceNotFoundException extends RuntimeException{
	private String msg;
	
	public ResourceNotFoundException(String message){
		this.msg = message;
	}
	
	public String getMsg(){
		return msg;
	}
}
