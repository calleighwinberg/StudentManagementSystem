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
	
	
	public Courses addCourse(FileInfoReader fr, String courseID, String courseName, String courseProf, String courseStart, String courseEnd, String courseDate, String courseCapacity) {

		Courses newCourse = new Courses(courseID, courseName, courseProf, courseStart, courseDate, courseEnd, courseCapacity);
			
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
	
	
	public boolean meetsCourseIDNamingConvention(String courseID) {
		
		return true;
	}
	
	
	
	public Professor lecturerExistsInSystem(FileInfoReader fr, String lecturerID) {
		
		for(Professor professor : fr.getProfessorInfo()) {
			if(professor.getId().equals(lecturerID)) {
				return professor;
			}
		}
		return null;
	}
	
	
	public Professor addProfessor(FileInfoReader fr, String id, String name, String username, String password) {
		
		Professor professor = new Professor(id, name, username, password);
		
		fr.getProfessorInfo().add(professor);
		
		return professor;
	}
	
	
	
	
	
	
	

}
