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
	void returnProfessorObjFromID() {
		
		//test that professor Greenberg is returned when we input 001
		Admin admin = fr.getAdminInfo().get(0);
		Professor professor1 = admin.returnProfessorObjFromID(fr, "001");
		assertEquals(fr.getProfessorInfo().get(0), professor1);
		
		//test that professor Naik is returned when we input 001
		Professor professor2 = admin.returnProfessorObjFromID(fr, "012");
		assertEquals(fr.getProfessorInfo().get(11), professor2);
		
		
		//test that a null professor object is returned when we input an ID that doesn't correlate to a prof
		Professor professor3 = admin.returnProfessorObjFromID(fr, "000");
		assertEquals(null, professor3);

	}
	
	@Test
	void returnUserObjFromID() {
		
		//test that professor Greenberg is returned when we input 001
		Admin admin = fr.getAdminInfo().get(0);
		User professor1 = admin.returnUserObjFromID(fr, "001", "Professor"); //cast to prof 
		//System.out.println(professor1.getName());
		assertEquals(fr.getProfessorInfo().get(0), professor1);
		
		//test that professor Naik is returned when we input 001
		User professor2 = admin.returnUserObjFromID(fr, "012", "Professor");
		assertEquals(fr.getProfessorInfo().get(11), professor2);
		
		
		//test that a null professor object is returned when we input an ID that doesn't correlate to a prof
		User professor3 = admin.returnUserObjFromID(fr, "000", "Professor");
		assertEquals(null, professor3);
		
		
		//test that student1 is returned when we input 001 and Student
		User student1 = admin.returnUserObjFromID(fr, "001", "Student"); //cast to prof 
		//System.out.println(student1.getName());
		//System.out.println(professor1.getName());
		assertEquals(fr.getStudentInfo().get(0), student1);
	}

}
