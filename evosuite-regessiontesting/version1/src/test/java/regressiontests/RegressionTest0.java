package regressiontests;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTest0 {

    public static boolean debug = false;

    public void assertBooleanArrayEquals(boolean[] expectedArray, boolean[] actualArray) {
        if (expectedArray.length != actualArray.length) {
            throw new AssertionError("Array lengths differ: " + expectedArray.length + " != " + actualArray.length);
        }
        for (int i = 0; i < expectedArray.length; i++) {
            if (expectedArray[i] != actualArray[i]) {
                throw new AssertionError("Arrays differ at index " + i + ": " + expectedArray[i] + " != " + actualArray[i]);
            }
        }
    }

    @Test
    public void test01() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test01");
        pck.Foo foo0 = new pck.Foo();
        java.lang.Class<?> wildcardClass1 = foo0.getClass();
        org.junit.Assert.assertNotNull(wildcardClass1);
    }

    @Test
    public void test02() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test02");
        java.lang.String str1 = pck.Foo.cryptHash("hi!");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "r/lxYEdKBW6DjB9yGvAe3w==" + "'", str1, "r/lxYEdKBW6DjB9yGvAe3w==");
    }

    @Test
    public void test03() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test03");
        java.lang.String str1 = pck.Foo.cryptHash("r/lxYEdKBW6DjB9yGvAe3w==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "mzF5ROE4UJccs0E/VUOgww==" + "'", str1, "mzF5ROE4UJccs0E/VUOgww==");
    }

    @Test
    public void test04() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test04");
        java.lang.Object obj0 = new java.lang.Object();
        java.lang.Class<?> wildcardClass1 = obj0.getClass();
        org.junit.Assert.assertNotNull(wildcardClass1);
    }

    @Test
    public void test05() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test05");
        java.lang.String str1 = pck.Foo.cryptHash("");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "1B2M2Y8AsgTpgAmY7PhCfg==" + "'", str1, "1B2M2Y8AsgTpgAmY7PhCfg==");
    }

    @Test
    public void test06() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test06");
        java.lang.String str1 = pck.Foo.cryptHash("1B2M2Y8AsgTpgAmY7PhCfg==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "2iu9I2HkRim3Dk3gBSXrow==" + "'", str1, "2iu9I2HkRim3Dk3gBSXrow==");
    }

    @Test
    public void test07() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test07");
        java.lang.String str1 = pck.Foo.cryptHash("2iu9I2HkRim3Dk3gBSXrow==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "EfAyW18tUFDUZEO4PGLL8w==" + "'", str1, "EfAyW18tUFDUZEO4PGLL8w==");
    }

    @Test
    public void test08() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test08");
        java.lang.String str1 = pck.Foo.cryptHash("EfAyW18tUFDUZEO4PGLL8w==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "LxmRa3JrT5O9ZLYm5gsqtQ==" + "'", str1, "LxmRa3JrT5O9ZLYm5gsqtQ==");
    }

    @Test
    public void test09() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test09");
        java.lang.String str1 = pck.Foo.cryptHash("mzF5ROE4UJccs0E/VUOgww==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "4IT5kZd5EeYPwueuB/O+kw==" + "'", str1, "4IT5kZd5EeYPwueuB/O+kw==");
    }

    @Test
    public void test10() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test10");
        java.lang.String str1 = pck.Foo.cryptHash("LxmRa3JrT5O9ZLYm5gsqtQ==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "d/yH+87XRSIddNvQFa9jRw==" + "'", str1, "d/yH+87XRSIddNvQFa9jRw==");
    }

    @Test
    public void test11() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test11");
        java.lang.String str1 = pck.Foo.cryptHash("d/yH+87XRSIddNvQFa9jRw==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "MXukouLtMcbPbvVL2ZFnYw==" + "'", str1, "MXukouLtMcbPbvVL2ZFnYw==");
    }

    @Test
    public void test12() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test12");
        java.lang.String str1 = pck.Foo.cryptHash("MXukouLtMcbPbvVL2ZFnYw==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "6XxaQ79ikX+jCamwGXKbPw==" + "'", str1, "6XxaQ79ikX+jCamwGXKbPw==");
    }

    @Test
    public void test13() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test13");
        java.lang.String str1 = pck.Foo.cryptHash("4IT5kZd5EeYPwueuB/O+kw==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "Cd8tkkCAN2jehwc9FdUy8Q==" + "'", str1, "Cd8tkkCAN2jehwc9FdUy8Q==");
    }

    @Test
    public void test14() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test14");
        java.lang.String str1 = pck.Foo.cryptHash("Cd8tkkCAN2jehwc9FdUy8Q==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "mCCrr/ZAS66/IT7Rd63aeg==" + "'", str1, "mCCrr/ZAS66/IT7Rd63aeg==");
    }

    @Test
    public void test15() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test15");
        java.lang.String str1 = pck.Foo.cryptHash("6XxaQ79ikX+jCamwGXKbPw==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "5Lid/wBU49o+RBHOztQuBw==" + "'", str1, "5Lid/wBU49o+RBHOztQuBw==");
    }

    @Test
    public void test16() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test16");
        java.lang.String str1 = pck.Foo.cryptHash("mCCrr/ZAS66/IT7Rd63aeg==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "5wnmMLgEDQ9bZiZBbIiDRA==" + "'", str1, "5wnmMLgEDQ9bZiZBbIiDRA==");
    }

    @Test
    public void test17() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test17");
        java.lang.String str1 = pck.Foo.cryptHash("5wnmMLgEDQ9bZiZBbIiDRA==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "stfLxb81FVO09WwLlCOulw==" + "'", str1, "stfLxb81FVO09WwLlCOulw==");
    }

    @Test
    public void test18() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test18");
        java.lang.String str1 = pck.Foo.cryptHash("stfLxb81FVO09WwLlCOulw==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "T0w43K0ajP67vg7sHD4kLw==" + "'", str1, "T0w43K0ajP67vg7sHD4kLw==");
    }

    @Test
    public void test19() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test19");
        java.lang.String str1 = pck.Foo.cryptHash("5Lid/wBU49o+RBHOztQuBw==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "4omJAuHE6eT8e/YhQjQ1wQ==" + "'", str1, "4omJAuHE6eT8e/YhQjQ1wQ==");
    }

    @Test
    public void test20() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test20");
        java.lang.String str1 = pck.Foo.cryptHash("T0w43K0ajP67vg7sHD4kLw==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "QNHzGfGt4AKhOFo4cFJlxA==" + "'", str1, "QNHzGfGt4AKhOFo4cFJlxA==");
    }

    @Test
    public void test21() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test21");
        java.lang.String str1 = pck.Foo.cryptHash("4omJAuHE6eT8e/YhQjQ1wQ==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "CmyWcQ/3pawKUcc/2v/Q9Q==" + "'", str1, "CmyWcQ/3pawKUcc/2v/Q9Q==");
    }

    @Test
    public void test22() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test22");
        java.lang.String str1 = pck.Foo.cryptHash("CmyWcQ/3pawKUcc/2v/Q9Q==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "7XcDLDj7JCqjQT24OKRfYw==" + "'", str1, "7XcDLDj7JCqjQT24OKRfYw==");
    }

    @Test
    public void test23() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test23");
        java.lang.String str1 = pck.Foo.cryptHash("QNHzGfGt4AKhOFo4cFJlxA==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "IEKVc9WtyMw/o6AEnA7KUA==" + "'", str1, "IEKVc9WtyMw/o6AEnA7KUA==");
    }

    @Test
    public void test24() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test24");
        java.lang.String str1 = pck.Foo.cryptHash("7XcDLDj7JCqjQT24OKRfYw==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "nqh48pwAYIjalF49oSWHvQ==" + "'", str1, "nqh48pwAYIjalF49oSWHvQ==");
    }

    @Test
    public void test25() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test25");
        java.lang.String str1 = pck.Foo.cryptHash("IEKVc9WtyMw/o6AEnA7KUA==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "6O/ubJUBldGDvKXqSxHdfQ==" + "'", str1, "6O/ubJUBldGDvKXqSxHdfQ==");
    }

    @Test
    public void test26() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test26");
        java.lang.String str1 = pck.Foo.cryptHash("nqh48pwAYIjalF49oSWHvQ==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "GMJGxYrTWYvHd0YirXayhQ==" + "'", str1, "GMJGxYrTWYvHd0YirXayhQ==");
    }

    @Test
    public void test27() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test27");
        java.lang.String str1 = pck.Foo.cryptHash("6O/ubJUBldGDvKXqSxHdfQ==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "q1Cizm1f0rIMf1Zae/Iv/A==" + "'", str1, "q1Cizm1f0rIMf1Zae/Iv/A==");
    }

    @Test
    public void test28() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test28");
        java.lang.String str1 = pck.Foo.cryptHash("GMJGxYrTWYvHd0YirXayhQ==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "BGBOg7FYYCPtAeuTjtvG1A==" + "'", str1, "BGBOg7FYYCPtAeuTjtvG1A==");
    }

    @Test
    public void test29() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test29");
        java.lang.String str1 = pck.Foo.cryptHash("q1Cizm1f0rIMf1Zae/Iv/A==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "CIbLBfTFTWOP6re0HYMz7w==" + "'", str1, "CIbLBfTFTWOP6re0HYMz7w==");
    }

    @Test
    public void test30() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test30");
        java.lang.String str1 = pck.Foo.cryptHash("CIbLBfTFTWOP6re0HYMz7w==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "hQoaJHem4uJLxlOzhyPoTA==" + "'", str1, "hQoaJHem4uJLxlOzhyPoTA==");
    }

    @Test
    public void test31() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test31");
        java.lang.String str1 = pck.Foo.cryptHash("BGBOg7FYYCPtAeuTjtvG1A==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "oqf8mQ6Ip95HIpp2+CrZhw==" + "'", str1, "oqf8mQ6Ip95HIpp2+CrZhw==");
    }

    @Test
    public void test32() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test32");
        java.lang.String str1 = pck.Foo.cryptHash("oqf8mQ6Ip95HIpp2+CrZhw==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "3Z5CX4mhyvpe7qNgwLcFXg==" + "'", str1, "3Z5CX4mhyvpe7qNgwLcFXg==");
    }

    @Test
    public void test33() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test33");
        java.lang.String str1 = pck.Foo.cryptHash("hQoaJHem4uJLxlOzhyPoTA==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "IRoCd2bV0vNytlEyFMtIkg==" + "'", str1, "IRoCd2bV0vNytlEyFMtIkg==");
    }

    @Test
    public void test34() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test34");
        java.lang.String str1 = pck.Foo.cryptHash("IRoCd2bV0vNytlEyFMtIkg==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "TkPsCHYiSh6/oa5gXMV/0Q==" + "'", str1, "TkPsCHYiSh6/oa5gXMV/0Q==");
    }

    @Test
    public void test35() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test35");
        java.lang.String str1 = pck.Foo.cryptHash("3Z5CX4mhyvpe7qNgwLcFXg==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "nVRxp6zpYMlyj+PugBPzqQ==" + "'", str1, "nVRxp6zpYMlyj+PugBPzqQ==");
    }

    @Test
    public void test36() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test36");
        java.lang.String str1 = pck.Foo.cryptHash("nVRxp6zpYMlyj+PugBPzqQ==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "q1R3t0o1BrOPqQlaM60sQQ==" + "'", str1, "q1R3t0o1BrOPqQlaM60sQQ==");
    }

    @Test
    public void test37() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test37");
        java.lang.String str1 = pck.Foo.cryptHash("TkPsCHYiSh6/oa5gXMV/0Q==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "60LWTylp9nQMh5tsO0YYeQ==" + "'", str1, "60LWTylp9nQMh5tsO0YYeQ==");
    }

    @Test
    public void test38() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test38");
        java.lang.String str1 = pck.Foo.cryptHash("q1R3t0o1BrOPqQlaM60sQQ==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "yvwMwuCj1rQKGWg5KtoWDg==" + "'", str1, "yvwMwuCj1rQKGWg5KtoWDg==");
    }

    @Test
    public void test39() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test39");
        java.lang.String str1 = pck.Foo.cryptHash("yvwMwuCj1rQKGWg5KtoWDg==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "bDtCgB/CuDuk4gr66CADuQ==" + "'", str1, "bDtCgB/CuDuk4gr66CADuQ==");
    }

    @Test
    public void test40() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test40");
        java.lang.String str1 = pck.Foo.cryptHash("60LWTylp9nQMh5tsO0YYeQ==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "SvopM7viJ69im8UzaJxDBQ==" + "'", str1, "SvopM7viJ69im8UzaJxDBQ==");
    }

    @Test
    public void test41() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test41");
        java.lang.String str1 = pck.Foo.cryptHash("SvopM7viJ69im8UzaJxDBQ==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "Hx2A/xK482XiJkrNGgjZmA==" + "'", str1, "Hx2A/xK482XiJkrNGgjZmA==");
    }

    @Test
    public void test42() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test42");
        java.lang.String str1 = pck.Foo.cryptHash("bDtCgB/CuDuk4gr66CADuQ==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "ISw7uCFF5HhyBtWw60hpwg==" + "'", str1, "ISw7uCFF5HhyBtWw60hpwg==");
    }

    @Test
    public void test43() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test43");
        java.lang.String str1 = pck.Foo.cryptHash("Hx2A/xK482XiJkrNGgjZmA==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "uqfqsBU6VZaxc5VYpEqBfg==" + "'", str1, "uqfqsBU6VZaxc5VYpEqBfg==");
    }

    @Test
    public void test44() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test44");
        java.lang.String str1 = pck.Foo.cryptHash("uqfqsBU6VZaxc5VYpEqBfg==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "6twixzE+9joMEkHmvCT/vg==" + "'", str1, "6twixzE+9joMEkHmvCT/vg==");
    }

    @Test
    public void test45() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test45");
        java.lang.String str1 = pck.Foo.cryptHash("ISw7uCFF5HhyBtWw60hpwg==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "1zlUTNTQQMh6080Y8btmCA==" + "'", str1, "1zlUTNTQQMh6080Y8btmCA==");
    }

    @Test
    public void test46() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test46");
        java.lang.String str1 = pck.Foo.cryptHash("1zlUTNTQQMh6080Y8btmCA==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "H8+G0gRUB4Qwsex8r2ZKBQ==" + "'", str1, "H8+G0gRUB4Qwsex8r2ZKBQ==");
    }

    @Test
    public void test47() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test47");
        java.lang.String str1 = pck.Foo.cryptHash("H8+G0gRUB4Qwsex8r2ZKBQ==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "gHl5sfLZLlej3DY3NZAjSQ==" + "'", str1, "gHl5sfLZLlej3DY3NZAjSQ==");
    }

    @Test
    public void test48() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test48");
        java.lang.String str1 = pck.Foo.cryptHash("gHl5sfLZLlej3DY3NZAjSQ==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "OAJLNmreOzKE+zQ2Pz5s/A==" + "'", str1, "OAJLNmreOzKE+zQ2Pz5s/A==");
    }

    @Test
    public void test49() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test49");
        java.lang.String str1 = pck.Foo.cryptHash("OAJLNmreOzKE+zQ2Pz5s/A==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "wiHa7hhERO0QzfRQquEF3A==" + "'", str1, "wiHa7hhERO0QzfRQquEF3A==");
    }

    @Test
    public void test50() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test50");
        java.lang.String str1 = pck.Foo.cryptHash("wiHa7hhERO0QzfRQquEF3A==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "6+w4DZ616NYSk1h8xpe6HA==" + "'", str1, "6+w4DZ616NYSk1h8xpe6HA==");
    }

    @Test
    public void test51() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test51");
        java.lang.String str1 = pck.Foo.cryptHash("6twixzE+9joMEkHmvCT/vg==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "MqtSHkn72Za51468jzj4rA==" + "'", str1, "MqtSHkn72Za51468jzj4rA==");
    }

    @Test
    public void test52() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test52");
        java.lang.String str1 = pck.Foo.cryptHash("6+w4DZ616NYSk1h8xpe6HA==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "gSZMRtfrCjaDmmBb3JpKVg==" + "'", str1, "gSZMRtfrCjaDmmBb3JpKVg==");
    }

    @Test
    public void test53() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test53");
        java.lang.String str1 = pck.Foo.cryptHash("MqtSHkn72Za51468jzj4rA==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "aS1Pf2BsRX1LRyd206YhIw==" + "'", str1, "aS1Pf2BsRX1LRyd206YhIw==");
    }

    @Test
    public void test54() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test54");
        java.lang.String str1 = pck.Foo.cryptHash("gSZMRtfrCjaDmmBb3JpKVg==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "I+4paYHFXA6WH9ZjyaXHVQ==" + "'", str1, "I+4paYHFXA6WH9ZjyaXHVQ==");
    }

    @Test
    public void test55() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test55");
        java.lang.String str1 = pck.Foo.cryptHash("aS1Pf2BsRX1LRyd206YhIw==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "c3ARXSj4WmZ2XIIuplPnsw==" + "'", str1, "c3ARXSj4WmZ2XIIuplPnsw==");
    }

    @Test
    public void test56() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test56");
        java.lang.String str1 = pck.Foo.cryptHash("c3ARXSj4WmZ2XIIuplPnsw==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "Bbu31hQ4SDztA8hfhafjgw==" + "'", str1, "Bbu31hQ4SDztA8hfhafjgw==");
    }

    @Test
    public void test57() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test57");
        java.lang.String str1 = pck.Foo.cryptHash("Bbu31hQ4SDztA8hfhafjgw==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "61iE6JziiFTqGXr9EjVvkw==" + "'", str1, "61iE6JziiFTqGXr9EjVvkw==");
    }

    @Test
    public void test58() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test58");
        java.lang.String str1 = pck.Foo.cryptHash("61iE6JziiFTqGXr9EjVvkw==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "kdrB4QNdRamIjWODv5Ea3w==" + "'", str1, "kdrB4QNdRamIjWODv5Ea3w==");
    }

    @Test
    public void test59() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test59");
        java.lang.String str1 = pck.Foo.cryptHash("I+4paYHFXA6WH9ZjyaXHVQ==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "i4hr6p/0A1rBN3+zTY0nJA==" + "'", str1, "i4hr6p/0A1rBN3+zTY0nJA==");
    }

    @Test
    public void test60() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test60");
        java.lang.String str1 = pck.Foo.cryptHash("kdrB4QNdRamIjWODv5Ea3w==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "8Ggw39EESE3U+70Roef9tQ==" + "'", str1, "8Ggw39EESE3U+70Roef9tQ==");
    }

    @Test
    public void test61() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test61");
        java.lang.String str1 = pck.Foo.cryptHash("i4hr6p/0A1rBN3+zTY0nJA==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "QSMOWX9FaB9XehzgTs9D8A==" + "'", str1, "QSMOWX9FaB9XehzgTs9D8A==");
    }

    @Test
    public void test62() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test62");
        java.lang.String str1 = pck.Foo.cryptHash("8Ggw39EESE3U+70Roef9tQ==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "lWlS9NgFXn6eV/97QjasHQ==" + "'", str1, "lWlS9NgFXn6eV/97QjasHQ==");
    }

    @Test
    public void test63() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test63");
        java.lang.String str1 = pck.Foo.cryptHash("QSMOWX9FaB9XehzgTs9D8A==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "Uv7kSQVVoaOz3kLSOSNb2g==" + "'", str1, "Uv7kSQVVoaOz3kLSOSNb2g==");
    }

    @Test
    public void test64() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test64");
        java.lang.String str1 = pck.Foo.cryptHash("Uv7kSQVVoaOz3kLSOSNb2g==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "TZ0Tni0UK/Kw0ktOtTnjyw==" + "'", str1, "TZ0Tni0UK/Kw0ktOtTnjyw==");
    }

    @Test
    public void test65() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test65");
        java.lang.String str1 = pck.Foo.cryptHash("lWlS9NgFXn6eV/97QjasHQ==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "zb1W0+PhQgqWpZ/4oOXGew==" + "'", str1, "zb1W0+PhQgqWpZ/4oOXGew==");
    }

    @Test
    public void test66() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test66");
        java.lang.String str1 = pck.Foo.cryptHash("zb1W0+PhQgqWpZ/4oOXGew==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "YvsYui15cgt4znuiIY6Krw==" + "'", str1, "YvsYui15cgt4znuiIY6Krw==");
    }

    @Test
    public void test67() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test67");
        java.lang.String str1 = pck.Foo.cryptHash("YvsYui15cgt4znuiIY6Krw==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "xLzrIlsNLpTt7/8x1Le6sg==" + "'", str1, "xLzrIlsNLpTt7/8x1Le6sg==");
    }

    @Test
    public void test68() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test68");
        java.lang.String str1 = pck.Foo.cryptHash("TZ0Tni0UK/Kw0ktOtTnjyw==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "9COSslpXnh4KldlKqnlWXA==" + "'", str1, "9COSslpXnh4KldlKqnlWXA==");
    }

    @Test
    public void test69() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test69");
        java.lang.String str1 = pck.Foo.cryptHash("xLzrIlsNLpTt7/8x1Le6sg==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "B4OULDzfLmbfWhiHKoq3sQ==" + "'", str1, "B4OULDzfLmbfWhiHKoq3sQ==");
    }

    @Test
    public void test70() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test70");
        java.lang.String str1 = pck.Foo.cryptHash("9COSslpXnh4KldlKqnlWXA==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "rcYRI5UBIdTB8Qpdx9gK/Q==" + "'", str1, "rcYRI5UBIdTB8Qpdx9gK/Q==");
    }

    @Test
    public void test71() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test71");
        java.lang.String str1 = pck.Foo.cryptHash("rcYRI5UBIdTB8Qpdx9gK/Q==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "wcqv2XSbfmaxs5XLZbcbsQ==" + "'", str1, "wcqv2XSbfmaxs5XLZbcbsQ==");
    }

    @Test
    public void test72() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test72");
        java.lang.String str1 = pck.Foo.cryptHash("B4OULDzfLmbfWhiHKoq3sQ==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "BpsHXc978erSoXAgFS27yQ==" + "'", str1, "BpsHXc978erSoXAgFS27yQ==");
    }

    @Test
    public void test73() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test73");
        java.lang.String str1 = pck.Foo.cryptHash("BpsHXc978erSoXAgFS27yQ==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "HEPB0bji8orlHByMF43TBw==" + "'", str1, "HEPB0bji8orlHByMF43TBw==");
    }

    @Test
    public void test74() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test74");
        java.lang.String str1 = pck.Foo.cryptHash("wcqv2XSbfmaxs5XLZbcbsQ==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "yZPqJ+TQeDcyul70xPLQEg==" + "'", str1, "yZPqJ+TQeDcyul70xPLQEg==");
    }

    @Test
    public void test75() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test75");
        java.lang.String str1 = pck.Foo.cryptHash("HEPB0bji8orlHByMF43TBw==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "ZOxhBfdPmjjR6uxkwirlNQ==" + "'", str1, "ZOxhBfdPmjjR6uxkwirlNQ==");
    }

    @Test
    public void test76() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test76");
        java.lang.String str1 = pck.Foo.cryptHash("yZPqJ+TQeDcyul70xPLQEg==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "sKvl2APZQANbwn8ySLsIQQ==" + "'", str1, "sKvl2APZQANbwn8ySLsIQQ==");
    }

    @Test
    public void test77() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test77");
        java.lang.String str1 = pck.Foo.cryptHash("ZOxhBfdPmjjR6uxkwirlNQ==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "4PbWzDWyUmPc1LMZqACi5g==" + "'", str1, "4PbWzDWyUmPc1LMZqACi5g==");
    }

    @Test
    public void test78() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test78");
        java.lang.String str1 = pck.Foo.cryptHash("sKvl2APZQANbwn8ySLsIQQ==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "Yvc3hR45rvhL2TpsJXe7qQ==" + "'", str1, "Yvc3hR45rvhL2TpsJXe7qQ==");
    }

    @Test
    public void test79() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test79");
        java.lang.String str1 = pck.Foo.cryptHash("Yvc3hR45rvhL2TpsJXe7qQ==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "B6EN/ky7WJ/XNA930+NKUQ==" + "'", str1, "B6EN/ky7WJ/XNA930+NKUQ==");
    }

    @Test
    public void test80() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test80");
        java.lang.String str1 = pck.Foo.cryptHash("4PbWzDWyUmPc1LMZqACi5g==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "xu6hpsldZtnhHS8tcUmROA==" + "'", str1, "xu6hpsldZtnhHS8tcUmROA==");
    }

    @Test
    public void test81() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test81");
        java.lang.String str1 = pck.Foo.cryptHash("xu6hpsldZtnhHS8tcUmROA==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "JIN5lcABVbPWx2e9NcBv+A==" + "'", str1, "JIN5lcABVbPWx2e9NcBv+A==");
    }

    @Test
    public void test82() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test82");
        java.lang.String str1 = pck.Foo.cryptHash("B6EN/ky7WJ/XNA930+NKUQ==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "VThlPweNDvDyRoIvVPij6A==" + "'", str1, "VThlPweNDvDyRoIvVPij6A==");
    }

    @Test
    public void test83() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test83");
        java.lang.String str1 = pck.Foo.cryptHash("VThlPweNDvDyRoIvVPij6A==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "amKr9sZbq8d4kwds7/CrXA==" + "'", str1, "amKr9sZbq8d4kwds7/CrXA==");
    }

    @Test
    public void test84() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test84");
        java.lang.String str1 = pck.Foo.cryptHash("amKr9sZbq8d4kwds7/CrXA==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "DZVZewgkqYoLUwJTJYUnpg==" + "'", str1, "DZVZewgkqYoLUwJTJYUnpg==");
    }

    @Test
    public void test85() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test85");
        java.lang.String str1 = pck.Foo.cryptHash("DZVZewgkqYoLUwJTJYUnpg==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "uHF4wQEamJ3j1j9p6Meb0A==" + "'", str1, "uHF4wQEamJ3j1j9p6Meb0A==");
    }

    @Test
    public void test86() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test86");
        java.lang.String str1 = pck.Foo.cryptHash("JIN5lcABVbPWx2e9NcBv+A==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "Fuf8xO/SgSfMQkBJJzMIqg==" + "'", str1, "Fuf8xO/SgSfMQkBJJzMIqg==");
    }

    @Test
    public void test87() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test87");
        java.lang.String str1 = pck.Foo.cryptHash("uHF4wQEamJ3j1j9p6Meb0A==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "HwlI36l3FDyZWSgOAnCfrQ==" + "'", str1, "HwlI36l3FDyZWSgOAnCfrQ==");
    }

    @Test
    public void test88() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test88");
        java.lang.String str1 = pck.Foo.cryptHash("Fuf8xO/SgSfMQkBJJzMIqg==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "vCK926efmx8l346gSVPPQQ==" + "'", str1, "vCK926efmx8l346gSVPPQQ==");
    }

    @Test
    public void test89() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test89");
        java.lang.String str1 = pck.Foo.cryptHash("vCK926efmx8l346gSVPPQQ==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "ERfBp2v5lxtn7k5/v3/V4Q==" + "'", str1, "ERfBp2v5lxtn7k5/v3/V4Q==");
    }

    @Test
    public void test90() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test90");
        java.lang.String str1 = pck.Foo.cryptHash("HwlI36l3FDyZWSgOAnCfrQ==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "jnGsdbmP18Os0Ah2h+28/A==" + "'", str1, "jnGsdbmP18Os0Ah2h+28/A==");
    }

    @Test
    public void test91() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test91");
        java.lang.String str1 = pck.Foo.cryptHash("jnGsdbmP18Os0Ah2h+28/A==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "DgY3YUnr+nXBc8kTI/jUnA==" + "'", str1, "DgY3YUnr+nXBc8kTI/jUnA==");
    }

    @Test
    public void test92() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test92");
        java.lang.String str1 = pck.Foo.cryptHash("DgY3YUnr+nXBc8kTI/jUnA==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "G7GhRMr+oanpxFDHuRLhng==" + "'", str1, "G7GhRMr+oanpxFDHuRLhng==");
    }

    @Test
    public void test93() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test93");
        java.lang.String str1 = pck.Foo.cryptHash("ERfBp2v5lxtn7k5/v3/V4Q==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "QbY6HheImG9WITTiJrX24A==" + "'", str1, "QbY6HheImG9WITTiJrX24A==");
    }

    @Test
    public void test94() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test94");
        java.lang.String str1 = pck.Foo.cryptHash("G7GhRMr+oanpxFDHuRLhng==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "28kdJ2QAKiNLPGSmBGQIzw==" + "'", str1, "28kdJ2QAKiNLPGSmBGQIzw==");
    }

    @Test
    public void test95() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test95");
        java.lang.String str1 = pck.Foo.cryptHash("28kdJ2QAKiNLPGSmBGQIzw==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "hjyW2LH/Yv7Vr7dz0Qjmeg==" + "'", str1, "hjyW2LH/Yv7Vr7dz0Qjmeg==");
    }

    @Test
    public void test96() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test96");
        java.lang.String str1 = pck.Foo.cryptHash("QbY6HheImG9WITTiJrX24A==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "iBa3ncB4Ra8+nlqXYYJSnA==" + "'", str1, "iBa3ncB4Ra8+nlqXYYJSnA==");
    }

    @Test
    public void test97() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test97");
        java.lang.String str1 = pck.Foo.cryptHash("iBa3ncB4Ra8+nlqXYYJSnA==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "BotJzy0VR3XeSl/Tqa97sg==" + "'", str1, "BotJzy0VR3XeSl/Tqa97sg==");
    }

    @Test
    public void test98() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test98");
        java.lang.String str1 = pck.Foo.cryptHash("BotJzy0VR3XeSl/Tqa97sg==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "YYiupqtDsYqAyUEuR2ksTQ==" + "'", str1, "YYiupqtDsYqAyUEuR2ksTQ==");
    }

    @Test
    public void test99() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test99");
        java.lang.String str1 = pck.Foo.cryptHash("YYiupqtDsYqAyUEuR2ksTQ==");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "zVOurrcZlRNgH+Uckc8R/w==" + "'", str1, "zVOurrcZlRNgH+Uckc8R/w==");
    }
}

