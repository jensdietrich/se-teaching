package nz.ac.vuw.jenz.rest.server;

import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.crud;

/**
 * Javalin app: expose the service,
 * @author jens dietrich
 */
public class OrderManagement {

    public static void main(String[] args) {
        final Javalin app = Javalin.create().start(8080);
        app.routes(() -> {
            crud("orders/:order-id", new OrderDB());
        });
    }
}
