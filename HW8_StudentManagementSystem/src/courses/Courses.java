package courses;

import java.util.ArrayList;

import files.FileInfoReader;
import roles.Student;
import roles.User;

public class Courses {
	
	//instance variables 
	
	private String courseID;
	
	private String courseName;
	
	private int courseStart;
	
	private int courseEnd;
	
	private String courseDays;
	
	private String courseProfessor;
	
	private String courseCapacity;
	
	private int lecturerID;
	
	private int numStudentsEnrolled = 0;
	
	ArrayList<Student> studentsEnrolled = new ArrayList<Student>();
	
	/**
	 * Constructor #1 takes a string and parses the info into separate parts. Then initializes each instance variable with the info.
	 */ 
	public Courses(String courseInfo) {
		
		String [] array = courseInfo.trim().split(";");
		
		//set each item in the array to an instance variable based upon it's known index position, which will not change
		this.setCourseID(array[0].trim());
		
		this.setCourseName(array[1].trim());
		
		this.setCourseProfessor(array[2].trim());
		
		this.setCourseDays(array[3].trim());
		
		this.setCourseStart(array[4].trim());
		
		this.setCourseEnd(array[5].trim());
		
		this.setCourseCapacity(array[6].trim());		
	}
	
	
	/**
	 * Constructor #2 used by admin when they're adding a new course.
	 */ 
	public Courses(String courseID, String courseName, String courseProf, String courseDate, String courseStart, String courseEnd, String courseCapacity) {
		
		//set each item to an instance variable
		this.courseID = courseID;
		
		this.courseName = courseName;
		
		this.courseProfessor = courseProf;
		
		this.courseDays = courseDate;
		
		this.courseStart = this.setCourseStart(courseStart); 
		
		this.courseEnd = this.setCourseEnd(courseEnd);
	
		this.courseCapacity = courseCapacity;		
	}
	
	
	//SHOULD WE DELETE THIS????
	/**
	 * Method uses the given lecturerID to retrieve the lecturer.  
	 * @param lecturerID
	 * @return
	 */
	String retrieveCourseLecturer(int lecturerID) {
		
		return "";
	}
	
	/**
	 * Method checks for time conflict of given course
	 * @param course to check for conflict
	 * @return true if there is a conflict, otherwise false
	 */
	public boolean timeConflict(Courses course) {
		//check if the course days are the same
		if(this.courseDays.equals(course.getCourseDays())) {
			//check if the start times are the same
			if(this.courseStart == course.courseStart) {
				return true; //there is a conflict
			//check if course start time is later	
			} else if(this.courseStart > course.courseStart) {
				//check if course start time is before other course end time
				if(course.courseEnd > this.courseStart) {
					return true; //there is a conflict
				}
			//check if course start time is before other course start time	
			} else if(this.courseStart < course.courseStart) {
				//check if course end time is after other course start time
				if(this.courseEnd > course.courseStart) {
					return true; //there is a conflict
				}
			}			
		}		
		return false; //no time conflicts found
	}
	
	
	@Override
	/**
	 * Override toString method for printing course info
	 */
	public String toString() {
		
		//return a string in the format CIT590 | Programming Languages, 16:30-18:00 on MW, with course capacity 110, students: 0, 
		//lecturer: Prof Brandon 
		
		return (this.courseID + "|" + this.courseName + ", " + this.getCourseStart() + "-" + this.getCourseEnd() + " on " + this.courseDays + 
				", with course capacity: " + this.courseCapacity + ", students: " + this.numStudentsEnrolled + ", lecturer: Professor " + 
				this.courseProfessor);
	}

	
	//getters and setters 

	/**
	 * @return the courseID
	 */
	public String getCourseID() {
		return courseID;
	}


	/**
	 * @param courseID the courseID to set
	 */
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}


	/**
	 * @return the courseName
	 */
	public String getCourseName() {
		return courseName;
	}


	/**
	 * @param courseName the courseName to set
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}


	/**
	 * @return the courseStart time
	 */
	public String getCourseStart() {
		//find the hours and minutes for the course
		int minutes = this.courseStart % 60;
		int hours = (this.courseStart - minutes) / 60;
		//convert into a string
		String minutesString = "" + minutes;
		String hoursString = "" + hours;
		
		if(hours < 10) {
			hoursString = "0" + hours;
		}
		if(minutes < 10) {
			minutesString = "0" + minutes;
		}
		if(minutes == 0) {
			minutesString = "00";
		}
		
		return (hoursString + ":" + minutesString);
	}


	/**
	 * @param courseStart the courseStart to set
	 */
	public int setCourseStart(String courseStart) {
		//split the start times by ':'
		String[] array = courseStart.split(":");
		//cast the appropriate index to an integer for hours and minutes
		int hour = Integer.parseInt(array[0]);
		int minute = Integer.parseInt(array[1]);
		//find the total for course lecture
		int totalTime = (hour*60) + minute;
		//set the course start time
		this.courseStart = totalTime;
		
		return totalTime;
	}


	/**
	 * @return the courseEnd time
	 */
	public String getCourseEnd() {
		//find the hours and minutes for the course
		int minutes = this.courseEnd % 60;
		int hours = (this.courseEnd - minutes) / 60;
		//convert into a string
		String minutesString = "" + minutes;
		String hoursString = "" + hours;
		
		if(hours < 10) {
			hoursString = "0" + hours + ":";
		}
		if(minutes < 10) {
			minutesString = "0" + minutes + ":";
		}
		if(minutes == 0) {
			minutesString = "00";
		}
		
		return (hoursString + ":" + minutesString);
	}
		
	
	/**
	 * @param courseEnd the courseEnd to set
	 */
	public int setCourseEnd(String courseEnd) {
		//split the end time by ':'
		String[] array = courseEnd.split(":");
		//cast the appropriate index to an integer for hours and minutes
		int hour = Integer.parseInt(array[0]);
		int minute = Integer.parseInt(array[1]);
		//find the total time for course lecture
		int totalTime = (hour*60) + minute;

		this.courseEnd = totalTime;
		
		return totalTime;		
	}


	/**
	 * @return the courseDays
	 */
	public String getCourseDays() {
		return courseDays;
	}


	/**
	 * @param courseDays the courseDays to set
	 */
	public void setCourseDays(String courseDays) {
		this.courseDays = courseDays;
	}


	/**
	 * @return the courseCapacity
	 */
	public String getCourseCapacity() {
		return courseCapacity;
	}


	/**
	 * @param courseCapacity the courseCapacity to set
	 */
	public void setCourseCapacity(String courseCapacity) {
		this.courseCapacity = courseCapacity;
	}


	/**
	 * @return the lecturerID
	 */
	public int getLecturerID() {
		return lecturerID;
	}


	/**
	 * @param lecturerID the lecturerID to set
	 */
	public void setLecturerID(int lecturerID) {
		this.lecturerID = lecturerID;
	}


	/**
	 * @return the numStudentsEnrolled
	 */
	public int getNumStudentsEnrolled() {
		return numStudentsEnrolled;
	}


	/**
	 * @param numStudentsEnrolled the numStudentsEnrolled to set
	 */
	public void setNumStudentsEnrolled(int numStudentsEnrolled) {
		this.numStudentsEnrolled = numStudentsEnrolled;
	}

	/**
	 * @return the course professor
	 */
	public String getCourseProfessor() {
		return courseProfessor;
	}

	/**
	 * @param courseProfessor to set
	 */
	public void setCourseProfessor(String courseProfessor) {
		this.courseProfessor = courseProfessor;
	}
	
	
	/**
	 * @return the studentsEnrolled
	 */
	public ArrayList<Student> getStudentsEnrolled() {
		return studentsEnrolled;
	}


	/**
	 * @param studentsEnrolled the studentsEnrolled to set
	 */
	public void setStudentsEnrolled(ArrayList<Student> studentsEnrolled) {
		this.studentsEnrolled = studentsEnrolled;
	}

  
	@Override
	/**
	 * Override the equals method
	 */
	public boolean equals(Object o) {
		
		Courses otherCourse = (Courses) o;
		
		return this.courseID.equals(otherCourse.getCourseID());
	}
}
