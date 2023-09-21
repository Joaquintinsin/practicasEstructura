import Ej6.pilaGen.PilaGen;

public class MainPila {
    public static void main (String[] args) {
        PilaGen<Integer> secPrincipal = new PilaGen<Integer>();
        
        secPrincipal.apilar(1);
        secPrincipal.apilar(2);
        secPrincipal.apilar(3);
        secPrincipal.apilar(4);
        
        secPrincipal.imprimirOrdenInverso();
    }
}
