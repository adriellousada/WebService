package com.aulapds.code.services.exceptions;

public class DatabaseException extends RuntimeException{
	
	private static final long serialVersioUID = 1L;
	
	public DatabaseException(String msg) {
		super(msg);
	}

}
