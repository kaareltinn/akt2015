package ee.ut.cs.akt.parsers;

public class LLParser extends Parser {

    public LLParser(String input) {
        super(input);
    }

    public static void main(String[] args) {
        Parser parser = new LLParser(args[0]);
        parser.testParser();
    }

    // Grammatika reeglid:
    // S -> aSb | ε
    protected Node s() {
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
                unexpected('a', 'b', '$');
        }
        return n;
    }

}
