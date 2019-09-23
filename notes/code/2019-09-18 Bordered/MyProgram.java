// MyProgram.java

import java.util.*;

public class MyProgram {

    public static double totalArea( ArrayList<Bordered> borderedList ) {

        double area = 0;

        for ( Bordered b : borderedList ) {
            area += b.area();
        }
        return area;
    }

    public static void main( String argc[] ) {

        ArrayList<Bordered> borderedList = new ArrayList<>();
        
        borderedList.add( new Square( 3 ) );
        borderedList.add( new Circle( 2 ) );

        System.out.println( totalArea( borderedList ) );
    }
}
