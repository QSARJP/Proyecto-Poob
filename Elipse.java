import java.awt.*;
import java.awt.geom.*;
import java.util.Calendar;
import java.lang.*;

/**
 * Esta clase ayuda a elegir una forma para las naciones y el respectivo color.
 * 
 * @author Juan Pablo Ospina Henao - Miguel Angel Rivera Rojas 
 * @version 1
 */

public class Elipse{

    public static double PI=3.1416;
    private int side;
    private int diameter;
    private int xPosition;
    private int yPosition;
    private String color;
    private boolean isVisible;
    /**
     * Constructor de los objetos de tipo triangulo
     * @param nDiameter  el diametro de la elipse
     * @param nSide      el largo de la elipse
     * @param nxPosition  posicion en el eje x de la figura 
     * @param nyPosition  posicion en el eje y de la figura 
     * @param nColor color del circulo
     */
    public Elipse(int nDiameter,int nSide, int nxPosition, int nyPosition, String nColor){
        diameter = nDiameter;
        xPosition = nxPosition;
        yPosition = nyPosition;
        color = nColor;
        side = nSide;
        isVisible = false;
    }

    /**
     * Vuelve la elpise visible
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Vuelve la elpise invisible
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }

    /**
     * Cambia de color dependiendo el nombre   
     * @param newColor nombre del color.
     */
    public void changeColor(String newColor){
        color = newColor;
        draw();
    }

    /*
     * Draw the circle with current specifications on screen.
     */
    private void draw(){
        if(isVisible) {
            Canvas circle = Canvas.canvas;
            circle.draw(this, color, 
                new Ellipse2D.Double(xPosition, yPosition, 
                diameter, side));
        }
    }

    /*
     * Erase the circle on screen.
     */
    private void erase(){
        if(isVisible) {
            Canvas circle = Canvas.canvas;
            circle.erase(this);
        }
    }

}
