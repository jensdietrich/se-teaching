package nz.ac.vuw.jenz.jpa.references;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * DB-related functionality.
 * @author jens dietrich
 */
public class DB {

    private static final String PERSISTENCE_UNIT_NAME = "nz.ac.vuw.jenz.jpa2";
    private static EntityManagerFactory EntityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    // uncommon operation to reset ORM, used in text fixtures
    void reset() throws IOException {
        DB.cullDB();
    }

    private static final Path[] DB_FILES = new Path[]{
        // System.getProperty("user.home") is ~ in unix path names !
        Path.of(System.getProperty("user.home")).resolve(".mydb.mv.db"),
        Path.of(System.getProperty("user.home")).resolve(".mydb.trace.db")
    };

    // cull the db file(s) -- hard reset , can be used in text fixtures
    // the DB location is defined in the jdbc url defined in src/main/resources/META-INF/persistence.xml
    static void cullDB () throws IOException {
        for (Path file: DB_FILES) {
            if (Files.deleteIfExists(file)) {
                System.out.println("Culled DB file " + file);
            }
            else {
                System.out.println("Culling DB file " + file + " -- nothing todo, DB does not exist");
            }
        }
    }

    // fetch all persons from the database
    public List<Person> fetchAllPersons() {
        System.out.println("fetching all persons from DB");
        return inTransaction(
            persistenceManager -> persistenceManager.createQuery("from Person", Person.class).getResultList()
        );
    }

    // fetch persons from the DB with a certain string in the last name
    public List<Person> fetchPersonsWithNameMatching(String txt) {
        System.out.println("fetching person with \"" + txt + "\" in the title from DB");
        return inTransaction(
            persistenceManager -> persistenceManager.createQuery("from Person where lastName like \'%" + txt +"%\'", Person.class).getResultList()
        );
    }

    // fetch one person from the database by id
    public Address fetchAddressById(long id) {
        System.out.println("fetching address with id=" + id + " from DB");
        return inTransaction(
            persistenceManager -> persistenceManager.find(Address.class,id)
        );
    }

    // fetch one person from the database by id
    public Person fetchPersonById(long id) {
        System.out.println("fetching person with id=" + id + " from DB");
        return inTransaction(
            persistenceManager -> persistenceManager.find(Person.class,id)
        );
    }

    public Person fetchPersonByIdLazy(long id) {
        System.out.println("fetching person with id=" + id + " from DB");
        return inTransaction(
            persistenceManager -> persistenceManager.getReference(Person.class,id)
        );
    }

    // insert one person into the database
    public boolean insertPerson(Person person) {
        System.out.println("inserting person " + person);
        return inTransaction(persistenceManager -> {
            if (person.getAddress()!=null) {
                // merge creates an address instance in this context so that it can be saved
                if (!persistenceManager.contains(person.getAddress()) && person.getAddress().getId()!=null) {
                    Address address = persistenceManager.merge(person.getAddress());
                    person.setAddress(address); // set attached address
                }
            }
            persistenceManager.persist(person);
            return true;
        });
    }

    // insert one person into the database
    public boolean insertAddress(Address address) {
        System.out.println("inserting address " + address);
        return inTransaction(persistenceManager -> {
            persistenceManager.persist(address);
            return true;
        });
    }

    // insert persons into the database
    // all will be inserted in a single transaction
    public boolean insertPersons(List<Person> persons) {
        System.out.println("inserting persons " + persons.stream().map(e -> e.toString()).collect(Collectors.joining(" , ")));
        return inTransaction(persistenceManager -> {
            for (Person person: persons) {
                persistenceManager.persist(person);
            }
            return true;
        });
    }

    public boolean insertPersons(Person... persons) {
        List<Person> personList = List.of(persons);
        return insertPersons(personList);
    }

    // delete one person from the database
    public boolean deletePerson(Person person) {
        System.out.println("deleting person " + person);
        return inTransaction(persistenceManager -> {
            Person person2 = persistenceManager.find(Person.class,person.getId());
            if (person2==null) {
                System.out.println("person not in DB: " + person);
                return false;
            }
            else {
                persistenceManager.remove(person2);
                return true;
            }
        });
    }

    // delete one person from the database by id
    public boolean deletePerson(long id) {
        System.out.println("deleting person " + id);
        return inTransaction(persistenceManager -> {
            Person person = persistenceManager.find(Person.class,id);
            if (person==null) {
                System.out.println("person not in DB: " + person);
                return false;
            }
            else {
                persistenceManager.remove(person);
                return true;
            }
        });
    }

    // delete one person from the database by id
    public boolean updatePersonById(long id) {
        System.out.println("updating person with id=\" + id + \" from DB");
        Person person = fetchPersonById(id);
        return inTransaction(persistenceManager -> {
            persistenceManager.remove(person);
            return true;
        });
    }

    // delete all persons from the database
    public boolean deleteAllPersons() {
        System.out.println("deleting all persons from DB");

        return inTransaction(persistenceManager -> {
            List<Person> persons = persistenceManager.createQuery("from Person", Person.class).getResultList();
            for (Person person : persons) {
                persistenceManager.remove(person);
            }
            //persistenceManager.clear();
            return true;
        });
    }

    // delete all persons from the database
    public boolean deleteAllAddresses() {
        System.out.println("deleting all addresses from DB");

        return inTransaction(persistenceManager -> {
            List<Address> addresses = persistenceManager.createQuery("from Address", Address.class).getResultList();
            for (Address address : addresses) {
                persistenceManager.remove(address);
            }
            //persistenceManager.clear();
            return true;
        });
    }

    <T> T inTransaction(Function<EntityManager,T> work) {
        EntityManager entityManager = EntityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            T result = work.apply(entityManager);
            transaction.commit();
            return result;
        }
        catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
        finally {
            entityManager.close();
        }
    }
}
