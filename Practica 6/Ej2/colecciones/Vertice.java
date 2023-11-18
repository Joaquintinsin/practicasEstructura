package Ej2.colecciones;

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
    adyacentes = null;
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
    if (adyacentes == null) {
      return resultadoHashCode;
    }
    for (int i = 0; i < adyacentes.size(); i++) {
      resultadoHashCode += adyacentes.get(i).getId().hashCode();
    }
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
    // Los ids no son iguales devuelve false
    if (!verticeAComparar.getId().equals(id)) {
      return false;
    } else {
      // Reviso por los adyacentes, comparo las dos listas de adyacentes
      List<Vertice> adyacentesComp = verticeAComparar.getAdyacentes();
      // Si no hay elementos adyacentes, depende de si la otra lista es vacía o no
      if (adyacentes == null) {
        return adyacentesComp == null;
      } else {
        // Si tengo adyacentes en this, pero la otra lista es null, devuelve false.
        if (adyacentesComp == null) {
          return false;
        }
      }
      // Si hay elementos adyacentes, compara id por id de las listas
      for (Vertice verticeComp : adyacentesComp) {  // Para todo vertice de adyacentes a comparar
        for (Vertice esteVertice : adyacentes) {    // Para todo vertice de adyacentes (este)
          if (!(verticeComp.getId().equals(esteVertice.getId()))) {   // Si algún id difiere, devuelve falso
            return false;
          }
        }
      }
      return true;
    }
  }

  /**
   * Representamos los vértices como una comunidad, con sus identificadores personales presentes en ella.
   * Mostramos el identificador del vértice y sus vértices adyacentes.
   * @return Representacion del vértice en forma de String.
   */
  @Override
  public String toString() {
    String c = "{" + String.valueOf(id) + "}";
    if (adyacentes == null) {
      return c;
    }
    for (Vertice verticeLista : adyacentes) {
      c += "->";
      c += "{" + String.valueOf(verticeLista.getId()) + "}";
    }
    return c;
  }
}
