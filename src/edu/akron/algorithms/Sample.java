package edu.akron.algorithms;

public enum Sample {
    IN_ORDER("Already Sorted", new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}),
    REVERSE("Reversed Array", new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1});
    public final String tag;
    public final int[] data;

    Sample(final String tag, final int[] data) {
        this.tag = tag;
        this.data = data;
    }

    @Override
    public String toString() {
        return tag;
    }
}
