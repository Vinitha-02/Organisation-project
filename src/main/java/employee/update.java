package employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class update extends HttpServlet{
	static Logger log= Logger.getLogger(employee.class.getName());  

	protected void doPut(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("application/json");
		JSONObject record= new JSONObject();
		String  employeeAge= (String) record.get("employeeAge");
		String  employeePass= (String) record.get("employeePass");
	    String employeeID= (String) record.get("employeeId");
	    
	
	    		
		int employeeId=Integer.parseInt(employeeID);
         PreparedStatement ps=null;
	     ResultSet rs=null;
	     PrintWriter out= res.getWriter();
	    HttpSession session = req.getSession();
	    System.out.println("checked successfully");
		Connection c=null;
		if(session  != null)
		{
	    System.out.println("update successfully");	
		String employeeName= (String) session.getAttribute("employeeName");
			
	    try {
				Class.forName("com.mysql.jdbc.Driver");
				c=DriverManager.getConnection("jdbc:mysql://localhost:3306/organisation","root","12345");
				String sql="UPDATE employeedetails SET employeeAge =?, employeePass=? WHERE employeeId =?";
				ps= c.prepareStatement(sql);
				log.info("update successfully");
				System.out.println("update successfully");
			    ps.setString(1,employeeAge);
			    ps.setString(2,employeePass);
				ps.setInt(3, employeeId);
		        ps.executeUpdate(); 
		 
		       out.println("Details are updated sucessfully");
		       ps= c.prepareStatement("SELECT * FROM organisation.employeedetails  where employeeName=?");   
               ps.setString(1,employeeName);
               rs = ps.executeQuery();
			
			    while (rs.next()) {
			         JSONArray data= new JSONArray();
				      record= new JSONObject();
					 record.put("employeeId", rs.getInt("employeeId"));
				     record.put("employeeName", rs.getString("employeeName"));
				     record.put("employeeAge", rs.getString("employeeAge"));
				     record.put("employeePass", rs.getString("employeePass"));
				     record.put("departmentID", rs.getString("departmentID"));
                     log.info("printed the details");
				     data.add(record);
				     out.println(data);
			         }
				}
			 catch(Exception e) {
		
				e.printStackTrace();
			}
	    }else {log.warn("Session should not be null");
	    }

	}}
