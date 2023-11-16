package roles;

import courses.Courses;

public class User {
	
	//instance variables 

	/**
	 * Represents the id for the user
	 */
	private String id;
	
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
	 *
	@Override
	public String toString() {
		return this.name;
	}*/
	

	//getters and setters

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}


	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}


	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}


	/**
	 * @param userType the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	

}
