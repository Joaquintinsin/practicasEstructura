package cola.b;

import java.util.Scanner;
import cola.ColaArregloFijo;

public class Palindromo {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Entrada 1: ");
        // amorazulrameradetodotedimariposacolosalsiyodetodotedipodalarosavenuselatomocomotalesunevasoraladopidetodotedoyislasolocasopiramidetodotedaremarluzaroma
        String entradaString = entrada.nextLine();

        ColaArregloFijo<String> colaStrings = new ColaArregloFijo<>(50);
        colaStrings.encolar(entradaString);

        boolean logicValue = colaStrings.esPalindromo(colaStrings.desencolar());

        System.out.println("Resultado esPalindromo: " + logicValue);
        entrada.close();
    }
}
