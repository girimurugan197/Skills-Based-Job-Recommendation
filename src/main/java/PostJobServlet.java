

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class PostJobServlet
 */
@WebServlet("/PostJobServlet")
public class PostJobServlet extends HttpServlet {
	private static final String CSV_FILE_PATH = "A:\\job.csv";
	public static final String INSERT_QUERY = "INSERT INTO jobposting(JobId,JobRole,RequiredSkills,JobDescription,Experience,Location,Company,RecruiterId,RecruiterMail,RecruiterMobile) VALUES(?,?,?,?,?,?,?,?,?,?)";
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostJobServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	private void updateCSV() {
	    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobs", "root", "Msgm@123");
	         PreparedStatement ps = con.prepareStatement("SELECT * FROM jobposting");
	         ResultSet rs = ps.executeQuery();
	         BufferedWriter bw = new BufferedWriter(new FileWriter(CSV_FILE_PATH, true))) {

	        // Append each job record to the CSV file
	        while (rs.next()) {
	            String[] skillsArray = rs.getString("RequiredSkills").split(","); // Split the skills string into an array
	            StringBuilder skillString = new StringBuilder();

	            for (String skill : skillsArray) {
	                skillString.append(skill).append("; "); // Use semicolon as delimiter
	            }

	            // Remove the trailing delimiter and space
	            if (skillString.length() > 0) {
	                skillString.setLength(skillString.length() - 2);
	            }

	            String jobData = String.join(",", 
	            							   rs.getString("JobRole"), 
	                                           skillString.toString(), // Use the concatenated skill string
	                                           rs.getString("JobDescription"), 
	                                           rs.getString("Experience"), 
	                                           rs.getString("Location"), 
	                                           rs.getString("Company"),
	                                           rs.getString("RecruiterId"), 
	                                           rs.getString("RecruiterMail"), 
	                                           rs.getString("RecruiterMobile"),
	                                           rs.getString("JobId"));

	            bw.write(jobData);
	            bw.newLine();
	        }

	        System.out.println("CSV file updated successfully!");
	    } catch (SQLException | IOException e) {
	        e.printStackTrace();
	    }
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");

		ServletContext context = getServletContext();
		String JobId = request.getParameter("JobId");
		String JobRole = request.getParameter("JobRole");
		String[] skillsArray = request.getParameterValues("RequiredSkills"); // Get array of skills
        String RequiredSkills = String.join(",", skillsArray);
		String JobDescription = request.getParameter("JobDescription");
		String Experience = request.getParameter("Experience");
		String Location = request.getParameter("Location");
		String Company = request.getParameter("Company");
		String RecruiterId = request.getParameter("RecruiterId");
		String RecruiterMail = request.getParameter("RecruiterMail");
		String RecruiterMobile = request.getParameter("RecruiterMobile");

		Connection con = null;
		PreparedStatement ps = null;

		try {
		    Class.forName("com.mysql.cj.jdbc.Driver");
		    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobs", "root", "Msgm@123");
		    ps = con.prepareStatement(INSERT_QUERY);
		    
		    ps.setString(1,	JobId);
		    ps.setString(2, JobRole);
		    ps.setString(3, RequiredSkills);
		    ps.setString(4, JobDescription);
		    ps.setString(5, Experience);
		    ps.setString(6, Location);
		    ps.setString(7, Company);
		    ps.setString(8, RecruiterId);
		    ps.setString(9, RecruiterMail);
		    ps.setString(10, RecruiterMobile);

		    int count = ps.executeUpdate();
		    if (count == 0) {
		        pw.println("Record not stored into Database");
		    } else {
		    	updateCSV();
		    	pw.println("<script>");
		    	pw.println("alert('Job Posted Successfully');");
		    	pw.println("</script>");

		    }
		} catch (ClassNotFoundException | SQLException e) {
		    pw.println(e.getMessage());
		    e.printStackTrace();
		} finally {
		    try {
		        if (ps != null) {
		            ps.close();
		        }
		        if (con != null) {
		            con.close();
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    pw.close();
		}
	}
}

