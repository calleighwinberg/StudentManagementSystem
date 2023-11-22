package roles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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
		
		this.setPastCoursesAndGrades(array[4].trim());		
	}
	
	
	
	
	
	
	
	boolean okToAddCourse(FileInfoReader fr, String courseID) {
		
		Courses thisCourse = this.returnCourseObjFromID(fr, courseID);
		
		if (thisCourse == null) {
			
			System.out.println("The course you selected does not exist.");
			return false;
			
		} 
		
		for(Courses course : this.enrolledCourses) {
			if(thisCourse.equals(course)) {
				System.out.println("The course you selected is already in your list");
				return false;
			}
		}
		
		for(Courses course : this.enrolledCourses) {
			if(thisCourse.timeConflict(course)) {
				System.out.println("The course you selected has a time conflict with " + course.getCourseID() + " " + course.getCourseName());
				return false;
			}
		}
		
		if(!(Integer.parseInt(thisCourse.getCourseCapacity()) > thisCourse.getNumStudentsEnrolled())) {
			
			System.out.println("Sorry, " + thisCourse.getCourseID() + " " + thisCourse.getCourseName() + " is full.");
			
			return false;
		}

		System.out.println("Course added successfully");
		return true;
	}
	
	
	
	
	public void addCourse(FileInfoReader fr, String courseID) {
		
		if(this.okToAddCourse(fr, courseID)) {
			
			Courses course = this.returnCourseObjFromID(fr, courseID);

			//System.out.println(this.getEnrolledCourses().size());
			
			this.enrolledCourses.add(course);
			
			course.setNumStudentsEnrolled(course.getNumStudentsEnrolled() + 1);
			course.getStudentsEnrolled().add(this);

		}

	}
	
	
	public void dropCourse(FileInfoReader fr, String courseID) {
		
		Courses thisCourse = this.returnCourseObjFromID(fr, courseID);
		
		//if we are able to remove the course from the enrolledCourses array
		if(this.enrolledCourses.remove(thisCourse)) {
			System.out.println("Course dropped successfully");
			//decrement the number of students enrolled in that specific class 
			thisCourse.setNumStudentsEnrolled(thisCourse.getNumStudentsEnrolled() - 1);
			
		} else {
			System.out.println("The course you selected is not in your schedule");
		}
	}
	
	
	
	
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
		
		//create a map to store the course ID/name and letter grade in 
		Map<String, String> courseAndGrade = new HashMap<String, String>();
		
		//take the course string (which contains all course/grades) and split it based on commas
		String array[] = this.pastCoursesAndGrades.trim().split(",");
		
		//for each course in the array, search for the course ID in the courses array until a match is found.
		for(String course : array) {
			
			String arrayCourse[] = course.trim().split(":");
			
			for(Courses courseObj : fr.getCourseInfo()) {
				
				if(arrayCourse[0].trim().equals(courseObj.getCourseID())) {
					
					//concatenate the course ID and the course name as the key. Grade will be the value
					courseAndGrade.put(arrayCourse[0].trim() + " " + courseObj.getCourseName(), arrayCourse[1].trim());
				}
			}	
		}
		
		
		//String coursesAndGrades = ("Here are the courses you have already taken, with your grade in a letter format \n" +
		//"Grade for " + array[0].trim() + "\n" + "Grade for " + array[1].trim());
		
		return courseAndGrade;
	}


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
