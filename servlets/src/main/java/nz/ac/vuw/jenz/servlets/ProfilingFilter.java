
package nz.ac.vuw.jenz.servlets;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * A simple filter that measures and logs the times it takes to handle a request.
 * @author  Jens Dietrich
 */

// alternative, annotation-based configuration, see README for discussion
// @WebFilter(filterName = "ProfiningFilter", urlPatterns = "/*")
public class ProfilingFilter implements Filter {

    private FilterConfig filterConfig = null;

    /**
     * Intercept request and response with an AOP-like filter.
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {

    	// BEFORE
		long before = System.currentTimeMillis();

		// ACTUAL INVOKE
		chain.doFilter(request, response);

		// AFTER
		long after = System.currentTimeMillis();
		String msg = String.format("Time to process %s was %d ms",((HttpServletRequest)request).getRequestURI(),after-before);
        // server version of System.out.println -- logs into server log system
		filterConfig.getServletContext().log(msg);

    }

    public void destroy() {
    	this.filterConfig = null;
    }

    public void init(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
    }

}
