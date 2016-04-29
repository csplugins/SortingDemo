package edu.akron.algorithms;

public enum Sample {
    IN_ORDER("Already Sorted", new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
    public final String tag;
    public final int[] data;

    Sample(final String tag, final int[] data) {
        this.tag = tag;
        this.data = data;
    }
}
