package nz.ac.vuw.jenz.jpa;


public class DeleteAllEvents {


    public static void main(String[] args)  {

        Event event1 = new Event("Our very first event!");
        Event event2 = new Event("another event !");

        System.out.println("fetching all events from DB");
        DB.inTransaction(persistenceManager -> {
            Event event = persistenceManager.find(Event.class,1);
            persistenceManager.remove(event);
        });
    }
}