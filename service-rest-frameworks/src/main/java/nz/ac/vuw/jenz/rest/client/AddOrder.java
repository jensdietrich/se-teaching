package nz.ac.vuw.jenz.rest.client;

import com.sun.jersey.api.client.ClientResponse;
import nz.ac.vuw.jenz.rest.Order;
import static nz.ac.vuw.jenz.rest.client.Helpers.client;
import static nz.ac.vuw.jenz.rest.client.Helpers.mapper;

/**
 * Client example adding one order.
 * Note: server (nz.ac.vuw.jenz.rest.server.OrderManagement) must be started. Restart server to reset database.
 * @author jens dietrich
 */
public class AddOrder {

    public static void main(String[] args) {
        try {
            System.out.println("Adding order via service");
            Order newOrder = new Order();
            newOrder.setId("46");
            newOrder.setProduct("cheese");
            newOrder.setAmount(5);
            newOrder.setPrice(12.00);
            if (addOrder(newOrder)) {
                System.out.println("Order succesfully added !");
            }
        }
        catch (Exception x) {
            System.err.println("check that server is running !");
            x.printStackTrace();
        }
    }

    public static boolean addOrder(Order order) throws Exception {
        String json = mapper.writeValueAsString(order);
        ClientResponse response = client
            .resource("http://localhost:8080")
            .path("orders")
            .entity(json)
            .post(ClientResponse.class);

        if (response.getStatus()>=400) { // error
            System.err.println("Cannot add order, service returned status " + response.getStatus());
            return false;
        }
        else {
            return true;
        }
    }
}
