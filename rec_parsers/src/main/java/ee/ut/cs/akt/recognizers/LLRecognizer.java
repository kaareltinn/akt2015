package ee.ut.cs.akt.recognizers;

public class LLRecognizer extends Recognizer {

    public LLRecognizer(String input) {
        super(input);
    }

    public static void main(String[] args) {
        Recognizer recognizer = new LLRecognizer(args[0]);
        recognizer.parse();
    }

    // Grammatika reeglid:
    // S -> aSb | ε
    protected void s() throws ParseException {
        switch(peek()) {
            case 'a':
                match('a');
                s();
                match('b');
                break;
            case 'b':
            case '$':
                epsilon();
                break;
            default:
                unexpected();
        }
    }
}
