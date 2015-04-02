package ee.ut.cs.akt.recognizers;

import java.text.ParseException;
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
        for (int i = 0; i < N; ) {
            try {
                recognizer.attemptParse();
                System.out.println("Attempts: " + i);
                return;
            } catch (ParseException e) {
                i++;
            }
        }
        System.out.printf("REJECT! (After %d random trials.)\n", N);
    }

    // Grammatika reeglid:
    // S -> aSb | ε
    void s() throws ParseException {

        switch (generator.nextInt(2)) {
            case 0: // S -> aSb
                match('a');
                s();
                match('b');
            case 1: // S -> ε
                epsilon();
        }
    }
}
