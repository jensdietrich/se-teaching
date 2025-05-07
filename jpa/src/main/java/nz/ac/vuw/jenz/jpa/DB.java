package nz.ac.vuw.jenz.jpa;

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

    private static final String PERSISTENCE_UNIT_NAME = "nz.ac.vuw.jenz.jpa";
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

    // fetch all events from the database
    public List<Event> fetchAllEvents() {
        System.out.println("fetching all events from DB");
        return inTransaction(
            persistenceManager -> persistenceManager.createQuery("from Event", Event.class).getResultList()
        );
    }

    // fetch events from the DB with a certain string in the tile
    public List<Event> fetchEventsWithTitleMatching(String txt) {
        System.out.println("fetching event with \"" + txt + "\" in the title from DB");
        return inTransaction(
            persistenceManager -> persistenceManager.createQuery("from Event where title like \'%" + txt +"%\'", Event.class).getResultList()
        );
    }

    // fetch one event from the database by id
    public Event fetchEventById(long id) {
        System.out.println("fetching event with id=" + id + " from DB");
        return inTransaction(
            persistenceManager -> {
                Event event = persistenceManager.find(Event.class,id);
                assert persistenceManager.contains(event);
                return event;
            }
        );
    }

    // insert one event into the database
    public boolean insertEvent(Event event) {
        System.out.println("inserting event " + event);
        return inTransaction(persistenceManager -> {
            persistenceManager.persist(event);
            return true;
        });
    }

    // insert events into the database
    // all will be inserted in a single transaction
    public boolean insertEvents(List<Event> events) {
        System.out.println("inserting events " + events.stream().map(e -> e.toString()).collect(Collectors.joining(" , ")));
        return inTransaction(persistenceManager -> {
            for (Event event: events) {
                persistenceManager.persist(event);
            }
            return true;
        });
    }

    public boolean insertEvents(Event... events) {
        List eventList = List.of(events);
        return insertEvents(eventList);
    }

    // delete one event from the database
    public boolean deleteEvent(Event event) {
        System.out.println("deleting event " + event);
        return inTransaction(persistenceManager -> {
            Event event2 = persistenceManager.find(Event.class,event.getId());
            if (event2==null) {
                System.out.println("event not in DB: " + event);
                return false;
            }
            else {
                persistenceManager.remove(event2);
                return true;
            }
        });
    }

    // delete one event from the database by if
    public boolean deleteEvent(long id) {
        System.out.println("deleting event " + id);
        return inTransaction(persistenceManager -> {
            Event event = persistenceManager.find(Event.class,id);
            if (event==null) {
                System.out.println("event not in DB: " + event);
                return false;
            }
            else {
                persistenceManager.remove(event);
                return true;
            }
        });
    }

    // delete one event from the database by id
    public boolean updateEventById(long id) {
        System.out.println("updating events with id=\" + id + \" from DB");
        Event event = fetchEventById(id);
        return inTransaction(persistenceManager -> {
            persistenceManager.remove(event);
            return true;
        });
    }

    // delete all events from the database
    public boolean deleteAllEvents() {
        System.out.println("deleting all events from DB");

        return inTransaction(persistenceManager -> {
            List<Event> events = persistenceManager.createQuery("from Event", Event.class).getResultList();
            for (Event event : events) {
                persistenceManager.remove(event);
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
