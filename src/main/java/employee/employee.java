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

import employee.database;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



public class employee extends HttpServlet {
	
	static Logger log = Logger.getLogger(employee.class.getName());	

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("application/json");
		
		
		
		/*GetterandSetter gas = new GetterandSetter();
		String employeeName, departmentID; 
		int employeePass;
		employeeName = gas.getEmployeeName();
		employeePass = gas.getEmployeePass();
		departmentID  = gas.getDepartmentID();
		
		 /*String  employeeName= (String) record.get("employeeName");
		int  employeePass= (int) record.get("employeePass");
		 String  departmentID= (String) record.get("departmentID");
		// System.out.println("check1" + employeeName);*/
		 
		 
		 String employeeName = req.getParameter("employeeName");
		String employeePass=req.getParameter("employeePass");
		String departmentID =req.getParameter("departmentID");
		
		req.setAttribute("employeeName", employeeName);
		req.setAttribute("employeePass", employeePass);
		req.setAttribute("departmentID", departmentID);
		
				 //System.out.println("check1" +employeeName);
				 log.info("code checking");
		 PrintWriter out= res.getWriter();
		 database d= new database();
		
		 Connection c=null;
	       PreparedStatement ps=null;
	       Statement st=null;
	        ResultSet r=null;
		 try {
			 if(d.validate(employeeName, employeePass, departmentID)) {	 
				 HttpSession session=req.getSession(); 
				 session.setAttribute("employeeName",employeeName); 
			
				 
	             Class.forName("com.mysql.jdbc.Driver");
	             c=DriverManager.getConnection("jdbc:mysql://localhost:3306/organisation","root","12345");
				 if(departmentID.equals("A1")) {
                 st= c.createStatement();
		         r = st.executeQuery("SELECT * FROM organisation.employeedetails");
                 while(r.next()) {
                	 JSONObject record= new JSONObject();
            		 JSONArray data= new JSONArray();
            		
				        record.put("employeeId", r.getInt("employeeId"));
		                record.put("employeeName", r.getString("employeeName"));
		                record.put("employeeAge", r.getInt("employeeAge"));
		                record.put("employeePass", r.getInt("employeePass"));
		                record.put("departmentID", r.getString("departmentID"));
		                data.add(record);
		                out.println(data);
		               System.out.println(data);
		              // RequestDispatcher rd = req.getRequestDispatcher("update.java");
					  // rd.include(req, res);
		              
			}
                 }else{
				      ps= c.prepareStatement("SELECT * FROM organisation.employeedetails  where employeeName=?");   
		              ps.setString(1,employeeName);
		              log.info("device connected");
				      //System.out.println("Device connected");
                      ResultSet rs = ps.executeQuery();
		              while (rs.next()) {
		            	  JSONObject record= new JSONObject();
		         		 JSONArray data= new JSONArray();
		         		
			          
				      record.put("employeeId", rs.getInt("employeeId"));
			          record.put("employeeName", rs.getString("employeeName"));
			          record.put("employeeAge", rs.getInt("employeeAge"));
			          record.put("employeePass", rs.getInt("employeePass"));
			          record.put("departmentID", rs.getString("departmentID"));
			          data.add(record);
			          out.println(data);
		
		         }
			}
			}}
		 catch(Exception e) {
			
			e.printStackTrace();
		}
		
		
	}

}
