


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import com.opencsv.CSVWriter;

import javax.servlet.*;
import javax.servlet.http.*;


@WebServlet("/RECRUITERSUBMIT")

public class RecruiterRegisterServlet extends HttpServlet {
	private static final String INSERT_QUERY = "INSERT INTO recruiterregister(FirstName,LastName,Email,Mobile,Company,RecruiterId,Location) VALUES(?,?,?,?,?,?,?)";
	//
	
	@SuppressWarnings("unused")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		ServletContext context = getServletContext();

		String FirstName = req.getParameter("FirstName");
		String LastName = req.getParameter("LastName");
		String Email = req.getParameter("Email");
		String numericField = req.getParameter("numericField");
		String Company = req.getParameter("Company");		
		String RecruiterId = req.getParameter("RecruiterId");
		String Location = req.getParameter("Location");

		

		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobs", "root", "Msgm@123");
				PreparedStatement ps = con.prepareStatement(INSERT_QUERY);) {
			ps.setString(1, FirstName);
			ps.setString(2, LastName);
			ps.setString(3, Email);
			ps.setString(4, numericField);
			ps.setString(5,Company);
			ps.setString(6, RecruiterId);
			ps.setString(7, Location);
			int count = ps.executeUpdate();
			if (count == 0) {
			pw.println("Record not stored into Database");
			} else {
				
			
				
				req.setAttribute("Email",Email);
				req.setAttribute("FirstName", FirstName);
				req.setAttribute("LastName", LastName);
				req.setAttribute("RecruiterId", RecruiterId);
				HttpSession session = req.getSession();
				session.setAttribute("Email", Email);
				session.setAttribute("RecruiterId", RecruiterId);
				req.getRequestDispatcher("RecruiterDashboard.jsp").forward(req, res);
				
				
		}
		}
		catch (SQLException se) {
			pw.println(se.getMessage());
			se.printStackTrace();
		} catch (Exception e) {
			pw.println(e.getMessage());
			e.printStackTrace();
		}
		
		
		
		
		 
		

		pw.close();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}
}
