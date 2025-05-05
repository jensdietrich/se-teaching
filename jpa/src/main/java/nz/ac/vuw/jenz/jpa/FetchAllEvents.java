package nz.ac.vuw.jenz.jpa;

import java.util.List;

public class FetchAllEvents {





    public static void main(String[] args) throws ClassNotFoundException {

        System.out.println("fetching all events from DB");
        DB.inTransaction(persistenceManager -> {
            List<Event> events = persistenceManager.createQuery("from Event", Event.class).getResultList();
            events.forEach(e -> System.out.println(e));
        });
    }
}