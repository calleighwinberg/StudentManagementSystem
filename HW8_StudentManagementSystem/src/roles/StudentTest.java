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
	
	

}
