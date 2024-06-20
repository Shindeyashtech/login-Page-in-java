

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.cj.protocol.Resultset;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
	 String username = request.getParameter("username");
	 String password = request.getParameter("password");
	 Connection con = null;
	 PreparedStatement ps = null;
	 ResultSet rs = null;
  
	 try{
		 Class.forName("com.mysql.cj.jdbc.Driver");
		 con = DriverManager.getConnection(	"jdbc:mysql://localhost:3306/login","root","root");
		String query = "select * from user from where username = ? and password = ?";
		 ps = con.prepareStatement(query);
		 ps.setString(1, username);
		 ps.setString(2,password);
		 
		 rs =  ps.executeQuery();
		 if (rs.next()) {
			 HttpSession session = request.getSession();
			 session.setAttribute("username", username );
			 response.sendRedirect("welcome.jsp");
			 
		 }else {
			response.sendRedirect("Login.jsp?error=Invalid Username or password");
		 }	
		 catch(Exception e){
			 e.printStackTrace();
			 response.sendRedirect("Login.jsp?error occured , please try again");	 
		 }finally {
			 
		 }
	 	)
	 }
	}

}
