import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;

/**
 * Esta es la clase de representacion grafica.
 * 
 * @author Juan Pablo Ospina Henao - Miguel Angel Rivera Rojas 
 * @version 1
 */
public class Canvas 
{
    private static int altura;
    private static int ancho;
    private Color fondo;
    private CrearMundoPane mundo;
    private JFrame frame;
    private Graphics2D graphic;
    private Color backgroundColour;
    private Image canvasImage;
    private List <Object> objects;
    private HashMap <Object,ShapeDescription> shapes;
    public static Canvas canvas;
    public Graphics2D g;
    /**
     * Crear un muevo mundo dependiendo de las caracteristicas de tamaño.
     * @param vAltura Es la altura que se desea del canvas o la representacion del mundo.
     * @param vAncho Es el ancho que se desea del canvas o la representacion del mundo.
     */
    public Canvas(int vAltura, int vAncho)
    {
        altura = vAltura;
        ancho = vAncho;
        fondo = Color.white;
        crear(altura,ancho,fondo);
        this.setVisible(false);
        canvas = this;
    }

    /**
     * Crear el canvas
     * @param altura Segun la altura dado anteriormente
     * @param ancho Segun el ancho dado anteriormente
     * @param fondo Color del fondo
     * @author: Bruce Quig
     * @author: Michael Kolling (mik)
     */
    private void crear(int altura, int ancho, Color fondo)
    {
        frame = new JFrame();
        mundo = new CrearMundoPane();
        frame.setContentPane(mundo);
        backgroundColour = fondo;
        mundo.setPreferredSize(new Dimension(ancho, altura));
        frame.pack();
        objects = new ArrayList <Object>();
        shapes = new HashMap <Object,ShapeDescription>();
        
        
    }
    
    /**
     * Puede cambiar el estado de visibilidad del canvas, ademas de traer al frente el canvas 
     * @param visible es el estado actual del canvas
     * @author: Bruce Quig
     * @author: Michael Kolling (mik)
     */
    
    public void setVisible(boolean visible){
        if(graphic == null) {
            // first time: instantiate the offscreen image and fill it with
            // the background colour
            Dimension size = mundo.getSize();
            canvasImage = mundo.createImage(size.width, size.height);
            graphic = (Graphics2D)canvasImage.getGraphics();
            graphic.setColor(backgroundColour);
            graphic.fillRect(0, 0, size.width, size.height);
            graphic.setColor(Color.black);
        }
        frame.setVisible(visible);
        
    }
    
    /**
     * Vuelve visible el canvas
     */
    public void visible(){
      setVisible(true);  
    }
    
    /**
     * Vuelve invisible el canvas
     */
    public void inVisible(){
        setVisible(false);
    }
    
    /**
     * Dibuja una figura en el canvas
     * @param  referenceObject  un objeto de referencia shapes
     * @param  color            el color de la figura
     * @param  shape            el objeto que se desea.
     * @author: Bruce Quig
     * @author: Michael Kolling (mik)
     */
    public  void draw(Object referenceObject, String color, Shape shape){
        objects.remove(referenceObject);   // just in case it was already there
        objects.add(referenceObject);
        shapes.put(referenceObject, new ShapeDescription(shape, color));
        redraw();
       
    }
    
    /**
     * @author: Bruce Quig
     * @author: Michael Kolling (mik)
     */
    private void redraw(){
        erase();
        for(Iterator i=objects.iterator(); i.hasNext(); ) {
                       shapes.get(i.next()).draw(graphic);
        }
        mundo.repaint();
    }
    
    /**
     * Borra el objeto que se pide.
     * @param  referenceObject  es el objeto que se desea borrar
     * @author: Bruce Quig
     * @author: Michael Kolling (mik)
     */
    public void erase(Object referenceObject){
        objects.remove(referenceObject);   // just in case it was already there
        shapes.remove(referenceObject);
        redraw();
    }
    public void terminar(){
        erase();
    }
    
    /**
     * Crear un muevo mundo dependiendo de las caracteristicas de tamaño.
     * @author: Bruce Quig
     * @author: Michael Kolling (mik)
     */
    private void erase(){
        Color original = graphic.getColor();
        graphic.setColor(backgroundColour);
        Dimension size = mundo.getSize();
        graphic.fill(new java.awt.Rectangle(0, 0, size.width, size.height));
        graphic.setColor(original);
    }
    
    /**
     * Crear un muevo mundo dependiendo de las caracteristicas de tamaño.
     * @author: Bruce Quig
     * @author: Michael Kolling (mik)
     */
      public void setForegroundColor(String colorString){
        if(colorString.equals("red"))
            graphic.setColor(Color.red);
        else if(colorString.equals("black"))
            graphic.setColor(Color.black);
        else if(colorString.equals("blue"))
            graphic.setColor(Color.blue);
        else if(colorString.equals("yellow"))
            graphic.setColor(Color.yellow);
        else if(colorString.equals("green"))
            graphic.setColor(Color.green);
        else if(colorString.equals("magenta"))
            graphic.setColor(Color.magenta);
        else if(colorString.equals("white"))
            graphic.setColor(Color.white);
        else if(colorString.equals("cyan"))
            graphic.setColor(Color.cyan);
        else if(colorString.equals("darkGray"))
            graphic.setColor(Color.darkGray);
        else if(colorString.equals("gray"))
            graphic.setColor(Color.gray);
        else if(colorString.equals("orange"))
            graphic.setColor(Color.orange);
        else if(colorString.equals("lightGray"))
            graphic.setColor(Color.lightGray);
        else if(colorString.equals("pink"))
            graphic.setColor(Color.pink);
        else
            graphic.setColor(Color.black);
    }
    
    /**
     * Crear un muevo mundo dependiendo de las caracteristicas de tamaño.
     * @author: Bruce Quig
     * @author: Michael Kolling (mik)
     */
    private class CrearMundoPane extends JPanel{
        public void paint(Graphics g){
            g.drawImage(canvasImage, 0, 0, null);
        }
    }
    
    /**
     * Crear un muevo mundo dependiendo de las caracteristicas de tamaño.
     * @author: Bruce Quig
     * @author: Michael Kolling (mik)
     */
    private class ShapeDescription{
        private Shape shape;
        private String colorString;

        public ShapeDescription(Shape shape, String color){
            this.shape = shape;
            colorString = color;
        }

        public void draw(Graphics2D graphic){
            setForegroundColor(colorString);
            
            graphic.fill(shape);
        }
    }

}