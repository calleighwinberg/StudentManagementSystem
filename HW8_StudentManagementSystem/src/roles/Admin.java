package roles;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	//IS THIS METHOD USED OR JUST THE ONE IN THE CONTROLLER CLASS?????
	/**
	 * Method to add a new student.
	 * @param fr FileReaderInfo to read
	 * @param studentInfo to add student
	 * @return new student
	 */
	public Student addStudent(FileInfoReader fr, String studentInfo) {
		//Create new student with given studentInfo
		Student newStudent = new Student(studentInfo);
		//Add it to the ArrayList
		fr.getStudentInfo().add(newStudent);
		
		return newStudent;
		
	}
	
	/**
	 * Method to delete an existing course.
	 * @param fr FileReaderInfo to read
	 * @param courseID to delete
	 * @return true if course deleted, otherwise false
	 */
	public boolean deleteCourse(FileInfoReader fr, String courseID) {
		//check if course ID exists and remove it
		if(fr.getCourseInfo().remove(this.returnCourseObjFromID(fr, courseID))) {
			//Print friendly message if successful
			System.out.println("Course successfully removed.");
			return true;
		}
		//Otherwise, print friendly message that course doesn't exist
		System.out.println("That course doesn't exist.");
		return false;
	}
	
	/**
	 * Method to delete an existing professor.
	 * @param fr FileReaderInfo to read
	 * @param profID to delete
	 * @return true if professor deleted, otherwise false
	 */
	public boolean deleteProfessor(FileInfoReader fr, String profID) {
		//check if professor ID exists and remove it
		if(fr.getProfessorInfo().remove(this.returnProfessorObjFromID(fr, profID))) {
			//print friendly message if successful
			System.out.println("Professor successfully removed.");
			return true;
		}
		//Otherwise, print friendly message that professor doesn't exist
		System.out.println("That professor ID doesn't exist.");
		return false;
	}
	
	/**
	 * Method to delete an existing student
	 * @param fr FileReaderInfo to read
	 * @param studentID to delte
	 * @return true if student deleted, otherwise false
	 */
	public boolean deleteStudent(FileInfoReader fr, String studentID) {
		//check if student ID exists and remove it
		if(fr.getStudentInfo().remove(this.returnStudentObjFromID(fr, studentID))) {
			//print friendly message if successful
			System.out.println("Student successfully removed.");
			return true;
		}
		//Otherwise, print friendly message that student doesn't exist
		System.out.println("That student ID doesn't exist.");
		return false;
	}
	//SHOULD THIS BE DELETED?????
	public boolean meetsCourseIDNamingConvention(String courseID) { //still needs to be implemented 
		
		return true;
	}
		
	/**
	 * Method to use given professor's username to get the professor object.
	 * @param fr FileReaderInfo to read
	 * @param username to find
	 * @return the professor object, else null
	 */
	public Professor returnProfessorObjFromUsername(FileInfoReader fr, String username) {
		//iterate over professors and match given username with stored usernames
		for(Professor professor : fr.getProfessorInfo()) {
			if(professor.getUsername().equals(username)) {
				return professor;
			}
		}
		return null;
	}
	
	/**
	 * Method to use given professor's ID to get the professor object.
	 * @param fr FileReaderInfo to read
	 * @param lecturerID to find
	 * @return the professor object, else null
	 */
	public Professor returnProfessorObjFromID(FileInfoReader fr, String lecturerID) {
		//iterate over professors and match given professor ID with stored IDs
		for(Professor professor : fr.getProfessorInfo()) {
			if(professor.getId().equals(lecturerID)) {
				return professor;
			}
		}
		return null;
	}
	
	/**
	 * Method to use given student's username to get the student object.
	 * @param fr FileReaderInfo to read
	 * @param username to find
	 * @return the student object, else null
	 */
	public Student returnStudentObjFromUsername(FileInfoReader fr, String username) {
		//iterate over students and match the given student username with stored usernames
		for(Student student : fr.getStudentInfo()) {
			if(student.getUsername().equals(username)) {
				return student;
			}
		}
		return null;
	}
	
	/**
	 * Method to use given student ID to get the student object.
	 * @param fr FileReaderInfo to read
	 * @param studentID to find
	 * @return the student object, else null
	 */
	public Student returnStudentObjFromID(FileInfoReader fr, String studentID) {
		//iterate over students and match the given student ID with stored IDs
		for(Student student : fr.getStudentInfo()) {
			if(student.getId().equals(studentID)) {
				return student;
			}
		}
		return null;
	}
	
	/**
	 * Method to use given user ID (professor or student) to get that object.
	 * @param fr FileReaderInfo to read
	 * @param userID to find
	 * @param type in which to search
	 * @return that object, else null
	 */
	public User returnUserObjFromID(FileInfoReader fr, String userID, String type) {
		//if the user ID belongs to professor, iterate over professors and match the given ID with stored IDs
		if(type == "Professor") {
			for(Professor user : fr.getProfessorInfo()) {
				if(user.getId().equals(userID)) {
					return user;
				}
			}
			return null;
		}
		//if the user ID belongs to a student, iterate over students and match the given ID with stored IDs
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
	
	/**
	 * Method to add a professor by an admin.
	 * @param fr FileReaderInfo to read
	 * @param id to add
	 * @param name to add
	 * @param username to add
	 * @param password to add
	 * @return new professor
	 */
	public Professor addProfessor(FileInfoReader fr, String id, String name, String username, String password) {
		Professor professor = new Professor(id, name, username, password);
		fr.getProfessorInfo().add(professor);
		return professor;
	}
	
	/**
	 * Method using regex to validate time input by user.
	 * @param time to validate
	 * @return true if valid, otherwise false
	 */
	public boolean isValidTime(String time) {
		//check if time input is in military time (HH:MM)
		String regex = "^([01]?\\d|2[0-3]):([0-5]?\\d)$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(time);
		return m.matches();
	}
	
	/**
	 * Method using regex to validate courseID input by user.
	 * @param courseID to validate
	 * @return true if valid, otherwise false
	 */
	public boolean isValidCourseID(String courseID) {
		//check if courseID input is three capital letters and 3 digits
		String regex = "^[A-Z]{3}\\d{3}$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(courseID);
		return m.matches();
	}
	
	/**
	 * Method using regex to validate userID input by user.
	 * @param userID to validate
	 * @return true if valid, otherwise false
	 */
	public boolean isValidUserID(String userID) {
		//check if userID is 3 digits
		String regex = "^\\d{3}$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(userID);
		return m.matches();
	}
	
	/**
	 * Method using regex to validate letter grade input by user.
	 * @param grade to validate
	 * @return true if valid, otherwise false
	 */
	public boolean isValidGrade(String grade) {
		//check if grade is standard letter grade
		String regex = "^[ABCD][+-]?$|^F$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(grade);
		return m.matches();
	}
}
