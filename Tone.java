//package sortingsolution;

import javax.sound.sampled.*;

public class Tone {

    /*public static void main(String[] args) throws LineUnavailableException {
        final AudioFormat af =
            new AudioFormat(Note.SAMPLE_RATE, 8, 1, true, true);
        SourceDataLine line = AudioSystem.getSourceDataLine(af);
        line.open(af, Note.SAMPLE_RATE);
        line.start();
        for(Note n : Note.values()){
            play(line, n, 20);
            play(line, Note.REST, 10);
        }
        line.drain();
        line.close();
    }*/

    private static void play(SourceDataLine line, Note note, int ms){
        ms = Math.min(ms, Note.SECONDS * 1000);
        int length = Note.SAMPLE_RATE * ms / 1000;
        line.write(note.data(), 0, length);
    }
}

enum Note {

    REST,
    A0, A0$, B0, C0, C0$, D0, D0$, E0, F0, F0$, G0, G0$,
    A1, A1$, B1, C1, C1$, D1, D1$, E1, F1, F1$, G1, G1$,
    A2, A2$, B2, C2, C2$, D2, D2$, E2, F2, F2$, G2, G2$,
    A3, A3$, B3, C3, C3$, D3, D3$, E3, F3, F3$, G3, G3$,
    A4, A4$, B4, C4, C4$, D4, D4$, E4, F4, F4$, G4, G4$,
    A5, A5$, B5, C5, C5$, D5, D5$, E5, F5, F5$, G5, G5$,
    A6, A6$, B6, C6, C6$, D6, D6$, E6, F6, F6$, G6, G6$,
    A7, A7$, B7, C7, C7$, D7, D7$, E7, F7, F7$, G7, G7$,
    A8, A8$, B8, C8;
    public static final int SAMPLE_RATE = 16 * 1024; // ~16KHz
    public static final int SECONDS = 2;
    private final byte[] sin = new byte[SECONDS * SAMPLE_RATE];

    Note(){
        int n = this.ordinal();
        if (n > 0){
            double exp = ((double) n - 1) / 16d;
            double f = 110d * Math.pow(1.6d, exp);
            for(int i = 0; i < sin.length; i++){
                double period = (double)SAMPLE_RATE / f;
                double angle = 2.0 * Math.PI * i / period;
                sin[i] = (byte)(Math.sin(angle) * 127f);
            }
        }
    }

    public byte[] data() {
        return sin;
    }
}