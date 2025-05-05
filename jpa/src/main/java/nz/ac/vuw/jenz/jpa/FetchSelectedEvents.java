package nz.ac.vuw.jenz.jpa;

import java.util.List;

public class FetchSelectedEvents {

    public static void main(String[] args) throws ClassNotFoundException {

        System.out.println("fetching all events from DB");
        DB.inTransaction(persistenceManager -> {
            List<Event> events = persistenceManager.createQuery("from Event where title like \'%first%\'", Event.class).getResultList();
            events.forEach(e -> System.out.println(e));
        });
    }
}