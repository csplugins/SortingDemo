package edu.akron.algorithms;

public enum Sample {
    IN_ORDER("Already Sorted", new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}),
    REVERSE("Reversed Array", new int[]{20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1});
    public final String tag;
    private final int[] data;

    Sample(final String tag, final int[] data) {
        this.tag = tag;
        this.data = data;
    }

    public int[] data() {
        return this.data.clone();
    }

    @Override
    public String toString() {
        return tag;
    }
}
