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
        Node _n = new Node("S");
        _n.add(t());
        _n.add(r());
        return _n;
    }

    // R → '+' T R | ε
    private Node r() {
        Node _n = new Node("R");
        switch(peek()) {
            case '+':
                _n.add(match('+'));
                _n.add(t());
                _n.add(r());
                break;
            case '$':
            case ')':
                _n.add(epsilon());
                break;
            default:
                unexpected();
        }
        return _n;
    }

    // T → F Q
    private Node t() {
        Node _n = new Node("T");
        _n.add(f());
        _n.add(q());
        return _n;
    }

    // Q → '*' F Q | ε
    private Node q() {
        Node _n = new Node("Q");
        switch(peek()) {
            case '*':
                _n.add(match('*'));
                _n.add(f());
                _n.add(q());
                break;
            case '$':
            case '+':
            case ')':
                _n.add(epsilon());
                break;
            default:
                unexpected();
        }
        return _n;
    }

    // F → 'x' | '(' S ')'
    private Node f() {
        Node _n = new Node("F");
        switch(peek()) {
            case '(':
                _n.add(match('('));
                _n.add(s());
                _n.add(match(')'));
                break;
            case 'x':
                _n.add(match('x'));
                break;
            default:
                unexpected();
        }
        return _n;
    }

}
