package Ej2;

import Ej2.colecciones.Grafo;
import Ej2.colecciones.GrafoNoDirigido;
import Ej2.colecciones.Vertice;
import Ej2.colecciones.Arista;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashSet;
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
    System.out.println(grafo.toString());
    System.out.println("Calculando amistades esenciales ...");
    System.out.println("Cantidad de amistades esenciales = " + amistadEscencial(grafo));
    System.out.println(grafo.toString());
  }

  /**
   * Dada la informacion de una comunidad especifica, determina la cantidad de amistades esenciales.
   * Una amistad es esencial si, cuando falta, quedan usuarios, que antes pertenecían a la comunidad, fuera de la misma.
   * @param grafo representacion de una comunidad de amigos especifica.
   * @return la cantidad de amistades esenciales.
   */
  public static int amistadEscencial(Grafo grafo) {
    Set<Vertice> verticesEsenciales = new HashSet<Vertice>();
    for (Vertice v : grafo.vertices()) {
      Set<Vertice> amigos = new HashSet<Vertice>();
      for (Arista a : grafo.aristas()) {
        if (a.getPrimero().equals(v)) {
          amigos.add(a.getSegundo());
        } else if (a.getSegundo().equals(v)) {
          amigos.add(a.getPrimero());
        }
      }
      for (Vertice amigo : amigos) {
        if (amigos.size() == 1) {
          verticesEsenciales.add(v);
          break;
        }
        amigos.remove(amigo);
        Set<Vertice> visitados = new HashSet<Vertice>();
        visitados.add(v);
        if (!dfs(grafo, visitados, amigos)) {
          verticesEsenciales.add(v);
          break;
        }
        amigos.add(amigo);
      }
    }
    return verticesEsenciales.size();
  }

  private static boolean dfs(Grafo grafo, Set<Vertice> visitados, Set<Vertice> amigos) {
    if (amigos.isEmpty()) {
      return true;
    }
    Vertice v = visitados.iterator().next();
    for (Arista a : grafo.aristas()) {
      Vertice w = a.getSegundo();
      if (v.equals(w)) {
        w = a.getPrimero();
      }
      if (amigos.contains(w)) {
        amigos.remove(w);
        visitados.add(w);
        if (dfs(grafo, visitados, amigos)) {
          return true;
        }
        visitados.remove(w);
        amigos.add(w);
      }
    }
    return false;
  }

  /**
   * Parsea el archivo de entrada y asigna los valores correspondientes a los campos estáticos,
   * `numeroUsuarios` y `numeroAmistades` y construye un grafo con los datos del archivo.
   * @param archivoEntrada nombre del archivo de entrada.
   * @return Grafo obtenido de los datos del archivo de entrada.
   * @throws IOException problemas con el archivo de entrada.
   */
  private static Grafo parsearEntrada(String archivoEntrada) throws IOException {
    GrafoNoDirigido grafo = new GrafoNoDirigido();
    try {
      BufferedReader lector = new BufferedReader(new FileReader(archivoEntrada));
      System.out.println("Grafo inicial: " + grafo.toString());
      String linea = lector.readLine();
      if (linea != null) {
        String[] campos = linea.split(" ");
        numeroUsuarios = Integer.valueOf(campos[0]);
        numeroAmistades = Integer.valueOf(campos[1]);
        linea = lector.readLine();
      }
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
      System.out.println("Grafo del archivo ingresado = " + grafo.toString());
      lector.close();
    } catch (IOException e) {
      System.err.println("Error al abrir o leer el archivo: " + e.getMessage());
      throw e;
    }
    return grafo;
  }
}
