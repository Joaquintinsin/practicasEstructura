package tpgrafos.colecciones;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Implementamos grafos con una lista de adyacencias
 */
public class GrafoNoDirigido implements Grafo {
  /**
   * Lista de los nodos presentes en el grafo
   */
  private List<Vertice> vertices;

  /**
   * Lista de las aristas presentes en el grafo
   */
  private Collection<Arista> aristas;

  /**
   * Constructor no parametrizado
   */
  public GrafoNoDirigido() {
    vertices = new ArrayList<Vertice>();
    aristas = new ArrayList<Arista>();
  }

  /**
   * Constructor que recibe una lista de los vertices y una coleccion con las aristas
   * @param vertices Listas de vertices del grafo a construir.
   * @param aristas coleccion de aristas del grafo a construir.
   */
  public GrafoNoDirigido(List<Vertice> vertices, Collection<Arista> aristas) {
    for (Arista a : aristas) {
      a.getPrimero().agregarAdyacente(a.getSegundo());
      a.getSegundo().agregarAdyacente(a.getPrimero());
    }
    this.vertices = vertices;
    this.aristas = aristas;
  }

  /**{@inheritDoc}*/
  public boolean agregarVertice(Vertice nodo) {
    if (nodo == null) {
      throw new IllegalArgumentException("agregarVertice: Se quiso agregar un vertice null");
    }
    if (existe(nodo)) {
      return false;
    }
    return vertices.add(nodo);  // Retorna el boolean definido de List.add(Object) de Java.
  }


  /**{@inheritDoc}*/
  public boolean eliminarVertice(Vertice nodo) {
    if (nodo == null) {
      throw new IllegalArgumentException("eliminarVertice: Se quiso eliminar un nodo null");
    }
    if (!existe(nodo)) {
      return false;
    }
    desconectarAdyacentes(nodo); // Elimina aristas y adyacentes desde el nodo dado hacia sus adyacentes
    return vertices.remove(nodo); // Retorna el boolean definido de List.remove(Object) de Java.
  }

  private void desconectarAdyacentes(Vertice v) {
    List<Vertice> auxAdy = new ArrayList<>(v.getAdyacentes());
    for (Vertice ady : auxAdy) {
      eliminarArista(v, ady);
      v.eliminarAdyacente(ady);
    }
  }

  /**{@inheritDoc}*/
  public boolean existe(Vertice nodo) {
    if (nodo == null) {
      throw new IllegalArgumentException("existe: Se quiso ver un nodo null");
    }
    // Recorre la lista de vértices, devuelve true ni bien encuentra el Id en la lista, si no la encuentra devuelve false
    for (Vertice v : vertices) {
      if (v.getId().equals(nodo.getId())) {
        return true;
      }
    }
    return false;
  }

  /**{@inheritDoc}*/
  public List<Vertice> vertices() {
    return this.vertices;
  }

  /**{@inheritDoc}*/
  public Collection<Arista> aristas() {
    return this.aristas;
  }

  /**{@inheritDoc}*/
  public boolean agregarArista(Vertice vertice1, Vertice vertice2) {
    if (vertice1 == null || vertice2 == null) {
      throw new IllegalArgumentException("agregarArista: Los vertices no pueden ser null");
    }
     // Si alguno de los vértices no está en la lista de vértices del grafo, devuelve false.
     if (!existe(vertice1) || !existe(vertice2)) {
      return false;
    }
    // Si la arista a agregar son el mismo vértice (mismo identificador), devuelve false.
    if (vertice1.getId().equals(vertice2.getId())) {
      return false;
    }
    // Si los dos vértices ya son arco (ya tienen una arista), entonces devuelve false.
    if (sonArco(vertice1, vertice2)) {
      return false;
    }
    // Sobre la lista de vértices del grafo, en donde se encuentre vertice1 y vertice2, agrega como adyacente el otro vértice.
    for (Vertice v : vertices) {
      if (v.equals(vertice1)) {
        v.agregarAdyacente(vertice2);
      }
      if (v.equals(vertice2)) {
        v.agregarAdyacente(vertice1);
      }
    }
    // Agrego los adyacentes correspondientes a los nodos parametrizados.
    vertice1.agregarAdyacente(vertice2);
    vertice2.agregarAdyacente(vertice1);
    aristas.add(new Arista(vertice1, vertice2));
    aristas.add(new Arista(vertice2, vertice1));
    return true;
  }

  /**{@inheritDoc}*/
  public boolean eliminarArista(Vertice vertice1, Vertice vertice2) {
    if (vertice1 == null || vertice2 == null) {
      throw new IllegalArgumentException("eliminarArista: Los vertices no pueden ser null");
    }
    // Si alguno de los vértices no está en la lista de vértices del grafo, devuelve false.
    if (!existe(vertice1) || !existe(vertice2)) {
      return false;
    }
    // Si entre los vértices no hay una arista, entonces devuelve false.
    if (!sonArco(vertice1, vertice2)) {
      return false;
    }
    // Sobre la lista de vértices del grafo, en donde se encuentre vertice1 y vertice2, elimina el otro vértice de su lista de adyacentes.
    for (Vertice v : vertices) {
      if (v.equals(vertice1)) {
        v.eliminarAdyacente(vertice2);
      }
      if (v.equals(vertice2)) {
        v.eliminarAdyacente(vertice1);
      }
    }
    // Elimino los adyacentes correspondientes a los nodos parametrizados.
    vertice1.eliminarAdyacente(vertice2);
    vertice2.eliminarAdyacente(vertice1);
    aristas.remove(new Arista(vertice1, vertice2));
    aristas.remove(new Arista(vertice2, vertice1));
    return true;
  }

  /**{@inheritDoc}*/
  public List<Vertice> obtenerAdyacentes(Vertice v) {
    if (v == null) {
      throw new IllegalArgumentException("obtenerAdyacentes: El vertice no puede ser null");
    }
    // Si la lista de nodos/vértices no contiene el vértice dado, devuelve una lista vacia.
    if (!vertices.contains(v)) {
      return new ArrayList<Vertice>();
    }
    // Si el vértice existe en el grafo, retornamos sus adyacentes.
    return v.getAdyacentes();
  }

  /**{@inheritDoc}*/
  public int cantidadVertices() {
    return vertices.size();
  }

  /**{@inheritDoc}*/
  public boolean esVacio() {
    // Si el grafo no posee ningún vértice es vacío.
    return cantidadVertices() == 0;
  }

  /**{@inheritDoc}*/
  public int cantidadAristas() {
    return aristas.size();
  }

  /**{@inheritDoc}*/
  public boolean sonArco(Vertice v1, Vertice v2) {
    if (v1 == null || v2 == null) {
      throw new IllegalArgumentException("sonArco: Los vertices no pueden ser null");
    }
    Arista arcoEntreVertices = new Arista(v1, v2); // Construyo una arista de v1 a v2
    Arista arcoSimetria = new Arista(v2, v1); // Construyo una arista de v2 a v1
    // Si está alguna de las dos aristas (de v1 a v2 o de v2 a v1) entonces son arco
    return aristas.contains(arcoEntreVertices) || aristas.contains(arcoSimetria);
  }

  /**
   * Representación de un grafo no dirigido mediante vértices y la lista de adyacencias de cada uno.
   * @return Representacion de los vertices y arcos del grafo en forma de String.
   */
  @Override
  public String toString() {
    String c = "";
    if (esVacio()) {
      return "El grafo es vacío \n";
    } else {
      for (Vertice elem : vertices) {
        c += elem.toString();
        c += "\n";
      }
      return c;
    }
  }
}
