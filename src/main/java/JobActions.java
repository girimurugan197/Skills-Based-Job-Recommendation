

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/JobActions")
public class JobActions extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirect to doPost method
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        String RecruiterId = (String) session.getAttribute("RecruiterId");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobs", "root", "Msgm@123");

            // Query to retrieve job details associated with the recruiter
            String jobQuery = "SELECT * FROM jobapplies WHERE RecruiterId = ?";
            PreparedStatement jobStmt = conn.prepareStatement(jobQuery);
            jobStmt.setString(1, RecruiterId);
            ResultSet jobRs = jobStmt.executeQuery();

            List<JobClass> recommendedJobs = new ArrayList<>();

            while (jobRs.next()) {
                String applicantEmail = jobRs.getString("Email");
                String jobId = jobRs.getString("JobId");

                // Query to retrieve applicant's details
                String applicantQuery = "SELECT * FROM applicantregister WHERE Email = ?";
                PreparedStatement applicantStmt = conn.prepareStatement(applicantQuery);
                applicantStmt.setString(1, applicantEmail);
                ResultSet applicantRs = applicantStmt.executeQuery();

                if (applicantRs.next()) {
                    String firstName = applicantRs.getString("FirstName");
                    String lastName = applicantRs.getString("LastName");
                    String mobile = applicantRs.getString("Mobile");
                    String university = applicantRs.getString("University");
                    String degree = applicantRs.getString("Degree");
                    String passoutYear = applicantRs.getString("Year");
                    String applicantSkills = jobRs.getString("Skills");
                    System.out.println(applicantSkills);
                    String email = applicantRs.getString("Email");
                    int experience = applicantRs.getInt("Experience");
                    String jobLocation = jobRs.getString("Location");

                    // Create a Job object and add it to the list of recommended jobs
                    recommendedJobs.add(new JobClass(jobId, applicantSkills, firstName, lastName, mobile, university, degree, passoutYear, email, experience, jobLocation));
                }

                applicantRs.close();
                applicantStmt.close();
            }

            jobRs.close();
            jobStmt.close();
            conn.close();

            // Generate HTML response
            response.setContentType("text/html");
            PrintWriter writer = response.getWriter();
            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<title>Appiled Job</title>");
            writer.println("<style>");
            writer.println("body { font-family: Arial, sans-serif; background-color: #f4f4f9; margin: 0; padding: 20px; }");
            writer.println("h2 { text-align: center; color: #333; }");
            writer.println("table { width: 100%; border-collapse: collapse; margin: 20px 0; }");
            writer.println("th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }");
            writer.println("th { background-color: #4CAF50; color: white; }");
            writer.println("tr:nth-child(even) { background-color: #f2f2f2; }");
            writer.println("tr:hover { background-color: #ddd; }");
            writer.println("button { background-color: #4CAF50; color: white; border: none; padding: 10px 20px; cursor: pointer; border-radius: 5px; }");
            writer.println("button:hover { background-color: #45a049; }");
            writer.println(".popup-container { display: none; width: 80%; max-width: 600px; border-radius: 10px; position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%); background: white; padding: 20px; border: 2px solid #333; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); z-index: 999; }");
            writer.println(".popup-container.active { display: block; }");
            writer.println(".popup-container h3 { margin-top: 0; }");
            writer.println(".popup-container p { margin: 10px 0; }");
            writer.println(".popup-container button { margin-top: 20px; background-color: #f44336; }");
            writer.println("</style>");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("<h2>Appiled Job</h2>");
            writer.println("<table>");
            writer.println("<tr><th>Job ID</th><th>Applicant Email</th><th>Applicant Skills</th><th>View More</th></tr>");

            for (JobClass job : recommendedJobs) {
                writer.println("<tr>");
                writer.println("<td>" + job.getJobId() + "</td>");
                writer.println("<td>" + job.getEmail() + "</td>");
                writer.println("<td>" + job.getApplicantSkills() + "</td>");
                writer.println("<td><button onclick=\"viewMore('" + job.getEmail() + "', '" + job.getFirstName() + "', '" + job.getLastName() + "', '" + job.getMobile() + "', '" + job.getUniversity() + "', '" + job.getDegree() + "', '" + job.getPassoutYear() + "', '" + job.getJobLocation() + "')\">View More</button></td>");
                writer.println("</tr>");
            }

            writer.println("</table>");
            writer.println("<div id='moreDetails' class='popup-container'>");
            writer.println("<h3>Additional Details</h3>");
            writer.println("<p><strong>First Name:</strong> <span id='firstName'></span></p>");
            writer.println("<p><strong>Last Name:</strong> <span id='lastName'></span></p>");
            writer.println("<p><strong>Mobile:</strong> <span id='mobile'></span></p>");
            writer.println("<p><strong>University:</strong> <span id='university'></span></p>");
            writer.println("<p><strong>Degree:</strong> <span id='degree'></span></p>");
            writer.println("<p><strong>Passout Year:</strong> <span id='passoutYear'></span></p>");
            writer.println("<p><strong>Job Location:</strong> <span id='jobLocation'></span></p>");
            writer.println("<button onclick='hidePopup()'>Close</button>");
            writer.println("</div>");
            writer.println("<script>");
            writer.println("function viewMore(email, firstName, lastName, mobile, university, degree, passoutYear, jobLocation) {");
            writer.println("document.getElementById('firstName').textContent = firstName;");
            writer.println("document.getElementById('lastName').textContent = lastName;");
            writer.println("document.getElementById('mobile').textContent = mobile;");
            writer.println("document.getElementById('university').textContent = university;");
            writer.println("document.getElementById('degree').textContent = degree;");
            writer.println("document.getElementById('passoutYear').textContent = passoutYear;");
            writer.println("document.getElementById('jobLocation').textContent = jobLocation;");
            writer.println("document.getElementById('moreDetails').classList.add('active');");
            writer.println("}");
            writer.println("function hidePopup() {");
            writer.println("document.getElementById('moreDetails').classList.remove('active');");
            writer.println("}");
            writer.println("</script>");
            writer.println("</body>");
            writer.println("</html>");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error occurred: " + e.getMessage());
        }
    }
}
