package arraySorter;

import java.util.Arrays;

/**
* Provee métodos para ordenar arreglos de objetos comparables.
* Los algoritmos de ordenamiento provistos por esta clase son:
* <ul>
* <li>Bubble sort.</li>
* <li>Selection sort.</li>
* <li>Shell sort.</li>
* <li>Quick sort.</li>
* <li>Merge sort.</li>
* </ul>
* El invariante que deben cumplir todos los arreglos {@code array} para ser utilizados como argumentos de los distintos algoritmos de ordenamiento es:
* <pre>
* array != null &amp;&amp; for (T elem : array) {elem != null}
* </pre>
*/
public class ArraySorter {
  /**
   * Ordena un arreglo, <i>in place</i>, usando el orden natural de sus elementos utilizando Bubble Sort.
   * @param <T> el tipo de los elementos del arreglo, los cuales deben ser comparables entre sí
   * @param array el arreglo de los elementos a ordenar, no puede ser {@code null}
   */
  public static <T extends Comparable<? super T>> void bubbleSort(T[] array) {
    if (array == null) throw new IllegalArgumentException("El arreglo es null, no se puede ordenar");
    boolean sorted = false;
    int n = array.length; 
    for (int pass = 1; (pass < n) && !sorted; pass++) {
      sorted = true;
      for (int index = 0; index < n-pass; index++) {
        int nextIndex = index + 1;
        if (array[index].compareTo(array[nextIndex]) > 0) {
          swap(array, index, nextIndex);
          sorted = false;
        }
      }
    }
  }

  /**
   * Ordena un arreglo, <i>in place</i>, usando el orden natural de sus elementos utilizando Selection Sort.
   * @param <T> el tipo de los elementos del arreglo, los cuales deben ser comparables entre sí
   * @param array el arreglo de los elementos a ordenar, no puede ser {@code null}
   */
  public static <T extends Comparable<? super T>> void selectionSort(T[] array) {
    if (array == null) throw new IllegalArgumentException("array is null, can't sort");
    //last = indice del ultimo elemento de la parte no ordenada
    for (int last = array.length - 1; last >= 1; last--) {
      //largest = posicion del elemento mas grande
      int largest = indexOfLargest(array, last+1);
      swap(array, last, largest);
    }
  }
  
  /* (non-Javadoc)
   * Este método retorna el indice del elemento mas grande. 
   */
  private static <T extends Comparable<? super T>> int indexOfLargest(T[] array, int n) {
    int largest = 0;
    for (int i = 1; i < n; i++){
      if (array[i].compareTo(array[largest]) > 0){
        largest = i;
      }
    }  
    return largest;
  }

  // Idea: Para cada i determinamos el número de j's menores a
  // él en el arreglo, y acomodamos a i en el lugar que va.
  // Necesitamos usar arreglos adicionales para esto.
  
  // T(n) = n + n + k pertenece O(n)
  // primera n: Contar menores o iguales
  // segunda n: Poner los elementos en su lugar
  // k:         Contar repetidos
  // Si el k es muy grande, el algoritmo es ineficiente!
  
  // n = Numero más grande a aparecer
  // k = Long de la lista/cantidad de elementos
  public static void countingSort(int [] array, int n, int k) {
    int[] b = new int[n];
    int[] c = new int[k];
    for (int i = 0; i < n; i++) {
      //contamos la cantidad de elementos iguales a array [ i ]
      c[array[i]] = c[array[i]] + 1;
    }
    for (int i = 1; i < k; i++) {
      //contamos la cantidad de elementos iguales o menores a array [ i ]
      c[i] = c[i] + c[i-1];
    }
    // ponemos cada elemento array [ i ] en su lugar
    for (int j = n-1; i == 0; j--) {    // Tiene problemas en i == 0, no esta declarada la variable. No se que se habra querido poner
      b[c[array[j]] - 1] = array[j];
      c[array[j]] = c[array[j]] - 1;
    }
  }

  /**
   * Ordena un arreglo, <i>in place</i>, usando el orden natural de sus elementos utilizando Shell Sort.
   * @param <T> el tipo de los elementos del arreglo, los cuales deben ser comparables entre sí
   * @param array el arreglo de los elementos a ordenar, no puede ser {@code null}
   */
  // ShellSort de chat
  public static <T extends Comparable<? super T>> void shellSort(T[] array) {
    if (array == null) throw new IllegalArgumentException("array is null, can't sort");
    int n = array.length;

    // Comenzar con un espacio grande y reducirlo gradualmente
    for (int gap = n / 2; gap > 0; gap /= 2) {
      // Realizar la inserción de elementos con un cierto espacio (gap)
      for (int i = gap; i < n; i++) {
        T temp = array[i];
        int j;
        for (j = i; j >= gap && array[j-gap].compareTo(temp) > 0; j -= gap) {
          array[j] = array[j - gap];
        }
        array[j] = temp;
      }
    }
  }
  
  // RadixSort de chat
  public static void radixSort(int[] arr) {
    if (arr == null || arr.length == 0) throw new IllegalArgumentException("array is null, can't sort");

    int max = Arrays.stream(arr).max().getAsInt();
    for (int exp = 1; max / exp > 0; exp *= 10) {
      countingSortByDigit(arr, exp);
    }
  }

  // Para el RadixSort
  private static void countingSortByDigit(int[] arr, int exp) {
    int n = arr.length;
    int[] output = new int[n];
    int[] count = new int[10]; // 10 digits (0-9)

    Arrays.fill(count, 0);

    // Count the occurrences of each digit in the current position
    for (int i = 0; i < n; i++) {
      count[(arr[i] / exp) % 10]++;
    }

    // Calculate the acumulative count
    for (int i = 1; i < 10; i++) {
      count[i] += count[i - 1];
    }

    // Build the output array using the counts and place elements correctly
    for (int i = n - 1; i >= 0; i--) {
      output[count[(arr[i] / exp) % 10] - 1] = arr[i];
      count[(arr[i] / exp) % 10]--;
    }

    // Copy the sorted elements back to the original array
    for (int i = 0; i < n; i++) {
      arr[i] = output[i];
    }
  }

  public static <T extends Comparable<? super T>> void insertionSort (T[] array) {
    int n = array.length;
    for (int unsorted = 1; unsorted < n; unsorted++) {
      T nextItem = array[unsorted];
      int loc = unsorted;
      while ((loc > 0) && (array[loc-1].compareTo(nextItem) > 0)) {
        array[loc] = array[loc-1];
        loc--;
      }
      array[loc] = nextItem;
    }
  }

  /**
   * Ordena un arreglo, <i>in place</i>, usando el orden natural de sus elementos utilizando Quick Sort.
   * @param <T> el tipo de los elementos del arreglo, los cuales deben ser comparables entre sí
   * @param array el arreglo de los elementos a ordenar, no puede ser {@code null}
   */
  public static <T extends Comparable<? super T>> void quickSort(T[] array, int leftmost, int rightmost) {
    if (array == null) throw new IllegalArgumentException("array is null, can't sort");
    
    if (leftmost < rightmost) {
      int p = partition(array, leftmost, rightmost);
      quickSort(array, leftmost, p);
      quickSort(array, p+1, rightmost);
    }
  }
  
  // El partition no necesita espacio extra.
  // No es un algoritmo estable (depende de la implementación del partition).
  private static <T extends Comparable<? super T>> int partition(T[] array, int begin, int end) {
    T pivot = array[begin];
    int i = begin - 1;
    int j = end + 1;
    while (i < j) {
      //invariante:
      //para k < = i : a[k] <= pivot y para k >= j : pivot <= a[k]
      do 
        j--; 
      while (array[j].compareTo(pivot) > 0);
      do 
        i++; 
      while (array[i].compareTo(pivot) < 0);
      if (i < j)
        swap(array, i, j);
    }
    return j;
  }

  /**
   * Ordena un arreglo, usando el orden natural de sus elementos utilizando Merge Sort.
   * @param <T> el tipo de los elementos del arreglo, los cuales deben ser comparables entre sí
   * @param array el arreglo de los elementos a ordenar, no puede ser {@code null}
   */
  public static <T extends Comparable<? super T>> void mergeSort(T[] array) {
    if (array == null) throw new IllegalArgumentException("array is null, can't sort");
    mergeSortRecursivo(array, 0, array.length);
  }
  
  private static <T extends Comparable<? super T>> void mergeSortRecursivo(T[] array, int begin, int end) {
    if (begin < end) {
      int mid = (begin + end)/2;
      mergeSortRecursivo(array, begin, mid);   // ordena la primera mitad
      mergeSortRecursivo(array, mid+1, end);   // ordena la segunda mitad
      merge(array, begin, mid, end);  // mezcla las mitades ordenadas
    }
  }

  // merge según Chat
  private static <T extends Comparable<? super T>> void merge(T[] array, int begin, int mid, int end) {
    int leftArraySize = mid - begin + 1;
    int rightArraySize = end - mid;

    // Crear arrays temporales para almacenar las mitades izquierda y derecha
    T[] leftArray = (T[]) new Object[leftArraySize];
    T[] rightArray = (T[]) new Object[rightArraySize];

    // Copiar datos a los arrays temporales
    for (int i = 0; i < leftArraySize; i++) {
      leftArray[i] = array[begin + i];
    }
    for (int j = 0; j < rightArraySize; j++) {
      rightArray[j] = array[mid + 1 + j];
    }

    int i = 0, j = 0; // Índices iniciales para recorrer los arrays temporales
    int k = begin; // Índice inicial del subarray combinado

    // Combinar los elementos en orden en el array original
    while (i < leftArraySize && j < rightArraySize) {
      if (leftArray[i].compareTo(rightArray[j]) <= 0) {
        array[k] = leftArray[i];
        i++;
      } else {
        array[k] = rightArray[j];
        j++;
      }
      k++;
    }

    // Copiar los elementos restantes de los arrays temporales, si los hay
    while (i < leftArraySize) {
      array[k] = leftArray[i];
      i++;
      k++;
    }

    while (j < rightArraySize) {
      array[k] = rightArray[j];
      j++;
      k++;
    }
  }

  /* (non-Javadoc)
   * Este método intercambia dos posiciones de un arreglo.
   */ 
  private static <T extends Comparable<? super T>> void swap(T[] array, int i, int j) {
    T temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }
}
