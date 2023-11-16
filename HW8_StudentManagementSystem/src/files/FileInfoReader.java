package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import courses.Courses;
import roles.Admin;
import roles.Professor;
import roles.Student;


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
	public void readFile(String fileName) {
		
		//create file object
		File file = new File(fileName);
		
		//define file reader
		FileReader fileReader = null;
		
		//define buffered reader
		BufferedReader bufferedReader = null;
		
		try {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			
			String line;
			
			while ((line = bufferedReader.readLine()) != null) {
				
				if(fileName.equals("profInfo.txt")) {
					
					//create a professor object initialized with the string of info from the file
					Professor professor = new Professor(line);
					
					
					//add the professor to the professorInfo array
					professorInfo.add(professor);
					
				}
				
				else if(fileName.equals("courseInfo.txt")) {
					
					//create a course object initialized with the string of info from the file
					Courses course = new Courses(line);
					
					
					//add the professor to the professorInfo array
					courseInfo.add(course);
					
				}
				
				
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		


	}
	
	
	
	/*public void readCourseFile(String fileName) {
		
		//create file object
		File file = new File(fileName);
		
		//define file reader
		FileReader fileReader = null;
		
		//define buffered reader
		BufferedReader bufferedReader = null;
		
		try {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			
			String line;
			
			while ((line = bufferedReader.readLine()) != null) {
				
				//create a course object initialized with the string of info from the file
				Courses course = new Courses(line);
				
				
				//add the professor to the professorInfo array
				courseInfo.add(course);
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}*/
	


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
