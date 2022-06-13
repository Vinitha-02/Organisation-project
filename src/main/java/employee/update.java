package employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class update extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("application/json");
		String employeeAge =req.getParameter("employeeAge");
		String employeePass =req.getParameter("employeePass");
		String employeeId =req.getParameter("employeeId");
		
		Connection c=null;
	    PreparedStatement ps=null;
	    ResultSet rs=null;
	    PrintWriter out= res.getWriter();
	   HttpSession session = req.getSession(false);
		if(session!=null)
		{
			String employeeName= (String) session.getAttribute("employeeName");
			
	    try {
				Class.forName("com.mysql.jdbc.Driver");
				c=DriverManager.getConnection("jdbc:mysql://localhost:3306/organisation","root","12345");
				ps= c.prepareStatement("UPDATE organisation.employeedetails SET employeeAge =?, employeePass=? WHERE employeeId =?");
				System.out.println("update successfully");
			    ps.setString(1,employeeAge);
			    ps.setString(2,employeePass);
				ps.setString(3, employeeId);
			   rs = ps.executeQuery();
			    while (rs.next()) {
			         JSONArray data= new JSONArray();
				     JSONObject record= new JSONObject();
					 record.put("employeeId", rs.getInt("employeeId"));
				     record.put("employeeName", rs.getString("employeeName"));
				     record.put("employeeAge", rs.getInt("employeeAge"));
				     record.put("employeePass", rs.getInt("employeePass"));
				     record.put("departmentID", rs.getString("departmentID"));

				     data.add(record);
				     out.println(data);
			         }
				}
			 catch(Exception e) {
		
				e.printStackTrace();
			}}

	}}
