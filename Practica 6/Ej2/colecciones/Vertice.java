package tpgrafos.colecciones;

import java.util.List;
import java.util.ArrayList;

/**
 *  Representa un nodo en un grafo.
 */
public class Vertice {
  /**
   * identificador del vertice.
   */
  private final Integer id;

  /**
   * lista de vertices adyacentes a this.
   */
  private List<Vertice> adyacentes;

  /**
   * Obtiene el identificador de vertice.
   * @return el identificador del vertice.
   */
  public Integer getId() {
    return id;
  }

  /**
   * Obtiene la lista de vertices adyacentes a este.
   * @return lista de vertices adyacentes a este.
   */
  public List<Vertice> getAdyacentes() {
    return adyacentes;
  }

  /**
   * Construye un vertice con el identificador pasado como parametro.
   * @param id identificador de vertice.
   */
  public Vertice(Integer id) {
    this.id = id;
    adyacentes = new ArrayList<Vertice>();
  }

  /**
   * Conecta este vertice al vertice especificado.
   * @param v Vertice a unir con this.
   * @return {@code true} sii la conexión con el vértice especificado fue agregada.
   */
  public boolean agregarAdyacente(Vertice v) {
    if (adyacentes == null) {
      adyacentes = new ArrayList<Vertice>();
    }
    if (!adyacentes.contains(v)) {
      adyacentes.add(v);
      return true;
    }
    return false;
  }

  /**
   * desconecta este vertice del vertice especificado.
   * @param v Vertice a desconectar.
   * @return {@code true} sii la conexión con el vertice especificado fue eliminada.
   */
  public boolean eliminarAdyacente(Vertice v) {
    if (adyacentes != null) {
      return adyacentes.remove(v);
    }
    return false;
  }

  /**
   * Devuelve el hashCode provisto por Java de los objetos Integer de todos
   * los identificadores de este vértice y de nodos adyacentes.
   * @return valor de hash para este vertice.
   */
  @Override
  public int hashCode() {
    int resultadoHashCode = id.hashCode();
    return resultadoHashCode;
  }

  /**
   * Compara este vértice con otro vértice dado, retorna true si es el mismo identificador
   * y si los adyacentes son los mismos.
   * @param obj a comparar con este vertice.
   * @return true si el objeto especificado es igual a este vértice.
   */
  @Override
  public boolean equals(Object obj) {
    // Pasan null o algo que sea un vértice devuelve false
    if (obj == null || !(obj instanceof Vertice)) {
      return false;
    }
    Vertice verticeAComparar = (Vertice) obj;
    if (this == verticeAComparar) {
      return true;
    }
    if (verticeAComparar.getId() == null) {
      return false;
    }
    // ¿Los ids son iguales?
    return this.id.equals(verticeAComparar.getId());
  }

  /**
   * Representamos los vértices como una comunidad, con sus identificadores personales presentes en ella.
   * Mostramos el identificador del vértice y sus vértices adyacentes.
   * @return Representacion del vértice en forma de String.
   */
  @Override
  public String toString() {
    String c = "{" + String.valueOf(id) + "}";
    for (Vertice verticeLista : adyacentes) {
      c += " -> ";
      c += String.valueOf(verticeLista.getId());
    }
    return c;
  }
}
