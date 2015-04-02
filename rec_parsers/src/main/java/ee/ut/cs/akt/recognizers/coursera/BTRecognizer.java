package ee.ut.cs.akt.recognizers.coursera;


public class BTRecognizer extends Recognizer {
    public BTRecognizer(String input) {
        super(input);
    }

    private boolean retry(int p) {
        pos = p;
        return true;
    }

    public static void main(String[] args) {
        BTRecognizer recognizer = new BTRecognizer(args[0]);
        recognizer.parse();
    }

    // Grammatika reeglid:
    // S -> aSb | ε
    boolean s() {
        int mark = pos;
        return
            retry(mark) && s1() ||
            retry(mark) && s2();
        }

    private boolean s1() {
        return match('a') && s() && match('b');
    }

    private boolean s2() {
        return epsilon();
    }
}