package nz.ac.vuw.jenz.jpa2;

import jakarta.persistence.*;
import java.util.Objects;

/**
 * Simple domain class with some ORM annotations.
 * @author jens dietrich
 */
@Entity
@Table(name = "Addresses")
@Cacheable
public class Address {

    @Id @GeneratedValue private Long id; // primary key

    private String street;
    private String city;
    private int postcode;

    public Address() {}

    public Address(String street, String city, int postcode) {
        this.street = street;
        this.city = city;
        this.postcode = postcode;
    }

    public Long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return postcode == address.postcode && Objects.equals(id, address.id) && Objects.equals(street, address.street) && Objects.equals(city, address.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, city, postcode);
    }
}
