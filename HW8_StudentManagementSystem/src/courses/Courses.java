package courses;

import java.util.ArrayList;

import files.FileInfoReader;
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
	
	FileInfoReader fr = new FileInfoReader();
	
	/**
	 * constructor 1 takes a string and parses the info into separate parts. Then initializes each instance variable with the info
	 */ 
	public Courses(String courseInfo) {
		
		String [] array = courseInfo.trim().split(";");
		
		//set each item in the array to an instance variable 
		this.setCourseID(array[0].trim());
		
		this.setCourseName(array[1].trim());
		
		this.setCourseProfessor(array[2].trim());
		
		this.setCourseDays(array[3].trim());
		
		this.setCourseStart(array[4].trim());
		
		this.setCourseEnd(array[5].trim());
		
		this.setCourseCapacity(array[6].trim());
		
	}
	
	
	/**
	 * constructor 2 will be used by admin when they're adding a new course
	 */ 
	public Courses(String courseID, String courseName, String courseStart, String courseEnd, String courseDate, int lecturerID, int courseCapacity) {
		
		this.courseID = courseID;
		
		this.courseName = courseName;
		
		this.courseStart = this.setCourseStart(courseStart); 
		
		this.courseEnd = this.setCourseEnd(courseEnd);
		///and so on 
		
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
	
	
	public boolean timeConflict(Courses course) {

			
			if(this.courseStart == course.courseStart) {
				return true;
				
			} else if(this.courseStart > course.courseStart) {
				
				if(course.courseEnd > this.courseStart) {
					return true;	
				}
				
			} else if(this.courseStart < course.courseStart) {
				
				if(this.courseEnd > course.courseStart) {
					return true;
				}
			}
			
			
		//}
		
		return false;
	}
	
	
	
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
	 * @return the courseStart
	 */
	public String getCourseStart() {
		
		int minutes = this.courseStart % 60;
		int hours = (this.courseStart - minutes) / 60;
		
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
	 * @param courseStart the courseStart to set
	 */
	public int setCourseStart(String courseStart) {
		
		String[] array = courseStart.split(":");
		
		int hour = Integer.parseInt(array[0]);
		int minute = Integer.parseInt(array[1]);
		
		int totalTime = (hour*60) + minute;

		this.courseStart = totalTime;
		
		return totalTime;
	}


	/**
	 * @return the courseEnd
	 */
	public String getCourseEnd() {
		
		int minutes = this.courseEnd % 60;
		int hours = (this.courseEnd - minutes) / 60;
		
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
		
		String[] array = courseEnd.split(":");
		
		int hour = Integer.parseInt(array[0]);
		int minute = Integer.parseInt(array[1]);
		
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


	public String getCourseProfessor() {
		return courseProfessor;
	}


	public void setCourseProfessor(String courseProfessor) {
		this.courseProfessor = courseProfessor;
	}
	
	
	@Override
	public boolean equals(Object o) {
		
		Courses otherCourse = (Courses) o;
		
		return this.courseID.equals(otherCourse.getCourseID());
	}
	
	
	
	

}
