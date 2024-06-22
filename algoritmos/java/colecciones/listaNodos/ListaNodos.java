package colecciones.listaNodos;

public class ListaNodos<T> implements Lista<T> {
    public static final int CAP_MAX_NODOS = 20;
    private int cantElem;
    private T info;
    private ListaNodos<T> next;

    public ListaNodos() {
        this.cantElem = 0;
        this.info = null;
        this.next = null;
    }

    /** {@inheritDoc} */
    public boolean agregar(T elem) {
        if (elem == null || elem == this || cantElem >= CAP_MAX_NODOS)
            return false;

        ListaNodos<T> nuevoNodo = new ListaNodos<>();
        nuevoNodo.setInfo(elem);

        if (esVacia()) {
            this.info = elem;
        } else {
            ListaNodos<T> actual = this;
            while (actual.getNext() != null) {
                actual = actual.getNext();
            }
            actual.setNext(nuevoNodo);
        }

        this.cantElem++;
        return true;
    }

    private void setInfo(T elem) {
        this.info = elem;
    }

    public T getInfo() {
        return info;
    }

    private void setNext(ListaNodos<T> nodo) {
        this.next = nodo;
    }

    public ListaNodos<T> getNext() {
        return next;
    }

    /** {@inheritDoc} */
    public boolean agregarTodos(Lista<T> otraLista) {
        if (otraLista == null || otraLista == this)
            return false;
        if (cantElem + otraLista.elementos() >= CAP_MAX_NODOS)
            return false;
        for (int i = 0; i < otraLista.elementos(); i++) {
            this.agregar(otraLista.obtener(i));
        }

        return true;
    }

    /** {@inheritDoc} */
    public boolean insertar(T elem, int indice) {
        if (indice < 0 || indice > cantElem || cantElem == CAP_MAX_NODOS)
            return false;

        ListaNodos<T> nuevoNodo = new ListaNodos<>();
        nuevoNodo.setInfo(elem);
        if (indice == 0) {
            nuevoNodo.setNext(this);
            this.info = elem;
        } else {
            ListaNodos<T> actual = this;
            for (int i = 0; i < indice - 1; i++) {
                actual = actual.getNext();
            }
            nuevoNodo.setNext(actual.getNext());
            actual.setNext(nuevoNodo);
        }
        this.cantElem++;
        return true;
    }

    /** {@inheritDoc} */
    public T eliminar(int indice) {
        if (esVacia() || indice < 0 || indice >= cantElem)
            throw new IllegalArgumentException("Eliminar: Indice invalido o lista vacia");

        T elemRescatado;
        if (indice == 0) {
            // Si se desea eliminar el primer elemento, rescato desde this.info
            elemRescatado = this.info;
            // Pregunto si hay nodos mas adelante o no.
            // Si los hay entonces tomo justo el siguiente y lo pongo como primero
            // Si no hay entonces directamente elimino poniendo en null
            if (this.next != null) {
                this.info = this.next.getInfo();
                this.next = this.next.getNext();
            } else {
                this.info = null;
                this.next = null;
            }
        } else {
            // Recorro secuencialmente hasta el indice - 1
            ListaNodos<T> actual = this;
            for (int i = 0; i < indice - 1; i++) {
                actual = actual.getNext();
            }
            // Estoy parado justo antes del que quiero eliminar, por lo que nodoAEliminar lo
            // pongo en el que sigue
            // Rescato el info en elemRescatado
            // Hago el puentecito esquivando nodoAEliminar y luego le seteo null para
            // eliminarlo
            ListaNodos<T> nodoAEliminar = actual.getNext();
            elemRescatado = nodoAEliminar.getInfo();
            actual.setNext(nodoAEliminar.getNext());
            nodoAEliminar.setNext(null);
            nodoAEliminar.setInfo(null);
        }
        cantElem--;
        return elemRescatado;
    }

    /** {@inheritDoc} */
    public T obtener(int indice) {
        if (indice < 0 || indice >= cantElem)
            throw new IndexOutOfBoundsException("Obtener: Indice invalido");

        ListaNodos<T> actual = this;
        int i = 0;
        while (i < indice) {
            actual = actual.getNext();
            i++;
        }
        return actual.getInfo();
    }

    /** {@inheritDoc} */
    public Lista<T> subLista(int desdeInd, int hastaInd) {
        if (desdeInd < 0 || hastaInd > cantElem || desdeInd > hastaInd)
            throw new IndexOutOfBoundsException("subLista: Indices invalidos");

        ListaNodos<T> listaResultante = new ListaNodos<>();
        ListaNodos<T> actual = this;
        for (int i = 0; i < desdeInd; i++) {
            actual = actual.getNext();
        }
        for (int i = desdeInd; i < hastaInd; i++) {
            listaResultante.agregar(actual.getInfo());
            actual = actual.getNext();
        }
        return listaResultante;
    }

    /** {@inheritDoc} */
    public boolean contiene(T elem) {
        ListaNodos<T> actual = this;
        for (int i = 0; i < cantElem; i++) {
            if (actual.getInfo() == null) {
                if (elem == null)
                    return true;
            } else {
                if (actual.getInfo().equals(elem))
                    return true;
            }
            actual = actual.getNext();
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
        return (next != this && cantElem <= CAP_MAX_NODOS);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        if (esVacia())
            return "La lista esta vacia";
        ListaNodos<T> actual = this;
        String msg = "Mostrando la lista de nodos\n";
        msg += "Hay un total de " + cantElem + " elementos en la lista\n";
        int i = 1;
        while (actual != null) {
            msg += "Elemento nro " + i + ": ";
            msg += String.valueOf(actual.getInfo().toString()) + "\n";
            actual = actual.getNext();
            i++;
        }
        return msg;
    }

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object otro) {
        if (otro == this)
            return true;
        if (otro == null || getClass() != otro.getClass())
            return false;

        ListaNodos<T> otraLista = (ListaNodos<T>) otro;
        if (cantElem != otraLista.elementos())
            return false;

        ListaNodos<T> estaLista = this;
        ListaNodos<T> listaEntrante = otraLista;

        while (estaLista != null && listaEntrante != null) {
            T thisElem = estaLista.getInfo();
            T otroElem = listaEntrante.getInfo();
            if (thisElem == null) {
                if (otroElem != null)
                    return false;
            } else {
                if (!thisElem.equals(otroElem))
                    return false;
            }
            estaLista = estaLista.getNext();
            listaEntrante = listaEntrante.getNext();
        }
        return true;
    }
}
