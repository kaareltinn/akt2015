package ee.ut.cs.akt.recognizers.coursera;

public class LLRecognizer extends Recognizer {

    public LLRecognizer(String input) {
        super(input);
    }

    /**
     * Ennustav parser piilub sisendis ette ja selle tulemuse põhjal
     * otsustab millist produktsiooni valida.
     */
    public char peek() {
        if (pos < input.length())
            return input.charAt(pos);
        return '$'; // EOF sümbol
    }

    public static void main(String[] args) {
        LLRecognizer recognizer = new LLRecognizer(args[0]);
        recognizer.parse();
    }

    // Grammatika reeglid:
    // S -> aSb | ε
    boolean s() {
        switch (peek()) {
            case 'a': // S -> aSb
                return match('a') && s() && match('b');
            default:  // S -> ε
                return epsilon();
        }
    }
}
