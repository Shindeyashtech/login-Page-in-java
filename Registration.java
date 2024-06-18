

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet implementation class Registration
 */
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String JdbcUrl="jdbc:mysql://localhost:3306/login";
		String dbUser ="root";
		String dbPassword = "root";
		
		String Name =request.getParameter("RegistrationName");
		String UName =request.getParameter("RegistrationUserName");
		String email =request.getParameter("RegistrationEmail");
		String Number =request.getParameter("RegistrationNumber");
		String password =request.getParameter("RegistrationPassword");
		String ConPassword = request.getParameter("RegistrationConfirmPassword");
		
		if (!password.equals(ConPassword)){
			response.sendRedirect("Registration.jsp?=password do not match");
			return;
			}
		try {
			Class.forName("con.mysql.cj.jdbc.Driver");
			try(Connection conn = DriverManager.getConnection(JdbcUrl,dbUser,dbPassword)){
				String checkQuery = "select * from user where username = ? or email=?";
				try(PreparedStatement ps = conn.prepareStatement(checkQuery)){
					ps.setString(1,Name);
					ps.setString(2,UName );
					ps.setString(3,email);
					ps.setString(4,Number );
					ps.setString(5,password);
					ps.setString(6,ConPassword);
					
					int rows = ps.executeUpdate();
					if(rows > 0) {
						response.sendRedirect("Registration.jsp?=Registration sucessful");
					}else {
						response.sendRedirect("Registration.jsp?=failed to Register user");
					}	
				}
				
			}catch (SQLException e) {
				e.printStackTrace();
				response.sendRedirect("Registration.jsp?=Database error");
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
				response.sendRedirect("Registration.jsp?=Jdbc Driver npt found")
			}

		
	}

}
