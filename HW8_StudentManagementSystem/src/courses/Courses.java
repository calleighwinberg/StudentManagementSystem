package courses;

public class Courses {
	
	//instance variables 
	
	private String courseID;
	
	private String courseName;
	
	private String courseStart;
	
	private String courseEnd;
	
	private String courseDate;
	
	private int courseCapacity;
	
	private int lecturerID;
	
	private int numStudentsEnrolled;
	
	/**
	 * constructor 1 takes a string and parses the info into separate parts. Then initializes each instance variable with the info
	 */ 
	public Courses(String courseInfo) {
		
	}
	
	
	/**
	 * constructor 2 will be used by admin when they're adding a new course
	 */ 
	public Courses(String courseID, String courseName, String courseStart, String courseEnd, String courseDate, int lecturerID, int courseCapacity) {
		
		this.courseID = courseID;
		
		this.courseName = courseName;
		
		this.courseStart = courseStart; ///and so on 
		
	}
	
	
	
	/**
	 * this method uses the given courses lecturerID to retrieve the lecturer. I'm thinking iterate through a list of lecturers and get
	 * their IDs until we find a match 
	 * @param lecturerID
	 * @return
	 */
	String retrieveCourseLecturer(int lecturerID) {
		
		return "";
	}
	
	
	
	public String toString() {
		
		//return a string in the format CIT590 | Programming Languages, 16:30-18:00 on MW, with course capacity 110, students: 0, 
		//lecturer: Prof Brandon 
		
		return "";
	}

}
