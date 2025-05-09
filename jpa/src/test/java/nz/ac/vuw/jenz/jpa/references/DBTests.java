package nz.ac.vuw.jenz.jpa.references;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the flat domain model containing only the class Enity.
 * @author jens dietrich
 */
public class DBTests {

    private static DB db = new DB();

    @BeforeAll
    public static void resetDB() throws IOException {
        DB.cullDB();
    }

    // this fixture is not ideal as it requires a transaction as well
    @BeforeEach
    public void setup()  {
        db.deleteAllPersons();
        db.deleteAllAddresses();
    }


    @Test
    public void testCreateOneFetchOneWithoutReference() throws Exception {
        Person person = new Person("John","Smith");
        db.insertPerson(person);
        Person readPerson = db.fetchPersonById(person.getId());
        assertNotNull(readPerson);
        assertEquals(person.getId(), readPerson.getId());
        assertEquals(person.getFirstName(), readPerson.getFirstName());
        assertEquals(person.getLastName(), readPerson.getLastName());
    }

    @Test
    public void testCreateOneFetchOneWithReference1()  {
        Person person = new Person("John","Smith");
        // the referenced object is also new
        Address address = new Address("42 Douglas Adams Rd","New New York",4242);
        person.setAddress(address);
        db.insertPerson(person);

        long addressId = address.getId();

        Person readPerson = db.fetchPersonById(person.getId());
        assertNotNull(readPerson);
        assertEquals(person.getId(), readPerson.getId());
        assertEquals(person.getFirstName(), readPerson.getFirstName());
        assertEquals(person.getLastName(), readPerson.getLastName());
        assertEquals(address.getId(), readPerson.getAddress().getId());
        assertEquals(address.getStreet(), readPerson.getAddress().getStreet());
        assertEquals(address.getCity(), readPerson.getAddress().getCity());
        assertEquals(address.getPostcode(), readPerson.getAddress().getPostcode());

        // confirm that the Address is actually persistent now as we did not explicitly insert this
        // it was inserted through a cascading insert (see annotation in Person)
        // this is different from the insert
        Address readAddress = db.fetchAddressById(addressId);
        assertNotNull(readAddress);

    }

    @Test
    public void testCreateOneFetchOneWithReference2() throws Exception {
        Person person1 = new Person("John","Smith");
        Person person2 = new Person("Jane","Miller");

        // the referenced object is saved before saving the person
        // this is the address of a second person already being saved
        Address address = new Address("42 Douglas Adams Rd","New New York",4242);
        person1.setAddress(address);
        db.insertPerson(person1);

        Person readPerson1 = db.fetchPersonById(person1.getId());
        assertEquals(person1,readPerson1);

        person2.setAddress(address);
        db.insertPerson(person2);

        Person readPerson2 = db.fetchPersonById(person1.getId());
        assertNotNull(readPerson1);
        assertNotNull(readPerson2);

        Address readAddress1 = readPerson1.getAddress();
        Address readAddress2 = readPerson2.getAddress();

        assertEquals(readAddress1,readAddress2);
        // but they might not be the same object !

    }


    @Test
    public void testCreateOneDeleteOneWithReference() throws Exception {
        Person person = new Person("John","Smith");
        // the referenced object is also new
        Address address = new Address("42 Douglas Adams Rd","New New York",4242);
        person.setAddress(address);
        db.insertPerson(person);

        long personId = person.getId();
        long addressId = address.getId();

        // now delete
        db.deletePerson(person.getId());

        // the person should be gone
        Person readPerson = db.fetchPersonById(personId);
        assertNull(readPerson);

        // the address is not gone as this is not a cascading delete
        // the address might be referenced by other Person instances
        // this is different from the insert
        Address readAddress = db.fetchAddressById(addressId);
        assertNull(readAddress);

    }


}
