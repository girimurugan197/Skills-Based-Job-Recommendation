

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
 * Servlet implementation class ViewAppliedJobs
 */
@WebServlet("/ViewAppliedJobs")
public class ViewAppliedJobs extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String SELECT_QUERY = "SELECT * FROM jobapplies WHERE Email=?";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewAppliedJobs() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String CandidateEmail = (String) session.getAttribute("Email");
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobs", "root","Msgm@123");
			PreparedStatement ps = conn.prepareStatement(SELECT_QUERY);
			ps.setString(1, CandidateEmail);
			ResultSet rs = ps.executeQuery();
			PrintWriter writer = response.getWriter();
			
			writer.println("<!DOCTYPE html>");
			writer.println("<html>");
			writer.println("<head>");
			writer.println("<title>Applied Jobs</title>");
			writer.println("<style>");
			writer.println("table { width: 100%; border-collapse: collapse; }");
			writer.println("th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }");
			writer.println("th { background-color: #f2f2f2; }");
			writer.println(".popup-container { display: none; width: auto; border-radius: 20px; position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%); background: white; padding: 20px; border: 2px solid #333; z-index: 999; }");
			writer.println(".popup-container.active { display: block; }");
			writer.println(".popup-container p strong { font-weight: bold; }");
			writer.println("</style>");
			writer.println("</head>");
			writer.println("<body>");
			writer.println("<h2 style='text-align: center;'>Posted Jobs</h2>");
			writer.println("<table>");
			writer.println("<tr><th>Job ID</th><th>Job Role</th><th> Skills</th><th>View More</th></tr>");
			
			while (rs.next()) {
			    String JobId = rs.getString("JobId");
			    String RecruiterId = rs.getString("RecruiterID");
			    String Skills = rs.getString("Skills");
			    String Experience = rs.getString("Experience");
			    String Location = rs.getString("Location");
			   

			    writer.println("<tr>");
			    writer.println("<td>" + JobId + "</td>");
			    writer.println("<td>" + RecruiterId + "</td>");
			    writer.println("<td>" + Skills + "</td>");
			    writer.println("<td><button onclick=\"viewMore('" + Experience + "', '" + Location + "')\">View More</button></td>");
			    writer.println("</tr>");
			
			}
			
			
			writer.println("</table>");
			writer.println("<div id='moreDetails' class='popup-container'>");
			writer.println("<h3>Additional Details</h3>");
			writer.println("<p><strong>Experience:</strong> <span id='Experience'></span></p>");
			writer.println("<p><strong>Location:</strong> <span id='Location'></span></p>");			
			writer.println("<button onclick='hidePopup()'>Close</button>");
			writer.println("</div>");
			writer.println("<script>");
			writer.println("function viewMore(Experience, Location) {");
			writer.println("document.getElementById('Experience').textContent = Experience;");
			writer.println("document.getElementById('Location').textContent = Location;");			
			writer.println("document.getElementById('moreDetails').classList.add('active');");
			writer.println("}");
			writer.println("function hidePopup() {");
			writer.println("document.getElementById('moreDetails').classList.remove('active');");
			writer.println("}");
			writer.println("</script>");
			writer.println("</body>");
			writer.println("</html>");
			
            conn.close();
			
			}
		
		
		
		
		 catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// TODO Auto-generated method stub
		
	
	}

}
