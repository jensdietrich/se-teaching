package nz.ac.vuw.jenz.servlets.modular;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * The logout.
 * @author  Jens Dietrich
 */

// alternative, annotation-based configuration, see README for discussion
// @WebServlet(name = "Modular.Logout", urlPatterns = "/modular/logout")
public class Logout extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        session.removeAttribute("login");
        session.removeAttribute("counter");
        session.invalidate();
        getServletContext().getRequestDispatcher("/modular/login").forward(request,response);
    }

}
