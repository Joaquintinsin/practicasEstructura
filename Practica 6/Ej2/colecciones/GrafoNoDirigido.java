package Ej2.colecciones;

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

  // Crear un grafo vacı́o.
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
    this.vertices = new ArrayList<Vertice>(vertices);
    this.aristas = new ArrayList<Arista>(aristas);
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
  // Decir si un grafo es vacı́o.
  public boolean esVacio() {
    return cantidadVertices() == 0;
  }

  /**{@inheritDoc}*/
  // Dar el número de vértices de un grafo.
  public int cantidadVertices() {
    return vertices.size();
  }

  /**{@inheritDoc}*/
  // Dar el número de arcos de un grafo.
  public int cantidadAristas() {
    return aristas.size();
  }

  /**{@inheritDoc}*/
  // Determinar si hay un arco entre dos nodos dados.
  public boolean sonArco(Vertice v1, Vertice v2) {
    Arista arcoEntreVertices = new Arista(v1, v2);  // Construyo una arista de v1 a v2
    Arista arcoSimetria = new Arista(v2, v1);       // Construyo una arista de v2 a v1
    // Si está alguna de las dos aristas (de v1 a v2 o de v2 a v1) entonces son arco
    return aristas.contains(arcoEntreVertices) || aristas.contains(arcoSimetria);
  }

  /**{@inheritDoc}*/
  // Insertar un nodo en un grafo.
  public boolean agregarVertice(Vertice nodo) {
    if (existe(nodo)) {
      return false;
    }
    unirAdyacentesAristas(nodo);  // Agrega aristas desde el vértice origial hacia sus adyacentes.
    return vertices.add(nodo);  // Retorna el boolean definido de List.add(Object) de Java.
  }
  // Agrega aristas desde el vértice origial hacia sus adyacentes.
  private void unirAdyacentesAristas(Vertice v) {
    if (v.getAdyacentes() != null) {
      for (Vertice a : v.getAdyacentes()) {
        agregarArista(v, a);
      }
    }
  }

  /**{@inheritDoc}*/
  // Borrar un vértice del grafo.
  public boolean eliminarVertice(Vertice nodo) {
    if (!existe(nodo)) {
      return false;
    }
    eliminarAdyacentesAristas(nodo);  // Quita las aristas unidas desde el vértice origen hacia sus adyacentes
    eliminarAristasDelNodo(nodo);   // Quita las aristas que salen del vértice o que entran al vértice
    return vertices.remove(nodo); // Retorna el boolean definido de List.remove(Object) de Java.
  }
  // Quita las aristas unidas desde el vértice origen hacia sus adyacentes
  private void eliminarAdyacentesAristas(Vertice v) {
    if (v.getAdyacentes() != null) {
      for (Vertice a : v.getAdyacentes()) {
        eliminarArista(v, a);
      }
    }
  }
  // Quita las aristas que salen del vértice o que entran al vértice
  private void eliminarAristasDelNodo(Vertice nodo) {
    ArrayList<Arista> ari = new ArrayList<Arista>(aristas);
    for (Arista a : ari) {
      // Cuando encuentra una arista que sale o que entra del nodo, la remueve de la colección de aristas
      if (a.getPrimero().getId().equals(nodo.getId()) || a.getSegundo().getId().equals(nodo.getId())) {
        aristas.remove(a);
      }
    }
  }

  /**{@inheritDoc}*/
  // Insertar un arco entre dos nodos.
  public boolean agregarArista(Vertice vertice1, Vertice vertice2) {
    // Si alguno de los vértices no está en la lista de vértices del grafo, devuelve falso.
    if (!existe(vertice1) || !existe(vertice2)) {
      return false;
    }
    Arista a1 = new Arista(vertice1, vertice2); // Crea una arista de v1 a v2
    Arista a2 = new Arista(vertice2, vertice1); // Crea una arista de v2 a v1
    // Si alguna de las aristas ya está en la colección, devuelve falso.
    if (aristas.contains(a1) || aristas.contains(a2)) {
      return false;
    }
    /*
     * Recorro una lista paralela a la lista de vértices, con objetivo de buscar posición por posición
     * el vertice1 y el vertice2. Cuando los encuentre, va a agregarles a sus listas de adyacencias
     * el otro vértice. Eso lo hace sobre la lista de vértices paralela. Una vez hecho eso, actualiza
     * la lista original de vértices del grafo con la lista modificada, en donde los vértices ahora
     * tienen la lista de adyacencia modificada correctamente.
     */
    List<Vertice> vertGrafo = vertices;
    for (int i = 0; i < vertGrafo.size(); i++) {
      if (vertGrafo.get(i).getId().equals(vertice1.getId())) {
        vertGrafo.get(i).agregarAdyacente(vertice2);
      }
      if (vertGrafo.get(i).getId().equals(vertice2.getId())) {
        vertGrafo.get(i).agregarAdyacente(vertice1);
      }
    }
    vertices = vertGrafo;
    // Agrego los adyacentes correspondientes a los nodos parametrizados.
    vertice1.agregarAdyacente(vertice2);
    vertice2.agregarAdyacente(vertice1);
    return aristas.add(new Arista(vertice1, vertice2)); // Retorna el boolean definido de Collection.add(Object) de Java.
  }

  /**{@inheritDoc}*/
  // Borrar un arco del grafo.
  public boolean eliminarArista(Vertice vertice1, Vertice vertice2) {
    // Retorna el boolean definido de Collection.remove(Object) de Java.
    boolean res = aristas.remove(new Arista(vertice1, vertice2)) || aristas.remove(new Arista(vertice2, vertice1));
    return res;
  }

  /**{@inheritDoc}*/
  // Decir si un nodo pertenece al grafo.
  public boolean existe(Vertice nodo) {
    // Recorre la lista de vértices, devuelve true ni bien encuentra el Id en la lista, si no la encuentra devuelve false
    for (Vertice v : vertices) {
      if (v.getId().equals(nodo.getId())) {
        return true;
      }
    }
    return false;
  }

  /**{@inheritDoc}*/
  public List<Vertice> obtenerAdyacentes(Vertice v) {
    if (!vertices.contains(v)) {
      return new ArrayList<Vertice>();
    }
    return v.getAdyacentes();
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
      ArrayList<Vertice> verticesString = new ArrayList<Vertice>(vertices);
      for (Vertice elem : verticesString) {
        c += elem.toString();
        c += "\n";
      }
      return c;
    }
  }
}
