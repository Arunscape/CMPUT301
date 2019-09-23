// Shape.java

public abstract class Shape {
    protected Location location;

    public Shape() {
        location = new Location();
    }

    public void setLocation( Location location ) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public abstract double area();
    public abstract double perimeter();
}
