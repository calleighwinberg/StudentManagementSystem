package files;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileInfoReaderTest {
	
	FileInfoReader fr;

	@BeforeEach
	void setUp() throws Exception {
		
		this.fr = new FileInfoReader();
		
		this.fr.readFile("profInfo.txt");
		this.fr.readFile("courseInfo.txt");
		this.fr.readFile("studentInfo.txt");
		this.fr.readFile("adminInfo.txt");
	}

	@Test
	void testReadFile() {
		
		//test loading professors 
		assertEquals(32, this.fr.getProfessorInfo().size());
		
		assertEquals("Professor", this.fr.getProfessorInfo().get(0).getUserType());
		
		assertEquals("Clayton Greenberg", this.fr.getProfessorInfo().get(0).getName());
		
		
		//test loading courses 
		assertEquals(50, this.fr.getCourseInfo().size());
		System.out.println(this.fr.getCourseInfo().size());
		System.out.println(this.fr.getCourseInfo().get(0));

		assertEquals("CIT590", this.fr.getCourseInfo().get(0).getCourseID());
		
		
		//test loading admin 
		assertEquals(3, this.fr.getAdminInfo().size());
		//System.out.println(this.fr.getAdminInfo().size());
		assertEquals("admin01", this.fr.getAdminInfo().get(0).getUsername());
		
		//test loading student into
		assertEquals(2, this.fr.getStudentInfo().size());
		assertEquals("StudentName1", this.fr.getStudentInfo().get(0).getName());
		assertEquals("StudentName2", this.fr.getStudentInfo().get(1).getName());
		//System.out.println(this.fr.getStudentInfo().get(1).getName());
		
	}
		

}
