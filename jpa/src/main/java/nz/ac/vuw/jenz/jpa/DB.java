package nz.ac.vuw.jenz.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.function.Consumer;

public class DB {

    private static EntityManagerFactory EntityManagerFactory = Persistence.createEntityManagerFactory("nz.ac.vuw.jenz.jpa");

    static void inTransaction(Consumer<EntityManager> work) {
        EntityManager entityManager = EntityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            work.accept(entityManager);
            transaction.commit();
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
