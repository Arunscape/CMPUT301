// MyProgram.java

import java.util.*;

public class MyProgram {

    public static double totalArea( ArrayList<Shape> shapeList ) {

        double area = 0;

        for ( Shape s : shapeList ) {
            area += s.area();
        }
        return area;
    }

    public static void main( String argc[] ) {

        ArrayList<Shape> shapeList = new ArrayList<>();
        
        shapeList.add( new Square( 3 ) );
        shapeList.add( new Circle( 2 ) );

        System.out.println( totalArea( shapeList ) );
    }
}
