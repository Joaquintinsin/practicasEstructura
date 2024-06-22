package colecciones.listaArreglos;

public class MainListaArreglo {
    public static void main(String[] args) {
        Lista<Integer> varEnteros = new ImplementacionListasArreglo<Integer>();
        Lista<Integer> lista2enteros = new ImplementacionListasArreglo<Integer>();

        System.out.println(varEnteros.toString());

        boolean resAgregar = varEnteros.agregar(0);
        varEnteros.agregar(1);
        varEnteros.agregar(2);
        varEnteros.agregar(3);
        varEnteros.agregar(4);
        System.out.println("resAgregar: " + resAgregar);

        boolean resAgregarTodos = varEnteros.agregarTodos(lista2enteros);
        System.out.println("resAgregarTodos: " + resAgregarTodos);

        boolean resInsertar = varEnteros.insertar(0, 1);
        System.out.println("resInsertar: " + resInsertar);

        Integer elemEliminado = varEnteros.eliminar(0);
        System.out.println("elemEliminado: " + elemEliminado);

        System.out.println(varEnteros.toString());

        Integer elemObtenido = varEnteros.obtener(0);
        System.out.println("elemObtenido: " + elemObtenido);

        Lista<Integer> sublista = varEnteros.subLista(0, 1);
        System.out.println("sublista: " + sublista);

        boolean resContiene = varEnteros.contiene(elemObtenido);
        System.out.println("resContiene: " + resContiene);

        varEnteros.vaciar();
        System.out.println("desps de vaciar(): " + varEnteros.toString());

        int resElementos = varEnteros.elementos();
        System.out.println("resElementos: " + resElementos);

        boolean resVacia = varEnteros.esVacia();
        System.out.println("resVacia: " + resVacia);

        boolean repOk = varEnteros.repOK();
        System.out.println("repOk: " + repOk);

        boolean eq = varEnteros.equals(lista2enteros);
        System.out.println("eq: " + eq);
    }
}
