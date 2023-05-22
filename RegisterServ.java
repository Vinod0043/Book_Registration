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
@WebServlet("/register")
public class RegisterServ extends HttpServlet{
	private static final String query="INSERT INTO BOOKDATA(BOOKNAME,BOOKEDITION,BOOKPRICE,BOOKPAGES) VALUES(?,?,?,?)";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();
		resp.setContentType("text/html");
		String name = req.getParameter("bookname");
		String edition = req.getParameter("bookedition");
		String price = req.getParameter("price");
		String pages = req.getParameter("pages");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {	 
			e.printStackTrace();
		}
		try(Connection con = DriverManager.getConnection("jdbc:mysql:///book","root","root");
			PreparedStatement ps = con.prepareStatement(query);){
			ps.setString(1, name);
			ps.setString(2, edition);
			ps.setString(3, price);
			ps.setString(4, pages);
			int count = ps.executeUpdate();
			if(count ==1) {
				writer.println("<h2>Register successfully</h2>");
			}else {
				writer.println("<h2>Not Register successfully</h2>");
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
