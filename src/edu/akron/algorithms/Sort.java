package edu.akron.algorithms;

import edu.akron.algorithms.sorts.*;

public enum Sort {
    QUICK("Quick Sort", new QuickSort()),
    INSERT("Insertion Sort", new InsertionSort()),
    MERGE("Merge Sort", new MergeSort()),
    BUBBLE("Bubble Sort", new BubbleSort()),
    RADIX("Radix Sort", new RadixSort());
    private final String name;
    private final GenericSort sort;

    Sort(final String name, final GenericSort sort) {
        this.name = name;
        this.sort = sort;
    }

    public GenericSort getSort() {
        return sort;
    }

    @Override
    public String toString() {
        return name;
    }
}
