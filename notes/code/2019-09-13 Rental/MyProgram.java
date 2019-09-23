// MyProgram.java

import java.util.*;

public class MyProgram {

    public static void main( String args[] ) {

        Company company = new Company();

        Car car1 = new Car();
        Renter renter1 = new Renter();
        Rental rental1 = new Rental( car1, renter1 );        

        company.add( car1 );
        company.add( renter1 );
        company.add( rental1 );

        Car car2 = new Car();
        Rental rental2 = new Rental( car2, renter1 );

        company.add( car2 );
        company.add( rental2 );
    }
}
