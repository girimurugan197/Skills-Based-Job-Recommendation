
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class Signupservlet
 */
@WebServlet("/signupservlet")
public class signupservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public signupservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    private static final String INSERT_QUERY ="insert into applicantsignup(Name, Email, Password) VALUES(?,?,?)";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter(); // Corrected method name
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		request.setAttribute("FirstName",name);
		request.setAttribute("email", email);
		HttpSession session = request.getSession();
		session.setAttribute("Email",email);
        request.getRequestDispatcher("register.jsp").forward(request, response);
		// jdbc connection
		 try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	           
	        } catch (ClassNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
		 try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobs","root","Msgm@123");
	                PreparedStatement ps = con.prepareStatement(INSERT_QUERY);){
	            //set the values
	            ps.setString(1, name);
	            ps.setString(2, email);
	            ps.setString(3,  password);
	           

	            //execute the query
	            int count = ps.executeUpdate();

	            if(count==0) {
	                pw.println("Record not stored into database");
	                System.out.println("hello");
	            }else {
	            	response.sendRedirect("register.jsp"); 
	            }

			
			pw.close();
		}  catch (SQLException e) {
			out.print("<h1>"+"Already" +email+ "EXIST"+"</h1><br>");
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
