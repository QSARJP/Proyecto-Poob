import java.util.*;
import java.util.Collections;
/**
 * Write a description of class ConquerWorld here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ConquerWorld
{
    // instance variables - replace the example below with your own
    private boolean isVisible;
    private int cash;
    private int maxX;
    private int maxY;
    private HashMap<String,Nation> nats = new HashMap<String,Nation>();
    private HashMap<String,Route> routes = new HashMap<String,Route>();
    private ArrayList<String> listaNationes = new ArrayList<String>();
    private TreeMap<Integer,Route> goodMove = new TreeMap<Integer,Route>();
    private Nation nation;
    public static ConquerWorld world;
    private boolean indicador;
    /**
     * Constructor for objects of class ConquerWorld
     * @param x Ancho del canvas
     * @param y Altura del canvas
     */
    public ConquerWorld(int x, int y)
    {
        this.maxX = x;
        this.maxY = y;
        this.cash = 0;
        Canvas can = new Canvas(maxY,maxX);
        can.visible();
        world = this;
        }
    public Nation getNation(String nation){
        return nats.get(nation);
    }
    public Route getRoute(String route){
        return routes.get(route);
    }
    /**
     *Remueve una nación del conquerworld
     *@param nation Nombre de la nacion que se va a remover
     */
    public void removeN(String nation){
         nats.remove(nation);
    }
    /**
     * Adiciona presupuesto a la cantidad que se tiene actualmente
     * @param amount Cantidad que se va a adicionar al presupuesto
     */
    public void addCash(int amount) {
        this.cash = amount;
        Rectangle medidor = new Rectangle(amount,50,0,0,"green");
        medidor.makeVisible();
    }
    /**
     * Agrega una nación al mundo
     * @param shape Forma que va a representar la nacion
     * @param area Indica el area que va a tener dicha figura
     * @param color Indica el color y nombre que tendrá la nacion
     * @param position Indica la coordenada x y y, donde estará ubicada la figura
     * @param armiesNeeded Indica la cantidad de ejercitos necesarios para conquistar la nacion
     */
    public void addNation(String shape, int area, String color, int[] position, int armiesNeeded) {
        nation = getNation(color);
        boolean key = false;
        Nation n;
        for (String i:nats.keySet()){
                n = getNation(i); 
                if (n.getPosition()[0]== position[0] && n.getPosition()[1]== position[1] ){
                    key = true;
                }
        }
        if (nation == null && key == false){
            
            nation = new Nation(shape, area, color, position, armiesNeeded);
            nation.crear(shape, area, color, position);
            nats.put(nation.getName(), nation);
            indicador = true;
        }
        else{
            indicador = false;
        }
    }
    public ArrayList<String> getNations() {
        listaNationes = new ArrayList<String>(nats.keySet());
        return listaNationes;
    }
    /**
     * Devueve una lista con todas las naciones que se han creado hasta el momento
     */
    public String[] nations() {
        getNations();
        Collections.sort(listaNationes);
        String[] l = new String[listaNationes.size()];
        for (int i = 0; i < listaNationes.size(); i++) {
                l[i] =listaNationes.get(i);

        }
        //String[] l= listaNationes.toArray(new String[listaNationes.size()]);
        return l;
    }
    /**
     * Devuelve una matriz con todas las rutas que se han creado hasta el momento
     */
    public String[][] routes() {
        getNations();
        Collections.sort(listaNationes);
        String[][] l = new String[listaNationes.size()][listaNationes.size()];
        for (int i = 0; i < listaNationes.size(); i++) {
            for (int j = 0 ;j < listaNationes.size(); j++){
                String [] rutaSi = new String[]{listaNationes.get(i),listaNationes.get(j)};
                if (listaNationes.get(i) != listaNationes.get(j) && okRoute(rutaSi)){
                    Route r1 = getRoute(listaNationes.get(i) + " - " + listaNationes.get(j));
                    int costoR = r1.getCost();
                    String rela =listaNationes.get(i) + " - " + listaNationes.get(j)+"-"+costoR; 
                    l[i][j] = rela;
                }   
            }
        }
        
        return l;
    }
    /**
     * Devuelve una lista con todas las rutas que se han creado hasta el momento
     */
    public String[] routes(String nation){
        Nation n = getNation(nation);
        String [] r = new String[n.getSizeRoutes()];
        for (int i = 0; i < n.getSizeRoutes(); i++) {
            r[i] = n.getRoutes().get(i);
        }
        return r;
    }
    /**
     * Adiciona una ruta entre 2 naciones, teniendo en cuenta el costo de ir por dicha ruta
     * @param nations Es una lista donde se especifica entre que naciones va a ir la ruta
     * @param cost Indica el costo que se incurre al tomar la ruta
     */
    public void addRoute(String[] nations, int cost){
        Route r1 = getRoute(nations[0] + " - " + nations[1]);
        Route r2 = getRoute(nations[1] + " - " + nations[0]);
        Nation n1 = getNation(nations[0]);
        Nation n2 = getNation(nations[1]);
        if (n1 != null && n2 != null && r1 == null && r2 ==null && n1 != n2){
            Route route1 = new Route(nations,cost);
            String[] nations2 = {nations[1],nations[0]}; 
            Route route2 = new Route(nations2,cost);
            n1.addRoute(nations[1]);
            n2.addRoute(nations[0]);
            routes.put(route1.getName(),route1);
            routes.put(route2.getName(),route2);
            goodMove.put(cost,route1);
            indicador = true;
        }
        else{
            indicador = false;
        }
        distintivo();
    }

    /**
     * Remueve una ruta especifica del conquerworld
     * @param nations lista que contiene la pareja de naciones que conecta la ruta
     */
    public void removeRoute(String[] nations){
            Route r = getRoute(nations[0] + " - " + nations[1]);
            Route r2 = getRoute(nations[1] + " - " + nations[0]);
            Nation n1 = getNation(nations[0]);
            Nation n2 = getNation(nations[1]);
            if (r != null && r2!=null){
                routes.remove(nations[0] + " - " + nations[1]);
                routes.remove(nations[1] + " - " + nations[0]);
                n1.removeRoute(nations[1]);
                n2.removeRoute(nations[0]);
                
                goodMove.remove(r.getCost());
                indicador = true; 
            }
            else{
                indicador = false;
            }
    }
    /**
     * Adiciona un ejercito a la nacion seleccionada
     * @param nation Indica la nacion a la que se le adicionara el ejercito
     */
    public void addArmy(String nation){
        Nation n = getNation(nation);
        if (n != null){
            n.addArmy();
            indicador = true;
        }
        else{
            indicador = false;
        }
    }
    /**
     * Agrega un ejercito a cada una de las naciones que esta en la lista
     * @param nations Lista de naciones a las que se les va a adicionar un ejercito
     */
    public void addArmies(String[] nations){
        Nation n;
        boolean key = true;
        for (String i: nations){
            n = getNation(i);
            if (n == null){
                key = false;
                
            }
        }
        if (key){
            for (String i: nations){
                Nation nacion = getNation(i);
                nacion.addArmy();
            }
            indicador = true;
        }
        else{
            indicador = false;
        }
    }
    /**
     * Remueve todos los ejercitos de la nacion que se especifique
     * @param nation Nacion a la que se le quitaran todos sus ejercitos
     */
    public void removeArmies(String nation){
        Nation n = getNation(nation);
        if (n != null && n.getSizeArmies() > 0){
            n.clearArmies();
            indicador = true;
        }
        else{
            indicador = false;
        }
    }
    /**
     * Mueve un ejercito desde la nacion A a la nacion B
     * @param fromNation Nacion de donde se va a mover el ejercito
     * @param toNation Nacion a donde debe ir dicho ejercito
     */
    public void moveArmy(String fromNation, String toNation){   
        Nation n1 = getNation(fromNation);
        Nation n2 = getNation(toNation);
        int sizeN1 = n1.getSizeRoutes();
        int sizeN2 = n2.getSizeRoutes();
        int sizeArmyN1 =n1.getSizeArmies();
        Route r = getRoute(fromNation + " - " + toNation);
        if (r != null){
            int cost = r.getCost();
            if (sizeArmyN1>0 &&(n1 != null && n2 != null)&&(sizeN1>0 && sizeN2 > 0)&& cost<cash){
                n1.removeArmy();
                n2.addArmy();
                indicador = true;
            }else{
            indicador = false;
        }
        }else{
            indicador = false;
        }
        distintivo();
        
    }
    /**
     * Realiza un buen movimiento dentro del conquerworld
     */
    public void moveArmy(){
        for (int i:goodMove.keySet()){
            Route r = goodMove.get(i);
            Nation nO = getNation(r.getOrigin());
            Nation nD = getNation(r.getDestiny());
            if (nO.getSizeArmies() > 0 && !nD.conquered()){
                String n1 = r.getOrigin();
                String n2 = r.getDestiny();
                moveArmy(n1,n2);
                break;
            }
            else if(nD.getSizeArmies() > 0 && !nO.conquered()){
                String n1 = r.getOrigin();
                String n2 = r.getDestiny();
                moveArmy(n2,n1);
                break;
            }
        }
        distintivo();
    }
    /**
     * Verifica que ruta se puede recorrer, si es verde, se puede tomar, rojo en el caso contrario
     */
     private void distintivo(){
        for (String i: routes.keySet()){
            Route r = routes.get(i);
            int costo = r.getCost();
            Linea l = r.getLinea();
            if (costo > cash){
                l.changeColor("red");
            }
            else{
                l.changeColor("green");
            }
        }
    }
            
    /**
     * Remueve la nacion especificada del conquerworld
     * @param nation Indica el nombre de la nacion a remover
     */
    public void removeNation(String nation){
        Nation n = getNation(nation);
        if (n != null && n.getSizeRoutes() == 0){
            n.borrar();
            removeN(nation);
            indicador = true;
        }
        else{
            indicador = false;
        }
        
    }
    /**
     * Devueve true si la ruta especificada existe, false en caso contrario
     * @param Nations Lista de las naciones que estan conectas por la ruta
     */
    public boolean okRoute(String[] nations){
        if (getRoute(nations[0] + " - " + nations[1]) != null){
            return true;
        }
        else{
            return false;
        }
    }
    /**
     * Devuelve true si el último proceso ejecutado, se realizó con exito, false de lo contrario
     */
    public boolean ok(){
        return indicador;
    }
    public boolean okRoutes(){
        boolean okR = true;
        for (String i: nats.keySet()){
            Nation n = getNation(i);
            ArrayList<String> r = n.getRoutes();
            for (int j = 0 ; j<r.size(); j++){
                Nation n2 = getNation(r.get(j));
                ArrayList<String> r2 = n2.getRoutes();
                for (int z = 0 ; z<r2.size(); z++){
                    Nation n3 = getNation(r2.get(z));
                    if (r.contains(n3.getName()) == true){
                        okR = false;
                    }
                }
            }
        }
        return okR;
    }
    /**
     * Permite ampliar la imagen de conquerworld
     * @param c Indica si se debe aumentar o disminuir el zoom
     */
    public void zoom(char c){
         if (c == '+'){
             for (String i: nats.keySet()){
                Nation n = getNation(i);
                n.borrar();
                n.crear(n.getShape(), n.getArea()+1000, n.getName(), n.getPosition());
             }
             indicador = true;
        }
        else if(c == '-'){
              for (String i: nats.keySet()){
                Nation n = getNation(i);
                n.borrar();
                n.crear(n.getShape(), n.getArea()-1000, n.getName(), n.getPosition());
             }
             indicador = true;
        }else{
            indicador = false;
        }
    }
    /**
     * vuelve visible el mundo grafico
     */
    public void makeVisible(){
        Canvas canvas = Canvas.canvas;
        canvas.visible();
    }
    /**
     * vuelve invisible el mundo grafico
     */
    public void makeInvisible(){
        Canvas canvas = Canvas.canvas;
        canvas.inVisible();
    }
    /**
     * Devuelve true si el último proceso ejecutado, se realizó con exito, false de lo contrario
     */
    public void finish(){
        Canvas canvas  = Canvas.canvas;
        canvas.terminar();
        getNations();
        for (int i = 0; i< listaNationes.size(); i++){
            String nacio = listaNationes.get(i);
            Nation n = getNation(nacio);
            n.reset();
            n.borrar();
        }
        HashMap<String,Nation> nats = null;
        HashMap<String,Route> routes = null;
        ArrayList<String> listaNationes = null;
        TreeMap<Integer,Route> goodMove = null;
        canvas.inVisible();
    }
    
}
