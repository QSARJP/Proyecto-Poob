
/**
 * Write a description of class Figura here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Figura 
{
    // instance variables - replace the example below with your own
    public  int area;
    public  String color;
    public  int[] position;
    public  boolean isVisible;
    /**
     * Constructor for objects of class Figura
     */
    public Figura(int newArea, String newColor, int[]newPosition)
    {
        this.area=newArea;
        this.color=newColor;
        this.position=newPosition;
    }

    /**
     * vuelve visible la figura
     */
    public void changeSize(){
        this.area = area * 10;
        
    }
}
