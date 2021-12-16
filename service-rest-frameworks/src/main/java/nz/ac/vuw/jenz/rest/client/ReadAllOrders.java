package nz.ac.vuw.jenz.rest.client;

import nz.ac.vuw.jenz.rest.Order;
import static nz.ac.vuw.jenz.rest.client.Helpers.*;

/**
 * Client example reading some orders.
 * Note: server (nz.ac.vuw.jenz.rest.server.OrderManagement) must be started. Restart server to reset database.
 * @author jens dietrich
 */
public class ReadAllOrders  {

    public static void main(String[] args) {
        try {
            System.out.println("Reading all orders from server via rest service:");
            for (Order order : fetchAll()) {
                System.out.println(order);
            }
        }
        catch (Exception x) {
            System.err.println("check that server is running !");
            x.printStackTrace();
        }
    }

    public static Order[] fetchAll() throws Exception {
        String s = client
            .resource("http://localhost:8080")
            .path("orders")
            .accept("application/json")
            .get(String.class);
        return mapper.readValue(s,Order[].class);
    }
}
