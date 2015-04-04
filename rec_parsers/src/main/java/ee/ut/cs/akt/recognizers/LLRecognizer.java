package ee.ut.cs.akt.recognizers;

import ee.ut.cs.akt.parsers.Node;
import ee.ut.cs.akt.parsers.Parser;

public class LLRecognizer extends Parser {

    public LLRecognizer(String input) {
        super(input);
    }

    public static void main(String[] args) {
        Parser parser = new LLRecognizer(args[0]);
        parser.testRecognizer();
    }

    // Grammatika reeglid:
    // S -> aSb | ε
    protected Node s() {
        switch (peek()) {
            case 'a':
                // S -> aSb
                match('a');
                s();
                match('b');
                break;
            case 'b':
            case '$':
                // S -> ε
                epsilon();
                break;
            default:
                unexpected('a', 'b', '$');
        }
        return null;
    }
}
