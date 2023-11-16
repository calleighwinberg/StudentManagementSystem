package files;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileInfoReaderTest {
	
	FileInfoReader fr;

	@BeforeEach
	void setUp() throws Exception {
		
		this.fr = new FileInfoReader();
		
		this.fr.readProfessorFile("profInfo.txt");
		this.fr.readCourseFile("courseInfo.txt");
	}

	@Test
	void testReadProfessorFile() {
		
		assertEquals(32, this.fr.getProfessorInfo().size());
		
		assertEquals("Professor", this.fr.getProfessorInfo().get(0).getUserType());
		
		assertEquals("Clayton Greenberg", this.fr.getProfessorInfo().get(0).getName());
		
	}
	
	@Test
	void testReadCourseFile() {
		
		assertEquals(50, this.fr.getCourseInfo().size());
		//System.out.println(this.fr.getCourseInfo().size());

		
		assertEquals("CIT590", this.fr.getCourseInfo().get(0).getCourseID());
		
	}

}
