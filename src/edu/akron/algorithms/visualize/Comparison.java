package edu.akron.algorithms.visualize;

import java.awt.Color;

public class Comparison {
    private final Color color;
    private final int index;

    public Comparison(final Color color, final int index) {
        this.color = color;
        this.index = index;
    }

    public static Comparison basic(final int index) {
        return new Comparison(Color.red, index);
    }

    @Override
    public String toString() {
        return String.format("%s - %s", color.toString(), index);
    }
}
