// Company.java

import java.util.*;

public class Company {
    private ArrayList<Car> cars;
    private ArrayList<Renter> renters;
    private ArrayList<Rental> rentals;

    public Company() {
        cars = new ArrayList<Car>();
        renters = new ArrayList<Renter>();
        rentals = new ArrayList<Rental>();
    }

    public void add( Car aCar ) {

        if (! cars.contains( aCar )) {
            cars.add( aCar );
        }
    }

    public void add( Renter aRenter ) {

        if (! renters.contains( aRenter )){
            renters.add( aRenter );
        }
    }

    public void add( Rental aRental ) {

        if (! rentals.contains( aRental )) {
            rentals.add( aRental );
        }
    }

}
