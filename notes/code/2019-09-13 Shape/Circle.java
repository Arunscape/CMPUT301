// Circle.java

public class Circle extends Shape {
    private int radius;

    public Circle() {
        this( 0 );
    }

    public Circle( int radius ) {
        this.radius = radius;
    }

    public double area() {
        return Math.PI * radius * radius;
    }

    public double perimeter() {
        return Math.PI * 2 * radius;
    }
}
