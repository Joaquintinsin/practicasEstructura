// Si no alcanza la memoria: java -Xss64m Ej1

public class Ej1 {
  public static void main (String[] args) {
    int n;

    // Primer valor de n
    n = 0;
    metodoSalida(n);

    // Segundo valor de n
    n = 5000;
    metodoSalida(n);

    // Tercer valor de n
    n = 5000 * 2;
    metodoSalida(n);

    // Cuarto valor de n
    n = 5000 * 3;
    metodoSalida(n);

    // Quinto valor de n
    n = 5000 * 4;
    metodoSalida(n);

    // Sexto valor de n
    n = 5000 * 5;
    metodoSalida(n);
    }
    
  public static void metodoSalida (int n) {
    // Inicializaciones
    long nano1 = 0;
    long nano2 = 0;
    long res = 0;

    System.out.println("Tiempo de ejecucion de suma con entrada "+ n);
    // nanoTime antes del casteo
    nano1 = System.nanoTime();
    System.out.println(nano1);
    // Casteo de suma
    suma(n);
    // nanoTime después del casteo
    nano2 = System.nanoTime();
    System.out.println(nano2);
    res = nano2 - nano1;
    System.out.println("Diferencia entre nano times "+ res);

    System.out.println("Tiempo de ejecucion de suma1 con entrada "+ n);
    // nanoTime antes del casteo
    nano1 = System.nanoTime();
    System.out.println(nano1);
    // Casteo de suma
    suma1(n);
    // nanoTime después del casteo
    nano2 = System.nanoTime();
    System.out.println(nano2);
    res = nano2 - nano1;
    System.out.println("Diferencia entre nano times "+ res);
    System.out.println();
  }

  /**
  * suma primeros n numeros .
  * @param n : ultimo numero a sumar
  * @return suma de los primeros n numeros .
  */
  public static int suma1 (int n) {
    return ( n * ( n + 1 ) / 2 );
  }

  /**
  * suma primeros n numeros .
  * @param n : ultimo numero a sumar
  * @return suma de los primeros n numeros .
  */
  public static int suma (int n) {
    if ( n==0 )
      return 0;
    return ( n + suma ( n - 1 ) );
  }
}
