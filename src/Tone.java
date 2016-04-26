package tone;

import javax.sound.sampled.*;

public class Tone {
    public static void main(String[] args) throws LineUnavailableException {
        final AudioFormat af = new AudioFormat(16*1024, 8, 1, true, true);
        SourceDataLine line = AudioSystem.getSourceDataLine(af);
        line.open(af, 16*1024);
        line.start();
        int[] vals = new int[100];
        Integer min = null;
        Integer max = null;
        for(int i = 1; i <= 100; ++i){
            vals[i-1] = i%5 * i;
            if(min == null || vals[i-1] < min){
                min = vals[i-1];
            }
            if(max == null || vals[i-1] > max){
                max = vals[i-1];
            }
        }
        double scale = max - min + 1;
        scale = scale / 13;
        System.out.println(scale);
        for(int i = 0; i < vals.length; ++i){
            byte[] b = new byte[16*1024*2];
            double exp = ((double) vals[i]) / 6d / scale;
                double f = 440d * Math.pow(2d, exp);
                for(int j = 0; j < b.length; j++){
                    double period = (double)32*1024 / f;
                    double angle = 2.0 * Math.PI * j / period;
                    b[j] = (byte)(Math.sin(angle) * 127f);
                }
            line.write(b, 0, 600);
            b = new byte[16*1024*2];
            line.write(b, 0, 150);
        }
        line.drain();
        line.close();
    }
}

/*public class Tone {

    public static void main(String[] args) throws LineUnavailableException {
        final AudioFormat af =
            new AudioFormat(Note.SAMPLE_RATE, 8, 1, true, true);
        SourceDataLine line = AudioSystem.getSourceDataLine(af);
        line.open(af, Note.SAMPLE_RATE);
        line.start();
        for  (Note n : Note.values()) {
            play(line, n, 500);
            play(line, Note.REST, 10);
        }
        line.drain();
        line.close();
    }

    private static void play(SourceDataLine line, Note note, int ms) {
        ms = Math.min(ms, Note.SECONDS * 1000);
        int length = Note.SAMPLE_RATE * ms / 1000;
        int count = line.write(note.data(), 0, length);
    }
}

enum Note {

    REST, A4, A4$, B4, C4, C4$, D4, D4$, E4, F4, F4$, G4, G4$, A5;
    public static final int SAMPLE_RATE = 16 * 1024; // ~16KHz
    public static final int SECONDS = 2;
    private byte[] sin = new byte[SECONDS * SAMPLE_RATE];

    Note() {
        int n = this.ordinal();
        if (n > 0) {
            double exp = ((double) n - 1) / 12d;
            double f = 440d * Math.pow(2d, exp);
            for (int i = 0; i < sin.length; i++) {
                double period = (double)SAMPLE_RATE / f;
                double angle = 2.0 * Math.PI * i / period;
                sin[i] = (byte)(Math.sin(angle) * 127f);
            }
        }
    }

    public byte[] data() {
        return sin;
    }
}*/