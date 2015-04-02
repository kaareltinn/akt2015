package ee.ut.cs.akt.recognizers;


import java.text.ParseException;

public class BTRecognizer extends Recognizer {
    public BTRecognizer(String input) {
        super(input);
    }

    public static void main(String[] args) {
        Recognizer recognizer = new BTRecognizer(args[0]);
        recognizer.parse();
    }

    // Grammatika reeglid:
    // S -> aSb | ε
    void s() {
        int mark = pos;
        try {
            match('a');
            s();
            match('b');
        } catch (ParseException e) {
            pos = mark;
        }
        epsilon();
    }
}