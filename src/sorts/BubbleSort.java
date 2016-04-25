package sorts;

public class BubbleSort implements GenericSort {
    @Override
    public int[] sort(int[] elements) {
        int n = elements.length;
        for (int i = 0; i < elements.length; ++i) {
            for (int j = 0; j < n - 1; ++j) {
                if (elements[j] > elements[j + 1]) {
                    int temp = elements[j];
                    elements[j] = elements[j + 1];
                    elements[j + 1] = temp;
                }
            }
            n--;
        }
        return elements;
    }
}
