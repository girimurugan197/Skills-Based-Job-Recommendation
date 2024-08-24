
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
import javax.servlet.http.HttpSession;

@WebServlet("/RecruiterProfileServlet")
public class RecruiterProfileServlet extends HttpServlet {
    private static final String SELECT_QUERY = "SELECT * FROM recruiterregister WHERE Email = ?";

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        
//        String Email = req.getParameter("Email");
        HttpSession session = req.getSession();
        String Email=(String) session.getAttribute("Email");

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobs", "root", "Msgm@123");
                 PreparedStatement ps = con.prepareStatement(SELECT_QUERY)) {
                ps.setString(1, Email);
                ResultSet rs = ps.executeQuery();
                out.println("<html><head><title>Recruiter Profile</title>");
                out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"RecruiterProfile.css\">");
                out.println("</head><body>");
                out.println("<div class=\"navcontainer\"><center><h2>Recruiter Profile</h2><center></div>");
                if (rs.next()) {
                    String firstName = rs.getString("FirstName");
                    String lastName = rs.getString("LastName");
                    String numericField = rs.getString("Mobile");
                    String Company = rs.getString("Company");
                    String RecruiterId = rs.getString("RecruiterId");
                    String Location = rs.getString("Location");
                    req.setAttribute("FirstName",firstName );
                    req.setAttribute("LastName", lastName);
                    out.println("<div class=\"profile-table\">");
                    out.println("<table class=\"center\">");
                    out.println("<tr><th>Attribute</th><th>Value</th></tr>");
                    
                    // Iterate through the result set and print each attribute and its value
                    out.println("<tr><td>First Name</td><td>"+firstName + "</td></tr>");
                    out.println("<tr><td>Last Name</td><td>"+lastName+ "</td></tr>");
                    out.println("<tr><td>Email</td><td>" + Email+ "</td></tr>");
                    out.println("<tr><td>Mobile No</td><td>" + numericField+ "</td></tr>");
                    out.println("<tr><td>Comapany</td><td>" + Company+ "</td></tr>");
                    out.println("<tr><td>RecruiterId</td><td>" +RecruiterId + "</td></tr>");
                    out.println("<tr><td>Location<td>" + Location + "</td></tr>");
                    
                    
                    out.println("</table></center>");
                    out.println("</div>");
                    out.println("<div class=\"button-container\">");
                    out.println("<button onclick=\"location.href='RecruiterEditServlet?email=" + Email + "'\">Edit</button>");
                    out.println("<button onclick=\"location.href='RecruiterDashboard.jsp?email=" + Email + "'\">Back</button>");
                    out.println("</div>");
                    
                } else {
                    out.println("<p>No user found with email: " + Email + "</p>");
                }

                out.println("</body></html>");
                
            }
            catch(SQLException e) {
            	out.println(e);
            }
    
         
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            out.println("Error occurred: " + e.getMessage());
        }
    }
}


