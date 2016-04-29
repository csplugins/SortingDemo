package edu.akron.algorithms.sorts;

import edu.akron.algorithms.visualize.SortStep;
import edu.akron.algorithms.visualize.Sorted;

import java.util.LinkedList;
import java.util.Queue;

public class MergeSort implements GenericSort {
    public int[] _sort(final Queue<SortStep> q, int[] elements, int left , int right) {
        if (right - left + 1 < 2)
            return elements;
        if (right - left + 1 == 2)
            if (elements[left] > elements[right]) {
                int temp = elements[left];
                elements[left] = elements[right];
                elements[right] = temp;
                return elements;
            }
        int halfPoint = (right - left + 1) / 2;
        _sort(q, elements, left, halfPoint);
        _sort(q, elements, halfPoint + 1 , right);
        return elements;
    }

    @Override
    public Sorted sort(int[] arr) {
        final Queue<SortStep> q = new LinkedList<>();
        final int[] arr2 = _sort(q, arr, 0, arr.length);
        return new Sorted(arr2, q);
    }
}
