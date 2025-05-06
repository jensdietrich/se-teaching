package nz.ac.vuw.jenz.jpa;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    @BeforeEach
    public void setup() throws Exception {
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
}
