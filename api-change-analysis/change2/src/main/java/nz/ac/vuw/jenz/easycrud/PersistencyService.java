package nz.ac.vuw.jenz.easycrud;

import java.util.Set;

/**
 * A simple CRUD service.
 * @author jens dietrich
 */
public abstract class PersistencyService {
    public static final String VERSION = "1.0.0";  // to show inlining

    // ALL IMPLEMENTATIONS ARE DUMMIES ONLY THE API MATTERS !

    public abstract Object create(String... data);

    public abstract boolean delete(Object obj);

    public abstract boolean update(Object obj);

    public abstract  boolean read(String query);

    public abstract  Set<Object> readAll();

}
