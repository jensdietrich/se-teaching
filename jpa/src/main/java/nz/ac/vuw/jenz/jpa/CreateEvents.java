package nz.ac.vuw.jenz.jpa;

public class CreateEvents {





    public static void main(String[] args) throws ClassNotFoundException {

        Event event1 = new Event("Our very first event!");
        Event event2 = new Event("another event !");

        DB.inTransaction(persistenceManager -> {
            persistenceManager.persist(event1);
            persistenceManager.persist(event2);
        });
    }
}