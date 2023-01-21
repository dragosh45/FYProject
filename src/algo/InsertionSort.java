package algo;
/**
InsertionSort class providing insertion sort algorithm
*/
public class InsertionSort {
    /**
    Sorts int[] array and returns it
    */
    public int[] insertionSort(int[] array) {
        int i, j, aux;
        for (i = 0; i < array.length; i++) {
            j = i;
            while ((j > 0) && (array[j] < array[j - 1])) {
                aux = array[j];
                array[j] = array[j - 1];
                array[j - 1] = aux;
                j--;
            }
        }
        return array;
    }
}