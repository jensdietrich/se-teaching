package nz.ac.vuw.jenz.webkid;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Get client details for an id.
 * This illustrates a cross-site scripting vulnerability. This is a bit more sophisticated than GetClientDetails
 * as it uses a transformation of user-supplied input. Taint tracking should pick this up.
 * @author  Jens Dietrich
 */
@WebServlet(name = "GetClientDetails2", urlPatterns = "/client2")
public class GetClientDetails2 extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        String id = reverse(request.getParameter("id"));
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

    private static String reverse(String str){
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        return sb.toString();
    }

}