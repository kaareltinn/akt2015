package ee.ut.cs.akt.tutorial;

import ee.ut.cs.akt.parsers.Node;
import ee.ut.cs.akt.parsers.Parser;

public class TutParser extends Parser {

    public TutParser(String input) {
        super(input);
    }

    public static void main(String[] args) {
        TutParser parser = new TutParser(args[0]);
        parser.parse();
    }

    // S → T R
    protected Node s() {
        Node n = new Node("S");
        n.add(t());
        n.add(r());
        return n;
    }

    // R → '+' T R | ε
    private Node r() {
        Node n = new Node("R");
        switch(peek()) {
            case '+':
                n.add(match('+'));
                n.add(t());
                n.add(r());
                break;
            case '$':
            case ')':
                n.add(epsilon());
                break;
            default:
                unexpected('+', '$', ')');
        }
        return n;
    }

    // T → F Q
    private Node t() {
        Node n = new Node("T");
        n.add(f());
        n.add(q());
        return n;
    }

    // Q → '*' F Q | ε
    private Node q() {
        Node n = new Node("Q");
        switch(peek()) {
            case '*':
                n.add(match('*'));
                n.add(f());
                n.add(q());
                break;
            case '$':
            case '+':
            case ')':
                n.add(epsilon());
                break;
            default:
                unexpected('*', '+', '$', ')');
        }
        return n;
    }

    // F → 'x' | '(' S ')'
    private Node f() {
        Node n = new Node("F");
        switch(peek()) {
            case '(':
                n.add(match('('));
                n.add(s());
                n.add(match(')'));
                break;
            case 'x':
                n.add(match('x'));
                break;
            default:
                unexpected('(', 'x');
        }
        return n;
    }

}
