package pck;

import java.security.MessageDigest;
import java.util.Base64;

public class Foo {

    // hash calculation based on md5
    public static String cryptHash(String str) throws Exception {
        return Base64.getEncoder().encodeToString(MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8")));
    }
}
