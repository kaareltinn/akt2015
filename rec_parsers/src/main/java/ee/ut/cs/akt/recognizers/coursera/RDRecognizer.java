package ee.ut.cs.akt.recognizers.coursera;

import java.util.Random;

public class RDRecognizer extends Recognizer {
    static int N = 1000;
    Random generator;

    public RDRecognizer(String input) {
        super(input);
        generator = new Random();
    }

    public static void main(String[] args) {
        Recognizer recognizer = new RDRecognizer(args[0]);
        for (int i = 0; i < N; i++) {
            if (recognizer.attemptParse()) {
                System.out.println("Attempts: " + i);
                return;
            }
        }
        System.out.printf("REJECT! (After %d random trials.)\n", N);
    }

    // Grammatika reeglid:
    // S -> aSb | ε
    public boolean s() {

        switch (generator.nextInt(2)) {
            case 0: // S -> aSb
                return match('a') && s() && match('b');
            case 1: // S -> ε
                return epsilon();
        }
        return false; // (to make compiler happy.)
    }
}
