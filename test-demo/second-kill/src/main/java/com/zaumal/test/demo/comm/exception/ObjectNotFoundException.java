package com.zaumal.test.demo.comm.exception;

public class ObjectNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -2272479174787978971L;
	
	public ObjectNotFoundException() {
	}
	
	public ObjectNotFoundException(String message) {
		super(message);
	}
}
