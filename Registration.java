

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		   String jdbcUrl = "jdbc:mysql://localhost:3306/login";
	        String dbUser = "root";
	        String dbPassword = "root";

	        String name = request.getParameter("RegistrationName");
	        String username = request.getParameter("RegistrationUserName");
	        String email = request.getParameter("RegistrationEmail");
	        String number = request.getParameter("RegistrationNumber");
	        String password = request.getParameter("RegistrationPassword");
	        String confirmPassword = request.getParameter("RegistrationConfirmPassword");

	        if (!password.equals(confirmPassword)) {
	            response.sendRedirect("Registration.jsp?message=Passwords do not match");
	            return;
	        }

	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
//	            Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
	               try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
	                	 
	            	  System.out.println("Connection successful!");
	               
	                String checkQuery = "SELECT * FROM user WHERE username = ? OR email = ?";
	                try (PreparedStatement ps = conn.prepareStatement(checkQuery)) {
	                    ps.setString(1, username);
	                    ps.setString(2, email);

	                    try (ResultSet rs = ps.executeQuery()) {
	                        if (rs.next()) {
	                            response.sendRedirect("Registration.jsp?message=Username or Email already exists");
	                            return;
	                        }
	                    }
	                }

	                // Insert the new user into the database
	                String insertQuery = "INSERT INTO user (name, username, email, number, password) VALUES (?, ?, ?, ?, ?)";
	                try (PreparedStatement ps = conn.prepareStatement(insertQuery)) {
	                    ps.setString(1, name);
	                    ps.setString(2, username);
	                    ps.setString(3, email);
	                    ps.setString(4, number);
	                    ps.setString(5, password);

	                    int rows = ps.executeUpdate();
	                    if (rows > 0) {
	                        response.sendRedirect("Registration.jsp?message=Registration successful");
	                    } else {
	                        response.sendRedirect("Registration.jsp?message=Failed to register user");
	                    }
	                }
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	                response.sendRedirect("Registration.jsp?message=Database error: " + e.getMessage());
	            }
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	            response.sendRedirect("Registration.jsp?message=JDBC Driver not found");
	        }
	    }
	}
