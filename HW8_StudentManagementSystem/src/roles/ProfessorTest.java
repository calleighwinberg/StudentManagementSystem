package roles;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import courses.Courses;
import files.FileInfoReader;

class ProfessorTest {
	
	FileInfoReader fr;
	
	Professor professor;
	
	Courses courses;

	@BeforeEach
	void setUp() throws Exception {
		
		this.fr = new FileInfoReader();
		
		this.fr.readFile("profInfo.txt");
		this.fr.readFile("courseInfo.txt");
		this.fr.readFile("studentInfo.txt");
		this.fr.readFile("adminInfo.txt");
		
	}
	
	@Test
	void testProfConstructor() {
		
		//test creating a new prof and ensuring the variables save properly with whitespace 
		Professor prof = new Professor("Calleigh; 000          ; prof000;             password000");
		assertEquals("000", prof.getId());
		assertEquals("Calleigh", prof.getName());
		
		//test checking the other variables in a new prof
		Professor prof2 = new Professor("  Testing ; 100; prof100; password000");
		assertEquals("prof100", prof2.getUsername());
		assertEquals("password000", prof2.getPassword());
	}
	
	
	
	@Test
	void testProfConstructor2() {
		
		//test creating a new prof and ensuring the variables save properly with whitespace 
		Professor prof = new Professor("000", "Calleigh", "prof00", "password000");
		assertEquals("000", prof.getId());
		assertEquals("Calleigh", prof.getName());
		
		//test checking the other variables in a new prof
		Professor prof2 = new Professor("100", "Testing", "prof100", "password000");
		assertEquals("prof100", prof2.getUsername());
		assertEquals("password000", prof2.getPassword());
	}

	@Test
	void testViewGivenCourses() {
		
		//tests using professor Greenberg
		ArrayList<Courses> profGreenbergCourses = new ArrayList<Courses>();
		
		//test that correct number of courses are assigned to Greenberg
		profGreenbergCourses = fr.getProfessorInfo().get(0).ViewGivenCourses(fr.getCourseInfo());
		assertEquals(2, profGreenbergCourses.size());
		
		//tests using professor Krakowsky
		ArrayList<Courses> profKrakowskyCourses = new ArrayList<Courses>();
		
		//test that correct number of courses are assigned to Krakowsky
		profKrakowskyCourses = fr.getProfessorInfo().get(28).ViewGivenCourses(fr.getCourseInfo());
		assertEquals(1, profKrakowskyCourses.size());
		
	}
	
	@Test
	void testViewStudentListOfGivenCourse() {
		
		//tests using professor Greenberg
		Professor profGreenberg = fr.getProfessorInfo().get(0);
		
		//test that appropriate message displays when selecting wrong course
		String studentsInCourse1 = profGreenberg.viewStudentListOfGivenCourse(fr, "CIT593");
		assertEquals("This course is not is your course list.", studentsInCourse1);
		
		//test that appropriate message displays when selecting correct course
		String studentsInCourse2 = profGreenberg.viewStudentListOfGivenCourse(fr, "CIT592");
		assertEquals("Students in course CIT592 Mathematical Foundations of Computer Science:\n", studentsInCourse2);
		
		//test adding student 1 to a correct course and appropriate message displays
		Student student1 = fr.getStudentInfo().get(0);
		student1.addCourse(fr, "CIT592");
		String studentsInCourse3 = profGreenberg.viewStudentListOfGivenCourse(fr, "CIT592");
		assertEquals("Students in course CIT592 Mathematical Foundations of Computer Science:\n" + "001 StudentName1\n", studentsInCourse3);
		
		//test adding student 2 to a correct course and appropriate message displays
		Student student2 = fr.getStudentInfo().get(1);
		student2.addCourse(fr, "CIT592");
		String studentsInCourse4 = profGreenberg.viewStudentListOfGivenCourse(fr, "CIT592");
		assertEquals("Students in course CIT592 Mathematical Foundations of Computer Science:\n" + "001 StudentName1\n" + "002 StudentName2\n", 
				studentsInCourse4);
		
	}

}
