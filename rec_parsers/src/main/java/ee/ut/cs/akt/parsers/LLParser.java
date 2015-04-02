package ee.ut.cs.akt.parsers;

import java.text.ParseException;

public class LLParser extends Parser {

    public LLParser(String input) {
        super(input);
    }

    public static void main(String[] args) {
        Parser parser = new LLParser(args[0]);
        parser.parse();
    }

    // Grammatika reeglid:
    // S -> aSb | ε
    Node s() throws ParseException {
        Node n = new Node("S");
        switch(peek()) {
            case 'a':
                n.add(match('a'));
                n.add(s());
                n.add(match('b'));
                break;
            case 'b':
            case '$':
                n.add(epsilon());
                break;
            default:
                unexpected();
        }
        return n;
    }

}
