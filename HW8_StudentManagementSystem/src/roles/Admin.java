package roles;

import java.util.ArrayList;

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
	
	
	public Courses addCourse(FileInfoReader fr, String courseID, String courseName, String courseProf, String courseDate, String courseStart, String courseEnd, String courseCapacity) {

		//create a new course object with the given info
		Courses newCourse = new Courses(courseID, courseName, courseProf, courseDate, courseStart, courseEnd, courseCapacity);
		
		//find the professor object that will be teaching the created courses
		for(Professor professor : fr.getProfessorInfo()) {
			if(professor.getName().equals(courseProf)) {
				//iterate through all that professors course and see if the newCourse will pose a time conflict 
				for(Courses course : professor.ViewGivenCourses(fr.getCourseInfo())) {
					//if a time conflict is found, print statements 
					if(course.timeConflict(newCourse)) {
						System.out.println("The new added course has a time conflict with course: " + course);
						System.out.println("Unable to add new course: " + newCourse);
						return newCourse;
					}
				}
				//if not time conflict is found, add the course to the course array
				fr.getCourseInfo().add(newCourse);
				System.out.println("Successfully added the new course: " + newCourse);
			}
		}
		//the course created is returned mostly for testing purposes. We could leave this function void is needed 
		return newCourse;		
	}
	

	
	
	public boolean deleteCourse(FileInfoReader fr, String courseID) {
		
		if(fr.getCourseInfo().remove(this.returnCourseObjFromID(fr, courseID))) {
			
			System.out.println("Course successfully removed.");
			return true;
		}

		System.out.println("That course doesn't exist.");
		return false;
	}
	
	
	public boolean meetsCourseIDNamingConvention(String courseID) { //still needs to be implemented 
		
		return true;
	}
		
	
	public Professor returnProfessorObjFromUsername(FileInfoReader fr, String username) {
		
		for(Professor professor : fr.getProfessorInfo()) {
			if(professor.getUsername().equals(username)) {
				return professor;
			}
		}
		return null;
	}
	
	public Professor returnProfessorObjFromID(FileInfoReader fr, String lecturerID) {
		
		for(Professor professor : fr.getProfessorInfo()) {
			if(professor.getId().equals(lecturerID)) {
				return professor;
			}
		}
		return null;
	}
	
	public User returnUserObjFromID(FileInfoReader fr, String userID, String type) {
		
		if(type == "Professor") {
			for(Professor user : fr.getProfessorInfo()) {
				if(user.getId().equals(userID)) {
					return user;
				}
			}
			return null;
		}
		
		if(type == "Student") {
			for(Student user : fr.getStudentInfo()) {
				if(user.getId().equals(userID)) {
					return user;
				}
			}
			return null;
		}
		
		return null;
	}
	
	
	public Professor addProfessor(FileInfoReader fr, String id, String name, String username, String password) {
		
		Professor professor = new Professor(id, name, username, password);
		
		fr.getProfessorInfo().add(professor);
		
		return professor;
	}
	
	
	
	
	
	
	

}
