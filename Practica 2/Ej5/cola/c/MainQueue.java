package c;

import c.MyQueue;
import java.util.Scanner;

public class MainQueue {
    public static void main (String[] args) {
        MyQueue cola1 = new MyQueue();
        Scanner entrada = new Scanner( System.in );
        
        System.out.println("Ingrese un texto: ");
        String entradaString = entrada.nextLine( );
        
        cola1.encolar(entradaString);
        entradaString += "dos";
        cola1.encolar(entradaString);
        entradaString += "tres";
        cola1.encolar(entradaString);
        
        System.out.println(cola1.toString());
        
        System.out.println("Lets see if the Strings stored are all palindromes: \n");
        String msg;
        int j = 0;
        for (int i = 0 ; i < cola1.cantidadElementos() ; i++){
            msg = "String number " + (i+1) + " is palindrome?";
            System.out.println(msg);
            System.out.println(cola1.esPalindromo(cola1.getCadenitasPos(i+1)));
            if (cola1.esPalindromo(cola1.getCadenitasPos(i+1))) j++;
        }
        System.out.println("End of cycle. We have a total of " + j + " palindromes and " + (cola1.cantidadElementos()-j) + " not palindromes! \n");
        
    }
}
