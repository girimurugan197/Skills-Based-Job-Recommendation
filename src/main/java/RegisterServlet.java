
import java.io.FileWriter;
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


import javax.servlet.*;
import javax.servlet.http.*;

@WebServlet("/SUBMIT")

public class RegisterServlet extends HttpServlet {
	private static final String INSERT_QUERY = "INSERT INTO applicantregister(FirstName,LastName,Email,Mobile,University,Degree,Specialization,Year, Skills, Experience, Location) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
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
		String University = req.getParameter("University");
		String Degree = req.getParameter("Degree");
		String specialization = req.getParameter("specialization");
		String year = req.getParameter("year");
		String Skills = req.getParameter("Skills");
		String Experience = req.getParameter("Experience");
		String Location = req.getParameter("Location");
		
		req.setAttribute("FirstName", FirstName);
		req.setAttribute("LastName", LastName);
//		req.setAttribute("Skills", Skills);
		req.setAttribute("Experience", Experience);
		req.setAttribute("Location", Location);
		
		HttpSession session = req.getSession();
		session.setAttribute("Email", Email);
		
		

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
			ps.setString(5, University);
			ps.setString(6, Degree);
			ps.setString(7, specialization);
			ps.setString(8, year);
			ps.setString(9, Skills);
			ps.setString(10, Experience);
			ps.setString(11, Location);
		
			int count = ps.executeUpdate();
			if (count == 0) {
				pw.println("Record not stored into Database");
			} else {				

				req.getRequestDispatcher("UserDashBoard.jsp").forward(req, res);

			}
		} catch (SQLException se) {
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
		super.doPost(req, resp);
	}
}
