package colecciones; // colecciones.nodo , nombre de la carpeta

/**
 * Representa un nodo
 */

public class Nodo {
    /* atributos: privados. pueden ser de otra visibilidad, 
     * depende de cómo lo ven las otras clases y paquetes
     */
    // visibilidad - tipo - nombreAtributo
    private int valor;
    private Nodo siguiente; // se inicializa por defecto con null
    
    // Constructores
    public Nodo(){
        valor = 0;
        siguiente = null;
    }
    
    public Nodo(int valor){
        this.valor = valor;
        siguiente = null;
    }
    
    public Nodo(int valor, Nodo siguiente){
        this.valor = valor;
        this.siguiente = siguiente;
    }
    
    // formas de acceder a los atributos a traves de los metodos
    // visibilidad - tipo de retorno - nombreMetodo - argumentos (opcionales)
    /**
     * Obtiene el valor asociado a este nodo
     * #return el valor asociado a este nodo
     */
    public int getValor(){
        return valor;
    }
    /**
     * Modifica el atributo valor
     */
    public void setValor(int valor){
        this.valor = valor;
    }
    
    /**
     * Obtiene el siguiente nodo
     * Puede ser #return null.
     */
    public Nodo getSiguiente(){
        return siguiente;
    }
    /**
     * Modifica el atributo siguiente
     */
    public void setSiguiente(Nodo siguiente){
        this.siguiente = siguiente;
    }
}
