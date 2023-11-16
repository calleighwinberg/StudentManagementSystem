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
		//this.fr.readCourseFile("courseInfo.txt");

		
	}

	@Test
	void test() {
		
		//prof Greenberg test 
		ArrayList<Courses> profGreenbergCourses = new ArrayList<Courses>();
		
		profGreenbergCourses = fr.getProfessorInfo().get(0).ViewGivenCourses(fr.getCourseInfo());
		
		assertEquals(2, profGreenbergCourses.size());
		
		System.out.println(profGreenbergCourses);

		
	
	}

}
