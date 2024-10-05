package nz.ac.vuw.jenz.asm.cfg.testdata;

public class Foo1 {

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
            s = s+"3";
        }
        return s;
    }
}
