package roles;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.Test;

import courses.Courses;
import files.FileInfoReader;

public class Student extends User {

	static final String userType = "Student";
	
	private String pastCoursesAndGrades;
	
	private ArrayList<Courses> enrolledCourses = new ArrayList<Courses>();
	

	public Student(String studentInfo) {
		
		super(userType);
		
		String [] array = studentInfo.trim().split(";");
		
		//set each item in the array to an instance variable 
		this.setId(array[0].trim());
		
		this.setName(array[1].trim());
		
		this.setUsername(array[2].trim());
		
		this.setPassword(array[3].trim());
		
		//in the case that the admin doesn't enter past grades, if have a try/catch to handle. 
		try {
			this.setPastCoursesAndGrades(array[4].trim());	
		}
		catch(ArrayIndexOutOfBoundsException e) {
			this.setPastCoursesAndGrades(null);
		}
		
		
		
	}
	
	/**
	 * Method to check if given courseID is valid to add.
	 * @param fr FileInfo to read
	 * @param courseID to add
	 * @return true if course added, otherwise false
	 */
	boolean okToAddCourse(FileInfoReader fr, String courseID) {
		
		Courses thisCourse = this.returnCourseObjFromID(fr, courseID);
		//check if the course exists
		if (thisCourse == null) {
			//print friendly message
			System.out.println("The course you selected does not exist.");
			return false;			
		} 
		//check if this student is already enrolled in given course
		//iterate over this student's enrolled courses
		for(Courses course : this.enrolledCourses) {
			if(thisCourse.equals(course)) {
				System.out.println("The course you selected is already in your list"); //print friendly message
				return false;
			}
		}
		//check if the given course would create a time conflict
		//iterate over this student's enrolled courses
		for(Courses course : this.enrolledCourses) {
			//call time conflict method to check if it's okay to add given course
			if(thisCourse.timeConflict(course)) {
				System.out.println("The course you selected has a time conflict with " + course.getCourseID() + " " + course.getCourseName()); //print friendly message
				return false;
			}
		}
		//check if the given course has capacity
		if(!(Integer.parseInt(thisCourse.getCourseCapacity()) > thisCourse.getNumStudentsEnrolled())) {
			//print friendly message
			System.out.println("Sorry, " + thisCourse.getCourseID() + " " + thisCourse.getCourseName() + " is full.");
			
			return false;
		}
		//otherwise, the given course can be added to this student
		System.out.println("Course added successfully");
		return true;
	}
	
	/**
	 * Method to add given course to this student.
	 * @param fr FileInfo to read
	 * @param courseID to add
	 */
	public void addCourse(FileInfoReader fr, String courseID) {
		//check if given course is okay to add
		if(this.okToAddCourse(fr, courseID)) {
			//find the course object for the given courseID
			Courses course = this.returnCourseObjFromID(fr, courseID);
			//add the course
			this.enrolledCourses.add(course);
			//increment number of students enrolled in given course
			course.setNumStudentsEnrolled(course.getNumStudentsEnrolled() + 1);
			//add student to list of enrollment for given course
			course.getStudentsEnrolled().add(this);

		}
	}
	
	/**
	 * Method to drop given course for this student.
	 * @param fr FileInfo to read
	 * @param courseID to drop
	 */
	public void dropCourse(FileInfoReader fr, String courseID) {
		//find the course object for the given courseID
		Courses thisCourse = this.returnCourseObjFromID(fr, courseID);
		
		//check if the student is enrolled in the given course
		if(this.enrolledCourses.remove(thisCourse)) {
			System.out.println("Course dropped successfully");
			//decrement the number of students enrolled in that specific class
			thisCourse.setNumStudentsEnrolled(thisCourse.getNumStudentsEnrolled() - 1);
			
		} else {
			System.out.println("The course you selected is not in your schedule");
		}
	}
	
	/**
	 * Method to display enrolled courses for this student.
	 */
	public void viewEnrolledCourses() {
		
		System.out.println("The courses in your list:");
		
		//iterate through each course in the enrolledCourses array and print them. Calling print on the course obj calls the toString method 
		for(Courses course : this.getEnrolledCourses()) {
			System.out.println(course);
		}
	}
	


	/**
	 * @return returns a HashMap of the past courses and their grades 
	 */
	public Map<String, String>  getPastCoursesAndGrades(FileInfoReader fr) {
		
		//if the string for past grades is null, return null
		if(this.pastCoursesAndGrades == null) {
			return null;
		}
		
		//create a map to store the course ID/name and letter grade in 
		Map<String, String> courseAndGrade = new HashMap<String, String>();
		
		//take the course string (which contains all course/grades) and split it based on commas
		String array[] = this.pastCoursesAndGrades.trim().split(",");
		
		//for each course in the array, search for the course ID in the courses array until a match is found.
		for(String course : array) {
			//remove whitespace and split
			String arrayCourse[] = course.trim().split(":");
			//iterate over the array of course information
			for(Courses courseObj : fr.getCourseInfo()) {
				
				if(arrayCourse[0].trim().equals(courseObj.getCourseID())) {
					
					//concatenate the course ID and the course name as the key. Grade will be the value
					courseAndGrade.put(arrayCourse[0].trim() + " " + courseObj.getCourseName(), arrayCourse[1].trim());
				}
			}	
		}
		
		return courseAndGrade;
	}

	//getters and setters
	
	/**
	 * @param pastCoursesAndGrades the pastCoursesAndGrades to set
	 */
	public void setPastCoursesAndGrades(String pastCoursesAndGrades) {
		this.pastCoursesAndGrades = pastCoursesAndGrades;
	}

	/**
	 * @return the enrolledCourses
	 */
	public ArrayList<Courses> getEnrolledCourses() {
		return enrolledCourses;
	}

	/**
	 * @param enrolledCourses the enrolledCourses to set
	 */
	public void setEnrolledCourses(ArrayList<Courses> enrolledCourses) {
		this.enrolledCourses = enrolledCourses;
	}
	
}
