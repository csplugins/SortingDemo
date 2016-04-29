import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class Tone {
    public static void main(String[] args) throws LineUnavailableException {
        final AudioFormat af = new AudioFormat(16 * 1024, 8, 1, true, true);
        SourceDataLine line = AudioSystem.getSourceDataLine(af);
        SourceDataLine line2 = AudioSystem.getSourceDataLine(af);
        line2.open(af, 16 * 1024);
        line2.start();
        line.open(af, 16 * 1024);
        line.start();
        int[] vals = new int[100];
        Integer min = null;
        Integer max = null;
        for (int i = 1; i <= 100; ++i) {
            vals[i - 1] = 2 * i;
            if (min == null || vals[i - 1] < min) {
                min = vals[i - 1];
            }
            if (max == null || vals[i - 1] > max) {
                max = vals[i - 1];
            }
        }
        double scale = (max - min + 1) / 13;
        for (int i = 0; i < vals.length - 20; ++i) {
            byte[] b = new byte[16 * 1024 * 2];
            byte[] b2 = new byte[16 * 1024 * 2];
            line.write(b, 0, 150);
            line2.write(b2, 0, 150);
            doMath(b, vals[i], scale);
            doMath(b2, vals[i + 20], scale);
            line.write(b, 0, 1000);
            line2.write(b2, 0, 1000);
        }
        line.drain();
        line.close();
        line2.drain();
        line2.close();
    }

    private static void doMath(byte[] b, int val, double scale) {
        double exp = ((double) val) / 6d / scale;
        double f = 440d * Math.pow(2d, exp);
        for (int i = 0; i < b.length; i++) {
            double period = (double) 32 * 1024 / f;
            double angle = 2.0 * Math.PI * i / period;
            b[i] = (byte) (Math.sin(angle) * 127f);
        }
    }
}