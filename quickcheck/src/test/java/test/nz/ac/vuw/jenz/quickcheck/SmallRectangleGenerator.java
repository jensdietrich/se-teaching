package test.nz.ac.vuw.jenz.quickcheck;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;
import nz.ac.vuw.jenz.Rectangle;

public class SmallRectangleGenerator extends Generator<Rectangle> {

    public SmallRectangleGenerator() {
        super(Rectangle.class);
    }

    public Rectangle generate(SourceOfRandomness sourceOfRandomness, GenerationStatus generationStatus) {
        int x = sourceOfRandomness.nextInt(1,1000);
        int y = sourceOfRandomness.nextInt(1,1000);
        return new Rectangle(x,y);
    }
}
