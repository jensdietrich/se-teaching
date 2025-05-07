package nz.ac.vuw.jenz.jpa;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        db.insertEvents(event1,event2);

        Event readEvent = db.fetchEventById(event1.getId());
        db.deleteEvent(readEvent);
        List<Event> events = db.fetchAllEvents();
        assertEquals(1,events.size());

        // check that survivor is not the event that has been deleted
        assertEquals(event2.getId(),events.get(0).getId());
        assertEquals(event2.getTitle(),events.get(0).getTitle());
    }


    @Test
    public void testRollback() throws Exception {

        Event event1 = new Event("my first event");
        Event event2 = new Event("my second event");

        try {
            db.inTransaction(persistenceManager -> {
                persistenceManager.persist(event1);

                // inject fault
                if (System.currentTimeMillis() > 0) { // fake condition always true to make next persit op reachable
                    throw new RuntimeException(); // the actual fault
                }
                persistenceManager.persist(event2);
                return true;
            });
        }
        catch (Exception e) {}   // just carry on

        // check whether event1 is still in the database
        // it should have been removed during transaction rollback
        List<Event> allEvents = db.fetchAllEvents();
        assertEquals(0,allEvents.size());

    }

}
