package ee.ut.cs.akt.parsers;

import java.text.ParseException;

public class BTParser extends Parser {
    public BTParser(String input) {
        super(input);
    }

    public static void main(String[] args) {
        Parser parser = new BTParser(args[0]);
        parser.parse();
    }

    // Grammatika reeglid:
    // S -> aSb | ε
    Node s() throws ParseException {
        int mark = pos;
        Node n = new Node("S");
        try {
            n.add(match('a'));
            n.add(s());
            n.add(match('b'));
        } catch (ParseException e) {
            pos = mark;
            n = new Node("S");
        }
        n.add(epsilon());
        return n;
    }
}
