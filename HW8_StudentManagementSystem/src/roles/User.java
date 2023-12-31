package roles;

import courses.Courses;
import files.FileInfoReader;

public abstract class User {
	
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
	
	
	//Courses courseObj;
	
	
	//constructor 
	public User(String type) {
		
		this.userType = type;
	}
	
	/**
	 * Method to display all courses.
	 * @param fr FileInfo to read
	 */
	public void viewAllCourseInfo(FileInfoReader fr) {
		//iterate over each course and print the course information
		for(Courses course : fr.getCourseInfo()) {
			 
			System.out.println(course);
		 }
	}
	
	/**
	 * Method to return course object for given courseID
	 * @param fr FileInfo to read
	 * @param id of course
	 * @return course information
	 */
	public Courses returnCourseObjFromID(FileInfoReader fr, String id) {
		//iterate over each course to find a match
		for(Courses course: fr.getCourseInfo()) {
			//return the course info if a match is found
			if(course.getCourseID().equals(id)) {
				return course;
			}		
		}
		return null;
	}
	
	
	/**
	 * toString here will be overridden in every class that extends user
	 */
	@Override
	public String toString() {
		return (this.id + " " + this.name);
	}
	

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
	
	
	
	@Override
	/**
	 * Method to compare User objects.
	 */
	public boolean equals(Object o) {
		
		User otherUser = (User) o;
		
		if((this.id.equals(otherUser.id)) && (this.name.equals(otherUser.name))) {
			return true;
		}
		
		return false;
	}
	
	

}
