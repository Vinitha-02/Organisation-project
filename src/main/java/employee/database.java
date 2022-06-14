package employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;
public class database {
	static Logger log= Logger.getLogger(database.class.getName());  
	public static boolean validate(String employeeName, String employeePass,String departmentID) 
	 
	{
		boolean status = false;  
       

		try{  
	Class.forName("com.mysql.cj.jdbc.Driver");
	
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/organisation","root","12345");
		log.info("driver is connected");
		
		PreparedStatement ps= con.prepareStatement("SELECT * FROM organisation.employeedetails  where employeeName=? and employeePass=? and departmentID=?");
	
		     

			ps.setString(1,employeeName);  
			ps.setString(2,employeePass); 
			ps.setString(3,departmentID); 

			ResultSet rs=ps.executeQuery(); 
			log.info("Successfully checked the login details");
			 System.out.println("Drivers connected");

if(rs.next()){
	status= true;
}else {
	log.warn("enter the correct detail and format");}
	   ps.close(); 
	   con.close();
	   }catch(Exception e)
		{e.printStackTrace();}
		return status;
}
}
