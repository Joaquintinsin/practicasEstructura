package Ej5;

/* 5. Considere el siguiente fragmento de una implementación clásica de listas sobre arreglos:

    Además de las operaciones básicas se desea proveer una operación public Integer buscar(T x), que
    retorna la posición del elemento en caso de que pertenezca, y una excepción en caso contrario. Esta
    operación debe ser eficiente, se requiere que sea O(1) si el elemento buscado está al final de la lista, y
    que sea O(log N) en otro caso, manteniendo la inserción y la eliminación en O(N).
*/
public class ListaSobreArreglos<T extends Comparable <T>> {
    private static final int MAXLIST = 100;
    private T[] item;
    private int numItems;
    
    public ListaSobreArreglos (){
        item = (T[]) new Comparable[MAXLIST];
        numItems = 0;
    }
    
    public ListaSobreArreglos (int capMax) {
        item = (T[]) new Comparable[capMax];
        numItems = 0;
    }
        
    public Integer buscar(T x) {
        if (numItems == 0)
            throw new ArrayIndexOutOfBoundsException("El arreglo está vacío");
        if (item[numItems-1].compareTo(x) == 0)
            return numItems-1;
        
        Integer resBusqueda = buscarDicot(0, numItems, x);
        
        if (item[resBusqueda].compareTo(x) != 0)
            throw new ArrayIndexOutOfBoundsException("No se encontró el elemento");
        
        return resBusqueda;
    }
        
    private Integer buscarDicot(int x, int y, T elem) {
        int pivot = (x+y) % 2;
        
        if (x < y)
            return pivot;
        if (item[pivot].compareTo(elem) == 0)
            return pivot;
        if (item[pivot].compareTo(elem) == -1)
            buscarDicot(x, pivot-1, elem);
        if (item[pivot].compareTo(elem) == 1)
            buscarDicot(y, pivot, elem);
            
        return pivot; // O se pasan los indices o se encuentra el elemento, no entra nunca a este return
    }
        
    private void corrimiento(int pos) {
        T aux = item[pos];
        do{
            item[pos+1] = aux;
            aux = item[pos];
            pos++;
        } while (pos < numItems);
    }
        
    public void insercion(T x) {
        if (numItems == 0) {
            item[numItems] = x;
            numItems++;
        }
        
        boolean auxBool = true;
        int i;
        for (i = 0 ; auxBool && i < numItems && i < MAXLIST ; i++) {
            if (item[i].compareTo(x) == 0) {
                corrimiento(i+1);
                item[i] = x;
                numItems++;
                auxBool = false;
            }else{
                if (item[i].compareTo(x) == 1) {
                    corrimiento(i-1);
                    item[i] = x;
                    numItems++;
                    auxBool = false;
                }
                // Si el compareTo da -1 entonces sigue buscando
            }
        }
        if (i == MAXLIST)
            throw new IllegalArgumentException("El arreglo está lleno");
        
        if (i == numItems) {
            item[numItems] = x;
            numItems++;
        }
    }

    public void eliminacion(T x) {
        if (numItems == 0)
            throw new IllegalArgumentException("El arreglo está vacío");
        
        boolean auxBool = true;
        int i;
        for (i = 0 ; auxBool && i < numItems && i < MAXLIST ; i++) {
            if (item[i].compareTo(x) == 0) {
                corrimiento(i-1);
                numItems--;
                auxBool = false;
            }else{
                if (item[i].compareTo(x) == 1) {
                    corrimiento(i+1);
                    numItems--;
                    auxBool = false;
                }
                // Si el compareTo da -1 entonces sigue buscando
            }
        }
        if (i == MAXLIST) {
            item[i-1] = item[i];
            numItems--;
        }
    }
}

