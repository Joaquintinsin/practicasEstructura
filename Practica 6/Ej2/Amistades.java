package tpgrafos;

import tpgrafos.colecciones.Arista;
import tpgrafos.colecciones.Grafo;
import tpgrafos.colecciones.GrafoNoDirigido;
import tpgrafos.colecciones.Vertice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Dada la información de una comunidad específica de una red social, es decir personas y amistades que las relacionan,
 * calcula el número de amistades esenciales.
 * La entrada del problema es un archivo que contiene las siguientes líneas:
 *  <ul>
 *  <li>
 *  La primera lı́nea contiene dos enteros separados por un espacio {@code U} y {@code A},
 *  que representan La cantidad de usuarios (({@code >= 1} y {@code <= 100000})) y amistades (({@code >= 1} y {@code <= 150000})).
 *  </li>
 *  <li>
 *  Las siguientes {@code A} líneas contienen información acerca de las amistades directas, {@code a}, {@code b} enteros positivos separados por un espacio.
 *  </li>
 *  </ul>
 */
public class Amistades {

  /**
   * Numero de parametros recibidos por CLI.
   */
  private static final int NUMPARAMS = 1;

  /**
   * Numero de usuarios de la red que pertenecen a la comunidad (U),
   * {@code 1} &lt; {@code U} && {@code U} &le; {@code 100000}
   */
  private static int numeroUsuarios;
  /**
   * Numero de amistades (A).
   * {@code 1} &le; {@code A} && {@code A} &le; {@code 150000}
   */
  private static int numeroAmistades;

  /**
   * Ejecuta la aplicación que descubre amistades esenciales, según la información pasada como argumento, .
   *  @param args Los argumentos del programa.
   *  @throws IOException en caso de problemas al acceder a un archivo.
   */
  public static void main(String[] args) throws IOException {
    if ((args.length != NUMPARAMS) || args[0].trim().isEmpty()) {
      throw new IllegalArgumentException("args debe contener el nombre de un archivo existente");
    }
    File archivoEntrada = Paths.get(args[0].trim()).toFile();
    if (!archivoEntrada.exists()) {
      throw new IllegalArgumentException("El archivo" + args[0] + "no es un archivo");
    }
    if (!archivoEntrada.isFile()) {
      throw new IllegalArgumentException("El archivo" + args[0] + "no tiene permisos de lectura");
    }
    String nombreArchivo = archivoEntrada.getAbsolutePath();
    Grafo grafo = parsearEntrada(nombreArchivo);
    System.out.println("Archivo de entrada: " + args[0] + " procesado.");
    System.out.println("Calculando amistades esenciales ...");
    System.out.println("Cantidad de amistades esenciales = " + amistadEscencial(grafo));
    System.out.println("Conjunto de amistades no esenciales = " + amistadNoEscencial(grafo));
    System.out.println("Lista de amistades esenciales = " + amistadEscencial2(grafo));
  }

  /**
   * Dada la informacion de una comunidad especifica, determina la cantidad de amistades esenciales.
   * Una amistad es esencial si, cuando falta, quedan usuarios, que antes pertenecían a la comunidad, fuera de la misma.
   * @param grafo representacion de una comunidad de amigos especifica.
   * @return la cantidad de amistades esenciales.
   */
  public static int amistadEscencial(Grafo grafo) {
    // Lista que almacenará las aristas esenciales
    List<Arista> aristasEsenciales = new ArrayList<Arista>();
    // Coleccion de las aristas del grafo
    Collection<Arista> grafoAristas = new ArrayList<Arista>(grafo.aristas());
    // Utilizamos un grafo auxiliar para no manipular ni modificar el grafo original, evitamos errores.
    GrafoNoDirigido grafoAuxiliar = new GrafoNoDirigido();
    // Vamos a pasar toda la informacion que contenga el original
    for (Vertice v : grafo.vertices()) {
      // Agregamos cada vertice
      grafoAuxiliar.agregarVertice(v);
      // Construimos sus adyacentes y aristas.
      for (Vertice adyacentes : v.getAdyacentes()) {
        // Lo agregamos para poder armar la arista
        grafoAuxiliar.agregarVertice(adyacentes);
        grafoAuxiliar.agregarArista(v, adyacentes);
      }
    }
    // Iterar sobre las aristas originales (grafo)
    for (Arista a : grafoAristas) {
      // Eliminamos la arista del grafo auxiliar
      grafoAuxiliar.eliminarArista(a.getPrimero(), a.getSegundo());
      // Filtramos aristas, para no obtener la reciproca
      if (!aristasEsenciales.contains(new Arista(a.getSegundo(), a.getPrimero()))) {
        // Si no es conexo el grafo agregamos esa arista como esencial
        if (!esConexo(grafoAuxiliar)) {
          aristasEsenciales.add(a);
        }
      }
      // Restauramos la arista eliminada anteriormente
      grafoAuxiliar.agregarArista(a.getPrimero(), a.getSegundo());
    }

    // Devolvemos la cantidad de aristas esenciales
    return aristasEsenciales.size();
  }

  public static List<Arista> amistadEscencial2(Grafo grafo) {
    // Lista que almacenará las aristas esenciales
    List<Arista> aristasEsenciales = new ArrayList<Arista>();
    // Coleccion de las aristas del grafo
    Collection<Arista> grafoAristas = new ArrayList<Arista>(grafo.aristas());
    // Utilizamos un grafo auxiliar para no manipular ni modificar el grafo original, evitamos errores.
    GrafoNoDirigido grafoAuxiliar = new GrafoNoDirigido();
    // Vamos a pasar toda la informacion que contenga el original
    for (Vertice v : grafo.vertices()) {
      // Agregamos cada vertice
      grafoAuxiliar.agregarVertice(v);
      // Construimos sus adyacentes y aristas.
      for (Vertice adyacentes : v.getAdyacentes()) {
        // Lo agregamos para poder armar la arista
        grafoAuxiliar.agregarVertice(adyacentes);
        grafoAuxiliar.agregarArista(v, adyacentes);
      }
    }
    // Iterar sobre las aristas originales (grafo)
    for (Arista a : grafoAristas) {
      // Eliminamos la arista del grafo auxiliar
      grafoAuxiliar.eliminarArista(a.getPrimero(), a.getSegundo());
      // Filtramos aristas, para no obtener la reciproca
      if (!aristasEsenciales.contains(new Arista(a.getSegundo(), a.getPrimero()))) {
        // Si no es conexo el grafo agregamos esa arista como esencial
        if (!esConexo(grafoAuxiliar)) {
          aristasEsenciales.add(a);
        }
      }
      // Restauramos la arista eliminada anteriormente
      grafoAuxiliar.agregarArista(a.getPrimero(), a.getSegundo());
    }

    // Devolvemos la lista de aristas esenciales
    return aristasEsenciales;
  }

  /**
   * Método privado que devuelve un valor booleano, para saber si el grafo es conexo o no.
   * @param grafo grafo auxiliar a analizar.
   * @return si el grafo es conexo, o caso contrario, para ver si esa arista eliminada es esencial o no.
   */
  private static boolean esConexo(Grafo grafo) {
    if (grafo.vertices().isEmpty()) {
      return false; // El grafo vacío no es conexo.
    }
    // Vertice inicial para comenzar la busqueda, puede ser cualquiera, en este caso se toma el primero
    Vertice vInicial = grafo.vertices().get(0);

    // Colección de vertices, se inicializa vacio,
    // pero luego contendrá los vertices ya visitados, funciona como marca.
    Set<Vertice> visitados = new HashSet<>();
    // Llamada a dfs.
    dfs(grafo, vInicial, visitados);

    // Si la cantidad de vertices visitados es igual a la cantidad de vertices totales del grafo,
    // significa que están todos conectados y el grafo es conexo
    return visitados.size() == grafo.vertices().size();
  }

  // Depth-First Search (DFS)
  private static void dfs(Grafo grafo, Vertice vInicial, Set<Vertice> visitados) {
    // Se marca como visitado al vertice, es decir, se añade a visitados
    visitados.add(vInicial);
    // Se itera sobre sus adyacentes
    for (Vertice ady : grafo.obtenerAdyacentes(vInicial)) {
      // Si no está en visitados, hacemos una llamada recursiva de dfs sobre este para continuar la busqueda.
      if (!visitados.contains(ady)) {
        dfs(grafo, ady, visitados);
      }
    }
  }

  /**
   * Método que devuelve el conjunto de aristas no esenciales en el grafo.
   * @param grafo El grafo a ver las amistades no esenciales.
   * @return el conjunto de amistades/aristas no esenciales.
   */
  public static List<Arista> amistadNoEscencial(Grafo grafo) {
    // Lista que almacenará las aristas no esenciales
    List<Arista> aristasNoEsenciales = new ArrayList<Arista>();
    // Coleccion de las aristas del grafo
    Collection<Arista> grafoAristas = new ArrayList<Arista>(grafo.aristas());
    // Utilizamos un grafo auxiliar para no manipular ni modificar el grafo original, evitamos errores.
    GrafoNoDirigido grafoAuxiliar = new GrafoNoDirigido();
    // Vamos a pasar toda la información que contenga el original
    for (Vertice v : grafo.vertices()) {
      // Agregamos cada vertice
      grafoAuxiliar.agregarVertice(v);
      // Construimos sus adyacentes y aristas.
      for (Vertice adyacentes : v.getAdyacentes()) {
        // Lo agregamos para poder armar la arista
        grafoAuxiliar.agregarVertice(adyacentes);
        grafoAuxiliar.agregarArista(v, adyacentes);
      }
    }
    // Iteramos sobre las aristas originales (grafo)
    for (Arista a : grafoAristas) {
      // Eliminamos la arista del grafo auxiliar
      grafoAuxiliar.eliminarArista(a.getPrimero(), a.getSegundo());
      // Filtramos aristas, para no obtener la reciproca
      if (!aristasNoEsenciales.contains(new Arista(a.getSegundo(), a.getPrimero()))) {
        // Si es conexo el grafo agregamos esa arista como no esencial
        if (esConexo(grafoAuxiliar)) {
          aristasNoEsenciales.add(a);
        }
      }
      // Restauramos la arista eliminada anteriormente
      grafoAuxiliar.agregarArista(a.getPrimero(), a.getSegundo());
    }

    // Devolvemos la lista de aristas NO esenciales
    return aristasNoEsenciales;
  }

  /**
   * Parsea el archivo de entrada y asigna los valores correspondientes a los campos estáticos,
   * `numeroUsuarios` y `numeroAmistades` y construye un grafo con los datos del archivo.
   * @param archivoEntrada nombre del archivo de entrada.
   * @return Grafo obtenido de los datos del archivo de entrada.
   * @throws IOException problemas con el archivo de entrada.
   */
  private static Grafo parsearEntrada(String archivoEntrada) throws IOException {
    // Creamos un grafo con un constructor no parametrizado.
    GrafoNoDirigido grafo = new GrafoNoDirigido();
    try {
      BufferedReader lector = new BufferedReader(new FileReader(archivoEntrada));
      System.out.println("Grafo inicial: " + grafo.toString());
      // Leemos la primer linea donde se encuentra el numeroUsuarios y numeroAmistades.
      String linea = lector.readLine();
      if (linea != null) {
        String[] campos = linea.split(" ");
        // Realizamos la asignación de cada uno respectivamente.
        numeroUsuarios = Integer.valueOf(campos[0]);
        numeroAmistades = Integer.valueOf(campos[1]);
        linea = lector.readLine();
      }
      // Luego de esto, vamos a ir creando el grafo de acuerdo a la informacion que va siendo ingresada.
      while (linea != null) {
        String[] palabras = linea.split(" ");
        int vert1 = Integer.valueOf(palabras[0]);
        int vert2 = Integer.valueOf(palabras[1]);
        Vertice v1 = new Vertice(vert1);
        Vertice v2 = new Vertice(vert2);

        grafo.agregarVertice(v1);
        grafo.agregarVertice(v2);

        grafo.agregarArista(v1, v2);

        linea = lector.readLine();
      }
      System.out.println("Grafo del archivo ingresado = " + "\n" + grafo.toString());
      lector.close();
    } catch (IOException e) {
      // Problemas con el archivo de entrada.
      System.err.println("Error al abrir o leer el archivo: " + e.getMessage());
      throw e;
    }
    if (numeroUsuarios < 1 || numeroUsuarios > 100000) {
      throw new IllegalArgumentException("El número de amigos no puede ser menor que uno ni mayor que 100000");
    }
    if (numeroAmistades < 1 || numeroAmistades > 150000) {
      throw new IllegalArgumentException("El número de amistades no puede ser menor que uno ni mayor que 150000");
    }
    // Devolvemos el grafo resultante
    return grafo;
  }
}
