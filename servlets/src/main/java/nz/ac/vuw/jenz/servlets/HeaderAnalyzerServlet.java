package nz.ac.vuw.jenz.servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.*;

/**
 * Servlet displaying the request headers.
 * @author  Jens Dietrich
 */
// alternative, annotation-based configuration, see README for discussion
// @WebServlet(name = "HeaderAnalyzer", urlPatterns = "/HeaderAnalyzer")
public class HeaderAnalyzerServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
       
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet</title>");
        out.println("</head>");
        out.println("<body>");
         
        out.println("<h1>Echoing Request Headers</h1>");
        out.println("<table border><th>Header Name</th><th>Header Value</th>");
        // list headers send by the client
        for (Enumeration headers = request.getHeaderNames();headers.hasMoreElements();) {
            String header = headers.nextElement().toString();
            String value = request.getHeader(header);
            out.print("<tr><td>");
            out.print(header);
            out.print("</td><td>");
            out.print(value);
            out.println("</td></tr>");
        }
        
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
        
        out.close();
    }

    
}
