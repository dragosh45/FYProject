package algo;
/**
BinarySearch class providing binary search algorithm
*/
public class BinarySearch {
    /**
     * Searches in array array[] for an integer x and returns true or false
     * @param array to find x in
     * @param x element to look for
     * @return
     */
    public boolean binarySearch(int[] array, int x) {
        int low = 1, high = array.length - 1, middle;
        while (low <= high) {
            middle = (low + (high)) / 2;
            if (x == array[middle]) {
                return true;
            }
            if (x < array[middle]) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }
        return false;
    }
}