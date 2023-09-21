package colecciones.listaArreglos;

public class ImplementacionListasArreglo<T> implements Lista<T> {
    public static final int CAP_MAX_ARREGLO = 100;
    private T[] arr;
    private int cantElem;
    
    public ImplementacionListasArreglo(){
        this(CAP_MAX_ARREGLO);
    }
    
    public ImplementacionListasArreglo(int cantidad){
        arr = (T[]) new Object[cantidad];
        cantElem = 0;
    }
    
    public boolean agregar(T elem){
        // no quiero que venga null ni que venga este mismo objeto
        if (elem == null || elem == this || cantElem >= CAP_MAX_ARREGLO-1)
            return false;
        arr[cantElem] = elem;
        cantElem++;
        return true;
    }
    
    public boolean agregarTodos(Lista<T> otraLista){
        if ( arr.length == cantElem ){
            return false;
        }
        
        for ( int i = 0 ; cantElem < arr.length && i < arr.length ; i++){
            arr[cantElem] = otraLista.obtener(i);
            cantElem++;
        }
        
        return (cantElem + otraLista.elementos()) <= arr.length;
    }
    
    public boolean insertar(T elem, int indice){
        if ( indice < 0 || indice > cantElem ) throw new IndexOutOfBoundsException("Indice fuera de rango");
        
        if ( arr.length == cantElem )
            return false;
        
        if ( indice == cantElem ){
            arr[indice] = elem;
            cantElem++;
        }else{
            T aux = arr[indice];
            arr[indice] = elem;
            for ( int i = indice ; i < arr.length ; i++ ){
                arr[i] = aux;
                aux = arr[i+1];
            }
            cantElem++;
        }
        return true;
    }
    
    public T eliminar(int indice){
        if ( indice < 0 || indice > cantElem ) throw new IndexOutOfBoundsException("Indice fuera de rango");
        
        T rescueElem = arr[indice-1];
        arr[indice-1] = null;
        
        for (int i = cantElem ; i < indice ; i--)
            arr[i-1] = arr[i];
        return rescueElem;
    }
    
    public T obtener(int indice){
        return arr[indice];
    }
    
    public Lista<T> subLista(int desdeInd, int hastaInd){
        if ( desdeInd < 0 || hastaInd < cantElem || desdeInd > hastaInd) throw new IndexOutOfBoundsException("Indice fuera de rango");
        
        Lista<T> listaResultado = new ImplementacionListasArreglo();
        if ( desdeInd == hastaInd || esVacia() )
            return listaResultado;
        
        int i = 0;
        while ( desdeInd < hastaInd ){
            listaResultado.insertar(arr[desdeInd], i);
            desdeInd++;
            i++;
        }
        
        return listaResultado;
    }
    
    public boolean contiene(T elem){
        for ( int i = 0 ; i < cantElem ; i++ ){
            if ( arr[i] == elem )
                return true;
        }
        return false;
    }
    
    public void vaciar(){
        cantElem = 0;
    }
    
    public int elementos(){
        return cantElem;
    }
    
    public boolean esVacia(){
        return ( cantElem == 0 );
    }
    
    public boolean repOK(){
        throw new UnsupportedOperationException("Debe implementar este método");
    }
    
    @Override
	public String toString(){
        String msg = "Elementos de la lista implementada con arreglos: \n";
        msg += "Hay " + cantElem + " elementos almacenados en la lista. \n";
        for ( int i = 0 ; i < cantElem ; i++ ){
            msg += "Elemento número " + (i+1);
            msg += arr[i].toString();
        }
        return msg;
    }
    
    @Override
	public boolean equals(Object otro){
        if ( otro == null || !(otro instanceof ImplementacionListasArreglo) )
            return false;
        
        ImplementacionListasArreglo otraLista = (ImplementacionListasArreglo) otro;
        if ( otraLista.elementos() != cantElem )
            return false;
        
        boolean logicAux = true;
        for ( int i = 0 ; i < otraLista.elementos() ; i++ )
            logicAux = logicAux && (arr[i] == otraLista.obtener(i));
        
        return logicAux;
    }
}
