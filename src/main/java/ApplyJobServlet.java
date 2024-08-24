
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

@WebServlet("/applyjob")
public class ApplyJobServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get parameters from the request
        String jobId = request.getParameter("jobId");
        String recruiterId = request.getParameter("recruiterId");
        String recruiterMail = request.getParameter("RecruiterMail");
        String email = request.getParameter("email");
        String skills = request.getParameter("skills");
        int experience = Integer.parseInt(request.getParameter("experience"));
        String location = request.getParameter("location");
        
        // Insert data into jobapplies table
        if (insertJobApplication(jobId, recruiterId, recruiterMail, email, skills, experience, location)) {
            response.getWriter().println("Job application submitted successfully!");
        } else {
            response.getWriter().println("Failed to submit job application. Please try again Or You may have already applied to this job");
        }
    }

    // Method to insert job application data into jobapplies table
    private boolean insertJobApplication(String jobId, String recruiterId, String recruiterMail, String email, String skills, int experience, String location) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            // Connect to the database
        	 Class.forName("com.mysql.cj.jdbc.Driver");
        
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobs", "root", "Msgm@123");
                        // SQL query to insert data into jobapplies table
            String sql = "INSERT INTO jobapplies (JobId, RecruiterId, RecruiterMail, Email, Skills, Experience, Location) VALUES (?, ?, ?, ?, ?, ?, ?)";
            
            // Create prepared statement
            stmt = conn.prepareStatement(sql);
            
            // Set parameters
            stmt.setString(1, jobId);
            stmt.setString(2, recruiterId);
            stmt.setString(3, recruiterMail);
            stmt.setString(4, email);
            stmt.setString(5, skills);
            stmt.setInt(6, experience);
            stmt.setString(7, location);

            // Execute the statement
            int rowsAffected = stmt.executeUpdate();

            // Check if the insertion was successful
            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            // Close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
