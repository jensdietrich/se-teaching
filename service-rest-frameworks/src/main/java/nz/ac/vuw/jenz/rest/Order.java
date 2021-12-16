package nz.ac.vuw.jenz.rest;

/**
 * POJO representing an order. Jackson data binding for JSON depends on bean properties:
 * public constructor without parameters, getters and setters for fields.
 * @author jens dietrich
 */
public class Order {
    private String id = "";
    private String product = "";
    private int amount = 0;
    private double price = 0;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (amount != order.amount) return false;
        if (Double.compare(order.price, price) != 0) return false;
        if (id != null ? !id.equals(order.id) : order.id != null) return false;
        return product != null ? product.equals(order.product) : order.product == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (product != null ? product.hashCode() : 0);
        result = 31 * result + amount;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
            "id='" + id + '\'' +
            ", product='" + product + '\'' +
            ", amount=" + amount +
            ", price=" + price +
            '}';
    }
}
