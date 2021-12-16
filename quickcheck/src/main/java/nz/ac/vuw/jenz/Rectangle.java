package nz.ac.vuw.jenz;

/**
 * Sample domain class consisting of two int coordinates.
 * @author jens dietrich
 */
public class Rectangle {

    private int length = 1;
    private int height = 1;

    public Rectangle(int length, int y) {
        this.length = length;
        this.height = y;
    }

    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
    }

    public int size () {
        return length * height;
    }

    @Override
    public String toString() {
        return "Rectangle{" + "length=" + length + ", height=" + height + '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return height == rectangle.length;
    }

    @Override
    public int hashCode() {
        int result = length;
        result = 31 * result + height;
        return result;
    }
}
