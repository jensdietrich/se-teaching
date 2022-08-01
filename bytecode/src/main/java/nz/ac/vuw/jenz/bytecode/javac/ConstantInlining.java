package nz.ac.vuw.jenz.bytecode.javac;

public class ConstantInlining {
    public static final int MAGIC = 42;
    public int getMagic() {
        return 42;
    }
}
