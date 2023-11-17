package roles;

public class Student extends User {

	static final String userType = "Student";
	
	private String pastCoursesAndGrades;
	

	public Student(String studentInfo) {
		
		super(userType);
		
		String [] array = studentInfo.trim().split(";");
		
		//set each item in the array to an instance variable 
		this.setId(array[0].trim());
		
		this.setName(array[1].trim());
		
		this.setUsername(array[2].trim());
		
		this.setPassword(array[3].trim());
		
		this.setPastCoursesAndGrades(array[4].trim());
		
		

	}


	/**
	 * @return the pastCoursesAndGrades
	 */
	public String getPastCoursesAndGrades() {
		return pastCoursesAndGrades;
	}


	/**
	 * @param pastCoursesAndGrades the pastCoursesAndGrades to set
	 */
	public void setPastCoursesAndGrades(String pastCoursesAndGrades) {
		this.pastCoursesAndGrades = pastCoursesAndGrades;
	}
	


}
