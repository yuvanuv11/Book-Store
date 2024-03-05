package BookStore;
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
@WebServlet("/login")
public class Login extends HttpServlet {
	Connection con=null;
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore_sc","root", "root@123");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
        String password = req.getParameter("password");
        
        String usernamedb="";
        
        String passworddb="";
        
        PrintWriter pr=resp.getWriter();
        
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        
        try {
            String query = "SELECT * FROM bookstore_users WHERE username=? AND password=?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            
            boolean b=rs.next();
            
            pr.print(b);
            
            
			
            
            
             
            if(b==false) {
            	pr.println("<h1>Login  Failed</h1>");
            }
            
            
            
            else {
            	pr.println("<h1>Login 1 Failed</h1>");
            	resp.sendRedirect("index.html");
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
            
            pr.println("<h1>Error: Unable to process login.</h1>");
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        
        
        
        
        
			
	}
	

}
