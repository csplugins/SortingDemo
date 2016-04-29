package edu.akron.algorithms.sorts;

import edu.akron.algorithms.visualize.SortStep;
import edu.akron.algorithms.visualize.Sorted;

import java.util.LinkedList;
import java.util.Queue;

public class InsertionSort implements GenericSort {
    @Override
    public Sorted sort(int[] elements) {
        final Queue<SortStep> q = new LinkedList<>();
        for (int i = 1; i < elements.length; i++) {
            int temp = elements[i];
            int placeToInsert = i;
            while (placeToInsert > 0 && elements[placeToInsert - 1] > temp) {
                elements[placeToInsert] = elements[placeToInsert - 1];
                --placeToInsert;
            }
            elements[placeToInsert] = temp;
        }
        return new Sorted(elements, q);
    }
}
