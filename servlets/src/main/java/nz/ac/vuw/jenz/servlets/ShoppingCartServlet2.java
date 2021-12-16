package nz.ac.vuw.jenz.servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.*;
import static nz.ac.vuw.jenz.servlets.ShoppingCartServlet.*;

/**
 * Servlet representing a little shopping cart.
 * Works without cookies !
 * @author  Jens Dietrich   
 */

// alternative, annotation-based configuration, see README for discussion
// @WebServlet(name = "ShoppingCart2", urlPatterns = "/ShoppingCart2")
public class ShoppingCartServlet2 extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Jens' Music Store</title>");
        out.println("</head>");
        out.println("<body>");
         
        out.println("<h1>Shopping Cart Example</h1>");
        out.println("This example also works without cookies!<p>");
        
        // the (hardcoded!) list of available items
        String[] availableItems = {
            "Vinicius De Mores Grabado en Buenos Aires",
            "Toquino: Chega de Saudade",
            "100% Azucar: The Best of Celia Cruz & La Sonora Matancera",
            "Ladysmith Black Mambazo: Shaka Zulu",
            "Stan Getz: The Best of Two Worlds Featuring Joao Gilberto"
        };
        // initialize the list of selected items
        HttpSession session = request.getSession();
        Collection selectedItems = (Collection)session.getAttribute(SELECTION);
        if (selectedItems==null) {
            selectedItems = new TreeSet();
            session.setAttribute(SELECTION, selectedItems);
        }
        
        // build uri used to submit forms in order to support URL rewritting
        String uri = response.encodeURL("ShoppingCart2");
        
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
        printSelectionList(out, uri,availableItems, SELECT, "Available Items:", "Add to shopping cart");
        
        // print the list of items that have been selected
        if (selectedItems.size()==0) {
            out.println("<h2>No items selected - shopping cart is empty!</h2>");
        }
        else {
            printSelectionList(out, uri,selectedItems.toArray(), DESELECT, "Your shopping cart:", "Remove selected");
        }
        
        // logout button
        out.print("<p><form action=\"");
        out.print(uri);
        out.println("\">");
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
    private void printSelectionList(PrintWriter out,String uri, Object[] items,String actionName,String title,String buttonLabel) throws ServletException, IOException {
        out.print("<h2>");
        out.print(title);
        out.println("</h2>");
        
        // no parameters - this servlet will handle the form and it will be submitted using GET
        out.print("<form action=\"");
        out.print(uri);
        out.println("\">");
        // the action name is encoded as hidden form field
        out.print("<input type=hidden name=\"action\" value=\"");
        out.print(actionName);
        out.println("\">");
        // the list
        out.println("<select name =\"item\" size=5>");
        for (int i=0;i<items.length;i++) {
            out.print("  <option>");
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
