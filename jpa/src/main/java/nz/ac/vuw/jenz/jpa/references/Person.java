package nz.ac.vuw.jenz.jpa2;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * Simple domain class with some ORM annotations.
 * @author jens dietrich
 */
@Entity
@Table(name = "Persons")
@Cacheable
public class Person {

    @Id @GeneratedValue private Long id;  // primary key
    private String firstName;
    private String lastName;
    @ManyToOne(cascade = CascadeType.ALL) @JoinColumn(name = "address_id") private Address address; // foreign key annotation

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Person() {}

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(address, person.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, address);
    }
}


