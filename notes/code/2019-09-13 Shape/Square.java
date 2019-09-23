// Square.java

public class Square extends Shape {
    private int side;

    public Square() {
        this( 0 );
    }

    public Square( int side ) {
        this.side = side;
    }

    public double area() {
        return (double)side * side;
    }

    public double perimeter() {
        return 4.0d * side;
    }
}
