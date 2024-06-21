package colecciones.cola;

import java.util.Collection;

/**
 * Implementación basada en arreglos de tamaño fijo de la interfaz {@code Cola}.
 * 
 * @see colecciones.cola.Cola
 */
public class ColaArregloFijo<T> implements Cola<T> {

	/**
	 * Capacidad máxima por defecto ({@value #CAPACIDAD_POR_DEFECTO})
	 */
	public static final int CAPACIDAD_POR_DEFECTO = 20;
	private Object[] arreglo;
	private int elementos = 0;

	/**
	 * Construye una nueva cola vacía con una capacidad máxima de
	 * {@value #CAPACIDAD_POR_DEFECTO}.
	 */
	public ColaArregloFijo() {
		arreglo = new Object[CAPACIDAD_POR_DEFECTO];
	}

	/**
	 * Construye una nueva cola vacía con una capacidad determinada mayor a 0.
	 * 
	 * @param capacidad la capacidad de la cola
	 * @throws IllegalArgumentException si {@code capacidad} es menor o igual a 0
	 */
	public ColaArregloFijo(int capacidad) {
		if (capacidad <= 0)
			throw new IllegalArgumentException("la capacidad debe ser un numero positivo (" + capacidad + ")");
		arreglo = new Object[capacidad];
	}

	/**
	 * Construye una cola a partir de elementos en una colección.
	 * Los elementos en la colección se encolan de izquierda a derecha.
	 * La capacidad de la cola va a ser suficiente para por lo menos contener todos
	 * los elementos de la colección.
	 * 
	 * @param elems los elementos a agregar a la cola
	 */
	public ColaArregloFijo(Collection<T> elems) {
		if (elems == null)
			throw new IllegalArgumentException("elems es null");
		if (elems.size() == 0) {
			arreglo = new Object[CAPACIDAD_POR_DEFECTO];
		} else {
			arreglo = new Object[elems.size()];
			for (T e : elems) {
				encolar(e);
			}
		}
	}

	/** {@inheritDoc} */
	@Override
	public boolean esVacia() {
		return (elementos == 0);
	}

	/** {@inheritDoc} */
	@Override
	public boolean encolar(T elem) {
		if (elem == null || elementos == arreglo.length)
			return false;

		if (elementos == 0) {
			arreglo[0] = elem;
		} else {
			arreglo[elementos] = elem;
		}

		elementos++;
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public T desencolar() {
		if (elementos == 0)
			throw new IllegalStateException("No se puede desencolar, la cola está vacía");

		T elementoDesencolado = primero();
		for (int i = 0; i < elementos - 1; i++) {
			arreglo[i] = arreglo[i + 1];
		}

		elementos--;
		return elementoDesencolado;
	}

	/** {@inheritDoc} */
	@Override
	public T primero() {
		if (elementos == 0)
			throw new IllegalStateException("La cola está vacía");

		return elemento(0);
	}

	/** {@inheritDoc} */
	@Override
	public void vaciar() {
		elementos = 0;
	}

	/** {@inheritDoc} */
	@Override
	public int elementos() {
		return elementos;
	}

	/** {@inheritDoc} */
	@Override
	public boolean repOK() {
		boolean preservingMethods = true;
		if (this.arreglo != null) {
			if (elementos > 0) {
				Cola<T> colaAux = new ColaArregloFijo<>();
				colaAux.encolar(primero());
				colaAux.desencolar();
				preservingMethods = colaAux.esVacia();
			}
		}

		return this.arreglo != null && preservingMethods;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		String msg = "Elementos de la cola implementada con arreglos: \n";
		if (elementos == 0) {
			msg += "La cola está vacía! \n";
			return msg;
		}

		msg += "Hay " + elementos + " elementos almacenados en la cola. \n";
		for (int i = 0; i < elementos; i++) {
			msg += "Elemento número " + (i + 1) + ": \n";
			msg += String.valueOf(elemento(i));
			msg += "\n";
		}
		return msg;
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object other) {
		if (other == null || !(other instanceof ColaArregloFijo))
			return false;

		ColaArregloFijo<T> otraCola = new ColaArregloFijo<>();
		otraCola = (ColaArregloFijo<T>) other;
		if (otraCola.elementos() != elementos)
			return false;

		if (otraCola == this)
			return true;

		for (int i = elementos - 1; i < 0; i++)
			if (elemento(i) != otraCola.elemento(i))
				return false;
		return true;
	}

	/**
	 * Permite obtener un elemento del arreglo en un indice determinado realizando
	 * el casteo necesario.
	 * 
	 * @param index el indice del elemento a obtener
	 */
	@SuppressWarnings("unchecked")
	private T elemento(int index) {
		return (T) arreglo[index];
	}

	/*
	 * Ejercicio 5, b)
	 * Nueva implementación: esPalindromo dice si una String es Palindromo o no
	 */
	public boolean esPalindromo(String cadena) {
		if (cadena == null)
			throw new IllegalStateException("La String pasada es null");

		int i = 0, j = cadena.length() - 1;
		while (i < j) {
			if (cadena.charAt(i) != cadena.charAt(j))
				return false;
			i++;
			j--;
		}
		return true;
	}
}
