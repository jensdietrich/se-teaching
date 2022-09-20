package nz.ac.vuw.jenz.webkid;

import java.io.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Get client details for an id.
 * This illustrates a cross-site scripting vulnerability.
 * @author  Jens Dietrich
 */
@WebServlet(name = "GetClientDetails", urlPatterns = "/client")
public class GetClientDetails extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        String id = request.getParameter("id");
        Client client = DataManager.getClient(id);

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        if (client==null) {
            out.println("details not found: " + id);
        } else {
            out.println("client details: " + client.toString());
        }
        out.println("</body></html>");
        
        out.close();
    }


}