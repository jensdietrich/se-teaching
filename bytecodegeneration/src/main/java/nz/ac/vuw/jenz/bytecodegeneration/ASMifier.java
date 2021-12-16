package nz.ac.vuw.jenz.bytecodegeneration;


public class ASMifier {

    public static void main(String[] args) throws Exception {
        org.objectweb.asm.util.ASMifier.main(new String[]{"target/classes/nz/ac/vuw/jenz/bytecodegeneration/Calculator.class"});
    }
}
