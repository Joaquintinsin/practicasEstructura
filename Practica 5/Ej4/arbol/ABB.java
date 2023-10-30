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
        this.raiz = new NodoBinario<T>();
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

    private ABB(Comparator<? super T> comparador, NodoBinario<T> subArbol) {
        this.comparador = comparador;
        raiz = subArbol;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void insertar(T elem) {
        NodoBinario<T> newNode = new NodoBinario<T>(elem);
        if (esVacio()) {
            this.raiz = newNode;
            return;
        }
        
        int resCompare = comparador.compare(raiz(), elem);
        if (resCompare == 0) return;    // No inserta repetidos
        
        int alturaHi = raiz.getIzquierdo().getAltura();
        int alturaHd = raiz.getDerecho().getAltura();
        // Es hoja
        if (alturaHi == 0 && alturaHd == 0) {
            if (resCompare < 0)
                raiz.setDerecho(newNode);
            else
                raiz.setIzquierdo(newNode);
            return;
        }
        // Tiene lugar a la izquierda
        if (alturaHi == 0 && alturaHd != 0) {
            if (resCompare < 0)
                subArbolDerecho().insertar(elem);
            else
                raiz.setIzquierdo(newNode);
            return;
        }
        // Tiene lugar a la derecha
        if (alturaHi != 0 && alturaHd == 0) {
            if (resCompare < 0)
                raiz.setDerecho(newNode);
            else
                subArbolIzquierdo().insertar(elem);
            return;
        }
        if (alturaHi != 0 && alturaHd != 0) {
            if (resCompare < 0) {
                // Raiz menor que el elemento a insertar
                subArbolDerecho().insertar(elem);
            } else {
                // Raiz mayor que el elemento a insertar
                subArbolIzquierdo().insertar(elem);
            }
        }
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
            return (comparador.compare(raiz(), elem) < 0) ? subArbolDerecho().pertenece(elem) : subArbolIzquierdo().pertenece(elem);
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
        
        int resCompare = comparador.compare(raiz.getValor(), elem);
        // Encontró el que quería borrar.
        if (resCompare == 0) {
            int alturaHi = raiz.getIzquierdo().getAltura();
            int alturaHd = raiz.getDerecho().getAltura();
            
            T valorRaizDerecha = raiz.getDerecho().getValor();
            T valorRaizIzquierda = raiz.getIzquierdo().getValor();
            
            // Es hoja: directamente elimino.
            if (alturaHi == 0 && alturaHd == 0) {
                raiz.setValor(null);
            }
            // El nodo tiene solo hijo derecho: lo intercambio y lo elimino como una hoja.
            if (alturaHi == 0 && alturaHd == 1) {
                raiz.setValor(valorRaizDerecha);
                raiz.setDerecho(null);
            }
            // El nodo tiene solo hijo izquierdo: lo intercambio y lo elimino como una hoja.
            if (alturaHi == 1 && alturaHd == 0) {
                raiz.setValor(valorRaizIzquierda);
                raiz.setIzquierdo(null);
            }
            // El nodo tiene los dos hijos: 
            if (alturaHi != 1 && alturaHd != 1) {
                raiz.setValor(sucesor(raiz()));
                this.subArbolDerecho().borrar(raiz());
            }
            raiz.setAltura(raiz.getAltura() - 1);
            return;
        } else { // Busca el que quiere borrar
            // La raíz es más chica que el elemento a borrar: busco en los hijos derechos.
            if (resCompare < 0) {
                subArbolDerecho().borrar(elem);
            }
            // La raíz es más grande que el elemento a borrar: busco en los hijos izquierdos.
            if (resCompare > 0) {
                subArbolIzquierdo().borrar(elem);
            }
        }
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
        return new ABB<T>(comparador, raiz.getIzquierdo());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Diccionario<T> subArbolDerecho() {
        return new ABB<T>(comparador, raiz.getDerecho());
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
        return (raiz == null || raiz.getValor() == null);
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
            if (subArbolDerecho() == null) {
                return raiz();
            } else {
                return subArbolIzquierdo().sucesor(raiz());
            }
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
            return (subArbolIzquierdo() == null) ? raiz() : subArbolDerecho().sucesor(raiz());
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
    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof ABB)) {
            return false;
        }
        if (other == this) {
            return true;
        }
        ABB otroArbol = (ABB) new Object();
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

    /* (non-Javadoc)
     * Este método toma un nodo (que puede ser null), una lista de elementos (que no puede ser null)
     * y va llenando la lista con los elementos del árbol según un recorrido in order.
     * Si bien el prefil está pensando para una implementación recursiva, puede probar con una implementación iterativa.
     */
    private List<T> aListaInOrder(NodoBinario<T> raiz, List<T> elementos) {
        if (elementos == null) throw new IllegalArgumentException("La lista no puede ser null");
        
        aListaInOrder(raiz.getIzquierdo(), elementos);
        elementos.add(raiz());
        aListaInOrder(raiz.getDerecho(), elementos);
        
        return elementos;
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

    public static void main (String[] args) {
        ABB<Integer> abb1 = new ABB<Integer>(new ComparatorInteger());
        
        System.out.println(abb1.toString());
        
        abb1.insertar(1);
        System.out.println(abb1.toString());
        
        abb1.insertar(2);
        System.out.println(abb1.toString());
        
        abb1.insertar(4);
        System.out.println(abb1.toString());
        
        abb1.insertar(3);
        System.out.println(abb1.toString());
        
        abb1.insertar(0);
        System.out.println(abb1.toString());
        
        System.out.println("Borrar el 1");
        
        abb1.borrar(1);
        System.out.println(abb1.toString());
    }

}
