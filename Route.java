
import java.util.*;
/**
 * Write a description of class Route here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Route
{
    private String n1;
    private String n2;
    private int cost;
    public Linea line;
    /**
     * Constructor for objects of class Route
     */
    public Route(String[] nations, int cost)
    {
      this.n1 = nations[0];
      this.n2 = nations[1];
      this.cost = cost;
      doRoute();
    }
    public int getCost(){
        return cost;
    }
    public String getName(){
       return n1 + " - " + n2;
    }
    public String getOrigin(){
        return n1;
    }
    public String getDestiny(){
        return n2;
    }
    public void borrar(){
        
    }
    /**
     * Crea la linea que representa la ruta desde la nación A hasta la nación B
     */
    private void doRoute(){
        ConquerWorld w = ConquerWorld.world;
        Nation nation1 = w.getNation(n1);
        Nation nation2 = w.getNation(n2);
        int x1 = nation1.getPosition()[0];
        int x2 = nation2.getPosition()[0];
        int y1 = nation1.getPosition()[1];
        int y2 = nation2.getPosition()[1];
        line = new Linea(x1,y1,x2,y2,"black");
        line.makeVisible();
        
    }
    public Linea getLinea(){
        return line;
    }
    @Override 
    public String toString(){
       String nation1 = this.n1;
       String nation2 = this.n2;
       String costo = Integer.toString(this.cost);
       
       
       return nation1 + " - "+ nation2 + " - " + costo;

    }
  
}
