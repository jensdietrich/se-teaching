package test.nz.ac.vuw.jenz.setcontract;


import org.junit.Assume;
import org.junit.Test;
import java.util.*;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Tests showing the set contract broken in IdentityHashSet.
 * @author jens dietrich
 */
public class TestSetContract {

    private Map<String,String> map = null;

    @Test
    public void testHashSet() {
        map = new HashMap<>();
        String key1 = "go";
        String key2 = new String("go");
        Assume.assumeTrue(key1!=key2);
        map.put(key1,"gehen");
        Assume.assumeTrue(Objects.equals(key1,key2));
        assertTrue(map.containsKey(key2));
    }

    @Test
    public void testIdentityHashSet() {
        map = new IdentityHashMap<>();
        String key1 = "go";
        String key2 = new String("go");
        Assume.assumeTrue(key1!=key2);
        Assume.assumeTrue(Objects.equals(key1,key2));
        map.put(key1,"gehen");
        assertFalse(map.containsKey(key2));
    }

}