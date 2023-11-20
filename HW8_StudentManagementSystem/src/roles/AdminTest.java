package roles;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
		
	}

}
