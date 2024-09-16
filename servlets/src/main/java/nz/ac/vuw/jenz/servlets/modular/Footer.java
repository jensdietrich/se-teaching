package nz.ac.vuw.jenz.servlets.modular;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

/**
 * Footer - to be included by the main servlet.
 * @author  Jens Dietrich   
 */
// alternative, annotation-based configuration, see README for discussion
// @WebServlet(name = "Modular.Footer", urlPatterns = "/modular/footer")
public class Footer extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        // communicates with same session as other elements
        String counterAsString = (String) session.getAttribute("counter");
        if (counterAsString == null) counterAsString = "0";
        int counter = Integer.parseInt(counterAsString);
        session.setAttribute("counter", String.valueOf(counter + 1));

        out.println("<hr>");
        out.print("page no: ");
        out.println(counter);

    }
    
}
