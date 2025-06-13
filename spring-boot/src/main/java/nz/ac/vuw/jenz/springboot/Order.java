package nz.ac.vuw.jenz.springboot;

/**
 * A simple domain class.
 * @author jens dietrich
 */
public record Order (int id, OrderStatus status, String itemName, double unitPrice, int quantity, String customerId) {
    // not that this is a record type, so equals + hashcode are inferred -- they take all fields into account
}
