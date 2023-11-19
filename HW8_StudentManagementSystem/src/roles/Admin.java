package roles;

import courses.Courses;
import files.FileInfoReader;

public class Admin extends User {
	
	
	static final String userType = "Administrator";
	

	public Admin(String adminInfo) {
		
		super(userType);
		
		String [] array = adminInfo.trim().split(";");
		
		//set each item in the array to an instance variable 
		this.setId(array[0].trim());
		
		this.setName(array[1].trim());
		
		this.setUsername(array[2].trim());
		
		this.setPassword(array[3].trim());

	}
	
	
	public Courses addCourse(FileInfoReader fr, String courseID, String courseName, String courseStart, String courseEnd, String courseDate, int lecturerID, int courseCapacity) {

		Courses newCourse = new Courses(courseID, courseName, courseStart, courseEnd, courseDate, lecturerID, courseCapacity);
			
		return newCourse;
		
	}
	
	public boolean okayToAddCourse(FileInfoReader fr, String courseID, String courseName, String courseStart, String courseEnd, String courseDate, int lecturerID, int courseCapacity) {
		
		
		
		return true;
	}
	
	
	public boolean deleteCourse(FileInfoReader fr, String courseID) {
		
		if(fr.getCourseInfo().remove(this.returnCourseObjFromID(fr, courseID))) {
			
			System.out.println("Course successfully removed.");
			return true;
		}

		System.out.println("That course doesn't exist.");
		return false;
	}
	
	
	
	
	
	
	

}
