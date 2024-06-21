package arraySorter;

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
 * El invariante que deben cumplir todos los arreglos {@code array} para ser
 * utilizados como argumentos de los distintos algoritmos de ordenamiento es:
 * 
 * <pre>
* array != null &amp;&amp; for (T elem : array) {elem != null}
 * </pre>
 */
public class ArraySorter {

   /**
    * Ordena un arreglo, <i>in place</i>, usando el orden natural de sus elementos
    * utilizando Bubble Sort.
    * 
    * @param <T>   el tipo de los elementos del arreglo, los cuales deben ser
    *              comparables entre sí
    * @param array el arreglo de los elementos a ordenar, no puede ser {@code null}
    */
   public static <T extends Comparable<? super T>> void bubbleSort(T[] array) {
      if (array == null)
         throw new IllegalArgumentException("El arreglo es null, no se puede ordenar");
      boolean sorted = false;
      int n = array.length;
      for (int pass = 1; (pass < n) && !sorted; ++pass) {
         sorted = true;
         for (int index = 0; index < n - pass; ++index) {
            int nextIndex = index + 1;
            if (array[index].compareTo(array[nextIndex]) > 0) {
               swap(array, index, nextIndex);
               sorted = false;
            }
         }
      }
   }

   /**
    * Ordena un arreglo, <i>in place</i>, usando el orden natural de sus elementos
    * utilizando Selection Sort.
    * 
    * @param <T>   el tipo de los elementos del arreglo, los cuales deben ser
    *              comparables entre sí
    * @param array el arreglo de los elementos a ordenar, no puede ser {@code null}
    */
   public static <T extends Comparable<? super T>> void selectionSort(T[] array) {
      if (array == null)
         throw new IllegalArgumentException("array is null, can't sort");
      // last = indice del ultimo elemento de la parte no ordenada
      for (int last = array.length - 1; last >= 1; last--) {
         // largest = posicion del elemento mas grande
         int largest = indexOfLargest(array, last + 1);
         swap(array, last, largest);
      }
   }

   /**
    * Ordena un arreglo, <i>in place</i>, usando el orden natural de sus elementos
    * utilizando Shell Sort.
    * 
    * @param <T>   el tipo de los elementos del arreglo, los cuales deben ser
    *              comparables entre sí
    * @param array el arreglo de los elementos a ordenar, no puede ser {@code null}
    */
   public static <T extends Comparable<? super T>> void shellSort(T[] array) {
      if (array == null)
         throw new IllegalArgumentException("array is null, can't sort");
      int length = array.length;
      for (int gap = length / 2; gap > 0; gap /= 2) {
         for (int i = gap; i < length; i++) {
            T temp = array[i];
            int j;
            for (j = i; j >= gap && array[j - gap].compareTo(temp) > 0; j -= gap) {
               array[j] = array[j - gap];
            }
            array[j] = temp;
         }
      }
   }

   /**
    * Ordena un arreglo, <i>in place</i>, usando el orden natural de sus elementos
    * utilizando Quick Sort.
    * 
    * @param <T>   el tipo de los elementos del arreglo, los cuales deben ser
    *              comparables entre sí
    * @param array el arreglo de los elementos a ordenar, no puede ser {@code null}
    */
   public static <T extends Comparable<? super T>> void quickSort(T[] array) {
      quickSort(array, 0, array.length - 1);
   }

   private static <T extends Comparable<? super T>> void quickSort(T[] array, int low, int high) {
      if (low < high) {
         int pivot = partition(array, low, high);
         quickSort(array, low, pivot - 1);
         quickSort(array, pivot + 1, high);
      }
   }

   private static <T extends Comparable<? super T>> int partition(T[] array, int low, int high) {
      T pivot = array[high];
      int i = (low - 1);
      for (int j = low; j < high; j++) {
         if (array[j].compareTo(pivot) <= 0) {
            i++;
            T temp = array[i];
            array[i] = array[j];
            array[j] = temp;
         }
      }
      T temp = array[i + 1];
      array[i + 1] = array[high];
      array[high] = temp;

      return i + 1;
   }

   /**
    * Ordena un arreglo, usando el orden natural de sus elementos utilizando Merge
    * Sort.
    * 
    * @param <T>   el tipo de los elementos del arreglo, los cuales deben ser
    *              comparables entre sí
    * @param array el arreglo de los elementos a ordenar, no puede ser {@code null}
    */
   public static <T extends Comparable<? super T>> void mergeSort(T[] array) {
      mergeSort(array, 0, array.length - 1);
   }

   private static <T extends Comparable<? super T>> void mergeSort(T[] array, int low, int high) {
      if (low < high) {
         int mid = (low + high) / 2;
         mergeSort(array, low, mid);
         mergeSort(array, mid + 1, high);
         merge(array, low, mid, high);
      }
   }

   @SuppressWarnings("unchecked")
   private static <T extends Comparable<? super T>> void merge(T[] array, int left, int mid, int right) {
      int n1 = mid - left + 1;
      int n2 = right - mid;
      T[] leftArray = (T[]) new Object[n1];
      T[] rightArray = (T[]) new Object[n2];
      for (int i = 0; i < n1; ++i) {
         leftArray[i] = array[left + i];
      }
      for (int j = 0; j < n2; ++j) {
         rightArray[j] = array[mid + 1 + j];
      }
      int i = 0;
      int j = 0;
      int k = left;
      while (i < n1 && j < n2) {
         if (leftArray[i].compareTo(rightArray[j]) <= 0) {
            array[k] = leftArray[i];
            i++;
         } else {
            array[k] = rightArray[j];
            j++;
         }
         k++;
      }
      while (i < n1) {
         array[k] = leftArray[i];
         i++;
         k++;
      }
      while (j < n2) {
         array[k] = rightArray[j];
         j++;
         k++;
      }
   }

   /*
    * (non-Javadoc)
    * Este método intercambia dos posiciones de un arreglo.
    */
   private static <T extends Comparable<? super T>> void swap(T[] array, int i, int j) {
      T temp = array[i];
      array[i] = array[j];
      array[j] = temp;
   }

   /*
    * (non-Javadoc)
    * Este método retorna el indice del elemento mas grande.
    */
   private static <T extends Comparable<? super T>> int indexOfLargest(T[] array, int n) {
      int largest = 0;
      for (int i = 1; i < n; i++) {
         if (array[i].compareTo(array[largest]) > 0) {
            largest = i;
         }
      }
      return largest;
   }
}
