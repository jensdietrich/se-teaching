public class Foo2 {
    int f1 = 42;
    int f2 = 0;
    public int hashCode() {
        return f1 + f2%32;
    }
}