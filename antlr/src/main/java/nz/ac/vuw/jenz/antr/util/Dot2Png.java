package nz.ac.vuw.jenz.antr.util;

import com.google.common.base.Preconditions;

import java.io.File;

/**
 * Utility that tries to run dot to convert a dot file into a png -- the orginal files names path is used, just the extension is replaced.
 * @author jens dietrich
 */
public class Dot2Png {

    public static void convert (File dot) {
        // dot -Tpng:cairo foo.dot -o foo.png
        try {
            Preconditions.checkArgument(dot.exists());
            Preconditions.checkArgument(dot.getName().endsWith(".dot"));
            File png = new File(dot.getAbsolutePath().replace(".dot", ".png"));
            String[] c = {"dot", "-Tpng:cairo", dot.getAbsolutePath(), "-o", png.getAbsolutePath()};
            Process p = Runtime.getRuntime().exec(c);
            int err = p.waitFor();
            if (err!=0) {
                System.out.println("automated conversion of generated dot to png failed, check whether dot is installed");
            }
            else {
                System.out.println("dot converted to png: " + png.getAbsolutePath());
            }
        }
        catch (Exception x) {
            System.out.println("automated conversion of generated dot to png failed, check whether dot is installed");
            x.printStackTrace();
        }
    }
}
