package nz.ac.vuw.jenz.antr.util;

import com.google.common.base.Preconditions;

import java.io.File;

/**
 * Utility that tries to run dot to convert a dot file into a svg -- the orginal files names path is used, just the extension is replaced.
 * @author jens dietrich
 */
public class Dot2Svg {

    public static void convert (File dot) {
        // dot -Tsvg ast.dot -o ast.svg
        try {
            Preconditions.checkArgument(dot.exists());
            Preconditions.checkArgument(dot.getName().endsWith(".dot"));
            File png = new File(dot.getAbsolutePath().replace(".dot", ".svg"));
            String[] c = {"dot", "-Tsvg", dot.getAbsolutePath(), "-o", png.getAbsolutePath()};
            Process p = Runtime.getRuntime().exec(c);
            int err = p.waitFor();
            if (err!=0) {
                System.out.println("automated conversion of generated dot to svg failed, check whether dot is installed");
            }
            else {
                System.out.println("dot converted to svg: " + png.getAbsolutePath());
            }
        }
        catch (Exception x) {
            System.out.println("automated conversion of generated dot to svg failed, check whether dot is installed");
            x.printStackTrace();
        }
    }
}
