package nz.ac.vuw.jenz.rest.client;

import com.sun.jersey.api.client.ClientResponse;
import static nz.ac.vuw.jenz.rest.client.Helpers.client;

/**
 * Client example deleting one order.
 * Note: server (nz.ac.vuw.jenz.rest.server.OrderManagement) must be started. Restart server to reset database.
 * @author jens dietrich
 */
public class DeleteOrder {

    public static void main(String[] args) {
        try {
            System.out.println("Deleting order via service (note that such an order with this id must exist)");
            if (deleteOrder("44")) {
                System.out.println("Order succesfully deleted !");
            }
        }
        catch (Exception x) {
            System.err.println("check that server is running !");
            x.printStackTrace();
        }
    }

    public static boolean deleteOrder(String id) throws Exception {
        ClientResponse response = client
            .resource("http://localhost:8080")
            .path("orders")
            .path(id)
            .delete(ClientResponse.class);

        if (response.getStatus()>=400) { // error
            System.err.println("Cannot remove order, service returned status " + response.getStatus());
            return false;
        }
        else {
            return true;
        }
    }
}
