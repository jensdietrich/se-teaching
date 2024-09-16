package nz.ac.vuw.jenz.servlets.modular;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

/**
 * The login - to be included by the main servlet.
 * @author  Jens Dietrich
 */
// alternative, annotation-based configuration, see README for discussion
// @WebServlet(name = "Modular.Login", urlPatterns = "/modular/login")
public class Login extends HttpServlet {
    public static final String PASSWORD = "42";
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        boolean loginOK = session.getAttribute("login")!=null;
        
        if (!loginOK) {
            // check whether the user just logged in
            String passwd = request.getParameter("password");
            if (PASSWORD.equals(passwd)) {
                loginOK = true;
                session.setAttribute("login", "true");
            }
        }
        
        if (loginOK) {
            getServletContext().getRequestDispatcher("/modular/main").forward(request, response);
        }
        
        // else print login form 
        response.setContentType("text/html");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Modular Application using Include and Forward</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Login</h1>");
        out.println("hint: the password is 42<p>");
        out.println("<form>");
        out.println("<input type=password name=\"password\" size=30>");
        out.println("<input type=submit value=\"login\">");
        out.println("<form>");
        out.print("</form>");
        
        out.println("</body></html>");
        
    }

}
