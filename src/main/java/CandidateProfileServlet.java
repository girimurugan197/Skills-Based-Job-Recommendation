

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

@WebServlet("/CandidateProfileServlet")
public class CandidateProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	String Email = (String) session.getAttribute("Email");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        

//        String email = request.getParameter("email");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobs", "root", "Msgm@123");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM applicantregister WHERE Email=?");
            ps.setString(1, Email);
            ResultSet rs = ps.executeQuery();

            out.println("<html><head><title>User Profile</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"RecruiterProfile.css\">");
            out.println("</head><body>");
            out.println("<div class=\"navcontainer\"><center><h2>Candidate Profile</h2><center></div>");

            if (rs.next()) {
                out.println("<div class=\"profile-table\">");
                out.println("<table class=\"center\">");
                out.println("<tr><th>Attribute</th><th>Value</th></tr>");
                
                // Iterate through the result set and print each attribute and its value
                out.println("<tr><td>First Name</td><td>" + rs.getString("FirstName") + "</td></tr>");
                out.println("<tr><td>Last Name</td><td>" + rs.getString("LastName") + "</td></tr>");
                out.println("<tr><td>Email</td><td>" + rs.getString("Email") + "</td></tr>");
                out.println("<tr><td>Mobile No</td><td>" + rs.getString("Mobile") + "</td></tr>");
                out.println("<tr><td>University</td><td>" + rs.getString("University") + "</td></tr>");
                out.println("<tr><td>Degree</td><td>" + rs.getString("Degree") + "</td></tr>");
                out.println("<tr><td>specialization</td><td>" + rs.getString("Specialization") + "</td></tr>");
                out.println("<tr><td>Year</td><td>" + rs.getString("Year") + "</td></tr>");
                
                out.println("</table></center>");
                out.println("</div>");
                out.println("<div class=\"button-container\">");
                out.println("<button name='editButton' onclick=\"location.href='CandidateEdit?email=" + Email + "'\">Edit</button>");
                out.println("<button onclick=\"location.href='UserDashBoard.jsp?email=" + Email + "'\">Back</button>");
                out.println("</div>");
            } else {
                out.println("<p>No user found with email: " + Email + "</p>");
            }

            out.println("</body></html>");

            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
}

}
