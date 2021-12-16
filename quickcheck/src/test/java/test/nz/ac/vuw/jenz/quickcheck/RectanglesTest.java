package test.nz.ac.vuw.jenz.quickcheck;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import nz.ac.vuw.jenz.Rectangle;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

@RunWith(JUnitQuickcheck.class)
public class RectanglesTest {

    // test size invariant: science size is defined as the product of length and height,
    // we expect that it can be divided by those
    @Property(trials = 100)
    public void sizeCanBeDividedByLength(@From(LargeRectangleGenerator.class) Rectangle rect) {
        assertTrue(rect.size()%rect.getLength()==0);
    }

    @Property(trials = 100)
    public void sizeCanBeDividedByHeight(@From(LargeRectangleGenerator.class) Rectangle rect) {
        assertTrue(rect.size()%rect.getHeight()==0);
    }


    // test equals hashcode contract
    @Property(trials = 100_000)
    public void equalRectanglesShouldHaveSameHashcode(@From(SmallRectangleGenerator.class) Rectangle rect1, @From(SmallRectangleGenerator.class) Rectangle rect2) {
        assertTrue(!rect1.equals(rect2) || rect1.hashCode()==rect2.hashCode());
    }

}