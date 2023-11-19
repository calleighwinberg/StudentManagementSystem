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

		
	}

	@Test
	void testViewGivenCourses() {
		
		//prof Greenberg test 
		ArrayList<Courses> profGreenbergCourses = new ArrayList<Courses>();
		
		profGreenbergCourses = fr.getProfessorInfo().get(0).ViewGivenCourses(fr.getCourseInfo());
		
		assertEquals(2, profGreenbergCourses.size());
		
		//System.out.println(fr.getProfessorInfo().get(0));
	
	}
	
	@Test
	void testViewStudentListOfGivenCourse() {
		
		
		Professor profGreenberg = fr.getProfessorInfo().get(0);
		
		String studentsInCourse1 = profGreenberg.viewStudentListOfGivenCourse(fr, "CIT593");
		//System.out.println(studentsInCourse1);
		assertEquals("This course is not is your course list.", studentsInCourse1);
		
		String studentsInCourse2 = profGreenberg.viewStudentListOfGivenCourse(fr, "CIT592");
		//System.out.println(studentsInCourse2);
		assertEquals("Students in course CIT592 Mathematical Foundations of Computer Science:\n", studentsInCourse2);
		
		
		Student student1 = fr.getStudentInfo().get(0);
		student1.addCourse(fr, "CIT592");
		String studentsInCourse3 = profGreenberg.viewStudentListOfGivenCourse(fr, "CIT592");
		//System.out.println(studentsInCourse3);
		assertEquals("Students in course CIT592 Mathematical Foundations of Computer Science:\n" + "001 StudentName1\n", studentsInCourse3);
		
		Student student2 = fr.getStudentInfo().get(1);
		student2.addCourse(fr, "CIT592");
		String studentsInCourse4 = profGreenberg.viewStudentListOfGivenCourse(fr, "CIT592");
		//System.out.println(studentsInCourse4);
		assertEquals("Students in course CIT592 Mathematical Foundations of Computer Science:\n" + "001 StudentName1\n" + "002 StudentName2\n", 
				studentsInCourse4);
		
		
		//one more test after admin adds a student 
		
	}

}
