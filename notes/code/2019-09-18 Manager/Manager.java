// Manager.java

import java.util.*;

public class Manager extends Employee {
    private ArrayList<Employee> employees;

    public Manager( String name ) {
        super( name );
        employees = new ArrayList<Employee>();
    }
}
