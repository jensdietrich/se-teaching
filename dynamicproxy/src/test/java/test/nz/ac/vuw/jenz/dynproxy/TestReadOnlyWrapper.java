package test.nz.ac.vuw.jenz.dynproxy;

import nz.ac.vuw.jenz.dynproxy.ReadOnlyWrapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Collection;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Some tests to illustrate the functionality of the ReadOnlyWrapper.
 * @author jens dietrich
 */
public class TestReadOnlyWrapper {

    private Collection<String> collection = null;
    private Collection<String> readOnlyView = null;

    @Before
    public void setup() throws Exception {
        collection = new ArrayList<String>();
        collection.add("one");
        readOnlyView = ReadOnlyWrapper.unmodifiableCollection(collection);
    }
    @After
    public void tearDown() throws Exception {
        collection = new ArrayList<String>();
    }

    @Test
    public void testReadMethod() {
        assertEquals(1,readOnlyView.size());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testWriteMethod() {
        assertTrue(readOnlyView.add("foo"));
    }
}