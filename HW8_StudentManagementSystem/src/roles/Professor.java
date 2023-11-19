package roles;

import java.util.ArrayList;

import courses.Courses;
import files.FileInfoReader;

public class Professor extends User {
	
	static final String userType = "Professor";
	

	public Professor(String profInfo) {
		
		//call the User constructor first with userType
		super(userType);
		
		String [] array = profInfo.trim().split(";");
		
		//set each item in the array to an instance variable 
		this.setName(array[0].trim());
		
		this.setId(array[1].trim());
		
		this.setUsername(array[2].trim());
		
		this.setPassword(array[3].trim());
		

	}
	
	public ArrayList<Courses> ViewGivenCourses(ArrayList<Courses> allCourses) {
		
		ArrayList<Courses> profCourses = new ArrayList<Courses>();
		
		
		for(Courses course : allCourses) {
			
			if(course.getCourseProfessor().equals(this.getName())) {
				
				profCourses.add(course);
				
				//System.out.println(course);
			}
			
		}
		
		return profCourses;

	}
	
	
	public String viewStudentListOfGivenCourse(FileInfoReader fr, String courseID) {
		
		String studentList;
		
		Courses thisCourse = this.returnCourseObjFromID(fr, courseID);
		
		ArrayList<Courses> profCourses = this.ViewGivenCourses(fr.getCourseInfo());
		
		if(profCourses.contains(thisCourse)) {
			
			studentList = ("Students in course " + thisCourse.getCourseID() + " " + thisCourse.getCourseName()+ ":\n");
			
			
			//System.out.println("Students in course " + thisCourse.getCourseID() + " " + thisCourse.getCourseName()+ ":");
			
			for(Student student : thisCourse.getStudentsEnrolled()) {
				
				studentList += student.getId() + " " + student.getName() + "\n";
				
			}
			
		} else {
			studentList = "This course is not is your course list.";
		}
		
		//return studentsEnrolled;
		
		return studentList;
		
	}
	

}
