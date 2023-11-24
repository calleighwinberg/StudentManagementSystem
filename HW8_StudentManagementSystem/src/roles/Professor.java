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
	
	/**
	 * This constructor will be called when the admin is creating a new professor 
	 * @param id
	 * @param name
	 * @param username
	 * @param password
	 */
	public Professor(String id, String name, String username, String password) {
		
		//call the User constructor first with userType
		super(userType);
		
		//set the instance variables
		this.setId(id);
		this.setName(name);
		this.setUsername(username);
		this.setPassword(password);
	}
	/**
	 * Method to find courses taught by given professor
	 * @param allCourses
	 * @return array of courses
	 */
	public ArrayList<Courses> ViewGivenCourses(ArrayList<Courses> allCourses) {
		//create array list to hold courses taught by given professor
		ArrayList<Courses> profCourses = new ArrayList<Courses>();
		//iterate over courses
		for(Courses course : allCourses) {
			//if the courses matches one assigned to given professor, add it to profCourses array list
			if(course.getCourseProfessor().equals(this.getName())) {
				
				profCourses.add(course);
			}			
		}		
		return profCourses;
	}
	
	/**
	 * Method to display list of students enrolled in this professor's given course
	 * @param fr FileReaderInfo to read
	 * @param courseID to search
	 * @return list of enrolled students
	 */
	public String viewStudentListOfGivenCourse(FileInfoReader fr, String courseID) {
		
		String studentList;
		//find the course object of given course DI
		Courses thisCourse = this.returnCourseObjFromID(fr, courseID);
		//create array list of courses for this professor
		ArrayList<Courses> profCourses = this.ViewGivenCourses(fr.getCourseInfo());
		//if the given course ID matches one taught by the professor
		if(profCourses.contains(thisCourse)) {
			//display message for given courseID
			studentList = ("Students in course " + thisCourse.getCourseID() + " " + thisCourse.getCourseName()+ ":\n");
			//iterate over list of students enrolled in given course and add them to string for message
			for(Student student : thisCourse.getStudentsEnrolled()) {
				
				studentList += student.getId() + " " + student.getName() + "\n";				
			}
			
		} else {
			studentList = "This course is not is your course list."; //if courseID doesn't match courses list for this professor
		}
		
		return studentList;
		
	}
}
