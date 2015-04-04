package ee.ut.cs.akt.recognizers;

import ee.ut.cs.akt.parsers.Node;
import ee.ut.cs.akt.parsers.ParseException;
import ee.ut.cs.akt.parsers.Parser;

public class BTRecognizer extends Parser {
    public BTRecognizer(String input) {
        super(input);
    }

    // Grammatika reeglid:
    // S -> aSb | ε
    protected Node s() {
        int mark = pos;
        try {
            match('a');
            s();
            match('b');
        } catch (ParseException e) {
            pos = mark;
        }
        epsilon();
        return null;
    }
}