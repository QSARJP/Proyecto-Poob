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

public class Circle{

    
    
    private double diameter;
    private int xPosition;
    private int yPosition;
    private String color;
    private boolean isVisible;
    /**
     * Constructor de los objetos de tipo Circle
     * @param nDiameter  Diametro del circulo
     * @param nxPosition  posicion en el eje x de la figura 
     * @param nyPosition  posicion en el eje y de la figura 
     * @param nColor color del circulo
     */
    public Circle(double nDiameter, int nxPosition, int nyPosition, String nColor){
        diameter = nDiameter;
        xPosition = nxPosition;
        yPosition = nyPosition;
        color = nColor;
        isVisible = false;
    }

    /**
     * vuelve visible el circulo
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * vuelve visible el circulo
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

    /**
     * Draw the circle with current specifications on screen.
     */
    private void draw(){
        if(isVisible) {
            Canvas circle = Canvas.canvas;
            circle.draw(this, color, 
                new Ellipse2D.Double(xPosition, yPosition, 
                diameter, diameter));
        }
    }

    /**
     * Erase the circle on screen.
     */
    private void erase(){
        if(isVisible) {
            Canvas circle = Canvas.canvas;
            circle.erase(this);
        }
    }

}
