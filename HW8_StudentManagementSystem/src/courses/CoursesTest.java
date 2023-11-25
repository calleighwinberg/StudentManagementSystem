package courses;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import files.FileInfoReader;
import roles.Student;

class CoursesTest {
	
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
	void testCourses() {
		
		//test creating a course and ensure ID and name match
		Courses course1 = new Courses("     CIT000; Programming; Brandon L Krakowsky;         MW; 15:30; 17:00; 10");
		assertEquals("CIT000", course1.getCourseID());
		assertEquals("Programming", course1.getCourseName());
		
		//test creating a course and ensure porfessor and capacity match
		Courses course2 = new Courses("CIT111; HTML; Calleigh Winberg; MW; 11:30; 13:00; 20");
		assertEquals("Calleigh Winberg", course2.getCourseProfessor());
		assertEquals("20", course2.getCourseCapacity());
		
	}
	
	@Test
	void testCourses2ndConstructor() {
		
		//test creating a course and ensure ID and name match
		Courses course1 = new Courses("CIT000", "Programming", "Brandon L Krakowsky", "MW", "15:30", "17:00", "10");
		assertEquals("CIT000", course1.getCourseID());
		assertEquals("Programming", course1.getCourseName());
		
		//test creating a course and ensure porfessor and capacity match
		Courses course2 = new Courses("CIT111", "HTML", "Calleigh Winberg", "MW", "11:30", "13:00", "20");
		assertEquals("Calleigh Winberg", course2.getCourseProfessor());
		assertEquals("20", course2.getCourseCapacity());
		
	}
	

	@Test
	void testSetCourseStart() {
		
		//test setting a course start time to something else 
		Courses course1 = this.fr.getCourseInfo().get(0);
		assertEquals(1050, course1.setCourseStart("17:30"));
		
		//test that what is returned is an integer
		Courses course2 = this.fr.getCourseInfo().get(0);
		assertTrue(course2.setCourseStart("12:30") == 750);
	}
	
	
	@Test
	void testGetCourseStart() {
		
		//test that the getCourseStart returns a string 
		Courses course1 = this.fr.getCourseInfo().get(0);
		assertEquals("16:30", course1.getCourseStart());
		
		//test setting the course time to something different and then trying to Get it
		course1.setCourseStart("17:30");
		assertEquals("17:30", course1.getCourseStart());
		
		
	}
	
	
	@Test
	void testSetCourseEnd() {
		
		//test setting a course start time to something else 
		Courses course1 = this.fr.getCourseInfo().get(0);
		assertEquals(1050, course1.setCourseEnd("17:30"));
		
		//test that what is returned is an integer
		Courses course2 = this.fr.getCourseInfo().get(0);
		assertTrue(course2.setCourseEnd("12:30") == 750);
		
		
		
	}
	
	@Test
	void testGetCourseEnd() {
		
		//test that the getCourseEnd returns a string 
		Courses course1 = this.fr.getCourseInfo().get(0);
		assertEquals("18:00", course1.getCourseEnd());
		
		//test setting the course time to something different and then trying to Get it
		course1.setCourseEnd("17:30");
		assertEquals("17:30", course1.getCourseEnd());
		
		
	}
	
	@Test
	void testTimeConflict() {
		
		//test course 1 against course2 
		Courses course1 = this.fr.getCourseInfo().get(0);
		Courses course2 = this.fr.getCourseInfo().get(1);
		System.out.println(course1.getCourseStart());
		System.out.println(course1);
		System.out.println(course2);
		assertFalse(course1.timeConflict(course2));
		
		//change course 2 end time so there is a conflict
		course2.setCourseEnd("17:00");
		assertEquals("17:00", course2.getCourseEnd());
		assertTrue(course1.timeConflict(course2));
		
		//test that ending/starting the same time isn't an issue
		course2.setCourseEnd("16:30");
		assertEquals("16:30", course2.getCourseEnd());
		assertFalse(course1.timeConflict(course2));
		
		//test edge case for one minute over 
		course2.setCourseEnd("16:31");
		assertEquals("16:31", course2.getCourseEnd());
		assertTrue(course1.timeConflict(course2));
		
		//test changing course 1's start time
		course2.setCourseEnd("13:30");
		assertEquals("13:30", course2.getCourseEnd());
		assertFalse(course1.timeConflict(course2));
		
		course1.setCourseStart("13:29");
		assertEquals("13:29", course1.getCourseStart());
		assertTrue(course1.timeConflict(course2));
		
		//test courses with same times but different days
		course1.setCourseDays("TR");
		assertFalse(course1.timeConflict(course2));
	}
	
	
	@Test
	void testEquals() {
		
		//test creating a course obj for 590 and then calling the function for another course obj. the two objs should reference the same object 
		Courses course1 = fr.getCourseInfo().get(0);
		Courses course2 = fr.getCourseInfo().get(0);
		Courses course3 = fr.getCourseInfo().get(1);
		assertTrue(course1.equals(course2));
		assertFalse(course1.equals(course3));
		course3.setCourseID(course1.getCourseID());
		assertTrue(course1.equals(course3));
		
		//test a different course, CIT592
		Courses course4 = fr.getCourseInfo().get(2);
		Courses course5 = fr.getCourseInfo().get(2);
		assertTrue(course4.equals(course5));
	
	}

}
