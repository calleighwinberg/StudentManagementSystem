package roles;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import courses.Courses;
import files.FileInfoReader;

class UserTest {

	FileInfoReader fr;
	
	Courses courses;

	@BeforeEach
	void setUp() throws Exception {
		
		this.fr = new FileInfoReader();
		
		this.fr.readFile("studentInfo.txt");
		this.fr.readFile("profInfo.txt");
		this.fr.readFile("adminInfo.txt");
		this.fr.readFile("courseInfo.txt");
		
	}

	@Test
	void testReturnCourseObjFromID() {
		
		//test creating a course obj for 590 and then calling the function for another course obj. the two objs should reference the same object 
		Student student1 = fr.getStudentInfo().get(0);
		Courses course1 = fr.getCourseInfo().get(0);
		Courses course2 = student1.returnCourseObjFromID(fr, "CIT590");
		assertEquals(course1, course2);
		
	}
	
	
	@Test
	void testEquals() {
		
		//test creating a user obj for 590 and then calling the function for another user obj. the two objs should reference the same object 
		Student student1 = fr.getStudentInfo().get(0);
		Student student2 = fr.getStudentInfo().get(0);
		Student student3 = fr.getStudentInfo().get(1);
		assertTrue(student1.equals(student2));
		assertFalse(student2.equals(student3));
		student3.setId(student1.getId());
		assertTrue(student1.equals(student3));
	
	}
	

}
