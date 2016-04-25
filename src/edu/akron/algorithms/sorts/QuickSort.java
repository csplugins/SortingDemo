package edu.akron.algorithms.sorts;

public class QuickSort implements GenericSort {
    @Override
    public int[] sort(int[] elements) {
        if (elements.length < 2) return elements;
        if (elements.length == 2) {
            if (elements[0] > elements[1]) {
                int temp = elements[0];
                elements[0] = elements[1];
                elements[1] = temp;
            }
            return elements;
        }
        int a = elements[0], b = elements[(elements.length - 1) / 2], c = elements[elements.length - 1];
        int pivot = ((a - b) * (b - c) > -1 ? (elements.length - 1) / 2 : ((a - b) * (a - c) < 1 ? 0 : elements.length - 1));
        int temp = elements[pivot];
        elements[pivot] = elements[elements.length - 1];
        elements[elements.length - 1] = temp;
        pivot = elements.length - 1;
        int i = -1;
        int j = elements.length - 1;
        do {
            while (elements[++i] < elements[pivot]) ;
            while (i < j && elements[--j] > elements[pivot]) ;
            temp = elements[i];
            elements[i] = elements[j];
            elements[j] = temp;
        } while (i < j);
        temp = elements[pivot];
        elements[pivot] = elements[i];
        elements[i] = temp;

        int[] left = new int[i];
        int[] right = new int[elements.length - i - 1];
        System.arraycopy(elements, 0, left, 0, i);
        for (int k = i + 1; k < elements.length; ++k) {
            right[k - i - 1] = elements[k];
        }

        left = sort(left);
        right = sort(right);

        System.arraycopy(left, 0, elements, 0, left.length);
        for (int z = 0; z < right.length; z++) {
            elements[elements.length - 1 - z] = right[right.length - 1 - z];
        }

        return elements;
    }
}
