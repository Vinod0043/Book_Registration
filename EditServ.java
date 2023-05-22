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
@WebServlet("/editScreen")
public class EditServ extends HttpServlet{
	private static final String query="SELECT BOOKNAME,BOOKEDITION,BOOKPRICE,BOOKPAGES FROM BOOKDATA WHERE id=?";
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
		    ResultSet set = ps.executeQuery();
		    set.next();
		    
		    writer.println("<form action='editurl?id="+id+"' method='post'>");
		    writer.println("<table>");
		    writer.println("<tr>");
		    writer.println("<td>Book Name</td>");
		    writer.println("<td><input type='text' name ='bookName' value'"+set.getString(1)+"'></td>");
		    writer.println("</tr>");
		    writer.println("<tr>");
		    writer.println("<td>Book Edition</td>");
		    writer.println("<td><input type='text' name ='bookEdition' value'"+set.getString(2)+"'></td>");
		    writer.println("</tr>");
		    writer.println("<tr>");
		    writer.println("<td>Book Price</td>");
		    writer.println("<td><input type='text' name ='bookPrice' value'"+set.getFloat(3)+"'></td>");
		    writer.println("</tr>");
		    writer.println("<tr>");
		    writer.println("<td>Book pages</td>");
		    writer.println("<td><input type='text' name ='bookPages' value'"+set.getFloat(4)+"'></td>");
		    writer.println("</tr>");
		    writer.println("<tr>");
		    writer.println("<td><input type='submit' value='Edit'></td> ");
		    writer.println("<td><input type='reset' value='cancel'></td> ");
		    writer.println("</tr>");
		    writer.println("</table>");
		    writer.println("</form>");
		    
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
