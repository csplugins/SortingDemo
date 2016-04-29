package edu.akron.algorithms.visualize;

import java.util.Arrays;
import java.util.Set;

public class SortStep {
    private final int[] arr;
    private final Set<Comparison> comparisons;

    public SortStep(final int[] arr, final Set<Comparison> comparisons) {
        this.arr = arr.clone();
        this.comparisons = comparisons;
    }

    @Override
    public String toString() {
        return String.format("%s[%s]", Arrays.toString(arr), comparisons);
    }
}
