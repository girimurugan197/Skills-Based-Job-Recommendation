

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

/**
 * Servlet implementation class ViewJobs
 */
@WebServlet("/ViewJobs")
public class ViewJobs extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final static String SELECT_QUERY = "SELECT * FROM jobposting WHERE RecruiterId=?";

    public ViewJobs() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String RecruiterId = (String) session.getAttribute("RecruiterId");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobs", "root","Msgm@123");
            PreparedStatement ps = conn.prepareStatement(SELECT_QUERY);
            ps.setString(1, RecruiterId);
            ResultSet rs = ps.executeQuery();
            PrintWriter writer = response.getWriter();

            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<title>Posted Jobs</title>");
            writer.println("<style>");
            writer.println("body { font-family: Arial, sans-serif; background-color: #f4f4f9; margin: 0; padding: 20px; }");
            writer.println("h2 { text-align: center; color: #333; margin-bottom: 20px; }");
            writer.println("table { width: 100%; border-collapse: collapse; margin-bottom: 20px; }");
            writer.println("th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }");
            writer.println("th { background-color: #4CAF50; color: white; }");
            writer.println("tr:nth-child(even) { background-color: #f2f2f2; }");
            writer.println("tr:hover { background-color: #ddd; }");
            writer.println("button { background-color: #4CAF50; color: white; border: none; padding: 10px 20px; cursor: pointer; border-radius: 5px; transition: background-color 0.3s ease; }");
            writer.println("button:hover { background-color: #45a049; }");
            writer.println(".popup-container { display: none; width: 80%; max-width: 600px; border-radius: 10px; position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%); background: white; padding: 20px; border: 2px solid #333; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); z-index: 999; }");
            writer.println(".popup-container.active { display: block; }");
            writer.println(".popup-container h3 { margin-top: 0; }");
            writer.println(".popup-container p { margin: 10px 0; }");
            writer.println(".popup-container button { background-color: #f44336; }");
            writer.println("</style>");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("<h2>Posted Jobs</h2>");
            writer.println("<table>");
            writer.println("<tr><th>Job ID</th><th>Job Role</th><th>Skills</th><th>View More</th></tr>");

            while (rs.next()) {
                String JobId = rs.getString("JobId");
                String JobRole = rs.getString("JobRole");
                String Skills = rs.getString("RequiredSkills");
                String Experience = rs.getString("Experience");
                String Location = rs.getString("Location");
                String JD = rs.getString("JobDescription");

                writer.println("<tr>");
                writer.println("<td>" + JobId + "</td>");
                writer.println("<td>" + JobRole + "</td>");
                writer.println("<td>" + Skills + "</td>");
                writer.println("<td><button onclick=\"viewMore('" + Experience + "', '" + Location + "', '" + JD + "')\">View More</button></td>");
                writer.println("</tr>");
            }

            writer.println("</table>");
            writer.println("<div id='moreDetails' class='popup-container'>");
            writer.println("<h3>Additional Details</h3>");
            writer.println("<p><strong>Experience:</strong> <span id='Experience'></span></p>");
            writer.println("<p><strong>Location:</strong> <span id='Location'></span></p>");
            writer.println("<p><strong>Job Description:</strong> <span id='JobDescription'></span></p>");
            writer.println("<button onclick='hidePopup()'>Close</button>");
            writer.println("</div>");
            writer.println("<script>");
            writer.println("function viewMore(Experience, Location, JD) {");
            writer.println("document.getElementById('Experience').textContent = Experience;");
            writer.println("document.getElementById('Location').textContent = Location;");
            writer.println("document.getElementById('JobDescription').textContent = JD;");
            writer.println("document.getElementById('moreDetails').classList.add('active');");
            writer.println("}");
            writer.println("function hidePopup() {");
            writer.println("document.getElementById('moreDetails').classList.remove('active');");
            writer.println("}");
            writer.println("</script>");
            writer.println("</body>");
            writer.println("</html>");

            conn.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
