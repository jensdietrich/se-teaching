package nz.ac.vuw.jenz.rest.client;

import com.sun.jersey.api.client.ClientResponse;
import nz.ac.vuw.jenz.rest.Order;
import static nz.ac.vuw.jenz.rest.client.Helpers.client;
import static nz.ac.vuw.jenz.rest.client.Helpers.mapper;

/**
 * Client example reading one order.
 * Note: server (nz.ac.vuw.jenz.rest.server.OrderManagement) must be started. Restart server to reset database.
 * @author jens dietrich
 */
public class ReadOneOrder {

    public static void main(String[] args) {
        try {
            System.out.println("Reading order from server via rest service:");
            Order order = fetchById("42");
            if (order!=null) {
                System.out.println(order);
            }
        }
        catch (Exception x) {
            System.err.println("check that server is running !");
            x.printStackTrace();
        }
    }

    public static Order fetchById(String id) throws Exception {
        ClientResponse response = client
            .resource("http://localhost:8080")
            .path("orders")
            .path(id)
            .accept("application/json")
            .get(ClientResponse.class);

        if (response.getStatus()==404) {
            System.err.println("Order with id " + id + " not found (service return 404)");
            return null;
        }
        else {
            String s = response.getEntity(String.class);
            return mapper.readValue(s, Order.class);
        }
    }
}
