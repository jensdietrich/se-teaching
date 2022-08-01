package nz.ac.vuw.jenz.bytecode.javac;

import java.lang.reflect.Field;

public class PrintFieldsFoundInOuter1Inner {
    public static void main(String[] args) {
        for (Field field:Outer2.Inner.class.getDeclaredFields()) {
            System.out.print(field.getName());
            System.out.print(" -- synthetic: ");
            System.out.println(field.isSynthetic());
        }
    }
}
