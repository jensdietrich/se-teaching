package nz.ac.vuw.jenz.easycrud;

import java.io.IOException;
import java.util.Collection;

/**
 * A simple service implementation. All dummies, only APIs matter here.
 * @author jens dietrich
 */
public class JSONPersistencyService extends PersistencyService {
    @Override
    public Object create(String... data) {
        return null;
    }

    @Override
    public boolean delete(Object obj) {
        return false;
    }

    @Override
    public boolean update(Object obj) {
        return false;
    }

    @Override
    public boolean read(String query) {
        return false;
    }

    @Override
    public Collection<Object> readAll() throws IOException {
        return null;
    }

}
