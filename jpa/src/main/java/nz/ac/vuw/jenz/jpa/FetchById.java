package nz.ac.vuw.jenz.jpa;

public class FetchById {

    public static void main(String[] args) throws ClassNotFoundException {

        System.out.println("fetching event with id=1 from DB");
        DB.inTransaction(persistenceManager -> {
            Event event = persistenceManager.find(Event.class,1);
            System.out.println(event);
        });
    }
}