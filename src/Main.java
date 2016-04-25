import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class Main implements Runnable {
    private final JFrame frame;

    public enum ASort {
        SAMPLE("Sample");
        private final String name;

        ASort(final String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public Main() {
        frame = new JFrame();
        frame.setTitle("Sort Visualizer");
        frame.setLayout(new BorderLayout());
        final JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout());
        p1.add(new JLabel("Sort"));
        p1.add(new JComboBox<ASort>() {
            {
                addItem(ASort.SAMPLE);
            }
        });
        p1.add(new JLabel("Delay"));
        p1.add(new JSlider(1, 100, 25));
        p1.add(new JButton("Start"));

        frame.add(p1, BorderLayout.NORTH);
        frame.add(new SortVisualizer(), BorderLayout.CENTER);
    }

    public static void main(final String[] params) {
        final Runnable r = new Main();
        r.run();
    }

    @Override
    public void run() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(frame.getParent());
        frame.setVisible(true);
    }

    public class SortVisualizer extends JPanel {
        public SortVisualizer() {
            this(500, 500);
        }

        public SortVisualizer(final int w, final int h) {
            super();
            final Dimension d = new Dimension(w, h);
            setMinimumSize(d);
            setSize(d);
            setPreferredSize(d);
            setMaximumSize(d);
        }
    }
}
