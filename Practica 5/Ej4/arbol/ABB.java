package arbol;

import java.util.Comparator;
import java.util.List;
import java.util.LinkedList;

/**
 * Árbol binario de busqueda (ABB), es una implementación de {@code Diccionario}
 * mediante nodos encadenados que preserva las propiedades de Diccionario.
 * 
 * @param <T> Tipo del valor asociado a los nodos del árbol, debe ser posible
 *            definir un orden total para los mismos.
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
     * 
     * @param comparador define una forma de comparar los valores insertados en el
     *                   arbol.
     */
    public ABB(Comparator<? super T> comparador) {
        this.comparador = comparador;
        this.raiz = new NodoBinario<T>();
    }

    /**
     * Construye un nuevo árbol con un elemento en la raiz, ordenado acorde al
     * comparador dado.
     * 
     * @param comparador define una forma de comparar los valores insertados en el
     *                   arbol.
     * @param valor      de la raiz del nuevo arbol si no es null.
     */
    public ABB(Comparator<? super T> comparador, T valor) {
        raiz = new NodoBinario<T>(valor);
        this.comparador = comparador;
    }

    // Constructor privado para los subarboles
    private ABB(Comparator<? super T> comparador, NodoBinario<T> subArbol) {
        this.comparador = comparador;
        raiz = subArbol;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertar(T elem) {
        if (pertenece(elem))
            return; // No inserta repetidos
        raiz = insertarRec(raiz, elem);
    }

    private NodoBinario<T> insertarRec(NodoBinario<T> nodo, T elem) {
        if (nodo == null || nodo.getValor() == null) {
            nodo = new NodoBinario<T>(elem);
        }
        int resCompare = comparador.compare(nodo.getValor(), elem);
        if (resCompare > 0) {
            nodo.setIzquierdo(insertarRec(nodo.getIzquierdo(), elem));
        } else if (resCompare < 0) {
            nodo.setDerecho(insertarRec(nodo.getDerecho(), elem));
        }
        return nodo;
    }

    /**
     * {@inheritDoc}
     */
    public boolean pertenece(T elem) {
        if (esVacio()) {
            return false;
        }
        if (comparador.compare(raiz(), elem) == 0) {
            return true;
        } else {
            return (comparador.compare(raiz(), elem) < 0) ? subArbolDerecho().pertenece(elem)
                    : subArbolIzquierdo().pertenece(elem);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void borrar(T elem) {
        // No está en el árbol o es vacío. No borra nada.
        if (!pertenece(elem) || esVacio()) {
            return;
        }
        raiz = borrarRecursivo(raiz, elem);
    }

    private NodoBinario<T> borrarRecursivo(NodoBinario<T> nodo, T elem) {
        if (nodo == null)
            throw new IllegalStateException("elemento no encontrado");

        int cmp = comparador.compare(nodo.getValor(), elem);
        if (cmp > 0) {
            nodo.setIzquierdo(borrarRecursivo(nodo.getIzquierdo(), elem));
        } else if (cmp < 0) {
            nodo.setDerecho(borrarRecursivo(nodo.getDerecho(), elem));
        } else {
            if (nodo.getIzquierdo() == null) {
                return nodo.getDerecho();
            } else if (nodo.getDerecho() == null) {
                return nodo.getIzquierdo();
            }
            nodo.setValor(menorValorRecursivo(nodo.getDerecho()));
            nodo.setDerecho(borrarRecursivo(nodo.getDerecho(), nodo.getValor()));
        }
        return nodo;
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
        if (esVacio())
            throw new IllegalStateException("El árbol está vacío");
        return raiz.getValor();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Diccionario<T> subArbolIzquierdo() {
        return (raiz == null) ? new ABB<T>(comparador) : new ABB<T>(comparador, raiz.getIzquierdo());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Diccionario<T> subArbolDerecho() {
        return (raiz == null) ? new ABB<T>(comparador) : new ABB<T>(comparador, raiz.getDerecho());
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
        if (esVacio())
            return 0;
        return raiz.getAltura();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean esVacio() {
        return (raiz == null || raiz.getValor() == null);
    }

    /**
     * {@inheritDoc}
     */
    public T mayorValor() {
        return mayorValorRecursivo(raiz);
    }

    private T mayorValorRecursivo(NodoBinario<T> nodo) {
        if (nodo.getDerecho() != null) {
            return mayorValorRecursivo(nodo.getDerecho());
        }
        return nodo.getValor();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T menorValor() {
        return menorValorRecursivo(raiz);
    }

    private T menorValorRecursivo(NodoBinario<T> nodo) {
        if (nodo.getIzquierdo() != null) {
            return menorValorRecursivo(nodo.getIzquierdo());
        }
        return nodo.getValor();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T sucesor(T elem) {
        if (pertenece(elem)) {
            return subArbolDerecho().menorValor();
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
            return subArbolIzquierdo().mayorValor();
        } else {
            throw new IllegalArgumentException("El elemento no pertenece al arbol");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean repOK() {
        if (raiz == null) {
            return true;
        }
        if (raiz.getDerecho() == null) {
            if (raiz.getIzquierdo() == null) {
                return true;
            } else {
                if (comparador.compare(subArbolIzquierdo().raiz(), raiz()) < 0) {
                    return this.subArbolIzquierdo().repOK();
                } else {
                    return false;
                }
            }
        } else {
            if (raiz.getIzquierdo() == null) {
                if (comparador.compare(subArbolDerecho().raiz(), raiz()) > 0) {
                    return this.subArbolDerecho().repOK();
                } else {
                    return false;
                }
            } else {
                return this.subArbolDerecho().repOK();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String cadena = "";
        if (esVacio()) {
            cadena += "(/)";
        } else {
            cadena += this.subArbolIzquierdo().toString();
            cadena += raiz.getValor().toString();
            cadena += this.subArbolDerecho().toString();
        }
        return cadena;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof ABB)) {
            return false;
        }
        if (other == this) {
            return true;
        }
        ABB<T> otroArbol = (ABB<T>) new Object();
        if ((raiz() != otroArbol.raiz()) || (elementos() != otroArbol.elementos())) {
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

    /*
     * (non-Javadoc)
     * Este método toma un nodo (que puede ser null), una lista de elementos (que no
     * puede ser null)
     * y va llenando la lista con los elementos del árbol según un recorrido in
     * order.
     * Si bien el prefil está pensando para una implementación recursiva, puede
     * probar con una implementación iterativa.
     */
    private List<T> aListaInOrder(NodoBinario<T> raiz, List<T> elementos) {
        if (elementos == null)
            throw new IllegalArgumentException("La lista no puede ser null");
        if (raiz != null) {
            aListaInOrder(raiz.getIzquierdo(), elementos);
            elementos.add(raiz());
            aListaInOrder(raiz.getDerecho(), elementos);
        }
        return elementos;
    }

    /*
     * (non-Javadoc)
     * Este método toma un nodo (que puede ser null), una lista de elementos (que no
     * puede ser null)
     * y va llenando la lista con los elementos del árbol según un recorrido pre
     * order.
     * Si bien el prefil está pensando para una implementación recursiva, puede
     * probar con una implementación iterativa.
     */
    private List<T> aListaPreOrder(NodoBinario<T> raiz, List<T> elementos) {
        if (elementos == null)
            throw new IllegalArgumentException("La lista no puede ser null");
        if (raiz != null) {
            elementos.add(raiz());
            aListaPreOrder(raiz.getIzquierdo(), elementos);
            aListaPreOrder(raiz.getDerecho(), elementos);
        }
        return elementos;
    }

    /*
     * (non-Javadoc)
     * Este método toma un nodo (que puede ser null), una lista de elementos (que no
     * puede ser null)
     * y va llenando la lista con los elementos del árbol según un recorrido post
     * order.
     * Si bien el prefil está pensando para una implementación recursiva, puede
     * probar con una implementación iterativa.
     */
    private List<T> aListaPostOrder(NodoBinario<T> raiz, List<T> elementos) {
        if (elementos == null)
            throw new IllegalArgumentException("La lista no puede ser null");
        if (raiz != null) {
            aListaPostOrder(raiz.getIzquierdo(), elementos);
            aListaPostOrder(raiz.getDerecho(), elementos);
            elementos.add(raiz());
        }
        return elementos;
    }

    public static void main (String[] args) {
        ABB<Integer> abb1 = new ABB<Integer>(new ComparatorInteger());
        
        System.out.println(abb1.toString());
        
        System.out.println("Altura subarbol izquierdo: " + abb1.subArbolIzquierdo().altura());
        System.out.println("Altura subarbol derecho: " + abb1.subArbolDerecho().altura());

        abb1.insertar(1);
        System.out.println(abb1.toString());
        
        System.out.println("Altura subarbol izquierdo: " + abb1.subArbolIzquierdo().altura());
        System.out.println("Altura subarbol derecho: " + abb1.subArbolDerecho().altura());

        System.out.println("MayorValor()?: " + abb1.mayorValor());
        System.out.println("MenorValor()?: " + abb1.menorValor());

        abb1.insertar(2);
        System.out.println(abb1.toString());

        System.out.println("Altura subarbol izquierdo: " + abb1.subArbolIzquierdo().altura());
        System.out.println("Altura subarbol derecho: " + abb1.subArbolDerecho().altura());

        System.out.println("MayorValor()?: " + abb1.mayorValor());
        
        abb1.insertar(4);
        System.out.println(abb1.toString());

        System.out.println("Altura subarbol izquierdo: " + abb1.subArbolIzquierdo().altura());
        System.out.println("Altura subarbol derecho: " + abb1.subArbolDerecho().altura());

        System.out.println("MayorValor()?: " + abb1.mayorValor());
        
        abb1.insertar(3);
        System.out.println(abb1.toString());

        System.out.println("Altura subarbol izquierdo: " + abb1.subArbolIzquierdo().altura());
        System.out.println("Altura subarbol derecho: " + abb1.subArbolDerecho().altura());

        System.out.println("MayorValor()?: " + abb1.mayorValor());
        
        abb1.insertar(0);
        System.out.println(abb1.toString());

        System.out.println("Altura subarbol izquierdo: " + abb1.subArbolIzquierdo().altura());
        System.out.println("Altura subarbol derecho: " + abb1.subArbolDerecho().altura());

        System.out.println("MenorValor()?: " + abb1.menorValor());
        
        System.out.println("cantidad elementos: " + abb1.elementos());
        System.out.println("Pertenece 1?: " + abb1.pertenece(1));
        System.out.println("Pertenece 0?: " + abb1.pertenece(0));
        System.out.println("Pertenece 4?: " + abb1.pertenece(4));
        System.out.println("Pertenece 10?: " + abb1.pertenece(10));
        // System.out.println("sucesor: " + abb1.sucesor(0).toString());
        System.out.println("RepOk? = " + abb1.repOK());
        
        System.out.println("Borrar el 1");
        
        abb1.borrar(1);
        System.out.println(abb1.toString());
    }
}
