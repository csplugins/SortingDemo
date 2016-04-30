package edu.akron.algorithms.sorts;

import edu.akron.algorithms.visualize.Comparison;
import edu.akron.algorithms.visualize.SortStep;
import edu.akron.algorithms.visualize.Sorted;
import edu.akron.algorithms.tone.Tone;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;

public class BubbleSort implements GenericSort {
    @Override
    public Sorted sort(int[] elements) {
        final Queue<SortStep> q = new LinkedList<>();
        Tone tone = null;
        try {
            tone = new Tone(elements);
        } catch (LineUnavailableException ignored) {
        }
        int n = elements.length;
        for (int i = 0; i < elements.length; ++i) {
            for (int j = 0; j < n - 1; ++j) {
                final int finalJ = j;
                q.offer(new SortStep(elements, new HashSet<Comparison>() {
                    {
                        add(Comparison.basic(finalJ));
                        add(Comparison.basic(finalJ + 1));
                    }
                }));
                tone.PlayComparison(finalJ, finalJ + 1);
                if (elements[j] > elements[j + 1]) {
                    int temp = elements[j];
                    elements[j] = elements[j + 1];
                    elements[j + 1] = temp;
                }
            }
            n--;
        }
        return new Sorted(elements, q);
    }
}
