package nz.ac.vuw.jenz.springboot;

import java.util.Set;

/**
 * Simulates a simple database, e.g. this could be backed up by a relational database accessed with JPA / hibernate.
 * @author jens dietrich
 */
public class PetstoreDB {

    private static Set<Order> orders;

    static {
        reset();
    }

    static void reset() {
        orders = Set.of(
            new Order(1,OrderStatus.COMPLETED,"Nannostomus mortenthaleri",29.99, 5),
            new Order(2,OrderStatus.SHIPPED,"Altum Angel Peru",250,8),
            new Order(3,OrderStatus.PAID,"Heckel Discus Wildly Caught",250,5),
            new Order(4,OrderStatus.PAID,"JBL Root Tabs",19.99,1),
            new Order(5,OrderStatus.NEW,"Red Tail Rio Teles Pires Penguin Tetra",15.00,10),
            new Order(6,OrderStatus.NEW,"Ozelot Sword Plant",24.99,1)
        );
    }

    public static Order getOrder(int id) throws OrderNotFoundException {
        return orders.stream()
            .filter(o -> o.id()==id)
            .findFirst()
            .orElseThrow(()->new OrderNotFoundException("No order found with id "+id));
    }

}
