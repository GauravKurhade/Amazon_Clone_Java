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


public class login2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			PrintWriter pw=response.getWriter();
			String u=request.getParameter("mob");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection c=DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1521:xe","system","system");
			
			PreparedStatement ps=c.prepareStatement("select * from signup where name=?");
			
			ps.setString(1, u);
			ps.execute();
			ResultSet rs=ps.executeQuery();
			
			
			if(rs.next()) {
				RequestDispatcher
				rd=request.getRequestDispatcher("index.html");
				rd.forward(request, response);
			}
			else {
				pw.print("Sorry username and password is incorrect");
				RequestDispatcher rd=request.getRequestDispatcher("signup.html");
				rd.include(request, response);
			}
			
			c.close();
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
