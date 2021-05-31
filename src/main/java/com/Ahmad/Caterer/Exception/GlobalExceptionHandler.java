package com.Ahmad.Caterer.Exception;


public class GlobalExceptionHandler extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GlobalExceptionHandler(String message) {
		super(message);
	}


	
	public static String NotFoundException(String id) {
		return "Todo with "+id+" not found!";
	}
	
	public static String CatererAlreadyExists() {
		return "Caterer with given Email already exists";
	}
}
