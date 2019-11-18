public interface RectangleDrawer{
  pubic void draw( Point topLeft, Point bottomRight);
}


public class RectangleDrawerAdapter implements RectangleDrawer {

  RectangleDrawerAdapter(){
    ...
  }

  public void render (int x, int y, int width, int height){
    ...
  }

  public void draw(Point topLeft, Point bottomRight){
    // some calculations, here I'm assuming x, y is the bottom left corner
    int width = bottomRight.x - topLeft.x;
    int height = topLeft.y - bottomRight.y;
    int x = topLeft.x;
    int y = topLeft - height;

    render(x, y, width, height);
}

