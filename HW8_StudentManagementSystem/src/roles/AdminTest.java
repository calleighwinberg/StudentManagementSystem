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
	void testAdminConstructor() {
		
		//test creating a new admin and ensuring the variables save properly with whitespace 
		Admin admin = new Admin("000; Calleigh          ; admin00;             password000");
		assertEquals("000", admin.getId());
		assertEquals("Calleigh", admin.getName());
		
		//test checking the other variables in a new admin
		Admin admin2 = new Admin("111; Testing; admin22; password999");
		assertEquals("admin22", admin2.getUsername());
		assertEquals("password999", admin2.getPassword());
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
	void testDeleteCourse() {
		
		//test deleting existing course from Greenberg - size of Greenberg's given courses should decrease by one
		Admin admin = fr.getAdminInfo().get(0);
		assertEquals(2, fr.getProfessorInfo().get(0).ViewGivenCourses(fr.getCourseInfo()).size());
		assertTrue(admin.deleteCourse(fr, "CIT592"));
		assertEquals(1, fr.getProfessorInfo().get(0).ViewGivenCourses(fr.getCourseInfo()).size());
		//size of course array should decrease by one from 50 to 49
		assertFalse(fr.getProfessorInfo().get(0).ViewGivenCourses(fr.getCourseInfo()).contains(fr.getCourseInfo().get(2)));
		assertEquals(49, fr.getCourseInfo().size());
		
		//test that deleting a non-existant course does not work
		assertFalse(admin.deleteCourse(fr, "CIT000"));
		//add a new course, then delete it
		admin.addCourse(fr, "CIT000", "CS Basics", "Clayton Greenberg", "MWF", "12:00", "14:00", "100");
		assertTrue(admin.deleteCourse(fr, "CIT000"));
		
	}
	

	@Test
	void testAddStudent() {
		Admin admin = fr.getAdminInfo().get(0);
		//test having admin add a new student - array size should increase by one
		assertEquals(2, fr.getStudentInfo().size());
		
		//test adding a new student 
		Student student = admin.addStudent(fr, "003; StudentName3; testStudent03; password590; CIS000: F, CIS111: B");
		assertEquals("003", student.getId());
		assertEquals(3, fr.getStudentInfo().size());
		
		//test adding a new student without any grades
		Student student2 = admin.addStudent(fr, "004; StudentName4; testStudent04; password590");
		assertEquals(null, student2.getPastCoursesAndGrades(fr));
		assertEquals(4, fr.getStudentInfo().size());
		
		
	}
	
	@Test
	void testDeleteStudent() {
		Admin admin = fr.getAdminInfo().get(0);
		//test having an admin delete an existing student - array size should decrease by one
		assertEquals(2, fr.getStudentInfo().size());
		admin.deleteStudent(fr, "001");
		assertEquals(1, fr.getStudentInfo().size());
		
		//test deleting non-existant students - array size should not change
		admin.deleteStudent(fr, "001");
		assertEquals(1, fr.getStudentInfo().size());
		admin.deleteStudent(fr, "999");
		assertEquals(1, fr.getStudentInfo().size());
	}
	
	@Test
	void testAddProfessor() {
		
		//test having an admin add a new prof. The profArray size should increase by one
		Admin admin = fr.getAdminInfo().get(0);
		assertEquals(32, fr.getProfessorInfo().size());
		admin.addProfessor(fr, "033", "Calleigh Winberg", "winberg", "password590");
		assertEquals(33, fr.getProfessorInfo().size());
		
		//test adding an already existing professor
		//this will increase size of array because a different method would check for existing professors first
		//however, the outcome below would be expected for this method in isolation
		assertEquals(33, fr.getProfessorInfo().size());
		admin.addProfessor(fr, "001", "Clayton Greenberg", "Greenberg", "password590");
		assertEquals(34, fr.getProfessorInfo().size());
	}
	
	
	@Test
	void testDeleteProfessor() {
		Admin admin = fr.getAdminInfo().get(0);
		//test having an admin delete an existin professor - array size should decrease by one
		assertEquals(32, fr.getProfessorInfo().size());
		admin.deleteProfessor(fr, "001");
		assertEquals(31, fr.getProfessorInfo().size());
		
		//test deleting non-existant professors - array size should not change
		admin.deleteProfessor(fr, "001");
		assertEquals(31, fr.getProfessorInfo().size());
		admin.deleteProfessor(fr, "999");
		assertEquals(31, fr.getProfessorInfo().size());
	}
	
	@Test
	void testReturnProfessorObjFromUsername() {
		Admin admin = fr.getAdminInfo().get(0);
		//test that professor Greenberg is returned when we input his username "Greenberg"
		Professor professor001 = admin.returnProfessorObjFromUsername(fr, "Greenberg");
		assertEquals(fr.getProfessorInfo().get(0), professor001);
		
		//test that professor Krakowsky is returned when we input his username "Krakowsky"
		Professor professor029 = admin.returnProfessorObjFromUsername(fr, "Krakowsky");
		assertEquals(fr.getProfessorInfo().get(28), professor029);
		
		//test that null is returned for non-existant professor username
		Professor professorNull = admin.returnProfessorObjFromUsername(fr, "Vanderhorst");
		assertEquals(null, professorNull);
	}
	
	@Test
	void testReturnStudentObjFromUsername() {
		Admin admin = fr.getAdminInfo().get(0);
		//test that student 1 is returned when we input its username "testStudent01"
		Student student001 = admin.returnStudentObjFromUsername(fr, "testStudent01");
		assertEquals(fr.getStudentInfo().get(0), student001);
		
		//test that student 2 is returned when we input its username "testStudent02"
		Student student002 = admin.returnStudentObjFromUsername(fr, "testStudent02");
		assertEquals(fr.getStudentInfo().get(1), student002);
		
		//test that null is returned for non-existant student username
		Student studentNull = admin.returnStudentObjFromUsername(fr, "Vanderhorst");
		assertEquals(null, studentNull);
	}
	
	@Test
	void testReturnStudentObjFromID() {
		Admin admin = fr.getAdminInfo().get(0);
		//test that student 1 is returned when we input its ID "001"
		Student student001 = admin.returnStudentObjFromID(fr, "001");
		assertEquals(fr.getStudentInfo().get(0), student001);
		
		//test that student 1 is returned when we input its ID "001"
		Student student002 = admin.returnStudentObjFromID(fr, "002");
		assertEquals(fr.getStudentInfo().get(1), student002);
		
		//test that null is return for non-existant student ID
		Student studentNull = admin.returnStudentObjFromID(fr, "999");
		assertEquals(null, studentNull);
		
	}

	
	@Test
	void testReturnProfessorObjFromID() {		
		Admin admin = fr.getAdminInfo().get(0);
		//test that professor Greenberg is returned when we input 001
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
	void testReturnUserObjFromID() {
		
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
		User student1 = admin.returnUserObjFromID(fr, "001", "Student");
		//System.out.println(student1.getName());
		//System.out.println(professor1.getName());
		assertEquals(fr.getStudentInfo().get(0), student1);
	}
	
	@Test
	void testIsValidCourseID() {
		Admin admin = fr.getAdminInfo().get(0);
		//test if valid course ID input works
		assertTrue(admin.isValidCourseID("ABC123"));
		//test if course ID input is false when reversing capital letters and numbers
		assertFalse(admin.isValidCourseID("123ABC"));
		//test if whitespace in course ID is false (however, user input should always be trimmed elsewhere)
		assertFalse(admin.isValidCourseID(" CIT590 "));
		//test that lowercase letters in course ID is false
		assertFalse(admin.isValidCourseID("cit590"));
		//test if more than 3 capital letters and 3 numbers in course ID is false
		assertFalse(admin.isValidCourseID("CITT5900"));
	}
	
	@Test
	void testIsValidTime() {
		Admin admin = fr.getAdminInfo().get(0);
		//test if valid time input works
		assertTrue(admin.isValidTime("23:59"));
		//test if whitespace in time input is false (however, user input should always be trimmed elsewhere)
		assertFalse(admin.isValidTime(" 23:59 "));
		//test that midnight input as 24:00 is false, but 00:00 is true
		assertFalse(admin.isValidTime("24:00"));
		assertTrue(admin.isValidTime("00:00"));
		//test that military time standard is held
		assertFalse(admin.isValidTime("25:00"));
		assertFalse(admin.isValidTime("30:15"));
	}
	
	@Test
	void testIsValidUserID() {
		Admin admin = fr.getAdminInfo().get(0);
		//test if valid userID input works
		assertTrue(admin.isValidUserID("000"));
		assertTrue(admin.isValidUserID("999"));
		//test if whitespace in userID input is false (however, user input should always be trimmed elsewhere)
		assertFalse(admin.isValidUserID(" 000 "));
		//test that userID with more than 3 digits is false
		assertFalse(admin.isValidUserID("0000"));
		//test that a mix of 3 characters (letters and numbers) is false
		assertFalse(admin.isValidUserID("1a1"));
		assertFalse(admin.isValidUserID("A3A"));
		//test that 3 special characters is false
		assertFalse(admin.isValidUserID("$$$"));
	}
	
	@Test
	void testIsValidGrade() {
		Admin admin = fr.getAdminInfo().get(0);
		//test that 'traditional' letter grades as inputs work
		assertTrue(admin.isValidGrade("A"));
		assertTrue(admin.isValidGrade("B-"));
		assertTrue(admin.isValidGrade("C+"));
		assertTrue(admin.isValidGrade("F"));
		//test that '+' and '-' do not work with letter grade 'F'
		assertFalse(admin.isValidGrade("F+"));
		assertFalse(admin.isValidGrade("F-"));
		//test that 'non-traditional' letter grades are false
		assertFalse(admin.isValidGrade("E"));
		assertFalse(admin.isValidGrade("Z"));
	}

}
