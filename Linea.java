import java.awt.*;
import java.awt.geom.*;
import java.util.Calendar;
import java.lang.*;


/**
 * Esta clase es la encargada de crear el objeto linea la cual es la representacion grafica de una ruta.
 * 
 * @author Juan Pablo Ospina Henao - Miguel Angel Rivera Rojas 
 * @version 1
 */
public class Linea
{
    // instance variables - replace the example below with your own
    private int xPosition1;
    private int yPosition1;
    private int xPosition2;
    private int yPosition2;
    private String color;
    private boolean isVisible;
    /**
     * Constructor de los objetos de tipo linea
     * @param newx1  posicion en el eje x de la figura 1
     * @param newy1  posicion en el eje y de la figura 2
     * @param newx2  posicion en el eje x de la figura 1
     * @param newy2  posicion en el eje y de la figura 2
     * @param nColor color de la linea
     */
    public Linea(int newx1 , int newy1 , int newx2, int newy2,String nColor)
    {
        // initialise instance variables
        this.xPosition1 = newx1;
        this.yPosition1= newy1; 
        this.xPosition2 = newx2;
        this.yPosition2 = newy2;
        this.color = nColor;
        this.isVisible = false;
    }

    /**
     * volver visible la linea.
    */
    public void makeVisible(){
        isVisible= true;
        draw();
    }
    
    /**
     * volver invisible la linea.
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
     * Draw the line with current specifications on screen.
     */
    private void draw(){
        if(isVisible) {
            Canvas medidor = Canvas.canvas;
            int[] xpoints = { xPosition1,xPosition1+5,xPosition2, xPosition2+10};
            int[] ypoints = { yPosition1,yPosition1+5,yPosition2, yPosition2+10 };
            medidor.draw(this, color, new Polygon(xpoints, ypoints, 4));
        }
    }
    
/**
     * Erase the line on screen.
     */
    private void erase(){
        if(isVisible) {
            Canvas recta = Canvas.canvas;
            recta.erase(this);
        }
    }
    public String getColor(){
        return color;
    }
}
