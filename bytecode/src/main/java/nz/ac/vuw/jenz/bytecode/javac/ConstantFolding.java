package nz.ac.vuw.jenz.bytecode.javac;

public class ConstantFolding {
    public static final String MAGIC_STRING = "magic--";
    public static final int MAGIC_INT = -42;
    public String getMagic() {
        return MAGIC_STRING + (-MAGIC_INT);
    }
}
