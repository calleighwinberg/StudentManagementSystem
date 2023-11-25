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
	void testStudentConstructor() {
		
		//test creating a new student and ensuring the variables save properly with whitespace 
		Student student = new Student("000; Calleigh       ; student3; password000     ; CIT592:     F, CIT593: B");
		assertEquals("000", student.getId());
		assertEquals("Calleigh", student.getName());
		assertEquals(2, student.getPastCoursesAndGrades(fr).size());
		
		
		//test creating a new student with no past grades
		Student student2 = new Student("004; Calleigh Winberg     ; student4; password000     ");
		assertEquals("004", student2.getId());
		assertEquals("Calleigh Winberg", student2.getName());
		assertEquals(null, student2.getPastCoursesAndGrades(fr));
		
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
		
		//test adding 2 courses for student 1
		Student student1 = fr.getStudentInfo().get(0);
		student1.addCourse(fr, "CIT590");
		student1.addCourse(fr, "CIT591");
		//check that enrollment number for each course increases by 1
		assertEquals(1, fr.getCourseInfo().get(0).getNumStudentsEnrolled());
		assertEquals(1, fr.getCourseInfo().get(1).getNumStudentsEnrolled());
		//check that number of enrolled courses for student 1 equals 2
		assertEquals(2, student1.getEnrolledCourses().size());
		assertEquals(fr.getCourseInfo().get(0), student1.getEnrolledCourses().get(0));
		
		//check that the course has student1 enrolled
		assertEquals(1, fr.getCourseInfo().get(0).getStudentsEnrolled().size());
		assertEquals("StudentName1", fr.getCourseInfo().get(0).getStudentsEnrolled().get(0).getName());
		assertEquals("001", fr.getCourseInfo().get(0).getStudentsEnrolled().get(0).getId());

		
		//test adding 1 valid course for student 2
		Student student2 = fr.getStudentInfo().get(1);
		student2.addCourse(fr, "CIT592");
		//check that enrollment number for course increases by 1
		assertEquals(1, fr.getCourseInfo().get(2).getNumStudentsEnrolled());
		//check that number of enrolled courses for student 2 equals 1
		assertEquals(1, student2.getEnrolledCourses().size());
		//check that adding a non-existant course does not increase student 2's enrolled courses size
		student2.addCourse(fr, "CIT000");
		assertEquals(1, student2.getEnrolledCourses().size());
		
	}
	
	@Test 
	void testViewEnrolledCourses() {
		//not tested as this method only displays to the console - it does not return a string
	}
	
	
	@Test 
	void testDropCourse() {
		
		//System.out.println("TestDropCourse");
		
		//add three courses for student1. Confirm that the number of courses == 3. 
		Student student1 = fr.getStudentInfo().get(0);
		student1.addCourse(fr, "CIT590");
		student1.addCourse(fr, "CIT591");
		student1.addCourse(fr, "CIT592");
		assertEquals(3, student1.getEnrolledCourses().size());
		assertTrue(student1.getEnrolledCourses().contains(fr.getCourseInfo().get(0)));
		
		//Drop a course and confirm the number of courses == 2 and enrolledCourses no longer contains that course 
		student1.dropCourse(fr, "CIT590");
		assertEquals(2, student1.getEnrolledCourses().size());
		assertFalse(student1.getEnrolledCourses().contains(fr.getCourseInfo().get(0)));
		
		//check that the number of students enrolled for that class decrements 
		assertEquals(0, fr.getCourseInfo().get(0).getNumStudentsEnrolled());
		
		//test dropping a course that is not in the enrolledCourses. The size of the array should not change 
		student1.dropCourse(fr, "CI590");
		assertEquals(2, student1.getEnrolledCourses().size());
		
		//enrolledCourses still contains both courses 
		assertTrue(student1.getEnrolledCourses().contains(fr.getCourseInfo().get(1)));
		assertTrue(student1.getEnrolledCourses().contains(fr.getCourseInfo().get(2)));
	}
}
