import files.FileInfoReader;

public class Controller {
	
	//create instance of FileInfoReader so we can access files and arrays 
	FileInfoReader infoReader = new FileInfoReader();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//create isntance of controller class 
		Controller ct = new Controller();
		
		
		
		

	}
	
	
	/**
	 * Prints a list of all courses
	 * */
	public void printAllCourses() {
		System.out.println(infoReader.getCourseInfo()); // getCourseInfo returns the Array List, which when called for
														// printing calls the toString method in Course
	}


}
