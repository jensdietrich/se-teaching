package pck;

import java.security.MessageDigest;
import java.util.Base64;

public class Foo {

    // hash calculation based on sha1, also added precondition
    public static String cryptHash(String str) throws Exception {
        // added precondition
        if (str==null) {
            throw new IllegalArgumentException();
        }
        return Base64.getEncoder().encodeToString(MessageDigest.getInstance("SHA1").digest(str.getBytes("UTF-8")));
    }
}
