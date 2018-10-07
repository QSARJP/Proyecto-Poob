import java.awt.*;


/**
 * Esta clase ayuda a elegir una forma para las naciones y el respectivo color.
 * 
 * @author Juan Pablo Ospina Henao - Miguel Angel Rivera Rojas 
 * @version 1
 */
public class Rectangle{

    private int height;
    private int width;
    private int xPosition;
    private int yPosition;
    public String color;
    public boolean isVisible;
     /**
     * Constructor de los objetos de tipo Circle
     * @param nDiameter  Diametro del circulo
     * @param nxPosition  posicion en el eje x de la figura 
     * @param nyPosition  posicion en el eje y de la figura 
     * @param nColor color del circulo
     */
    public Rectangle(int newHeight,int newWidth,int newPositionX,int newPositionY, String ncolor){
        height = newHeight;
        width = newWidth;
        xPosition = newPositionX;
        yPosition = newPositionY;
        color = ncolor;
        isVisible = false;
    }
    
    /**
     * Vuelve el rectangulo visible
     */
    public void makeVisible(){
         isVisible = true;
         draw();
    }
    
    /**
     * Vuelve el rectangulo invisible
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }
    /**
     * Cambia el tamaño del rectangulo
     */
    public void changeSize(int newHeight, int newWidth) {
        erase();
        height = newHeight;
        width = newWidth;
        draw();
    }
    /**
     * Cambia el color del rectangulo
     */
    public void changeColor(String newColor){
        color = newColor;
        draw();
    }

    private void draw() {
        if(isVisible) {
            Canvas rectangle = Canvas.canvas;
            rectangle.draw(this, color,
                new java.awt.Rectangle(xPosition, yPosition, width, height));
           
        }
    }

    private void erase(){
        if(isVisible) {
            Canvas rectangle = Canvas.canvas;
            rectangle.erase(this);
        }
    }
}