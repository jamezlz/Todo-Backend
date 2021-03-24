package com.todo.controller;

public class TodoNotFoundException extends RuntimeException {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	TodoNotFoundException(String id) {
		    super("Could not find employee " + id);
		  }
}
