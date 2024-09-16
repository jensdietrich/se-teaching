package nz.ac.vuw.jenz.servlets;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

/**
 * Simple servlet displaying the server date and time.
 * @author  Jens Dietrich
 */
// alternative, annotation-based configuration, see README for discussion
// @WebServlet(name = "ServerDate", urlPatterns = "/ServerDate")
public class ServerDateServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Server Date Servlet</title>");
        out.println("</head>");
        out.println("<body>");
         
        out.println("<h1>Shows the Current Server Time</h1>");
        java.util.Date now = new java.util.Date();
        out.println(now);
        
        out.println("</body>");
        out.println("</html>");
        
        out.close();
    }


}