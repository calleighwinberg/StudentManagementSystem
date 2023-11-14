package roles;

import courses.Courses;

public class User {
	
	//instance variables 

	/**
	 * Represents the id for the user
	 */
	private int id;
	
	/**
	 * Represents the name for the user
	 */
	private String name; 
	
	/**
	 * Represents the username for the user
	 */
	private String username;
	
	/**
	 * Represents the password for the user
	 */
	private String password;
	
	/**
	 * Represents the user type
	 */
	private String userType;
	
	
	Courses courseObj;
	
	
	//constructor 
	public User(String type) {
		
		this.userType = type;
	}
	
	
	void viewCourseInfo() {
		
		//something like call this.courseObj.method and have it return a collection with all courses and their info. 
	}
	
	
	/**
	 * toString here will be overridden in every class that extends user
	 */
	@Override
	public String toString() {
		return this.name;
	}

}
