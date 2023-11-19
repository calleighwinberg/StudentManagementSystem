import files.FileInfoReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import courses.Courses;
import roles.Admin;
import roles.Professor;
import roles.Student;

public class Controller {
	
	//create instance of FileInfoReader so we can access files and arrays 
	FileInfoReader fr = new FileInfoReader();
	
	
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
		
		Scanner scanner = new Scanner(System.in);
		
		


	}
	
	/**
	 * a professor logs in and sees menu
	 * 
	 * professor.viewgivenCourse(Course Object);
	 * courseObject.viewCourse(int professor.getID)
	 */
	

	
	private void initialLogin(Scanner scan) {
		
		
		
		if(input == 1) {
			this.loginStudent(fr, fr.getStudentInfo());
		} 
		
		else if(input == 2) {
			
		}
		
		else if(input == 3) {
			
			//create hasmap of usernames and passwords for student 
			//for prof
			//foor user 
			
			this.loginAdmin(fr, fr.getAdminInfo());
			
		}
	}
	
	
	private void loginStudent(FileInfoReader fr2, ArrayList<Student> studentInfo) {
		// can i pass in generic arrayList<Object> and thens et to either stufent prof or admin 
		
		//promt for username
		
		
		
		for(Student student : studentInfo)) {
			if(student.getUsername().equals(usernameInput)) {
				
				//promt for pass
				if(student.getPassword().equals(passwordInput)) {
					
					//call method for student experience 
				}
			}
		}
		
	}
	
	private void loginAdmin(FileInfoReader fr2, ArrayList<Admin> adminInfo) {
		// can i pass in generic arrayList<Object> and thens et to either stufent prof or admin 
		
		//promt for username
		
		
		
		for(Admin admin : adminInfo) {
			if(student.getUsername().equals(usernameInput)) {
				
				//promt for pass
				if(admin.getPassword().equals(passwordInput)) {
					
					//call method for student experience 
				}
			}
		}
		
	}

	

}
