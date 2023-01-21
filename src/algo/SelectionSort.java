package algo;
/**
SelectionSort class providing selection sort algorithm
*/
public class SelectionSort {
    /**
    Sorts an Array and returns it
    */
    public int[] selectionSort(int[] array) {
        int j = 0, i = 0, smallest, aux;
        for (i = 0; i < array.length; i++) {
            smallest = i;
            for (j = i + 1; j < array.length; j++) {
                if (array[smallest] > array[j]) {
                    smallest = j;
                }
            }
            if (smallest != i) {
                aux = array[i];
                array[i] = array[smallest];
                array[smallest] = aux;
            }
        }
        return array;
    }
}