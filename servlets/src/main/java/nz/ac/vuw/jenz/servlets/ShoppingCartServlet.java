package nz.ac.vuw.jenz.servlets;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.util.*;

/**
 * Servlet representing a little shopping cart.
 * Needs cookies to be enabled on the browser!
 * @author  Jens Dietrich
 */

// alternative, annotation-based configuration, see README for discussion
// @WebServlet(name = "ShoppingCart", urlPatterns = "/ShoppingCart")
public class ShoppingCartServlet extends HttpServlet {

    // constants for actions -- actions are encoded in request attributes
    public static final String SELECTION = "selection"; 
    public static final String DESELECT = "deselect"; 
    public static final String SELECT = "select"; 
    public static final String LOGOUT = "logout";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
       
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Shopping Cart Example</title>");
        out.println("</head>");
        out.println("<body>");
         
        out.println("<h1>Jens' Music Store</h1>");
        out.println("This example works only with cookies being enabled!<p>");
        
        // the (hardcoded!) list of available items
        String[] availableItems = {
            "Vinicius De Mores Grabado en Buenos Aires",
            "Toquino: Chega de Saudade",
            "100% Azucar: The Best of Celia Cruz & La Sonora Matancera",
            "Ladysmith Black Mambazo: Shaka Zulu",
            "Stan Getz: The Best of Two Worlds Featuring Joao Gilberto"
        };

        // initialize the list of selected items if needed
        HttpSession session = request.getSession();
        Collection selectedItems = (Collection)session.getAttribute(SELECTION);
        if (selectedItems==null) {
            selectedItems = new TreeSet();
            session.setAttribute(SELECTION, selectedItems);
        }
        
        // execute action
        String action = request.getParameter("action");
        String item = request.getParameter("item");
        if (item!=null) {
            if (SELECT.equals(action)) selectedItems.add(item);
            else if (DESELECT.equals(action)) selectedItems.remove(item);
        }
        else if (LOGOUT.equals(action)) {
            session.invalidate();
            selectedItems.clear();
        }
        
        
        // build "in store" form - list items that have not been selected
        printSelectionList(out, availableItems, SELECT, "Available Items:", "Add to shopping cart");
        
        // print the list of items that have been selected
        if (selectedItems.size()==0) {
            out.println("<h2>No items selected - shopping cart is empty!</h2>");
        }
        else {
            printSelectionList(out, selectedItems.toArray(), DESELECT, "Your shopping cart:", "Remove selected");
        }
        
        // logout button
        out.println("<p><form>");
        out.print("<input type=hidden name=\"action\" value=\"");
        out.print(LOGOUT);
        out.println("\">");
        out.println("<input type=submit value=\"terminate session\">");
        out.println("</form>");
        
        out.println("</body>");
        out.println("</html>");
        
        out.close();
    }

    
    // build a form that can be reused to print selection lists
    private void printSelectionList(PrintWriter out,Object[] items,String actionName,String title,String buttonLabel) throws ServletException, IOException {
        out.print("<h2>");
        out.print(title);
        out.println("</h2>");
        
        // no parameters - this servlet will handle the form and it will be submitted using GET
        out.println("<form>");
        // the action name is encoded as hidden form field
        out.print("<input type=hidden name=\"action\" value=\"");
        out.print(actionName);
        out.println("\">");
        // the list
        out.println("<select name =\"item\" size=5>");
        for (int i=0;i<items.length;i++) {
            out.print(" <option>");
            out.print(items[i]);
            out.println("</option>");
        }
        out.println("</select>");
        out.print("<p><input type=submit value=\"");
        out.print(buttonLabel);
        out.println("\">");
        out.println("</form>");
    }
        
}
