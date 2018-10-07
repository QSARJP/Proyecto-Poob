import java.awt.*;

/**
 * Esta clase ayuda a elegir una forma para las naciones y el respectivo color.
 * 
 * @author Juan Pablo Ospina Henao - Miguel Angel Rivera Rojas 
 * @version 1
 */

public class Triangle{
    private int height;
    private int width;
    private int xPosition;
    private int yPosition;
    private String color;
    private boolean isVisible;
    /**
     * Constructor de los objetos de tipo triangulo
     * @param nHeight  la altura del triangulo
     * @param nWidth   el ancho del triangulo 
     * @param nxPosition  posicion en el eje x de la figura 
     * @param nyPosition  posicion en el eje y de la figura 
     * @param nColor color del circulo
     */
    public Triangle(int nHeight, int nWidth,int nxPosition,int nyPosition, String nColor){
        height = nHeight;
        width = nWidth;
        xPosition = nxPosition;
        yPosition = nyPosition;
        color = nColor;
        isVisible = false;
    }
    /**
     * Vuelve el triangle visible
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    /**
     * Vuelve el triangle invisible
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }
    
    private void draw(){
        if(isVisible) {
            Canvas medidor = Canvas.canvas;
            int[] xpoints = { xPosition, xPosition + (width/2), xPosition - (width/2) };
            int[] ypoints = { yPosition, yPosition + height, yPosition + height };
            medidor.draw(this, color, new Polygon(xpoints, ypoints, 3));
        }
    }

    /**
     * Erase the triangle on screen.
     */
    private void erase(){
        if(isVisible) {
            Canvas medidor = Canvas.canvas;
            medidor.erase(this);
        }
    }
}
