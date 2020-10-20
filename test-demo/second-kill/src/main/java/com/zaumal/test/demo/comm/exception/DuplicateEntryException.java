package com.zaumal.test.demo.comm.exception;

public class DuplicateEntryException extends RuntimeException {
	private static final long serialVersionUID = -6211731727815299284L;
	
	public DuplicateEntryException() {
	}
	
	public DuplicateEntryException(String message) {
		super(message);
	}
}
