

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RecruiterEditServlet
 */
@WebServlet("/RecruiterEditServlet")
public class RecruiterEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecruiterEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String email = request.getParameter("email");
		 HttpSession session = request.getSession();
		 String FirstName = (String) session.getAttribute("FirstName");
		 String LastName = (String) session.getAttribute("LastName");

//       if (email != null && !email.isEmpty()) {
           try {
           																		
               // Database connection
               Class.forName("com.mysql.cj.jdbc.Driver");
               Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobs", "root", "Msgm@123");

               // Query to retrieve user information
               String query = "SELECT * FROM recruiterregister WHERE Email=?";
               PreparedStatement ps = conn.prepareStatement(query);
               ps.setString(1, email);

               // Execute query
               ResultSet rs = ps.executeQuery();

               if (rs.next()) {
               	
                   // Retrieve user information from the result set
                   String firstName = rs.getString("FirstName");
                   String lastName = rs.getString("LastName");
                   String numericField = rs.getString("Mobile");
                   String Company = rs.getString("Company");
                   String RecruiterId = rs.getString("RecruiterId");
                   String Location = rs.getString("Location");
                   

                   // Set retrieved data as attributes in the request
                   request.setAttribute("FirstName", firstName);
                   request.setAttribute("LastName", lastName);
                   request.setAttribute("Email", email);
                   request.setAttribute("Mobile", numericField);
                   request.setAttribute("Company", Company);
                   request.setAttribute("RecruiterId", RecruiterId);
                   request.setAttribute("Location", Location);
                   

                   // Forward the request to update.jsp
                   RequestDispatcher dispatcher = request.getRequestDispatcher("RecruiterUpdate.jsp");
                   dispatcher.forward(request, response);
               } else {
                   // If user not found, redirect to error page
                   response.sendRedirect("error.jsp");
               }

               // Close resources
               rs.close();
               ps.close();
               conn.close();

           } catch (ClassNotFoundException | SQLException e) {
               // Handle exceptions
               e.printStackTrace();
               response.sendRedirect("error.jsp");
           }
//       } else {
//           // If email parameter is null or empty, redirect to error page
//           response.sendRedirect("error.jsp");
//       }
		
	}

}
