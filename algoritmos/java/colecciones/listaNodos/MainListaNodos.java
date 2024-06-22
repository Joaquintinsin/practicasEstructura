package colecciones.listaNodos;

public class MainListaNodos {

    public static void main(String[] args) {
        ListaNodos<Integer> lista = new ListaNodos<>();

        // Agregar elementos a la lista
        lista.agregar(10);
        lista.agregar(20);
        lista.agregar(30);

        // Imprimir la lista original
        System.out.println("Lista original:");
        System.out.println(lista);

        // Insertar un elemento en una posición específica
        lista.insertar(15, 1);
        System.out.println("Lista después de insertar 15 en la posición 1:");
        System.out.println(lista);

        // Eliminar un elemento en una posición específica
        lista.eliminar(2);
        System.out.println("Lista después de eliminar el elemento en la posición 2:");
        System.out.println(lista);

        // Obtener un elemento en una posición específica
        Integer elementoObtenido = lista.obtener(1);
        System.out.println("Elemento obtenido en la posición 1: " + elementoObtenido + "\n");

        // Crear una sublista entre los índices 1 (inclusivo) y 3 (exclusivo)
        Lista<Integer> subLista = lista.subLista(1, 3);
        System.out.println("Sublista entre los índices 1 (inclusive) y 3 (exclusive):");
        System.out.println(subLista);

        // Verificar si la lista contiene ciertos elementos
        System.out.println("¿La lista contiene el número 20? " + lista.contiene(20) + "\n");
        System.out.println("¿La lista contiene el número 40? " + lista.contiene(40) + "\n");

        // Probar el método equals comparando con una lista igual
        ListaNodos<Integer> otraLista = new ListaNodos<>();
        otraLista.agregar(10);
        otraLista.agregar(15);
        otraLista.agregar(30);

        System.out.println("¿La lista es igual a otraLista? " + lista.equals(otraLista) + "\n");

        // Vaciar la lista y verificar si está vacía
        lista.vaciar();
        System.out.println("Lista después de vaciarla:");
        System.out.println(lista);
        System.out.println("¿La lista está vacía? " + lista.esVacia() + "\n");
    }
}
