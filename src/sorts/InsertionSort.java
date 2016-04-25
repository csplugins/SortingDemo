package sorts;

public class InsertionSort implements GenericSort {
    @Override
    public int[] sort(int[] elements) {
        for (int i = 1; i < elements.length; i++) {
            int temp = elements[i];
            int placeToInsert = i;
            while (placeToInsert > 0 && elements[placeToInsert - 1] > temp) {
                elements[placeToInsert] = elements[placeToInsert - 1];
                --placeToInsert;
            }
            elements[placeToInsert] = temp;
        }
        return elements;
    }
}
