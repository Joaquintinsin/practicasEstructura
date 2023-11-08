package Ej5;

public class MainEj5 {
  public static void main (String[] args) {
    System.out.print("uno");
    ListaSobreArreglos<Integer> varMain = new ListaSobreArreglos<Integer>(20);

    varMain.insercion(1);
    varMain.insercion(2);
    varMain.insercion(3);
    varMain.insercion(0);
    varMain.insercion(-1);

  }
}
