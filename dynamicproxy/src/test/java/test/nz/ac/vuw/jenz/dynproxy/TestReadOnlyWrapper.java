package test.nz.ac.vuw.jenz.dynproxy;

import nz.ac.vuw.jenz.dynproxy.ReadOnlyWrapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collection;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Some tests to illustrate the functionality of the ReadOnlyWrapper.
 * @author jens dietrich
 */
public class TestReadOnlyWrapper {

    private Collection<String> collection = null;
    private Collection<String> readOnlyView = null;

    @BeforeEach
    public void setup() {
        collection = new ArrayList<String>();
        collection.add("one");
        readOnlyView = ReadOnlyWrapper.unmodifiableCollection(collection);
    }
    @AfterEach
    public void tearDown() {
        collection = new ArrayList<String>();
    }

    @Test
    public void testReadMethod() {
        assertEquals(1,readOnlyView.size());
    }

    @Test
    public void testWriteMethod() {
        assertThrows(UnsupportedOperationException.class, () -> readOnlyView.add("foo"));
    }
}