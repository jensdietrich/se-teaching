package nz.ac.vuw.jenz.osgi.webui;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nz.ac.vuw.jenz.osgi.df_spec.DateFormatter;

/**
 * Servlet for the simple web ui using the dataformatter service.
 * @author jens dietrich
 */

public class DateServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		out.println("<html>");
		out.println("<body>");
		DateFormatter df = null;
		for (DateFormatter f:Activator.knownDateFormatters.values()) 
			df = f;
		out.print("the current time is: ");
		out.println(df==null?new Date().toString():df.format(new Date()));
		out.println("<p/>");
		out.print("date formatter used is: ");
		out.println(df==null?"no date formatter service found":df.toString());
		out.println("</body>");
		out.println("</html>");
		out.close();
	}

}
