package com.anji.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Register extends HttpServlet {
	
	String url="jdbc:mysql://localhost:3306/user";
	String username="root";
	String password="1489";
	PrintWriter pw;
	Connection conn=null;
	PreparedStatement stmt=null;
	String q="insert into register(name,phone,email,password,address)values(?,?,?,?,?)";
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 conn=DriverManager.getConnection(url,username,password);
			stmt=conn.prepareStatement(q);
			 pw=resp.getWriter();
			String name=req.getParameter("name");
			Long phone=Long.parseLong(req.getParameter("phone"));
			String email=req.getParameter("email");
			String pwd=req.getParameter("pwd");
			String cpwd=req.getParameter("cpwd");
			String add=req.getParameter("add");
			stmt.setString(1, name);
			stmt.setLong(2, phone);
			stmt.setString(3, email);
			stmt.setString(4, pwd);
			stmt.setString(5, add);
			
			int x=stmt.executeUpdate();
			if(x!=0) {
				pw.println(name+" Successfully registerd");
			}
			else {
				pw.println(name+"please check once");
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
