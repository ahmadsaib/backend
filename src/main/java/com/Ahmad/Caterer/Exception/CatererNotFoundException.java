package com.Ahmad.Caterer.Exception;

public class CatererNotFoundException extends RuntimeException {

	private final String id;

	public CatererNotFoundException(final String id) {
		super("Person could not be found with id: " + id);
		this.id = id;
	}

	public String getId() {
		return id;
	}

}
