package nz.ac.vuw.jenz.asm.cfg.testdata;

public class Foo2 {
    String foo(int i,boolean b) {
        String s = "val";
        if (i>0) {
            if (b) {
                s = s+"1";
            }
            else {
                s = s+"2";
            }
        }
        else {
            if (b) {
                s = s + "3";
            }
            else {
                s = s + "4";
            }
        }
        return s;
    }
}
