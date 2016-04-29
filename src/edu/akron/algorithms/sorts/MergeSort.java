package edu.akron.algorithms.sorts;

import edu.akron.algorithms.visualize.SortStep;
import edu.akron.algorithms.visualize.Sorted;

import java.util.LinkedList;
import java.util.Queue;

public class MergeSort implements GenericSort {
    public int[] _sort(final Queue<SortStep> q, int[] elements) {
        if (elements.length < 2)
            return elements;
        if (elements.length == 2)
            if (elements[0] > elements[1]) {
                int temp = elements[0];
                elements[0] = elements[1];
                elements[1] = temp;
                return elements;
            }
        int halfPoint = elements.length / 2;
        int[] firstHalf = new int[halfPoint];
        int[] secondHalf = new int[elements.length - halfPoint];
        System.arraycopy(elements, 0, firstHalf, 0, firstHalf.length);
        System.arraycopy(elements, halfPoint, secondHalf, 0, secondHalf.length);
        firstHalf = _sort(q, firstHalf);
        secondHalf = _sort(q, secondHalf);
        int i = 0, j = 0, count = 0;
        while (i < firstHalf.length && j < secondHalf.length) {
            if (firstHalf[i] <= secondHalf[j]) {
                elements[count] = firstHalf[i];
                ++i;
            } else {
                elements[count] = secondHalf[j];
                ++j;
            }
            count++;
        }
        if (i < j)
            for (int k = i; k < firstHalf.length; k++) {
                elements[count] = firstHalf[k];
                count++;
            }
        else for (int k = j; k < secondHalf.length; k++) {
            elements[count] = secondHalf[k];
            count++;
        }
        return elements;
    }

    @Override
    public Sorted sort(int[] arr) {
        final Queue<SortStep> q = new LinkedList<>();
        final int[] arr2 = _sort(q, arr);
        return new Sorted(arr2, q);
    }
}
