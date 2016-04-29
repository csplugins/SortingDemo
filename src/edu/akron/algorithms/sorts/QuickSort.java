package edu.akron.algorithms.sorts;

import edu.akron.algorithms.visualize.SortStep;
import edu.akron.algorithms.visualize.Sorted;

import java.util.LinkedList;
import java.util.Queue;

public class QuickSort implements GenericSort {
    public int[] _sort(final Queue<SortStep> q, int[] elements, int left, int right) {
        if (right - left + 1 < 2) return elements;
        if (right - left + 1 == 2) {
            if (elements[left] > elements[right]) {
                int temp = elements[left];
                elements[left] = elements[right];
                elements[right] = temp;
            }
            return elements;
        }
        int a = elements[left], b = elements[(right - left) / 2], c = elements[right - left];
        int pivot = ((a - b) * (b - c) > -1 ? (right - left) / 2 : ((a - b) * (a - c) < 1 ? left : right - left));
        int temp = elements[pivot];
        elements[pivot] = elements[right - left];
        elements[right - left] = temp;
        pivot = right - left;
        int i = left - 1;
        int j = right - left;
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
        
        _sort(q, elements, left, i - 1);
        _sort(q, elements, i + 1 , right);

        return elements;
    }

    @Override
    public Sorted sort(int[] arr) {
        final Queue<SortStep> q = new LinkedList<>();
        final int[] arr2 = _sort(q, arr, 0, arr.length - 1);
        return new Sorted(arr2, q);
    }
}
