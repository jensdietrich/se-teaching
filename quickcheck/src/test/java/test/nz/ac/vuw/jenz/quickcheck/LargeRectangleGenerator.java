package test.nz.ac.vuw.jenz.quickcheck;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;
import nz.ac.vuw.jenz.Rectangle;

public class LargeRectangleGenerator extends Generator<Rectangle> {

    public LargeRectangleGenerator() {
        super(Rectangle.class);
    }

    public Rectangle generate(SourceOfRandomness sourceOfRandomness, GenerationStatus generationStatus) {
        int x = sourceOfRandomness.nextInt(1,Integer.MAX_VALUE);
        int y = sourceOfRandomness.nextInt(1,Integer.MAX_VALUE);
        return new Rectangle(x,y);
    }
}
