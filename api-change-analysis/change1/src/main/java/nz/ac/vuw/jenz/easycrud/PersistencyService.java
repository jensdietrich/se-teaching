package nz.ac.vuw.jenz.easycrud;

import java.util.List;

/**
 * A simple CRUD like service.
 * @author jens dietrich
 */
public abstract class PersistencyService {
    public static final String VERSION = "1.0.0";  // to show inlining

    // ALL IMPLEMENTATIONS ARE DUMMIES ONLY THE API MATTERS !

    public Object create(String... data) {
        return null;
    }

    public boolean delete(Object obj) {
        return false;
    }

    public boolean update(Object obj) {
        return false;
    }

    public boolean read(String query) {
        return false;
    }

    public List<Object> readAll() {return null;}

}
