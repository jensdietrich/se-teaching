package nz.ac.vuw.jenz.rest.server;

import io.javalin.Context;
import io.javalin.apibuilder.CrudHandler;
import nz.ac.vuw.jenz.rest.Order;
import org.jetbrains.annotations.NotNull;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import static javax.servlet.http.HttpServletResponse.*; // for status codes

/**
 * Simple in-memory data store illustrating how to connect rest endpoints with a database.
 * Note that the conversion from / to JSON is done by Jackson and is based on the bean
 * conventions used in the Order class (public constructor without parameters, getters and setters for fields).
 * @author jens dietrich
 */
public class OrderDB implements CrudHandler {

    public static Map<String, Order> ORDERS = new ConcurrentHashMap<>();

    static {
        Order order1 = new Order();
        order1.setId("42");
        order1.setProduct("bread");
        order1.setAmount(3);
        order1.setPrice(9.20);
        ORDERS.put(order1.getId(),order1);

        Order order2 = new Order();
        order2.setId("43");
        order2.setProduct("milk");
        order2.setAmount(4);
        order2.setPrice(11.20);
        ORDERS.put(order2.getId(),order2);
    }


    @Override
    public void create(@NotNull Context context) {
        Order order = context.bodyAsClass(Order.class);
        Order existingOrder = ORDERS.get(order.getId());
        if (existingOrder==null) {
            ORDERS.put(order.getId(), order);
            context.status(SC_CREATED);
        }
        else {
            context.status(SC_BAD_REQUEST);
        }
    }

    @Override
    public void delete(@NotNull Context context, @NotNull String resourceId) {
        Order order = ORDERS.get(resourceId);
        if (order==null) {
            context.status(SC_NOT_FOUND);
        }
        else {
            ORDERS.remove(order.getId());
            context.status(SC_OK);
        }
    }

    @Override
    public void getAll(@NotNull Context context) {
        Collection<Order> orders = ORDERS.values();
        context.json(orders);
    }

    @Override
    public void getOne(@NotNull Context context, @NotNull String resourceId) {

        Order order = ORDERS.get(resourceId);
        if (order==null) {
            context.status(SC_NOT_FOUND);
        }
        else {
            context.json(order);
            context.status(SC_OK);
        }
    }

    @Override
    public void update(@NotNull Context context, @NotNull String resourceId) {
        Order existingOrder = ORDERS.get(resourceId);
        if (existingOrder==null) {
            context.status(SC_NOT_FOUND);
        }
        else {
            Order newOrder = context.bodyAsClass(Order.class);
            ORDERS.put(newOrder.getId(),newOrder);
            context.status(SC_OK);
        }
    }
}
