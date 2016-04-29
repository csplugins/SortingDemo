package edu.akron.algorithms.sorts;

import edu.akron.algorithms.visualize.Comparison;
import edu.akron.algorithms.visualize.SortStep;
import edu.akron.algorithms.visualize.Sorted;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class InsertionSort implements GenericSort {
    @Override
    public Sorted sort(int[] elements) {
        final Queue<SortStep> q = new LinkedList<>();
        for (int i = 1; i < elements.length; i++) {
            final int temp = elements[i];
            int pos = i;
            while (pos > 0) {
                final int finalI = i;
                final int finalPos = pos;
                q.offer(new SortStep(elements, new HashSet<Comparison>() {
                    {
                        add(Comparison.special(finalI));
                        add(Comparison.basic(finalPos - 1));
                        add(Comparison.basic(finalPos));
                    }
                }));
                if (elements[pos - 1] <= temp) continue;
                elements[pos] = elements[pos - 1];
                --pos;
            }
            elements[pos] = temp;
        }
        return new Sorted(elements, q);
    }
}
