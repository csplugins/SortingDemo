package edu.akron.algorithms.visualize;

import java.util.Queue;

public class Sorted {
    private final int[] array;
    private final Queue<SortStep> steps;

    public Sorted(final int[] array, final Queue<SortStep> steps) {
        this.array = array;
        this.steps = steps;
    }
}
