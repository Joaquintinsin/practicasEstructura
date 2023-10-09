package lista;

public class ImplementacionListas1 <T> {
    private T info;
    private int cantElem;
    private ImplementacionListas1 next;
    
    public ImplementacionListas1(){
        this(null);
    }
    
    public ImplementacionListas1(T info){
        this.info = info;
        cantElem = 0;
        next = null;
    }
    
    public T getInfo(){
        return info;
    }
    
    public void setInfo(T info){
        this.info = info;
    }
    
    public int getCantElem(){
        return cantElem;
    }
    
    private ImplementacionListas1 getNext(){
        return next;
    }
    
    public boolean agregar(T elem){
        // no quiero que venga null ni que venga esta misma implementacion
        if (elem == null || (elem == this))
            return false;
        
        // agrega sin problema, hago un nuevo nodo, voy hasta el ultimo cargado, y ahora el next va a ser el nuevo nodo
        ImplementacionListas1 nuevoNodo = new ImplementacionListas1(elem);
        while (next != null)
            next = getNext();
        next = nuevoNodo;
        cantElem++;
        return true;
    }
}
