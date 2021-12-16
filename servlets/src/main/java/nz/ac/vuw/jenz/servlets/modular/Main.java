package nz.ac.vuw.jenz.servlets.modular;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * The main servlet in an application that uses forward and include.
 * @author  Jens Dietrich
 */
// alternative, annotation-based configuration, see README for discussion
// @WebServlet(name = "Modular.Main", urlPatterns = "/modular/main")
public class Main extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
       
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Modular Application using Include and Forward</title>");
        out.println("</head>");
        out.println("<body>");
        
        // display header - use static page
        this.getServletContext().getRequestDispatcher("/modular/header.html").include(request,response);
        
        // body 
        String counterAsString = (String)session.getAttribute("counter");
        if (counterAsString==null) {
            counterAsString="0";
        }
        int counter = Integer.parseInt(counterAsString);
        out.print("The current server time is: ");
        out.println(new java.util.Date());
        out.println("<p>");
        
        out.print("Click <a href=\"login\">here</a> to continue!");
        
        // display footer - use dynamic page
        this.getServletContext().getRequestDispatcher("/modular/footer").include(request,response);
        
        out.println("</body>");
        out.println("</html>");
        
        out.close();
    }

    
}
