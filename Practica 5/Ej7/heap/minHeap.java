package heap;

public class minHeap<T extends Comparable> {
    private T raiz;
    private T izq;
    private T der;

    public minHeap() {
        raiz = null;
        izq = null;
        der = null;
    }

    public minHeap(T elem) {
        raiz = elem;
        izq = null;
        der = null;
    }

    // Constructor privado para los subHeaps
    private minHeap<T> subHeapIzq () {
        return new minHeap(izq);
    }

    // Constructor privado para los subHeaps
    private minHeap<T> subHeapDer () {
        return new minHeap(der);
    }

    public void insertar(T elem) {
        if (raiz == null) {
            raiz = elem;
            return;
        }
        
        int resCompare = raiz.compareTo(elem);
        if (resCompare < 0) {
            subHeapIzq().insertar(elem);
        } else if (resCompare > 0) {
            subHeapDer().insertar(raiz);
            raiz = elem;
        } else {
            return; // No inserta repetidos
        }
    }

  /*  public void remover(T elem) {
        int resCompare = raiz.compareTo(elem);
        if (resCompare < 0) {
            subHeapIzq().remover(elem);
        } else if (resCompare > 0) {
            subHeapDer().remover(elem);
        } else {
            

        }
    }*/

    private void intercambiarRaizHijo(T hijo) {
        T aux = raiz;
        raiz = hijo;
        hijo = aux;
    }
    
    public static void main (String[] args) {
        minHeap<Integer> h1 = new minHeap<Integer>();
        
    }
}



/*
heapSort va a llevar el maximo al fondo del arreglo, reacomodar el fondo-1 como heap de nuevo y volver a intercambiar.
intecambio la raiz con el ultimo y achico el tratamiento.
luego de los intercambios, reacomodo el arbol restante como estilo del heap.
si fue minHeap, al acomodarlo quedó acomodado de mayor a menor.
si fue maxHeap, al acomodarlo quedó acomodado de menor a mayor.

heap es un arbol binario que tiene relacion padre-hijo siempre mayor-menor o menor-mayor y es completo de izquierda a derecha

si tengo un avl, tiene que cumplir que los hijos izquierdos son mas chicos que la raiz, los hijos derechos mas grandes que la raiz
y se cumple recursivamente
el factor de balanceo tiene que ser a lo sumo 1 (cero o uno)
si no, rebalancear: 
*/
