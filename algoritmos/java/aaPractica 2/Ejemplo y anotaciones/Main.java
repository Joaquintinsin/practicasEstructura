import colecciones.pila;
// importar sólo lo que estamos usando

// comentario de linea
/* comentario de lineas
 */
/**
 * java doc lenguaje demarcado, hay parsel de palabras
 */

public class Main {
	public static void main(String[] args) {
		int numero = 0;
		boolean verdadero = true;
		boolean falso = false;
		System.out.println(numero);
		System.out.println(verdadero);
        
        Pila p = new PilaEncadenada();
        p.Apilar(3);
        System.out.println(p);
        int tope = p.tope();
	}
}
