package nz.ac.vuw.jenz.springboot;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        System.out.println("Initialising / resetting datastore");
        orders = Set.of(
            new Order(1,OrderStatus.COMPLETED,"Nannostomus mortenthaleri",29.99, 5,"paxelrod"),
            new Order(2,OrderStatus.SHIPPED,"Altum Angel Peru",250,8,"hbleher"),
            new Order(3,OrderStatus.PAID,"Heckel Discus Wildly Caught",250,5,"wherzog"),
            new Order(4,OrderStatus.PAID,"JBL Root Tabs",19.99,1,"kkinski"),
            new Order(5,OrderStatus.NEW,"Red Tail Rio Teles Pires Penguin Tetra",15.00,10,"dadams42"),
            new Order(6,OrderStatus.NEW,"Ozelot Sword Plant",24.99,1,"robmusil"),
            new Order(7,OrderStatus.NEW,"Angel Manacapuru F1",150,4,"wherzog")
        );
    }

    public static Order getOrder(int id)  {
        return orders.stream()
            .filter(o -> o.id()==id)
            .findFirst()
            .orElse(null);
    }

    public static List<Order> searchOrderByItemName(String keyword) {
        return orders.stream()
            .filter(o -> o.itemName().toLowerCase().contains(keyword.toLowerCase())) // make case-insensitive !
            .collect(Collectors.toUnmodifiableList()
            );
    }

}
