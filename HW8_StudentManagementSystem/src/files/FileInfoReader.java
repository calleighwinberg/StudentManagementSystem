package files;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import courses.Courses;


public class FileInfoReader {
	
	/**
	 * how should the info be read in? I'm thinking we put the array of each set of objects here and have 
	 * getter to get it in other classes.
	 */
	
	/**
	 * List of professor objects.
	 */
	private ArrayList<Professor> professorInfo = new ArrayList<Professor>();
	
	/**
	 * List of course objects.
	 */
	private ArrayList<Courses> courseInfo = new ArrayList<Courses>();
	
	
	/**
	 * List of student objects.
	 */
	private ArrayList<Student> studentInfo = new ArrayList<Student>();
	
	
	/**
	 * List of admin objects.
	 */
	private ArrayList<Admin> adminInfo = new ArrayList<Admin>();
	
	
	
	/**
	 * read in each line of the professor file and create an array (or other collection) of Professor objects using 
	 * each line of the file as the constructor. All parsing of the info can be done in the professor class. 
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 */
	public static ArrayList<Professor> readProfessorFile(String fileName) throws FileNotFoundException {
		
	}
	
	
	public static ArrayList<Courses> readCoursesFile(String fileName) throws FileNotFoundException {
		
		Courses course;
		
		ArrayList<String> coursesString = new ArrayList<String>();
		
		//i'm thinking we just read each string, create an object of the courses class with string in the constructor, and then do 
		//parsing there. 
		
		ArrayList<Courses> courses = new ArrayList<Courses>();
		
		
		return courses;
		
	}


	/**
	 * @return the professorInfo
	 */
	public ArrayList<Professor> getProfessorInfo() {
		return professorInfo;
	}


	/**
	 * @return the courseInfo
	 */
	public ArrayList<Courses> getCourseInfo() {
		return courseInfo;
	}


	/**
	 * @return the studentInfo
	 */
	public ArrayList<Student> getStudentInfo() {
		return studentInfo;
	}


	/**
	 * @return the adminInfo
	 */
	public ArrayList<Admin> getAdminInfo() {
		return adminInfo;
	}
	
	
	

}
