package amazonPro;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class signup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PrintWriter pw=response.getWriter();
			String n=request.getParameter("Fname");
			String m=request.getParameter("No");
			String p=request.getParameter("Pass");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection c=DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1521:xe","system","system");
			
			PreparedStatement ps = c.prepareStatement("insert into signup values (?,?,?)");
			
			ps.setString(1, n);
			ps.setString(2, m);
			ps.setString(3, p);
			ps.execute();
			ResultSet rs=ps.executeQuery();
			
			
			int b = ps.executeUpdate();
			if(b>0) {
				RequestDispatcher rd = request.getRequestDispatcher("login.html");
				rd.forward(request, response);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace(); // Log the exception stack trace
		    System.out.println("Something went wrong!!!!!!! " + e.getMessage());
		}
	}

}
