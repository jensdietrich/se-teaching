package nz.ac.vuw.jenz.asm.staticanalysis4.data;

import java.util.function.Consumer;

public class Foo {

    static Consumer<String> consumer = s -> bar(s);

    static void foo() {
        consumer.accept("bar");
    }

    static void bar(String s) {}
}
