package nz.ac.vuw.jenz.bytecode;

public class Loop {
    public int foo(int s) {
        for (int i=0;i<5;i++) s=s+1;
        return s;
    }
}
