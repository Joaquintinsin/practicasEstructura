package main;

import clases.RationalClass;

public class MainRational {
    public static void main (String[] args){
        System.out.println("Inicio del main");
        
        System.out.print("Número primero: ");
        long mainA = 7;
        System.out.println(mainA);
        
        System.out.print("Número segundo: ");
        long mainB = 2;
        System.out.println(mainB);
        
        RationalClass rationalNumber = new RationalClass(mainA, mainB);
        System.out.println("toString del primer número racional");
        System.out.println(rationalNumber.toString());
        
        RationalClass secondRationalNumber = new RationalClass(mainA, mainB);
        System.out.println("toString del segundo número racional");
        System.out.println(secondRationalNumber.toString());
        RationalClass resResta = rationalNumber.resta(secondRationalNumber);
        System.out.print("Resultado resta: ");
        System.out.println(resResta.toString());
        
        secondRationalNumber = new RationalClass(mainA, mainB);
        RationalClass resSuma = rationalNumber.suma(secondRationalNumber);
        System.out.print("Resultado suma: ");
        System.out.println(resSuma.toString());
        
        mainA = 4;
        mainB = 14;
        RationalClass thirdRationalNumber = new RationalClass(mainA, mainB);
        
        System.out.println("toString del tercer número racional");
        System.out.println(thirdRationalNumber.toString());
        System.out.println("toString del primer número racional");
        System.out.println(rationalNumber.toString());
        
        resResta = rationalNumber.resta(thirdRationalNumber);
        System.out.print("Resultado resta: ");
        System.out.println(resResta.toString());
        
        thirdRationalNumber = new RationalClass(mainA, mainB);
        resSuma = rationalNumber.suma(thirdRationalNumber);
        System.out.print("Resultado suma: ");
        System.out.println(resSuma.toString());
        
        // System.out.println("Excepcion 1 por construir negativo");
        // RationalClass exceptOne = new RationalClass(mainA, mainA-mainB);
        
        // System.out.println("Excepcion 2 por setear negativo");
        // RationalClass exceptTwo = new RationalClass(mainA, mainB);
        // exceptTwo.setB(mainA-mainB);
        
        // System.out.println("Excepcion 3 por operar sin ser racional");
        // RationalClass exceptThree = new RationalClass(mainA, mainB);
        // exceptThree.suma(mainA);
        
        // RationalClass exceptThree = new RationalClass(mainA, mainB);
        // RationalClass exceptThreeAux = null;
        // exceptThree.suma(exceptThreeAux);
        
        return;
    }
}
