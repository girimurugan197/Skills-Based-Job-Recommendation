

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RecruiterUpdateServlet
 */
@WebServlet("/RecruiterUpdateServlet")
public class RecruiterUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecruiterUpdateServlet() {
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
		 String firstName = request.getParameter("FirstName");
	        String lastName = request.getParameter("LastName");
	        String email = request.getParameter("Email");
	        String mobileNo = request.getParameter("numericField");
	        String Company = request.getParameter("Company");
	        String RecruiterId = request.getParameter("RecruiterId");
	        String Location = request.getParameter("Location");
	        
	        System.out.println(email);
	        Connection con = null;
	        PreparedStatement ps = null;

	        try {
	            // Load the MySQL JDBC driver
	            Class.forName("com.mysql.cj.jdbc.Driver");

	            // Establish a connection to the database
	            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobs", "root", "Msgm@123");

	            // Prepare SQL query to update user information
	            ps = con.prepareStatement("UPDATE recruiterregister SET FirstName=?, LastName=?, Mobile=?, Company=?, RecruiterId=?, Location=? WHERE Email=?");
	            ps.setString(1, firstName);
	            ps.setString(2, lastName);
	            ps.setString(3, mobileNo);
	            ps.setString(4, Company);
	            ps.setString(5, RecruiterId);
	            ps.setString(6, Location);
	            ps.setString(7, email);
	            
	            request.setAttribute("FirstName", firstName);
	            request.setAttribute("LastName", lastName);
	            request.setAttribute("Email", email);
	            request.setAttribute("Mobile", mobileNo);
	            request.setAttribute("Company", Company);
	            request.setAttribute("RecruiterId", RecruiterId);
	            request.setAttribute("Location", Location);
	           

	            // Execute the update query
	            int rowsUpdated = ps.executeUpdate();

	            // Check if the update was successful
	            if (rowsUpdated > 0) {
	                // If successful, redirect to a profile servlet with the updated user's email as a parameter
	                response.sendRedirect("RecruiterProfileServlet?email=" + email);
	            } else {
	                // If update failed, display an error message
	                response.getWriter().println("Failed to update user information.");
	            }
	        } catch (ClassNotFoundException | SQLException e) {
	            // Log the specific error message
	            log("An error occurred while updating user information: " + e.getMessage());
	            
	            // Send a generic error message to the client
	            response.getWriter().println("An error occurred while updating user information. Please try again later.");
	        } finally {
	            // Close the PreparedStatement and Connection in a finally block to ensure they are always closed
	            try {
	                if (ps != null) {
	                    ps.close();
	                }
	                if (con != null) {
	                    con.close();
	                }
	            } catch (SQLException e) {
	                // Log any error that occurs while closing resources
	                log("Error closing resources: " + e.getMessage());
	            }
	        }
	    }
		
	}


