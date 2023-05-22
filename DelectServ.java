package com.Registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/deleteurl")
public class DelectServ extends HttpServlet{
	private static final String query="delete from BOOKDATA  WHERE id=?";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();
		resp.setContentType("text/html");
		int id = Integer.parseInt(req.getParameter("id"));
	
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {	 
			e.printStackTrace();
		}
		try(Connection con = DriverManager.getConnection("jdbc:mysql:///book","root","root");
			PreparedStatement ps = con.prepareStatement(query);){
		    ps.setInt(1, id);
			
		    int count=ps.executeUpdate();
		    if(count==1) {
		    	writer.println("<h2> Record is Delected Successfully</h2>");
		    }else {
		    	writer.println("<h2> Record is NOT Deleted Successfully</h2>");
		    }
		    
		} catch (SQLException se) {
			se.printStackTrace();
			writer.println("<h1>"+se.getMessage()+"</h1>");
		} catch (Exception e) {
			e.printStackTrace();
			writer.println("<h1>"+e.getMessage()+"</h1>");
		}
		writer.println("<a href='home.html'>Home</a>");
		writer.println("<br>");
		writer.println("<a href='booklist'>Book List</a>");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
}
