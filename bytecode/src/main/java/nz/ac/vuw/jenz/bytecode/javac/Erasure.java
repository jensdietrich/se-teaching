package nz.ac.vuw.jenz.bytecode.javac;

import java.util.List;

public class Erasure {

    public static void look(List<String> list) {
        for (String s:list) {
            System.out.println(s);
        }
    }
}
