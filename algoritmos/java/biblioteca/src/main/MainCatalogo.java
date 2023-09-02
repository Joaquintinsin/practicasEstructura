package main;

import datos.Libro;
import catalogo.Catalogo;

/**
* La clase {@code MainCatalogo} implementa una aplicación simple para mostrar el uso de {@code Catalogo} junto con {@code Libro}.
* Esta clase no usa parámetros, el comportamiento está completamente "hardcodeado" requiriendo modificiar y recompilar para hacer pruebas.
* @see catalogo.Catalogo
* @see datos.Libro
* @version 1.0
*/
public class MainCatalogo {

	/**
	* Experimenta con {@code Catalogo}, agregar libros, buscar, y mostrar el {@code Catalogo}.
	* @param args : arguments for this main method, not used in this implementation.
	*/
	public static void main(String[] args) {
		Libro libro1 = new Libro("Isaac Asimov", "The Caves of Steel", 42);
		Libro libro2 = new Libro("Isaac Asimov", "The Naked Sun", 47);
		//TODO: agregue más libros
		
		Catalogo catalogo = new Catalogo(10);
		//TODO: pruebe los métodos agregarLibro y buscarPorTitulo
		System.out.println("Catalogo:\n" + catalogo);
		
		if (catalogo.agregarLibro(libro1))
			System.out.println("Agregado con exito \n");
		else
			System.out.println("No se pudo agregar \n");
		
		System.out.println("Catalogo:\n" + catalogo);
		
		if (catalogo.agregarLibro(libro2))
			System.out.println("Agregado con exito \n");
		else
			System.out.println("No se pudo agregar \n");
		
		System.out.println("Catalogo:\n" + catalogo);
		
		Libro lookingForLibro = catalogo.buscarPorTitulo("Diccionario");
		if (lookingForLibro == null)
			System.out.println("No se encontró el libro \n");
		else
			System.out.println(lookingForLibro);
		
		Libro libro4 = new Libro("Joaquin", "Diccionario", 126);
		if (catalogo.agregarLibro(libro4))
			System.out.println("Agregado con exito \n");
		else
			System.out.println("No se pudo agregar \n");
		
		lookingForLibro = catalogo.buscarPorTitulo("Diccionario");
		
		if (lookingForLibro == null)
			System.out.println("No se encontró el libro \n");
		else
			System.out.println(lookingForLibro);
		
		boolean varLogica = (libro4 == lookingForLibro);
		System.out.println(varLogica);
		
	} 

}
