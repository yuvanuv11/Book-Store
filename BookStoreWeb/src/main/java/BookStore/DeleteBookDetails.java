package BookStore;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/delete")
public class DeleteBookDetails extends HttpServlet{
	Connection con=null;

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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		int bookid= Integer.parseInt(req.getParameter("bookid"));

		PreparedStatement pstmt=null;
		
		String query="delete from book_details where book_id=?";

			try {
				pstmt=con.prepareStatement(query);
				pstmt.setInt(1,bookid);
				
				PrintWriter pw =resp.getWriter();
				pw.print("<h1>"+" ...");
				pw.print("<h1>"+"..");
				int count=pstmt.executeUpdate();
				
				if (count==1) {
					
					
					pw.print("<h1>"+count+" Recourd Deleted");
					pw.print("<a href='delete.html'>");
					pw.print("<button class='btn btn-danger'>Delete Again</button>");
					pw.print("</a>");
					
					pw.print("<a href='index.html'>");
					pw.print("<button class='btn btn-danger'>Home</button>");
					pw.print("</a>");
					
				}
				else if(count>1) {
					
					pw.print("<h1>"+count+" Recourds Deleted");
					
					pw.print("<a href='delete.html'>");
					pw.print("<button class='btn btn-danger'>Delete Again</button>");
					pw.print("</a>");
					
					pw.print("<a href='index.html'>");
					pw.print("<button class='btn btn-danger'>Home</button>");
					pw.print("</a>");
					
				}
				else  {
					
					pw.print("<h1>"+"no Recourd Deleted");
					
				}
				
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
	}

}
