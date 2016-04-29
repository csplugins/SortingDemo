package edu.akron.algorithms.sorts;

import edu.akron.algorithms.visualize.Comparison;
import edu.akron.algorithms.visualize.SortStep;
import edu.akron.algorithms.visualize.Sorted;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class InsertionSort implements GenericSort {
    @Override
    public Sorted sort(int[] elements) {
        final Queue<SortStep> q = new LinkedList<>();
        for (int i = 1; i < elements.length; i++) {
            int temp = elements[i];
            int placeToInsert = i;
            while (placeToInsert > 0) {
                final int finalPlaceToInsert = placeToInsert, finalI = i;
                q.offer(new SortStep(elements, new HashSet<Comparison>() {
                    {
                        add(Comparison.basic(finalI));
                        add(Comparison.basic(finalPlaceToInsert - 1));
                    }
                }));
                if (elements[placeToInsert - 1] <= temp) continue;
                elements[placeToInsert] = elements[placeToInsert - 1];
                --placeToInsert;
            }
            elements[placeToInsert] = temp;
            q.offer(new SortStep(elements, Collections.emptySet()));
        }
        return new Sorted(elements, q);
    }
}
