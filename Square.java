import java.awt.*;

/**
 * Esta clase ayuda a elegir una forma para las naciones y el respectivo color.
 * 
 * @author Juan Pablo Ospina Henao - Miguel Angel Rivera Rojas 
 * @version 1
 */
 
public class Square{

    private int height;
    private int xPosition;
    private int yPosition;
    public String color;
    public boolean isVisible;
    /**
     * Constructor de los objetos de tipo rectangulo
     * @param newHeight  el lado del caudrado
     * @param newPositionX  posicion en el eje x de la figura 
     * @param newPositionY  posicion en el eje y de la figura 
     * @param nColor color del circulo
     */
    public Square(int newHeight,int newPositionX,int newPositionY, String ncolor){
        height = newHeight;
        xPosition = newPositionX;
        yPosition = newPositionY;
        color = ncolor;
        isVisible = false;
    }
    
    /**
     * Vuelve el cuadrado visible
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Vuelve el cuadrado invisible
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


    public void draw() {
        if(isVisible) {
            Canvas rectangle = Canvas.canvas;
            rectangle.draw(this, color,
                new java.awt.Rectangle(xPosition, yPosition, height, height));
           
        }
    }

    private void erase(){
        if(isVisible) {
            Canvas rectangle = Canvas.canvas;
            rectangle.erase(this);
        }
    }
}