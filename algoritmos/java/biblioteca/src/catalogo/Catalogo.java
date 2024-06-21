package catalogo;

import datos.Libro;

/**
 * La clase {@code Catalogo} representa un catálogo de libros ({@code Libro})
 * implementado sobre un arreglo.
 * Los métodos principales ofrecidos por esta clase son:
 * <ul>
 * <li>Agregar un nuevo Libro al catálogo.</li>
 * <li>Buscar un Libro por su título</li>
 * </ul>
 * Esta implementación no realiza ningún tipo de verificación, específicamente:
 * <ul>
 * <li>No verifica que un Libro agregado sea no nulo ({@code null}).</li>
 * <li>No verifica que la capacidad para crear un nuevo Catalogo sea mayor o
 * igual a {@code 1}.</li>
 * </ul>
 * 
 * @see Libro
 * @version 1.0
 */
public class Catalogo {

	/**
	 * La capacidad por defecto de un nuevo Catalogo.
	 */
	public static final int CAPACIDAD_POR_DEFECTO = 50;

	// el arreglo interno de libros.
	private Libro[] libros;
	// la cantidad actual de libros almacenados.
	private int nroLibros;

	/**
	 * Construye un nuevo {@code Catalogo} usando una capacidad de
	 * {@value #CAPACIDAD_POR_DEFECTO}.
	 */
	public Catalogo() {
		this(CAPACIDAD_POR_DEFECTO);
		// forwarder constructor? llama a un constructor definido con ese parametro
	}

	/**
	 * Construye un nuevo {@code Catalogo} con una capacidad dada.
	 * 
	 * @param capacidad : la capacidad del nuevo {@code Catalogo}.
	 */
	public Catalogo(int capacidad) {
		libros = new Libro[capacidad];
		nroLibros = 0;
	}

	/**
	 * Retorna {@code true} si este {@code Catalogo} no tiene más capacidad para
	 * almacenar libros.
	 * 
	 * @return {@code true} sii no hay capacidad disponible.
	 */
	public boolean estaLleno() {
		// throw new UnsupportedOperationException("Debe implementar este método");
		return nroLibros == libros.length;
	}

	/**
	 * Agrega un nuevo {@code Libro} a este {@code Catalogo} si el mismo no se
	 * encuentra lleno.
	 * 
	 * @param libro : el nuevo {@code Libro} a agregar.
	 * @return {@code true} sii el {@code Libro} pudo ser agregado, es decir,
	 *         {@code estaLleno()} retornó {@code false} previamente a llamar a este
	 *         método.
	 * @see #estaLleno()
	 */
	public boolean agregarLibro(Libro libro) {
		// throw new UnsupportedOperationException("Debe implementar este método (el
		// Libro debe ser agregado al final)");
		if (libro == null || !(libro instanceof Libro))
			return false;
		if (estaLleno())
			return false;

		libros[nroLibros] = libro;
		nroLibros++;
		return true;
	}

	/**
	 * Busca un {@code Libro} de acuerdo a un título dado.
	 * 
	 * @param titulo : el título del {@code Libro} a buscar.
	 * @return un libro {@code l} que pertenece a este {@code Catalogo} sii
	 *         {@code l.titulo().equals(titulo)}, {@code null} en caso contrario.
	 */
	public Libro buscarPorTitulo(String titulo) {
		// throw new UnsupportedOperationException("Debe implementar este método");
		if (nroLibros == 0)
			return null;

		// devuelve el que aparezca primero
		for (int i = 0; i < nroLibros; i++) {
			if (libros[i].titulo() == titulo)
				return libros[i];
		}

		// no encontró títulos iguales, devuelve null
		return null;
	}

	@Override
	public String toString() {
		// throw new UnsupportedOperationException("Debe implementar este método (debe
		// usar el método toString() de Libro)");
		String mensaje = "This is a Catalogo. You can save Libros in here and they'll be saved in an array.\n";
		if (nroLibros == 0) {
			mensaje += "There's no Libros stored here, so the Catalogo is empty. \n";
			return mensaje;
		} else {
			mensaje += "Let me display the Libros stored here: \n";
			for (int i = 0; i < nroLibros; i++) {
				mensaje += "\nLibro number " + (i + 1) + "\n";
				mensaje += libros[i].toString();
			}
			return mensaje;
		}
	}
}
