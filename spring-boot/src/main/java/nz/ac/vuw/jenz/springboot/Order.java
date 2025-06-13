package nz.ac.vuw.jenz.springboot;

/**
 * A simple domain class.
 * @author jens dietrich
 */
public record Order (int id, OrderStatus status, String itemName, double unitPrice, int quantity) {}
