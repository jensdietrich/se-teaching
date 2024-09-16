package nz.ac.vuw.jenz.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.*;

/**
 * Servlet used to process forms.
 * @author  Jens Dietrich
 */

// alternative, annotation-based configuration, see README for discussion
// @WebServlet(name = "FormAnalyzer", urlPatterns = "/FormAnalyzer")
public class FormAnalyzerServlet extends HttpServlet {

    private void processForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
       
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Form Analyzer Servlet</title>");
        out.println("</head>");
        out.println("<body>");
         
        out.println("<h1>Echoing Submitted Form Values</h1>");
        out.println("<table border><th>Parameter Name</th><th>Parameter Value</th>");
        // list headers send by the client
        for (Enumeration parameters = request.getParameterNames();parameters.hasMoreElements();) {
            String parameter = parameters.nextElement().toString();
            String value = request.getParameter(parameter);
            out.print("<tr><td>");
            out.print(parameter);
            out.print("</td><td>");
            out.print(value);
            out.println("</td></tr>");
        }
                
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
        
        out.close();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processForm(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        processForm(request, response);
    }

    
}
