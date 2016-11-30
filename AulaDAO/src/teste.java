import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;


@WebServlet("/teste")
public class teste extends HttpServlet {

    protected void service (HttpServletRequest request,HttpServletResponse response)
           throws  ServletException, IOException {

    	if(new DAO()!=null) {
    	PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("funcionando");
		out.println("<body><html>");
    	}
    } 
}