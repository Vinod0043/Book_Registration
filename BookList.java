package com.Registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/booklist")
public class BookList extends HttpServlet{
	private static final String query="SELECT ID,BOOKNAME,BOOKEDITION,BOOKPRICE,BOOKPAGES FROM BOOKDATA";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();
		resp.setContentType("text/html");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {	 
			e.printStackTrace();
		}
		try(Connection con = DriverManager.getConnection("jdbc:mysql:///book","root","root");
			PreparedStatement ps = con.prepareStatement(query);){
		    ResultSet set = ps.executeQuery();
		    writer.println("<table>");
		    writer.println("<tr>");
		    writer.println("<th>Book Id</th>");
		    writer.println("<th>Book name</th>");
		    writer.println("<th>Book edition</th>");
		    writer.println("<th>Book price</th>");
		    writer.println("<th>Book pages</th>");
		    writer.println("<th>Edit</th>");
		    writer.println("<th>Delete</th>");
		    writer.println("</tr>");
		    while(set.next()) {
		    	writer.println("<tr>");
		    	 writer.println("<td>"+set.getInt(1)+"</td>");
		    	 writer.println("<td>"+set.getString(2)+"</td>");
		    	 writer.println("<td>"+set.getString(3)+"</td>");
		    	 writer.println("<td>"+set.getFloat(4)+"</td>");
		    	 writer.println("<td>"+set.getFloat(5)+"</td>");
		    	 writer.println("<td><a href='editScreen?id="+set.getInt(1)+"'>Edit</a></td>");
		    	 writer.println("<td><a href='deleteurl?id="+set.getInt(1)+"'>delete</a></td>");
		    	 writer.println("</tr>");
		    }
		    writer.println("</table>");
			
		} catch (SQLException se) {
			se.printStackTrace();
			writer.println("<h1>"+se.getMessage()+"</h1>");
		} catch (Exception e) {
			e.printStackTrace();
			writer.println("<h1>"+e.getMessage()+"</h1>");
		}
		writer.println("<a href='home.html'>Home</a>");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}

}
