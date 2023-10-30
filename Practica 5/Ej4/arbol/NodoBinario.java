package arbol;

/**
 * Clase auxiliar para diccionarios implementados con nodos encadenados.
 */
public class NodoBinario<T>  {
    private T valor;
    private NodoBinario<T> izquierdo;
    private NodoBinario<T> derecho;
    private int altura;


    NodoBinario(T valor, NodoBinario<T> izquierdo, NodoBinario<T> derecho) {
        this.valor = valor;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
        this.altura = 2;
    }

    public NodoBinario(){
        this.valor = null;
        this.izquierdo = null;
        this.derecho = null;
        this.altura = 0;
    }

    public NodoBinario(T valor){
        this.valor = valor;
        this.izquierdo = new NodoBinario<T>();
        this.derecho = new NodoBinario<T>();
        this.altura = 1;
    }

    T getValor() {
        return valor;
    }

    void setValor(T nuevoValor) {
        valor = nuevoValor;
    }

    NodoBinario<T> getIzquierdo() {
        return izquierdo;
    }

    void setIzquierdo(NodoBinario<T> nuevoIzquierdo) {
        izquierdo = nuevoIzquierdo;
    }

    NodoBinario<T> getDerecho() {
        return derecho;
    }

    void setDerecho(NodoBinario<T> nuevoDerecho) {
        derecho = nuevoDerecho;
    }

    public int getAltura() {
        if (valor == null) 
            return 0;
        return 1 + maxAltura(izquierdo.getAltura(), derecho.getAltura());
    }

    private int maxAltura (int alturaIzq, int alturaDer) {
        return (alturaIzq >= alturaDer) ? alturaIzq : alturaDer;
    }
    public void setAltura(int altura) {
        this.altura = altura;
    }
}
