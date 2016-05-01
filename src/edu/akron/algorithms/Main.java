package edu.akron.algorithms;

import edu.akron.algorithms.sorts.GenericSort;
import edu.akron.algorithms.tone.Tone;
import edu.akron.algorithms.visualize.Comparison;
import edu.akron.algorithms.visualize.SortStep;
import edu.akron.algorithms.visualize.Sorted;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class Main implements Runnable {
    private static final int BAR_PADDING = 2;
    private final JFrame frame;
    private JComboBox sort, sample;
    private JSlider slider;

    public Main() {
        frame = new JFrame();
        frame.setTitle("Sort Visualizer");
        frame.setLayout(new BorderLayout());
        final JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout());
        p1.add(new JLabel("Sort"));
        p1.add(sort = new JComboBox<>(Sort.values()));
        p1.add(new JLabel("Data"));
        p1.add(sample = new JComboBox<>(Sample.values()));
        p1.add(new JLabel("Delay"));
        p1.add(slider = new JSlider(1, 1000, 25));
        final JButton b;
        p1.add(b = new JButton("Start"));

        frame.add(p1, BorderLayout.NORTH);
        final SortVisualizer v;
        frame.add(v = new SortVisualizer(), BorderLayout.CENTER);
        b.addActionListener(v);
    }

    public static void main(final String[] params) {
        final Runnable r = new Main();
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (final ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ignored) {
            }
            r.run();
        });
    }

    @Override
    public void run() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(frame.getParent());
        frame.setVisible(true);
    }

    public class SortVisualizer extends JPanel implements ActionListener {
        private final int w, h;
        private final AtomicBoolean running = new AtomicBoolean(false);
        private AtomicReference<SortStep> step = new AtomicReference<>();

        public SortVisualizer() {
            this(800, 500);
        }

        public SortVisualizer(final int w, final int h) {
            super();
            this.w = w;
            this.h = h;
            final Dimension d = new Dimension(w, h);
            setMinimumSize(d);
            setSize(d);
            setPreferredSize(d);
            setMaximumSize(d);
        }

        @Override
        public void paintComponent(final Graphics g) {
            g.setColor(Color.black);
            g.fillRect(0, 0, w, h);
            final SortStep step = this.step.get();
            if (step == null) {
                return;
            }
            final int[] data = step.arr;
            final int[] d2;
            Arrays.sort(d2 = data.clone());
            final int bar_width = (int) Math.floor(w / (double) data.length);
            for (int index = 0; index < data.length; ++index) {
                final int sx = index * bar_width + BAR_PADDING, ex = sx + bar_width - BAR_PADDING * 2;
                final int height = (int) Math.round(h * (data[index] / (double) d2[d2.length - 1]));
                g.setColor(Color.white);
                for (final Comparison c : step.comparisons) if (c.index == index) g.setColor(c.color);
                g.fillRect(sx, h - height, ex - sx, (int) Math.round(h * (data[index] / (double) d2[d2.length - 1])));
            }
        }

        @Override
        public void actionPerformed(final ActionEvent e) {
            final Sort s = (Sort) sort.getSelectedItem();
            final Sample s2 = (Sample) sample.getSelectedItem();
            if (!running.compareAndSet(false, true)) return;
            final Thread t = new Thread(() -> {
                final int[] data = s2.data();
                final GenericSort sort = s.getSort();
                final int[] soda;
                final Sorted sorted = sort.sort(soda = Arrays.copyOf(data, Math.min(data.length, s.limit)));
                Tone tone = null;
                try {
                    tone = new Tone(soda);
                } catch (final LineUnavailableException ignored) {
                }
                for (final SortStep step : sorted.steps) {
                    this.step.set(step);
                    Object[] ta = null;
                    if (step.comparisons.size() != 2) {
                        final HashSet<Comparison> set2 = new HashSet<>(step.comparisons);
                        set2.removeIf(comparison -> !comparison.isBasic());
                        if (set2.size() == 2) ta = set2.toArray();
                    } else ta = step.comparisons.toArray();
                    try {
                        SwingUtilities.invokeAndWait(this::repaint);
                    } catch (final InterruptedException | InvocationTargetException ignored) {
                    }
                    if (tone != null && ta != null) {
                        tone.compare(slider.getValue(), soda[((Comparison) ta[0]).index], soda[((Comparison) ta[1]).index]);
                    }
                    try {
                        Thread.sleep(slider.getValue());
                    } catch (final InterruptedException ignored) {
                    }
                }
                this.step.set(new SortStep(sorted.array, Collections.emptySet()));
                try {
                    SwingUtilities.invokeAndWait(this::repaint);
                } catch (final InterruptedException | InvocationTargetException ignored) {
                }
                if (tone != null) tone.complete(slider.getValue(), sorted.array);
                running.set(false);
            });
            t.start();
        }
    }
}
