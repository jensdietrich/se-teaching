package nz.ac.vuw.jenz.jpa;

import org.hibernate.proxy.HibernateProxy;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;
import jakarta.persistence.EntityExistsException;
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
        db.deleteAllEvents();
    }

    @Nested
    class Basic {
        @Test
        public void testCreateOneFetchOne() throws Exception {
            Event event = new Event("my first event");
            db.insertEvent(event);
            Event readEvent = db.fetchEventById(event.getId());
            assertNotNull(readEvent);
            assertEquals(event.getId(), readEvent.getId());
            assertEquals(event.getTitle(), readEvent.getTitle());
        }

        @Test
        public void testCreateManyFetchOneByContent() throws Exception {
            List<Event> events = List.of(
                new Event("my first event"),
                new Event("my second event")
            );
            db.insertEvents(events);

            List<Event> allEvents = db.fetchAllEvents();
            System.out.println(allEvents);

            List<Event> readEvents = db.fetchEventsWithTitleMatching("first");
            assertNotNull(readEvents);
            assertEquals(1, readEvents.size());
            Event readEvent = readEvents.get(0);
            assertEquals(events.get(0).getId(), readEvent.getId());
            assertEquals(events.get(0).getTitle(), readEvent.getTitle());
        }


        @Test
        public void testCreateSomeThenDeleteOne() throws Exception {
            Event event1 = new Event("my first event");
            Event event2 = new Event("my second event");
            db.insertEvents(event1, event2);

            Event readEvent = db.fetchEventById(event1.getId());
            db.deleteEvent(readEvent);
            List<Event> events = db.fetchAllEvents();
            assertEquals(1, events.size());

            // check that survivor is not the event that has been deleted
            assertEquals(event2.getId(), events.get(0).getId());
            assertEquals(event2.getTitle(), events.get(0).getTitle());
        }
    }

    @Nested
    class Advanced {

        // use this indirection to avoid compiler refusing to compile unreachable code
        private void blowUp() {
            throw new RuntimeException();
        }
        @Test
        public void testRollback() throws Exception {

            Event event1 = new Event("my first event");
            Event event2 = new Event("my second event");
            try {
                db.inTransaction(persistenceManager -> {
                    persistenceManager.persist(event1);
                    blowUp(); // inject fault !
                    persistenceManager.persist(event2);
                    return true;
                });
            } catch (Exception e) {}

            // check whether event1 is still in the database
            // it should have been removed during transaction rollback
            List<Event> allEvents = db.fetchAllEvents();
            assertEquals(0, allEvents.size());

        }

        @Test
        public void testPrimaryKeyConstraint() {
            Event event1 = new Event("my first event");
            db.insertEvent(event1);

            // note that the id is only created by the ORM after an insert !
            // manipulate event2 by overriding the id
            Event event2 = new Event("my second event");
            long id = event1.getId();
            event2.setId(id);

            // now the ORM enforces a primary key constraint -- only one *persistent* object with this id can exist
            assertThrows(EntityExistsException.class, () -> db.insertEvent(event2));

        }


        @Test
        public void testLazyLoading() throws Exception {
            Event event = new Event("my first event");
            db.insertEvent(event);
            Event readEvent = db.fetchEventByIdLazy(event.getId());
            assertNotNull(readEvent);

            // now field are not initialised, they will only be read from db if getters are invoked
            // the event is not a "real event" but a proxy
            assertTrue(readEvent instanceof HibernateProxy);

            // since lazy field resolution requires a database connection, those operations have to be run in a transaction context
            // in the persistency settings, we set this up such that no transactions are required for lazy loading
            // see hibernate.enable_lazy_load_no_trans in src/main/resources/META-INF/persistence.xml
            assertEquals("my first event", readEvent.getTitle());
        }

        @Test
        public void testObjectIdentity() {
            Event event = new Event("my first event");
            db.insertEvent(event);

            // this uses different object managers - so unless caching is setup, the objects returned are different
            // see also https://en.wikibooks.org/wiki/Java_Persistence/Caching#Example_JPA_2.0_Cacheable_annotation
            Event readEvent1 = db.fetchEventById(event.getId());
            Event readEvent2 = db.fetchEventById(event.getId());

            assertTrue(DB.EntityManagerFactory.getCache().contains(Event.class,readEvent1.getId()));
            assertTrue(DB.EntityManagerFactory.getCache().contains(Event.class,readEvent2.getId()));

            assertTrue(db.EntityManagerFactory.getCache().contains(Event.class, readEvent1.getId()));

            assertEquals(readEvent1,readEvent2);
            assertTrue(readEvent1==readEvent2);
        }
    }
}
