package colecciones.listaArreglos;

public class ImplementacionListasArreglo<T> implements Lista<T> {
    public static final int CAP_MAX_ARREGLO = 100;
    private T[] arr;
    private int cantElem;
    private boolean instance = false; // Flag de instanciamiento, para el repOK

    @SuppressWarnings("unchecked")
    public ImplementacionListasArreglo() {
        arr = (T[]) new Object[CAP_MAX_ARREGLO];
        cantElem = 0;
        instance = true;
    }

    @SuppressWarnings("unchecked")
    public ImplementacionListasArreglo(int cantidad) {
        arr = (T[]) new Object[cantidad];
        cantElem = 0;
        instance = true;
    }

    /** {@inheritDoc} */
    public boolean agregar(T elem) {
        // no quiero que venga null ni que venga este mismo objeto
        if (elem == null || elem == this || cantElem >= CAP_MAX_ARREGLO - 1)
            return false;
        arr[cantElem] = elem;
        cantElem++;
        return true;
    }

    /** {@inheritDoc} */
    public boolean agregarTodos(Lista<T> otraLista) {
        // Si esta lista ya esta llena, no puede agregar mas nada
        if (arr.length == cantElem)
            return false;
        // Si la cantidad de elementos de las dos listas supera la capacidad maxima
        // actual, no puede agregar mas nada
        if (cantElem + otraLista.elementos() >= arr.length)
            return false;

        // Agrega al final de este arreglo hasta que se llene o hasta terminar los
        // elementos de la otra lista. Lo que ocurra primero
        for (int i = 0; cantElem < arr.length && i < otraLista.elementos(); i++) {
            arr[cantElem] = otraLista.obtener(i);
            cantElem++;
        }

        // Devuelve la comparacion de:
        // Sumar la cantidad de elementos de las dos listas, si supero el arr.length
        // entonces es false (no pudo agregar todos)
        // O si no lo supero entonces pudo agregarlos a todos y devuelve verdadero.
        return (cantElem + otraLista.elementos()) <= arr.length;
    }

    /** {@inheritDoc} */
    public boolean insertar(T elem, int indice) {
        if (indice < 0 || indice >= cantElem)
            throw new IndexOutOfBoundsException("Indice fuera de rango");

        if (arr.length == cantElem)
            return false;

        for (int i = cantElem - 1; i > indice; i--) {
            arr[i] = arr[i - 1];
        }
        arr[indice] = elem;
        cantElem++;
        return true;
    }

    /** {@inheritDoc} */
    public T eliminar(int indice) {
        if (indice < 0 || indice >= cantElem)
            throw new IndexOutOfBoundsException("Indice fuera de rango");
        T rescueElem = arr[indice];

        for (int i = indice; i < cantElem - 1; i++) {
            arr[i] = arr[i + 1];
        }

        arr[cantElem - 1] = null;
        cantElem--;
        return rescueElem;
    }

    /** {@inheritDoc} */
    public T obtener(int indice) {
        return arr[indice];
    }

    /** {@inheritDoc} */
    public Lista<T> subLista(int desdeInd, int hastaInd) {
        if (desdeInd < 0 || hastaInd > cantElem || desdeInd > hastaInd)
            throw new IndexOutOfBoundsException("Indice fuera de rango");

        Lista<T> listaResultado = new ImplementacionListasArreglo<>();
        if (desdeInd == hastaInd || esVacia())
            return listaResultado;

        while (desdeInd < hastaInd) {
            listaResultado.agregar(arr[desdeInd]);
            desdeInd++;
        }

        return listaResultado;
    }

    /** {@inheritDoc} */
    public boolean contiene(T elem) {
        for (int i = 0; i < cantElem; i++) {
            if (arr[i] == elem)
                return true;
        }
        return false;
    }

    /** {@inheritDoc} */
    public void vaciar() {
        cantElem = 0;
    }

    /** {@inheritDoc} */
    public int elementos() {
        return cantElem;
    }

    /** {@inheritDoc} */
    public boolean esVacia() {
        return (cantElem == 0);
    }

    /** {@inheritDoc} */
    public boolean repOK() {
        // Si fue instanciado, la cantElem == arr.length. Si no, cumple con el repOK.
        return (instance) ? cantElem == arr.length : true;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        if (esVacia())
            return "La lista esta vacia";

        String msg = "Elementos de la lista implementada con arreglos: \n";
        msg += "Hay " + cantElem + " elementos almacenados en la lista. \n";
        for (int i = 0; i < cantElem; i++) {
            msg += "Elemento número " + (i + 1) + ": ";
            msg += String.valueOf(arr[i]) + "\n";
        }
        return msg;
    }

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object otro) {
        if (otro == null || !(otro instanceof ImplementacionListasArreglo))
            return false;

        ImplementacionListasArreglo<T> otraLista = (ImplementacionListasArreglo<T>) otro;
        if (otraLista.elementos() != cantElem)
            return false;

        boolean logicAux = true;
        for (int i = 0; i < otraLista.elementos(); i++)
            logicAux = logicAux && (arr[i] == otraLista.obtener(i));

        return logicAux;
    }
}
