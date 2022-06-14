package employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.constant.Constable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import employee.database;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



public class employee extends HttpServlet {
	
	static Logger log= Logger.getLogger(employee.class.getName());  

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		res.setContentType("application/json");
		JSONObject record= new JSONObject();
		
		String str=req.getParameter("employeeName");
		System.out.println("check1 "+str);
		String  employeeName= (String) record.get("employeeName");
		String  employeePass= (String) record.get("employeePass");
		 String  departmentID= (String) record.get("departmentID");
		
		 System.out.println(record);
		 employeeName="Kavya";
		 employeePass="12345";
		 departmentID="H1";
		
		 PrintWriter out= res.getWriter();
		 database d= new database();
		
		 Connection c=null;
	       PreparedStatement ps=null;
	       Statement st=null;
	        ResultSet r=null;
		 try {
			 if(d.validate(employeeName, employeePass, departmentID)) {	 
				log.info("successfully logged in");
				 HttpSession session=req.getSession(); 
				 session.setAttribute("employeeName",employeeName); 
			
				 
	             Class.forName("com.mysql.jdbc.Driver");
	             c=DriverManager.getConnection("jdbc:mysql://localhost:3306/organisation","root","12345");
				 if(departmentID.equals("A1")) {
                 st= c.createStatement();
		         r = st.executeQuery("SELECT * FROM organisation.employeedetails");
                 while(r.next()) {
                	 record= new JSONObject();
            		 JSONArray data= new JSONArray();
            		 System.out.println("Device connected");
				        record.put("employeeId", r.getInt("employeeId"));
		                record.put("employeeName", r.getString("employeeName"));
		                record.put("employeeAge", r.getString("employeeAge"));
		                record.put("employeePass", r.getString("employeePass"));
		                record.put("departmentID", r.getString("departmentID"));
		                data.add(record);
		                out.println(data);
		                log.info("Successfully fected the data");
		               System.out.println(data);
		               RequestDispatcher rd = req.getRequestDispatcher("update.java");
					   rd.include(req, res);
		              
			}
                 }else{
				      ps= c.prepareStatement("SELECT * FROM organisation.employeedetails  where employeeName=?");   
		              ps.setString(1,employeeName);
		             
				      //System.out.println("Device connected");
                      ResultSet rs = ps.executeQuery();
		              while (rs.next()) {
		            	 record= new JSONObject();
		         		 JSONArray data= new JSONArray();
		         		
			          
				      record.put("employeeId", rs.getInt("employeeId"));
			          record.put("employeeName", rs.getString("employeeName"));
			          record.put("employeeAge", rs.getInt("employeeAge"));
			          record.put("employeePass", rs.getInt("employeePass"));
			          record.put("departmentID", rs.getString("departmentID"));
			          data.add(record);
			          out.println(data);
			          log.info("Successfully fetched the data");
			          System.out.println(data);
		         }
			}
		
			}else log.warn("please enter the correct details");
			 }
		 catch(Exception e) {
			
			e.printStackTrace();
		}
		
		
	}

}
