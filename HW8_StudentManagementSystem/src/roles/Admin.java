package roles;


public class Admin extends User {
	
	
	static final String userType = "Administrator";
	

	public Admin(String adminInfo) {
		
		super(userType);
		
		String [] array = adminInfo.trim().split(";");
		
		//set each item in the array to an instance variable 
		this.setId(array[0].trim());
		
		this.setName(array[1].trim());
		
		this.setUsername(array[2].trim());
		
		this.setPassword(array[3].trim());

	}
	
	
	
	

}
