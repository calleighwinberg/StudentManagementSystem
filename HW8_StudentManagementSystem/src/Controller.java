import files.FileInfoReader;

import java.util.List;

import courses.Courses;
import roles.Admin;
import roles.Professor;
import roles.Student;

public class Controller {
	
	//create instance of FileInfoReader so we can access files and arrays 
	FileInfoReader fileInfoReader = new FileInfoReader();
	
	
	//these are isntance variables that prof has:
	Student student;
	Professor prof;
	Admin admin;
	List<Courses> courseList;
	List<Professor> profList;
	List<Admin> adminList;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//create isntance of controller class 
		Controller ct = new Controller();
		
		ct.fileInfoReader.readFile("profInfo.txt");
		System.out.println(ct.fileInfoReader.getProfessorInfo());
		System.out.println(ct.fileInfoReader.getProfessorInfo().get(0).getUserType());
		System.out.println(ct.fileInfoReader.getProfessorInfo().get(0).getName());
		System.out.println(ct.fileInfoReader.getProfessorInfo().get(0).getId());
		System.out.println(ct.fileInfoReader.getProfessorInfo().get(0).getUsername());
		System.out.println(ct.fileInfoReader.getProfessorInfo().get(0).getPassword());

	}
	
	/**
	 * a professor logs in and sees menu
	 * 
	 * professor.viewgivenCourse(Course Object);
	 * courseObject.viewCourse(int professor.getID)
	 */
	
	
	/**
	 * Prints a list of all courses
	 * */
	public void printAllCourses() {
		System.out.println(fileInfoReader.getCourseInfo()); // getCourseInfo returns the Array List, which when called for
														// printing calls the toString method in Course
	}


}
