package arbol;

import java.util.Comparator;
import java.util.List;
import java.util.LinkedList;

/**
 * Árbol binario de busqueda (ABB), es una implementación de {@code Diccionario} mediante nodos encadenados que preserva las propiedades de Diccionario.
 * @param <T> Tipo del valor asociado a los nodos del árbol, debe ser posible definir un orden total para los mismos.
 * @see NodoBinario
 */
public class ABB<T> implements Diccionario<T> {

    private NodoBinario<T> raiz;

    /**
     * Comparador usado para mantener el orden en un ABB, o null.
     */
    private final Comparator<? super T> comparador;

    /**
     * Construye un nuevo árbol vacío ordenado acorde al comparador dado.
     * @param comparador define una forma de comparar los valores insertados en el arbol.
     */
    public ABB(Comparator<? super T> comparador) {
        this.comparador = comparador;
        this.raiz = null;
    }

    /**
     * Construye un nuevo árbol con un elemento en la raiz, ordenado acorde al comparador dado.
     * @param comparador define una forma de comparar los valores insertados en el arbol.
     * @param valor de la raiz del nuevo arbol si no es null.
     */
    public ABB(Comparator<? super T> comparador, T valor) {
        raiz = new NodoBinario<T>(valor);
        this.comparador = comparador;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertar(T elem) {
        NodoBinario<T> newNode = new NodoBinario<T>(elem);
        
        int alturaHi = raiz.getIzquierdo().getAltura();
        int alturaHd = raiz.getDerecho().getAltura();
        
        if (alturaHi == 0 && alturaHd == 0) {
            // Es hoja
            raiz.setDerecho(newNode);
        } else {
            if (alturaHi == 0 && alturaHd != 0) {
                // Tiene lugar a la izquierda
                raiz.setIzquierdo(newNode);
            } else {
                // Tiene lugar a la derecha
                raiz.setDerecho(newNode);
            }
        }
        
        int resCompare = comparador.compare(raiz.getValor(), elem);
        if (resCompare < 0) {
            // Raiz menor que el elemento a insertar
            subArbolDerecho().insertar(elem);
        } else {
            if (resCompare > 0) {
                // Raiz mayor que el elemento a insertar
                subArbolIzquierdo().insertar(elem);
            } else {
                // No inserta repetidos
                return;
            }
        }
    }


    /**
     * {@inheritDoc}
     */
    public boolean pertenece(T elem) {
        if (raiz() == null) {
            return false;
        }
        if (comparador.compare(raiz(), elem) == 0) {
            return true;
        } else {
            if (comparador.compare(raiz(), elem) < 0) {
                return subArbolDerecho().pertenece(elem);
            } else {
                return subArbolIzquierdo().pertenece(elem);
            }
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void borrar(T elem) {
        throw new UnsupportedOperationException("TODO: implementar");
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void vaciar() {
        raiz = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T raiz() {
        return raiz.getValor();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Diccionario<T> subArbolIzquierdo() {
        return (Diccionario<T>) raiz.getIzquierdo();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Diccionario<T> subArbolDerecho() {
        return (Diccionario<T>) raiz.getDerecho();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int elementos() {
        return (esVacio()) ? 0 : 1 + this.subArbolIzquierdo().elementos() + this.subArbolDerecho().elementos();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int altura() {
        return raiz.getAltura();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean esVacio() {
        return (raiz.getAltura() == 0);
    }

    /**
     * {@inheritDoc}
     */
    public T mayorValor(){
        return (raiz.getAltura() == 1 || raiz.getAltura() == 0) ? raiz.getDerecho().getValor() : this.subArbolDerecho().menorValor();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T menorValor() {
        return (raiz.getAltura() == 1 || raiz.getAltura() == 0) ? raiz.getIzquierdo().getValor() : this.subArbolIzquierdo().menorValor();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T sucesor(T elem) {
        if (pertenece(elem)) {
            return subArbolIzquierdo().raiz();
        } else {
            throw new IllegalArgumentException("El elemento no pertenece al arbol");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T predecesor(T elem) {
        if (pertenece(elem)) {
            return subArbolDerecho().raiz();
        } else {
            throw new IllegalArgumentException("El elemento no pertenece al arbol");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean repOK() {
        throw new UnsupportedOperationException("TODO: implementar");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        throw new UnsupportedOperationException("TODO: implementar");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof ABB)) {
            return false;
        }
        if (other == this) {
            return true;
        }
        ABB otroArbol = (ABB) new Object();
        if ((raiz.getValor() == otroArbol.raiz()) || (elementos() != otroArbol.elementos())) {
            return false;
        } else {
            return subArbolDerecho().equals(otroArbol) && subArbolIzquierdo().equals(otroArbol);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> aLista() {
        return aLista(Orden.INORDER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> aLista(Orden orden) {
        List<T> elementos = new LinkedList<>();
        switch (orden) {
            case INORDER:
                return aListaInOrder(raiz, elementos);
            case PREORDER:
                return aListaPreOrder(raiz, elementos);
            case POSTORDER:
                return aListaPostOrder(raiz, elementos);
        }
        return elementos;
    }

    /* (non-Javadoc)
     * Este método toma un nodo (que puede ser null), una lista de elementos (que no puede ser null)
     * y va llenando la lista con los elementos del árbol según un recorrido in order.
     * Si bien el prefil está pensando para una implementación recursiva, puede probar con una implementación iterativa.
     */
    private List<T> aListaInOrder(NodoBinario<T> raiz, List<T> elementos) {
        throw new UnsupportedOperationException("TODO: implementar");
    }

    /* (non-Javadoc)
     * Este método toma un nodo (que puede ser null), una lista de elementos (que no puede ser null)
     * y va llenando la lista con los elementos del árbol según un recorrido pre order.
     * Si bien el prefil está pensando para una implementación recursiva, puede probar con una implementación iterativa.
     */
    private List<T> aListaPreOrder(NodoBinario<T> raiz, List<T> elementos) {
        throw new UnsupportedOperationException("TODO: implementar");
    }

    /* (non-Javadoc)
     * Este método toma un nodo (que puede ser null), una lista de elementos (que no puede ser null)
     * y va llenando la lista con los elementos del árbol según un recorrido post order.
     * Si bien el prefil está pensando para una implementación recursiva, puede probar con una implementación iterativa.
     */
    private List<T> aListaPostOrder(NodoBinario<T> raiz, List<T> elementos) {
        throw new UnsupportedOperationException("TODO: implementar");
    }


}
