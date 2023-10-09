package Ej6.pilaGen;

/**
 * PilaGen is a class created for a generic stack
 * @autor Joaquín Tissera
 */

public class PilaGen<T> {
    private static final int CAP_MAX_DEFECTO = 25;
    private T[] info;
    private int cantElem;
    
    public PilaGen () {
        this(CAP_MAX_DEFECTO);
    }
    
    public PilaGen (int capacidad) {
        info = (T[]) new Object[capacidad];
        cantElem = 0;
    }
    
    public int getElems (){
        return cantElem;
    }
    
    public T getInfoPos (int pos) {
        return info[pos];
    }
    
    public boolean apilar (T elem) {
        if (cantElem == info.length)
            return false;
        info[cantElem] = elem;
        cantElem++;
        return true;
    }
    
    public boolean desapilar () {
        if (cantElem == 0)
            return false;
        cantElem--;
        return true;
    }
    
    public void vaciarPila () {
        cantElem = 0;
    }
    
    public void imprimirOrdenInverso (PilaGen<T> secuencia) {
        if (!(secuencia instanceof PilaGen<Number>)) throw new IllegalArgumentException("El parametro no es heredero de Number");
        if (secuencia.getElems() == 0) System.out.println("La secuencia está vacía");
        
        System.out.println("La secuencia en orden inverso es: ");
        for (int i = secuencia.getElems() ; i > 0 ; i--)
            System.out.println(secuencia.getInfoPos(i-1));
    }
    
}
