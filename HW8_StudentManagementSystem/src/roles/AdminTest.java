package roles;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import courses.Courses;
import files.FileInfoReader;

class AdminTest {

	FileInfoReader fr;

	@BeforeEach
	void setUp() throws Exception {
		
		this.fr = new FileInfoReader();
		
		this.fr.readFile("studentInfo.txt");
		this.fr.readFile("profInfo.txt");
		this.fr.readFile("adminInfo.txt");
		this.fr.readFile("courseInfo.txt");
		
	}

	@Test
	void testAddCourse() {
		
		//test having an admin add a new course. The courseArray size should increase by one. Assuming all input is correct
		Admin admin = fr.getAdminInfo().get(0);
		assertEquals(2, fr.getProfessorInfo().get(0).ViewGivenCourses(fr.getCourseInfo()).size());
		admin.addCourse(fr, "CIT111", "Python Intro", "Clayton Greenberg", "MW", "12:30", "02:00", "2");
		assertEquals(3, fr.getProfessorInfo().get(0).ViewGivenCourses(fr.getCourseInfo()).size());
		
		//test having a different admin add a course for the same prof. His courses should now be 4
		Admin admin2 = fr.getAdminInfo().get(1);
		assertEquals(3, fr.getProfessorInfo().get(0).ViewGivenCourses(fr.getCourseInfo()).size());
		admin2.addCourse(fr, "CIT333", "Python 2", "Clayton Greenberg", "MW", "02:30", "04:00", "2");
		assertEquals(4, fr.getProfessorInfo().get(0).ViewGivenCourses(fr.getCourseInfo()).size());
		
		//test trying to add a course with a time conflict 
		assertEquals(4, fr.getProfessorInfo().get(0).ViewGivenCourses(fr.getCourseInfo()).size());
		Courses courseWithConflict = admin2.addCourse(fr, "CIT444", "Python 3", "Clayton Greenberg", "MW", "03:30", "05:00", "2");
		assertEquals(4, fr.getProfessorInfo().get(0).ViewGivenCourses(fr.getCourseInfo()).size());
		assertFalse(fr.getProfessorInfo().get(0).ViewGivenCourses(fr.getCourseInfo()).contains(courseWithConflict));
	}
	
	@Test
	void testAddProfessor() {
		
		//test having an admin add a new prof. The profArray size should increase by one. Assuming all input is correct
		Admin admin = fr.getAdminInfo().get(0);
		assertEquals(32, fr.getProfessorInfo().size());
		admin.addProfessor(fr, "033", "Calleigh Winberg", "winberg", "password590");
		assertEquals(33, fr.getProfessorInfo().size());
		
		
		
	}
	
	@Test
	void testLecturerExistsInSystem() {
		
		//test that professor Greenberg is returned when we input 001
		Admin admin = fr.getAdminInfo().get(0);
		Professor professor1 = admin.lecturerExistsInSystem(fr, "001");
		assertEquals(fr.getProfessorInfo().get(0), professor1);
		
		//test that professor Naik is returned when we input 001
		Professor professor2 = admin.lecturerExistsInSystem(fr, "012");
		assertEquals(fr.getProfessorInfo().get(11), professor2);
		
		
		//test that a null professor object is returned when we input an ID that doesn't correlate to a prof
		Professor professor3 = admin.lecturerExistsInSystem(fr, "000");
		assertEquals(null, professor3);
		

	
	
	
	
	}

}
