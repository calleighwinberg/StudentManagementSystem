package roles;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import courses.Courses;
import files.FileInfoReader;

class StudentTest {
	
	FileInfoReader fr;
	
	Student student;
	
	Courses courses;

	@BeforeEach
	void setUp() throws Exception {
		
		this.fr = new FileInfoReader();
		
		this.fr.readFile("studentInfo.txt");
		this.fr.readFile("courseInfo.txt");
		
	}

	@Test
	void testGetPastCoursesAndGrades() {
		
		//test student 1 
		Student student1 = fr.getStudentInfo().get(0);
		assertEquals(2, student1.getPastCoursesAndGrades(fr).size());
		assertEquals("A", student1.getPastCoursesAndGrades(fr).get("CIS320 Introduction to Algorithms"));
		assertEquals("A", student1.getPastCoursesAndGrades(fr).get("CIS191 Linux/Unix Skills"));
		
		//test student 2 classes 
		Student student2 = fr.getStudentInfo().get(1);
		assertEquals(2, student2.getPastCoursesAndGrades(fr).size());
		assertEquals("A", student2.getPastCoursesAndGrades(fr).get("CIT592 Mathematical Foundations of Computer Science"));
		assertEquals("A-", student2.getPastCoursesAndGrades(fr).get("CIT593 Introduction to Computer Systems"));
	}
	
	@Test
	void testOkToAddCourse() {
		
		//check adding a course that is okay to add 
		Student student1 = fr.getStudentInfo().get(0);
		student1.addCourse(fr, "CIT590");
		student1.addCourse(fr, "CIT591");
		assertTrue(student1.okToAddCourse(fr, "CIT592"));
		student1.addCourse(fr, "CIT592");

		
		//test adding the same course again 
		assertFalse(student1.okToAddCourse(fr, "CIT592"));
		
		//test adding course that doesn't exist 
		assertFalse(student1.okToAddCourse(fr, "CI592"));
		
		//test adding a course that has time conflict 
		assertFalse(student1.okToAddCourse(fr, "CIS555"));
		
		
		//test that you can't add students past course capacity 
		Student student2 = fr.getStudentInfo().get(1);
		fr.getCourseInfo().get(0).setCourseCapacity("1");
		assertFalse(student2.okToAddCourse(fr, "CIT590"));

		

	}
	
	@Test
	void testAddCourse() {
		
		//check adding two courses that are allowed. Students enrolled should be 1 for both
		Student student1 = fr.getStudentInfo().get(0);
		student1.addCourse(fr, "CIT590");
		student1.addCourse(fr, "CIT591");
		assertEquals(1, fr.getCourseInfo().get(0).getNumStudentsEnrolled());
		assertEquals(1, fr.getCourseInfo().get(1).getNumStudentsEnrolled());
		assertEquals(2, student1.getEnrolledCourses().size());
		assertEquals(fr.getCourseInfo().get(0), student1.getEnrolledCourses().get(0));
		
		
		
	}
	
	

}
