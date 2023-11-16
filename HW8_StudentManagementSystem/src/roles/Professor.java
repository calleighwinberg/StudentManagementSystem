package roles;

import java.util.ArrayList;

import courses.Courses;

public class Professor extends User {
	
	static final String userType = "Professor";
	

	public Professor(String profInfo) {
		
		//call the User constructor first with userType
		super(userType);
		
		String [] array = profInfo.trim().split(";");
		
		//set each item in the array to an instance variable 
		this.setName(array[0].trim());
		
		this.setId(array[1].trim());
		
		this.setUsername(array[2].trim());
		
		this.setPassword(array[3].trim());
		

	}
	
	public ArrayList<Courses> ViewGivenCourses(ArrayList<Courses> allCourses) {
		
		ArrayList<Courses> profCourses = new ArrayList<Courses>();
		
		
		for(Courses course : allCourses) {
			
			if(course.getCourseProfessor().equals(this.getName())) {
				
				profCourses.add(course);
				
				//System.out.println(course);
			}
			
		}
		
		return profCourses;

	}

}
