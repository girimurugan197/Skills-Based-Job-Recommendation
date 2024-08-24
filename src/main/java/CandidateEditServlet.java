
import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/CandidateEdit")
public class CandidateEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        // Retrieve email from request parameter
        String email = request.getParameter("email");

//        if (email != null && !email.isEmpty()) {
            try {
            																		
                // Database connection
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobs", "root", "Msgm@123");

                // Query to retrieve user information
                String query = "SELECT * FROM applicantregister WHERE Email=?";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, email);

                // Execute query
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                	
                    // Retrieve user information from the result set
                    String firstName = rs.getString("FirstName");
                    String lastName = rs.getString("LastName");
                    String numericField = rs.getString("Mobile");
                    String university = rs.getString("University");
                    String degree = rs.getString("Degree");
                    String specialization = rs.getString("Specialization");
                    String year = rs.getString("Year");

                    // Set retrieved data as attributes in the request
                    request.setAttribute("FirstName", firstName);
                    request.setAttribute("LastName", lastName);
                    request.setAttribute("Email", email);
                    request.setAttribute("numericField", numericField);
                    request.setAttribute("University", university);
                    request.setAttribute("Degree", degree);
                    request.setAttribute("Specialization", specialization);
                    request.setAttribute("Year", year);

                    // Forward the request to update.jsp
                    RequestDispatcher dispatcher = request.getRequestDispatcher("CandidateUpdate.jsp");
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
//        } else {
//            // If email parameter is null or empty, redirect to error page
//            response.sendRedirect("error.jsp");
//        }
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
