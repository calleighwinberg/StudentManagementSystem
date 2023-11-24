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
/**
 * Represents the class running the Student Management System. Contains the main method.
 */
public class Controller {
	
	//create instance of FileInfoReader so we can access files and arrays 
	FileInfoReader fr = new FileInfoReader();
	
	//instance variables
	Student student;
	Professor prof;
	Admin admin;
	List<Courses> courseList;
	List<Professor> profList;
	List<Admin> adminList;

	public static void main(String[] args) {
		
		//create instance of controller class 
		Controller ct = new Controller();
		//read in txt files
		ct.fr.readFile("courseInfo.txt");
		ct.fr.readFile("profInfo.txt");
		ct.fr.readFile("studentInfo.txt");
		ct.fr.readFile("adminInfo.txt");
		//create new scanner
		Scanner scanner = new Scanner(System.in);
		//load main screen - initial login
		ct.initialLogin(scanner);
		//close scanner after quits the program
		scanner.close();
		
		System.out.println("Goodbye!");		
		}
	
	/**
	 * Method for determining which user is logging into the Student Management System.
	 * @param scanner for user input
	 */
	private void initialLogin(Scanner scanner) {
		
		boolean systemRunning = true;
		//enter while loop until valid selection is made
		while(systemRunning) {
			//main menu for initial login
			System.out.println("-------------------------\n" + "Student Management System\n" + "-------------------------\n" + "1 -- Login as student\n" 
					+ "2 -- Login as professor\n" + "3 -- Login as admin\n" + "4 -- Quit the system\n");
			
			System.out.println("Please enter your option, e.g., '1'.");
			//remove whitespace from user input
	        String option = scanner.next().trim();
	        //based upon user selection, login as Student, Professor, Admin or quit.
			if(option.equals("1")) {
				this.loginStudent(scanner);
			} else if(option.equals("2")) {
				this.loginProf(scanner);
			} else if(option.equals("3")) {
				this.loginAdmin(scanner);
			} else if(option.equals("4")) {
				systemRunning = false;
			}
		}
	}

	/**
	 * Method for logging in Student user.
	 * @param scanner for user input
	 */
	private void loginStudent(Scanner scanner) {
		boolean usernameFound = false;
		boolean passwordFound = false;
		//enter while loop until valid username is entered
		while(!(usernameFound)) {
			
			System.out.println("Please enter your username: ");
			//remove whitespace from user input
			String usernameInput = scanner.next().trim();
			//iterate over each item in student info
			for(Student student : fr.getStudentInfo()) {
				//if we find an existing username, then proceed to password
				if(student.getUsername().equals(usernameInput)) {
					//enter while loop until valid password for given username is entered
					while(!(passwordFound)) {
						
						System.out.println("Please enter your password: ");
						//remove whitespace from user input
						String passwordInput = scanner.next();
						//if correct password is entered, proceed
						if(student.getPassword().equals(passwordInput)) {
							//log in this student user, call the studentView menu
							this.studentView(student, scanner);
							passwordFound = true;
						}
					}
					usernameFound = true;
				}
			}
		}
	}
	
	/**
	 * Method for logging in Professor user.
	 * @param scanner for user input
	 */
	private void loginProf(Scanner scanner) {
		boolean usernameFound = false;
		boolean passwordFound = false;
		//enter while loop until valid username is entered
		while(!(usernameFound)) {
			
			System.out.println("Please enter your username: ");
			//remove whitespace from user input
			String usernameInput = scanner.next().trim();
			//iterate over each item in professor info
			for(Professor professor : fr.getProfessorInfo()) {
				//if we find an existing username, then proceed to password
				if(professor.getUsername().equals(usernameInput)) {
					//enter while loop until valid password for given username is entered
					while(!(passwordFound)) {
						
						System.out.println("Please enter your password: ");
						//remove whitespace from user input
						String passwordInput = scanner.next();
						//if correct password is entered, proceed
						if(professor.getPassword().equals(passwordInput)) {
							//log in this professor user, call the profView menu
							this.profView(professor, scanner);
							passwordFound = true;
						}
					}					
					usernameFound = true;
				}
			}
		}
	}
	
	/**
	 * Method for logging in Admin user.
	 * @param scanner for user input
	 */
	private void loginAdmin(Scanner scanner) { 
		boolean usernameFound = false;
		boolean passwordFound = false;
		//enter while loop until valid username is entered
		while(!(usernameFound)) {
			
			System.out.println("Please enter your username: ");
			//remove whitespace from user input
			String usernameInput = scanner.next().trim();
			//iterate over each item in admin info
			for(Admin admin : fr.getAdminInfo()) {
				//if we find an existing username, then proceed to password
				if(admin.getUsername().equals(usernameInput)) {
					//enter while loop until valid password for given username is entered
					while(!(passwordFound)) {
						
						System.out.println("Please enter your password: ");
						//remove whitespace from user input
						String passwordInput = scanner.next();
						//if correct password is entered, proceed
						if(admin.getPassword().equals(passwordInput)) {
							//log in this admin user, call the adminView menu
							this.adminView((Admin)admin, scanner);							
							passwordFound = true;
						}
					}					
					usernameFound = true;
				}
			}	
		}
	}
	
	/**
	 * Method for displaying menu of possible admin actions and executing them.
	 * @param admin logged in
	 * @param scanner for user input
	 */
	private void adminView(Admin admin, Scanner scanner) {			
		boolean adminViewRunning = true;
		//enter while loop until admin is finished
		while(adminViewRunning) {
			//admin menu
			System.out.println("-------------------------\n" + "Welcome, " + admin.getName() + "\n" + "-------------------------\n" + "1 -- View all courses\n" 
					+ "2 -- Add new courses\n" + "3 -- Delete courses\n" + "4 -- Add new professor\n" + "5 -- Delete professor\n" + "6 -- Add new student\n" 
					+ "7 -- Delete student\n" + "8 -- Return to previous menu\n");		
			System.out.println("Please enter your option, e.g., '1'.");
			//remove whitespace from user input
			String option = scanner.next().trim();
	        //take appropriate action based upon user input
			if(option.equals("1")) {
				admin.viewAllCourseInfo(fr); //display all the courses
			} else if(option.equals("2")) {
				this.addCourse(admin, scanner); //call the addCourse method in the Controller class
			} else if(option.equals("3")) {				
				boolean courseDeleted = false;
				//enter while loop until valid course ID is entered for deletion or user quits
				while(!courseDeleted) {					
					System.out.println("Please enter the course ID for the course to delete: ");
					String courseIDDelete = scanner.next(); //remove whitespace from user input
					if (courseIDDelete.equals("q")) {
						break;
					}
					courseDeleted = admin.deleteCourse(fr, courseIDDelete);	//call deleteCourse method in admin class; returns true if completed
				}
			} else if(option.equals("4")) {
				this.addProfessor(admin, scanner); //call addProfessor method in Controller class
			} else if(option.equals("5")) {				
				boolean professorDeleted = false;
				//enter while loop until valid professor ID is entered for deletion or user quits
				while(!professorDeleted) {					
					System.out.println("Please enter the professor's ID for the professor to delete: ");
					String profIDDelete = scanner.next(); //remove whitespace from user input
					if (profIDDelete.equals("q")) {
						break;
					}
					professorDeleted = admin.deleteProfessor(fr, profIDDelete); //call deleteProfessor method in admin class; returns true if completed
				}
			} else if(option.equals("6")) {
				this.addStudent(admin, scanner); //call addStudent method in Controller class
			} else if(option.equals("7")) {				
				boolean studentDeleted = false;
				//enter while loop until valid student ID is entered for deletion or user quits
				while(!studentDeleted) {					
					System.out.println("Please enter the student's ID for the student to delete: ");
					String studentIDDelete = scanner.next(); //remove whitespace from user input
					if (studentIDDelete.equals("q")) {
						break;
					}
					studentDeleted = admin.deleteStudent(fr, studentIDDelete); //call deleteStudent method in admin class; returns true if completed
				}
			} else if(option.equals("8")) {
				adminViewRunning = false; //exit adminView and return to initial login menu
				//initialLogin(scanner);
			}
		}	
	}
	
	/**
	 * Method for displaying menu of possible professor actions and executing them.
	 * @param professor logged in
	 * @param scanner for user input
	 */
	private void profView(Professor professor, Scanner scanner) {
		boolean profViewRunning = true;
		//enter while loop until professor is finished
		while(profViewRunning) {
			//professor menu
			System.out.println("-------------------------\n" + "Welcome, " + professor.getName() + "\n" + "-------------------------\n" + "1 -- View given courses\n" 
					+ "2 -- View student list of the given course\n" + "3 -- Return to the previous menu\n");			
			System.out.println("Please enter your option, e.g., '1'.");
			//remove whitespace from user input
			String option = scanner.next().trim();
			//take appropriate action based upon user input
			if(option.equals("1")) {
				//obtain list of all courses, iterate over array and return courses taught by this professor
				ArrayList<Courses> givenCourses = professor.ViewGivenCourses(fr.getCourseInfo()); 
				System.out.println("----------The Course List----------");
				for (Courses course : givenCourses) {
					System.out.println(course);
				}
			} else if(option.equals("2")) {
				System.out.println("Please enter the course ID, e.g., 'CIT590'. ");
				System.out.println("Or enter 'q' to return to the previous menu. ");
				String listOfStudents = scanner.next().trim();
				if (listOfStudents.equals("q")) {
					continue;	
				} else {
					String studentList = professor.viewStudentListOfGivenCourse(fr, listOfStudents); //iterate over list of students in this professor's course(s)
					System.out.println(studentList);
				}
			} else if(option.equals("3")) {
				profViewRunning = false; //exit profView and return to initial login menu
				//initialLogin(scanner);
			}
		}
	}
	
	/**
	 * Method for displaying menu of possible student actions and executing them.
	 * @param student logged in
	 * @param scanner for user input
	 */
	private void studentView(Student student, Scanner scanner) {	
		boolean studentViewRunning = true;
		//enter while loop until student is finished
		while(studentViewRunning) {
			System.out.println("");
			System.out.println("-------------------------\n" + "Welcome, " + student.getName() + "\n" + "-------------------------\n" + "1 -- View all courses\n" 
					+ "2 -- Add courses to your list\n" + "3 -- View selected courses\n" + "4 -- Drop course in your list\n" + "5 -- View grades\n" + "6 -- Return to previous menu\n");			
			System.out.println("Please enter your option, e.g., '1'.");
			//remove whitespace from user input
			String option = scanner.next().trim();
			
			if(option.equals("1")) {
				student.viewAllCourseInfo(fr); //display all courses
			} else if(option.equals("2")) {
				System.out.println("Please select the course ID you want to add to your list, e.g., 'CIT590'. ");
				System.out.println("Or enter 'q' to return to the previous menu. ");
				String addCourseInput = scanner.next();
				if (addCourseInput.equals("q")) {
					continue;
				} else {
					student.addCourse(fr, addCourseInput); //call addCourse method in Controller class, which will loop until valid course is added or user quits
				}
			} else if(option.equals("3")) {
				student.viewEnrolledCourses(); //display courses in which student is enrolled; calls the viewEnrolledCourses method in Student class
			} else if(option.equals("4")) {
				student.viewEnrolledCourses(); //display enrolled courses list from which student may drop
				System.out.println("");
				System.out.println("Please enter the ID of the course which you want to drop, e.g., 'CIT590'. ");
				System.out.println("Or enter 'q' to return to the previous menu. ");
				String dropCourseInput = scanner.next().trim(); //remove whitespace from user input
				if (dropCourseInput.equals("q")) {
					continue;
				} else {
					student.dropCourse(fr, dropCourseInput); //calls dropCourse method is Student class, which will loop until valid course is added or user quits
				}
			} else if(option.equals("5")) {
				Map<String,String> courseAndGrade = student.getPastCoursesAndGrades(fr); //assign this students past courses and grades to map for displaying
				System.out.println("Here are the courses you have already taken, with your grade in a letter format.");
				//iterate for each entry in the map and display the course and grade
				for (Map.Entry<String, String> entry : courseAndGrade.entrySet()) {
					System.out.println("Grade of " + entry.getKey() + ": " + entry.getValue());
				}	
			} else if(option.equals("6")) {
				studentViewRunning = false; //exit studentView and return to initial login menu
				//initialLogin(scanner);
			}
		}
	}
	
	/**
	 * Method for an admin to add a new professor. Given amount of user input, this method is placed in Controller class.
	 * @param admin logged in
	 * @param scanner for user input
	 * @return professor added
	 */
	Professor addProfessor(Admin admin, Scanner scanner) {
		boolean validProfID = false;
		//enter while loop until valid (unique) professor ID is entered
		while(!validProfID) {
			System.out.println("Please enter the professor's ID, or type 'q' to end. ");
			String profID = scanner.next().trim(); //remove whitespace from user input
			if (profID.equals("q")) {
				return null;
			} else if(!admin.isValidUserID(profID)) {
				System.out.println("Invalid ID format. Please enter a 3-digit ID number."); //calls regex method in admin class to validate input format
			} else if(!(admin.returnProfessorObjFromID(fr, profID) == null)) { //check if profID already exists
				System.out.println("The ID already exists");
			} else {
				validProfID = true;
				System.out.println("Please enter the professor's name, or type 'q' to end. ");
				scanner.nextLine();
				String profName = scanner.nextLine().trim(); //remove whitespace from user input
				if (profName.equals("q")) {
					return null;
				}
				boolean validUsername = false;
				//enter while loop until valid (unique) professor username is entered
				while(!validUsername) {
					System.out.println("Please enter the professor's username, or type 'q' to end. ");
					String profUsername = scanner.next().trim(); //remove whitespace from user input
					if (profUsername.equals("q")) {
						return null;
					} else if(!(admin.returnProfessorObjFromUsername(fr, profUsername) == null)) { //check if professor username already exists
						System.out.println("The username is not available.");
					} else {
						validUsername = true;
						System.out.println("Please enter the professor's password, or type 'q' to end. ");
						String profPassword = scanner.next().trim(); //remove whitespace from user input
						if (profPassword.equals("q")) {
							return null;
						} 
						//call addProfessor method in admin class with validated user inputs and assign to local variable for displaying
						Professor professor = admin.addProfessor(fr, profID, profName, profUsername, profPassword);
						System.out.println("Successfully added the new professor: " + professor);					
						return professor;					
					}					
				}
			}	
		}
		return null;
	}
	
	/**
	 * Method for an admin to add new student. Given amount of user input, this method is placed in Controller class.
	 * @param admin logged in
	 * @param scanner for user input
	 * @return student added
	 */
	Student addStudent(Admin admin, Scanner scanner) {
		boolean validStudentID = false;
		//enter while loop until valid (unique) student ID is entered
		while(!validStudentID) {
			System.out.println("Please enter the student's ID, or type 'q' to end. ");
			String studentID = scanner.next().trim(); //remove whitespace from user input
			if (studentID.equals("q")) {
				return null;
			} else if(!admin.isValidUserID(studentID)) { //calls regex method in admin class to validate input format
				System.out.println("Invalid ID format. Please enter a 3-digit ID number.");
			} else if(!(admin.returnStudentObjFromID(fr, studentID) == null)) { //check if student ID already exists
				System.out.println("The ID already exists");
			} else {
				validStudentID = true;
				System.out.println("Please enter the student's name, or type 'q' to end. ");
				scanner.nextLine();
				String studentName = scanner.nextLine().trim(); //remove whitespace from user input
				if (studentName.equals("q")) {
					return null;
				}
				boolean validUsername = false;
				//enter while loop until valid (unique) student username is entered
				while(!validUsername) {
					System.out.println("Please enter the student's username, or type 'q' to end. ");
					String studentUsername = scanner.next().trim(); //remove whitespace from user input
					if (studentUsername.equals("q")) {
						return null;
					} else if(!(admin.returnStudentObjFromUsername(fr, studentUsername) == null)) { //check if student username already exists
						System.out.println("The username is not available.");
					} else {
						validUsername = true;
						
						System.out.println("Please enter the student's password, or type 'q' to end. ");
						String studentPassword = scanner.next().trim(); //remove white space from user input
						if (studentPassword.equals("q")) {
							return null;
						}
						//initialize empty string to hold prior courses and grades
						String pastCourses = "";
						//enter while loop to add prior courses until user enters 'n' to stop
						while(true) {
							System.out.println("Please enter the ID of a course which this student has already taken, one at a time. \n"
									+ "Type 'q' to quit, type 'n' to stop adding ");
							String pastCourseID = scanner.next().trim(); //remove whitespace from user input
							if (pastCourseID.equals("q")) {
								return null;
							} else if (pastCourseID.equals("n")) { //break out of while loop for adding prior courses and grades, but continue with adding new student
								break;
							} else if(!admin.isValidCourseID(pastCourseID)) { //calls regex method in admin class to validate input format
								System.out.println("Invalid Course ID format. Please use format 'CIS'/'CIT' followed by 3 numbers, e.g., CIT590.");
								continue; //stays inside while loop until valid course ID is entered
							}
							boolean validGrade = false;
							//enter while loop until valid letter grades are entered
							while(!validGrade) {
								System.out.println("Please enter the grade, e.g., A+, B-, C.");
								String pastCourseGrade = scanner.next().trim(); //remove whitespace from user input
								if (pastCourseGrade.equals("q")) {
									return null;
								} else if (!admin.isValidGrade(pastCourseGrade)) { //calls regex method in admin class to validate input format
									System.out.println("Invalid course grade. Please input a valid grade, e.g., A+, B-, C.");
								} else {
									validGrade = true;
									pastCourses += pastCourseID + ": " + pastCourseGrade + ", "; //add validated course and grade to pastCourses string
								}
							}									
						}						
						pastCourses = pastCourses.substring(0, pastCourses.length()-2); //remove trailing comma in string
						//create string with validated user input
						String studentInfo = studentID + "; " + studentName + "; " + studentUsername + "; " + studentPassword + "; " + pastCourses;					
						//call addStudent method in admin class with string and assign to local variable for displaying
						Student student = admin.addStudent(fr, studentInfo); 				
						System.out.println("Successfully added the new student: " + student);		
						return student;					
					}					
				}
			}	
		}
		return null;
}
	
	/**
	 * Method for an admin to add a new course. Given amount of user input, this method is placed in Controller class.
	 * @param admin logged in
	 * @param scanner for user input
	 */
	void addCourse(Admin admin, Scanner scanner) {		
		boolean validCourseID = false;
		//enter while loop until valid course ID is entered
		while(!validCourseID) {
			System.out.println("Please enter the course ID, or type 'q' to end.");
			String courseID = scanner.next().trim(); //remove whitespace from user input
			System.out.println(courseID);
			if (courseID.equals("q")) {
				break;				
			} else if(!(admin.isValidCourseID(courseID))) { //calls regex method in admin class to validate input format
				System.out.println("Please use format 'CIS'/'CIT' followed by 3 numbers.");
			} else if(!(admin.returnCourseObjFromID(fr, courseID) == null)){ //checks if course ID already exists
				System.out.println("The course already exists.");
			} else {
				validCourseID = true;
				System.out.println("Please enter the course name, or type 'q' to end.");
				scanner.nextLine();
				String courseName = scanner.nextLine().trim(); //remove whitespace from user input
				System.out.println(courseName);
				if (courseName.equals("q")) {
					break;	
				}				
				boolean validStartTime = false;
				//enter while loop until valid start time is entered
				while (!validStartTime) {
					System.out.println("Please enter the course start time (HH:MM), or type 'q' to end.");
					String courseStart = scanner.next().trim(); //remove whitespace from user input
					if (courseStart.equals("q")) {
						break;
					} else if (!(admin.isValidTime(courseStart))) { //calls regex method in admin class to validate input format
						System.out.println("Please use the HH:MM format.");
					} else {
						validStartTime = true;
						boolean validEndTime = false;
						//enter while loop until valid end time is entered
						while(!validEndTime) {
							System.out.println("Please enter the course end time (HH:MM), or type 'q' to end.");
							String courseEnd = scanner.next().trim(); //remove whitespace from user input
							if (courseEnd.equals("q")) {
								break;	
							} else if(!(admin.isValidTime(courseEnd))) { //calls regex method in admin class to validate input format
								System.out.println("Please use the HH:MM format.");
							}else {
								validEndTime = true;
								System.out.println("Please enter the course date(s) (MTWRFS), or type 'q' to end.");
								String courseDate = scanner.next().trim(); //remove whitespace from user input
								if (courseDate.equals("q")) {
									break;	
								}
								System.out.println("Please enter the course capacity, or type 'q' to end.");
								String courseCapacity = scanner.next().trim(); //remove whitespace from user input
								if (courseCapacity.equals("q")) {
									break;	
								}
								System.out.println("Please enter the course lecturer's ID, or type 'q' to end.");
								String courseLecturerID = scanner.next().trim(); //remove whitespace from user input
								if (courseLecturerID.equals("q")) {
									break;	
								}
								Professor courseProf = admin.returnProfessorObjFromID(fr, courseLecturerID); //check if professor already exists
								if(courseProf == null) {									
									System.out.println("That professor isn't in the system, please add the professor first.");									
									courseProf = this.addProfessor(admin, scanner); //call addProfessor method in Controller to add the new professor									
									if(courseProf == null) {
										break;
									}
								}admin.addCourse(fr, courseID, courseName, courseProf.getName(), courseDate, courseStart, courseEnd, courseCapacity);
							}
						}
					}	
				}
			}	
		}			
	}
}
