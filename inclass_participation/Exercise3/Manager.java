import java.util.ArrayList;
  
 public class Manager extends Employee{
              
          private ArrayList<Employee> employees;
              
          Manager(String name, ArrayList<Employee> employees){
                  super(name);
                  this.employees = employees;
          }
  
  
  }
