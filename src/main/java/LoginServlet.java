


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter(); // Corrected method name
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		
		// jdbc connection
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobs", "root", "Msgm@123");
			
		
			Statement st = conn.createStatement();
			String query = " select * from applicantsignup where Email='" + email+ "' and Password='" + password+"'";

			String query1 = "select * from applicantregister where Email=?";
			PreparedStatement ps=conn.prepareStatement(query1);
			ps.setString(1, email);
			
			ResultSet rs = st.executeQuery(query);

	        ResultSet n = ps.executeQuery();
			
			
			if (rs.next()) {
				 if(n.next()) {
					 
						String FirstName = n.getString("FirstName");
						String LastName = n.getString("LastName");	
						request.setAttribute("FirstName", FirstName);
						request.setAttribute("LastName", LastName);
						HttpSession session = request.getSession();
						session.setAttribute("Email",email);
						session.setAttribute("FirstName",FirstName);
						session.setAttribute("LastName",LastName);
						request.getRequestDispatcher("UserDashBoard.jsp").forward(request, response);;
				 }
		            
		            
		            
		           
			} else 
			{	
				request.setAttribute("error", "INVALID EMAIL OR PASSWORD");
				RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
				rd.include(request, response);
				//out.print("<h1>" + email + ":please enter correct credentials</h1><br>");
				
		}
			rs.close();
			st.cancel();
			conn.close();
		} catch (ClassNotFoundException e) {
			out.print("<h1>Login Failed exception!</h1><br>");
			e.printStackTrace();
		} catch (SQLException e) {
			out.print("<h1>Login Failed ex!</h1><br>");
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	


}


	
