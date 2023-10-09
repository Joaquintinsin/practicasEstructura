package c;

/**
 * MyQueue is a class created for simulate a String Queue
 * @autor Joaquín Tissera
 */

public class MyQueue {
    /**
     * Fields. Where the maximum length of the array is {@value CAP_MAX_ARREGLO}.
     */
    private static final int CAP_MAX_ARREGLO = 50;
    private String[] cadenitas;
    private int cantElem;
    
    /**
     * Principal constructor. Sets the amount of elements at 0 and obtains a new String[].
     */
    public MyQueue() {
        cadenitas = new String[CAP_MAX_ARREGLO];
        cantElem = 0;
    }
    
    /**
     * Given a position, you gets a String stored in the array.
     * <li> @param posicion the position you want to get</li>
     * <li> @return {@value null} if the queue is empty, or {@code posicion} is &lt; {@code 1} or {@code posicion} is &gt; {@code #cantidadElementos()}.
     *      otherwise, it return the String in that exact position.</li>
     * <li> @see #cantidadElementos()</li>
     */
    public String getCadenitasPos (int posicion) {
        if ( cantElem == 0 || posicion < 1 || posicion > cantElem ) return null;
        return cadenitas[posicion - 1];
    }
    
    /**
     * Gets the amount of valid elements stored in the array.
     * <li> @return how much elements the queue currently have.</li>
     * 
     */
    public int cantidadElementos () {
        return cantElem;
    }
    
    /**
     * Given a String, puts it into the queue in the last position to get out.
     * <li> @param cad the String you want to store </li>
     */
    public void encolar (String cad) {
        cadenitas[cantElem] = cad;
        cantElem++;
    }
    
    /**
     * The String that was added first of others will leave the queue.
     * After this method is called, the amount of elements decreases by one.
     */
    public void desencolar () {
        if (cantElem == 0) return;
        
        for (int i = cantElem - 1 ; i < 0 ; i--)
            cadenitas[i-1] = cadenitas[i]; 
        cantElem--;
    }
    
    /**
     * Kick off all of the currents Strings stored in the queue, returns the amount of String that has been kicked.
     * <li> @return how much Strings does the queue lost. </li>
     */
    public int vaciar () {
        int cantElem = this.cantElem;
        this.cantElem = 0;
        return cantElem;
    }
    
    /**
     * Given a String, this method gives the proper boolean if its a palindrome or not.
     * <li> @return {@code true} when the String is a palindrome. Other cases returns {@code false} (even if the String is null).</li>
     */
    public boolean esPalindromo(String cadena){
        if ( cadena == null ) return false;
        
        int i = 0, j = cadena.length()-1;
        while ( i < j ){
            if ( cadena.charAt(i) != cadena.charAt(j) )
                return false;
            i++;
            j--;
        }
        return true;
    }
    
    @Override
    public String toString () {
        String msg = "Showing the strings stored in this queue.\n";
        if (cantElem == 0){
            msg += "The queue is currently empty.\n";
            return msg;
        }
        for (int i = 0 ; i < cantElem ; i++){
            msg += "String number " + (i+1) + ": \n";
            msg += cadenitas[i];
            msg += "\n";
        }
        msg += "End of queue.\n";
        return msg;
    }
    
    @Override
    public boolean equals (Object other) {
        if (other == null || !(other instanceof MyQueue)) return false;
        MyQueue otraCola = (MyQueue) other;
        if (otraCola.cantidadElementos() != cantElem) return false;
        for (int i = 0 ; i < cantElem ; i++)
            if (cadenitas[i] != otraCola.getCadenitasPos(i)) return false;
        return true;
    }
    
}
