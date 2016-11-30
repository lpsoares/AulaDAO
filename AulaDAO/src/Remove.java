import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/remove")
public class Remove extends HttpServlet {


	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
        PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<form method='post'>");
		out.println("Remover ID: <input type='number' name='id'><br>");
		out.println("<input type='submit' value='Submit'>");
		out.println("</form>");
		out.println("<body><html>");
	}
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
	
    	DAO dao = new DAO();		
		dao.remove(Integer.valueOf(request.getParameter("id")));
		
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("removido");
        out.println("</body></html>");
		
		dao.close();
        
    } 
}