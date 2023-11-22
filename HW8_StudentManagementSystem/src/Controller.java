import files.FileInfoReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import courses.Courses;
import roles.Admin;
import roles.Professor;
import roles.Student;
import roles.User;

public class Controller {
	
	//create instance of FileInfoReader so we can access files and arrays 
	FileInfoReader fr = new FileInfoReader();
	
	
	//these are instance variables that prof has:
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
		
		ct.fr.readFile("courseInfo.txt");
		ct.fr.readFile("profInfo.txt");
		ct.fr.readFile("studentInfo.txt");
		ct.fr.readFile("adminInfo.txt");
		
		Scanner scanner = new Scanner(System.in);
	
		ct.initialLogin(scanner);
		
		scanner.close();
		
		System.out.println("Goodbye!");
		

	        

		}
		
	/**
	 * a professor logs in and sees menu
	 * 
	 * professor.viewgivenCourse(Course Object);
	 * courseObject.viewCourse(int professor.getID)
	 */

	
	private void initialLogin(Scanner scanner) {
		
		boolean systemRunning = true;
		
		while(systemRunning) {
			
			System.out.println("-------------------------\n" + "Student Management System\n" + "-------------------------\n" + "1 -- Login as student\n" 
					+ "2 -- Login as professor\n" + "3 -- Login as admin\n" + "4 -- Quit the system\n");
			
			System.out.println("Please enter your option, eg. '1' ");
	
	        String option = scanner.next().trim();
	        
			if(option.equals("1")) {
				System.out.println("Student");
				this.loginStudent(scanner);
				//this.loginStudent(fr, fr.getStudentInfo());
			} 
			
			else if(option.equals("2")) {
				System.out.println("prof");
				this.loginProf(scanner);
				
			}
			
			else if(option.equals("3")) {
				
				System.out.println("admin");
				
				
				
				this.loginAdmin(scanner);
				
			}
			else if(option.equals("4")) {
				System.out.println("quit");
				systemRunning = false;
			}
		}
	}
	
	

	private void loginStudent(Scanner scanner) {
		boolean usernameFound = false;
		boolean passwordFound = false;
		
		while(!(usernameFound)) {
			
			System.out.println("Please enter your username: ");
			
			String usernameInput = scanner.next().trim();
			
			for(Student student : fr.getStudentInfo()) {
				
				if(student.getUsername().equals(usernameInput)) {
					
					while(!(passwordFound)) {
						
						System.out.println("Please enter your password: ");
						
						String passwordInput = scanner.next();
						
						if(student.getPassword().equals(passwordInput)) {
							
							this.studentView(student, scanner);
							
							passwordFound = true;
						}
					}
					
					usernameFound = true;
				}
			}
		}
	}
	
	
	private void loginProf(Scanner scanner) {
		boolean usernameFound = false;
		boolean passwordFound = false;
		
		while(!(usernameFound)) {
			
			System.out.println("Please enter your username: ");
			
			String usernameInput = scanner.next().trim();
			
			for(Professor professor : fr.getProfessorInfo()) {
				
				if(professor.getUsername().equals(usernameInput)) {
					
					while(!(passwordFound)) {
						
						System.out.println("Please enter your password: ");
						
						String passwordInput = scanner.next();
						
						if(professor.getPassword().equals(passwordInput)) {
							
							this.profView(professor, scanner);
							
							passwordFound = true;
						}
					}
					
					usernameFound = true;
				}
			}
		}
	}
	
	private void loginAdmin(Scanner scanner) {
		// can i pass in generic arrayList<Object> and thens et to either stufent prof or admin 
		
		//promt for username
		
		boolean usernameFound = false;
		boolean passwordFound = false;
		
		while(!(usernameFound)) {
			
			System.out.println("Please enter your username: ");
			
			String usernameInput = scanner.next().trim();
			
			for(User admin : fr.getAdminInfo()) {

				if(admin.getUsername().equals(usernameInput)) {
					
					while(!(passwordFound)) {
						
						System.out.println("Please enter your password: ");
						
						String passwordInput = scanner.next();
						
						if(admin.getPassword().equals(passwordInput)) {
							
							//if(user.getUserType == addmin) then cas tto Admin and call adminView 
							
							//call method for admin experience. first need to cast to an Admin obj 
							this.adminView((Admin)admin, scanner);
							
							passwordFound = true;
						}
					}
					
					usernameFound = true;
				}
			}	
		}
		
		//this doesnt need to be here, just testing 
		System.out.println("once quit, return to main menu");
	
	}
	
	
	private void adminView(Admin admin, Scanner scanner) {
			
		boolean adminViewRunning = true;
		
		while(adminViewRunning) {
			
			System.out.println("-------------------------\n" + "Welcome, " + admin.getName() + "\n" + "-------------------------\n" + "1 -- View all courses\n" 
					+ "2 -- Add new courses\n" + "3 -- Delete courses\n" + "4 -- Add new professor\n" + "5 -- Delete professor\n" + "6 -- Add new student\n" 
					+ "7 -- Delete student\n" + "8 -- Return to previous menu\n");
			
			System.out.println("Please enter your option, eg. '1' ");
			
			String option = scanner.next();
			

	        
			if(option.equals("1")) {
				admin.viewAllCourseInfo(fr);

			} 
			
			else if(option.equals("2")) {
				this.addCourse(admin, scanner);
			}
	
			else if(option.equals("3")) {
				
				System.out.println("delete course");
				
			}
			else if(option.equals("4")) {
				this.addProfessor(admin, scanner);
				
			}
			else if(option.equals("5")) {
				
				System.out.println("delete prof");
				
			}
			else if(option.equals("6")) {
				
				System.out.println("add student");
				
			}
			else if(option.equals("7")) {
				
				System.out.println("delete student");
				
			}
			else if(option.equals("8")) {
				System.out.println("return to previous");
				adminViewRunning = false;
				initialLogin(scanner);
			}
		}
		
	}
	
	private void profView(Professor professor, Scanner scanner) {
		
		boolean profViewRunning = true;
		
		while(profViewRunning) {
			
			System.out.println("-------------------------\n" + "Welcome, " + professor.getName() + "\n" + "-------------------------\n" + "1 -- View given courses\n" 
					+ "2 -- View student list of the given course\n" + "3 -- Return to the previous menu\n");
			
			System.out.println("Please enter your option, eg. '1' ");
			
			String option = scanner.next();
			
			if(option.equals("1")) {
				System.out.println("view course");
				ArrayList<Courses> givenCourses = professor.ViewGivenCourses(fr.getCourseInfo());
				System.out.println("----------The Course List----------");
				for (Courses course : givenCourses) {
					System.out.println(course);
				}
				

			} 
			
			else if(option.equals("2")) {
				System.out.println("view student list");
				System.out.println("Please enter the course ID, eg. 'CIT590'. ");
				System.out.println("Or enter 'q' to return to the previous menu. ");
				String listOfStudents = scanner.next().trim();
				if (listOfStudents.equals("q")) {
					continue;	
				} else {
					String studentList = professor.viewStudentListOfGivenCourse(fr, listOfStudents);
					System.out.println(studentList);
				}
				
			}
			
			else if(option.equals("3")) {
				
				System.out.println("return to previous");
				profViewRunning = false;
				//initialLogin(scanner);
			}
		}
	}
	
	private void studentView(Student student, Scanner scanner) {
		
		boolean studentViewRunning = true;
		
		while(studentViewRunning) {
			System.out.println("");
			System.out.println("-------------------------\n" + "Welcome, " + student.getName() + "\n" + "-------------------------\n" + "1 -- View all courses\n" 
					+ "2 -- Add courses to your list\n" + "3 -- View selected courses\n" + "4 -- Drop course in your list\n" + "5 -- View grades\n" + "6 -- Return to previous menu\n");
			
			System.out.println("Please enter your option, eg. '1' ");
			
			String option = scanner.next();
			
			if(option.equals("1")) {
				System.out.println("view all");
				student.viewAllCourseInfo(fr);

			} 
			
			else if(option.equals("2")) {
				System.out.println("add course");
				System.out.println("Please select the course ID you want to add to your list, eg. 'CIT590'. ");
				System.out.println("Or enter 'q' to return to the previous menu. ");
				String addCourseInput = scanner.next();
				if (addCourseInput.equals("q")) {
					continue;
				} else {
					student.addCourse(fr, addCourseInput);
				}
			}
			
			else if(option.equals("3")) {
				
				System.out.println("view course");
				student.viewEnrolledCourses();
				
			}
			else if(option.equals("4")) {
				
				System.out.println("drop course");
				student.viewEnrolledCourses();
				System.out.println("");
				System.out.println("Please enter the ID of the course which you want to drop, eg. 'CIT590'. ");
				System.out.println("Or enter 'q' to return to the previous menu. ");
				String dropCourseInput = scanner.next();
				if (dropCourseInput.equals("q")) {
					continue;
				} else {
					student.dropCourse(fr, dropCourseInput);
				}
				
			}
			else if(option.equals("5")) {
				
				System.out.println("view grades");
				Map<String,String> courseAndGrade = student.getPastCoursesAndGrades(fr); //need to assign to variable and print
				System.out.println("Here are the courses you have already taken, with your grade in a letter format.");
				for (Map.Entry<String, String> entry : courseAndGrade.entrySet()) {
					System.out.println("Grade of " + entry.getKey() + ": " + entry.getValue());
				}
				
			}
			else if(option.equals("6")) {
				
				System.out.println("return to previous");
				studentViewRunning = false;
				//initialLogin(scanner);
				
			}
		}
	}
	
	
	Professor addProfessor(Admin admin, Scanner scanner) {
		
		//String profID = "";
		boolean validProfID = false;
		//String courseID = "";
		
		while(!validProfID) {
			
			//retrieves profID
			System.out.println("Please enter the professor's ID, or type 'q' to end. ");
			String profID = scanner.next().trim();
			if (profID.equals("q")) {
				return null;
			}
			else if(!(admin.returnProfessorObjFromID(fr, profID) == null)) {
				System.out.println("The ID already exists");
			}
			//else if() {} something about regex for prof ID formatt. 3 numbers. can be used for ID of student as well
			
			else {
				validProfID = true;
				
				//retrieves prof Name
				System.out.println("Please enter the professor's name, or type 'q' to end. ");
				//need to clear the scanner buffer of the '\n' character that's leftover 
				scanner.nextLine();
				String profName = scanner.nextLine().trim();
				if (profName.equals("q")) {
					return null;
				}
				
				boolean validUsername = false;
				
				while(!validUsername) {
					
					//retrieves prof username
					System.out.println("Please enter the professor's username, or type 'q' to end. ");
					String profUsername = scanner.next().trim();
					if (profUsername.equals("q")) {
						return null;
					} 
					else if(!(admin.returnProfessorObjFromUsername(fr, profUsername) == null)) {
						System.out.println("The username is not available.");
					}
					//else if() {} something about regex for prof ID formatt. 3 numbers. can be used for ID of student as well
					
					else {
						validUsername = true;
						
						//retrieves prof password
						System.out.println("Please enter the professor's password, or type 'q' to end. ");
						String profPassword = scanner.next().trim();
						if (profPassword.equals("q")) {
							return null;
						} 
						
						Professor professor = admin.addProfessor(fr, profID, profName, profUsername, profPassword);
						System.out.println("Successfully added the new professor: " + professor.getId() + " " + professor.getName());
						
						return professor;					
					}					
				}
			}	
		}
		return null;
	}
	
	void addCourse(Admin admin, Scanner scanner) {
		
		boolean validCourseID = false;
		//String courseID = "";
		
		while(!validCourseID) {
			
			//retrieves courseID
			System.out.println("Please enter the course ID, or type 'q' to end. ");
			String courseID = scanner.next().trim();
			System.out.println(courseID);
			if (courseID.equals("q")) {
				break;
				
			} 
			else if(!(admin.returnCourseObjFromID(fr, courseID) == null)){
				System.out.println("The course already exists.");
			}
			else if(!(admin.meetsCourseIDNamingConvention(courseID))) { //we could implement regex here or just leave it out, doesn't matter
				System.out.println("Please use format 'CIS'/'CIT' followed by 3 numbers 0-9 ");
			}
			else {
				validCourseID = true;
				
				//retrieves courseName
				System.out.println("Please enter the course name, or type 'q' to end. ");
				//need to clear the scanner buffer of the '\n' character that's leftover 
				scanner.nextLine();
				String courseName = scanner.nextLine().trim();
				System.out.println(courseName);
				if (courseName.equals("q")) {
					break;	
				}
				
				//retrieves courseStart
				System.out.println("Please enter the course start time, or type 'q' to end. ");
				String courseStart = scanner.next().trim();
				if (courseStart.equals("q")) {
					break;	
				}
				
				//retrieves courseEnd
				System.out.println("Please enter the course end time, or type 'q' to end. ");
				String courseEnd = scanner.next().trim();
				if (courseEnd.equals("q")) {
					break;	
				}
				
				//retrieves courseDate
				System.out.println("Please enter the course date, or type 'q' to end. ");
				String courseDate = scanner.next().trim();
				if (courseDate.equals("q")) {
					break;	
				}
				
				//retrieves courseCapacity
				System.out.println("Please enter the course capacity, or type 'q' to end. ");
				String courseCapacity = scanner.next().trim();
				if (courseCapacity.equals("q")) {
					break;	
				}
				
				//retrieves courseLecturer's ID
				System.out.println("Please enter the course lecturer's ID, or type 'q' to end. ");
				String courseLecturerID = scanner.next().trim();
				if (courseLecturerID.equals("q")) {
					break;	
				}
				//retrieve course prof course the course ID
				Professor courseProf = admin.returnProfessorObjFromID(fr, courseLecturerID);
				
				if(courseProf == null) {
					
					System.out.println("That professor isn't in the system, please add the professor first.");
					
					courseProf = this.addProfessor(admin, scanner);
					
					if(courseProf == null) {
						break;
					}
				}					
				admin.addCourse(fr, courseID, courseName, courseProf.getName(), courseDate, courseStart, courseEnd, courseCapacity);		
			}	
		}			
	}

	

}
