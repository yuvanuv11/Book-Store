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
@WebServlet("/addbooks")
public class AddBookDetials extends HttpServlet {
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
		int id=(int) Double.parseDouble(req.getParameter("bookid"));
		String name=req.getParameter("bookname");
		double price=Double.parseDouble(req.getParameter("bookprice"));
		String author=req.getParameter("author");
		
		PreparedStatement pstmt=null;
		
		String query="insert into book_details values(?,?,?,?)";

			try {
				pstmt=con.prepareStatement(query);
				pstmt.setInt(1, id);
				pstmt.setString(2, name);
				pstmt.setDouble(3, price);
				pstmt.setString(4, author);
				int count = pstmt.executeUpdate();
				PrintWriter pw =resp.getWriter();
				pw.print("<h1>"+" Recourd Inserted");
				pw.print("<br>");
				
				pw.print("<a href='add.html' style='margin-right:10px'>");
				pw.print("<button class='btn btn-danger' style='background-color:#2BB80F'; border-radius: 5px; padding: 10px 20px; border: none>Add Again</button>");
				pw.print("</a>");
				
				pw.print("<a href='index.html'>");
				pw.print("<button class='btn btn-danger' style='background-color:#FC8D17'>Home</button>");
				pw.print("</a>");
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
	}
	

}
