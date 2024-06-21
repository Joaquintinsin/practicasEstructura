package cola;

public class MainColas {
    public static void main (String[] args) {
        System.out.println("Inicio main");
        
        // Cola de enteros
        ColaArregloFijo<Integer> cola = new ColaArregloFijo<>(10);
        
        System.out.print(cola.toString());
        
        Integer uno = 1;
        Integer dos = 2;
        Integer tres = 3;
        
        cola.encolar(uno);
        cola.encolar(dos);
        cola.encolar(tres);
        
        System.out.print(cola.toString());
        
        Integer rescatadoDesencolar = cola.desencolar();
        
        System.out.println("Elem desencolado: \n" + rescatadoDesencolar);
        
        System.out.print(cola.toString());
        
        // Cola de cadenas
        ColaArregloFijo<String> colaStrings = new ColaArregloFijo<>(50);
        System.out.println("Inicio cola de Strings");
        System.out.print(colaStrings.toString());
        
        String one = "One";
        String two = "Two";
        String three = "Three";
        
        colaStrings.encolar(one);
        colaStrings.encolar(two);
        colaStrings.encolar(three);
        
        System.out.print(colaStrings.toString());
        
        String resStringDesencolado = colaStrings.desencolar();
        
        System.out.println("\nElem desencolado: \n" + resStringDesencolado);
        
        System.out.print(colaStrings.toString());
        
        System.out.println("Fin main");
    }
}
