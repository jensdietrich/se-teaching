package nz.ac.vuw.jenz.webkid;

import java.util.Objects;

/**
 * Domain object -- just a dummy.
 * @author jens dietrich
 */
public class Client {
    private String id = null;
    private String firstName = null;
    private String name = null;

    public Client(String id, String firstName, String name) {
        this.id = id;
        this.firstName = firstName;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) && Objects.equals(firstName, client.firstName) && Objects.equals(name, client.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, name);
    }
}
