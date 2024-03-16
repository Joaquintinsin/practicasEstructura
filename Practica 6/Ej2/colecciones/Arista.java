package tpgrafos.colecciones;

public class Arista {
  /**
   * Primer vertice de una arista.
   */
  private final Vertice primero;
  /**
   * Segundo vertice de una arista
   */
  private final Vertice segundo;

  /**
   * Obtiene el primer vertice de una arista.
   * @return el primer vertice de una arista.
   */
  public Vertice getPrimero() {
    return primero;
  }

  /**
   * Obtiene el segundo vertice de una arista.
   * @return el segundo vertice de una arista.
   */
  public Vertice getSegundo() {
    return segundo;
  }

  /**
   * Constructor de una arista.
   * @param primero Primer vertice de la arista.
   * @param segundo Segundo vertice de la arista.
   */
  public Arista(Vertice primero, Vertice segundo) {
    this.primero = primero;
    this.segundo = segundo;
  }

  /**
   * Para el hashCode de la arista, suma los hashCode de sus vertices.
   * @return valor de hash para esta arista.
   */
  @Override
  public int hashCode() {
    int resultadoHashCode = primero.hashCode() + segundo.hashCode();
    return resultadoHashCode;
  }

  /**
   * Compara dos aristas, retorna true si ambas son iguales, sino False.
   * @param obj a comparar con esta arista.
   * @return true si el objeto especificado es igual a esta arista.
   */
  @Override
  public boolean equals(Object obj) {
    // retorna falso si el objeto dado es null o no es una instancia de Arista.
    if (obj == null || !(obj instanceof Arista)) {
      return false;
    }
    // Casteo el objeto una vez que me aseguro que es una arista
    Arista otraArista = (Arista) obj;

    // si son exactamente los mismos objetos
    if (this == otraArista) {
      return true;
    }
    // Sino, veo que las aristas vayan en el mismo sentido de vertice a vertice.
    boolean resArist1 = this.getPrimero().equals(otraArista.getPrimero());
    boolean resArist2 = this.getSegundo().equals(otraArista.getSegundo());
    boolean resEquals1 = (resArist1 && resArist2);

    boolean resArist11 = this.getPrimero().equals(otraArista.getSegundo());
    boolean resArist22 = this.getSegundo().equals(otraArista.getPrimero());
    boolean resEquals2 = (resArist11 && resArist22);

    return resEquals1 || resEquals2; // es la misma arista tanto si va o vuelve.
  }

  /**
   * Dada una arista, muestra como se relacionan sus vertices.
   * @return Representacion de la arista en forma de String.
   */
  @Override
  public String toString() {
    String relacion = "->";
    String vert1 = "{" + primero.getId().toString() + "}";
    String vert2 = "{" + segundo.getId().toString() + "}";
    //retorna la relacion
    return vert1 + relacion + vert2;
  }
}
