package ee.ut.cs.akt.tutorial;

import ee.ut.cs.akt.parsers.Node;
import ee.ut.cs.akt.parsers.Parser;

public class TutAST extends Parser {

    public TutAST(String input) {
        super(input);
    }

    public static void main(String[] args) {
        TutAST parser = new TutAST(args[0]);
        parser.testParser();
    }

    // Start symbol S.
    protected Node s() {
        Node n;
        Node tmp = t();
        n = r(tmp);
        return n;
        // ehk lihtsalt: return r(t())
    }

    private Node r(Node left) {
        Node n = null;
        switch(peek()) {
            case '+':
                Node tmp = match('+');
                tmp.add(left);
                tmp.add(t());
                n = r(tmp);
                break;
            case '$':
            case ')':
                n = left;
                break;
            default:
                unexpected();
        }
        return n;
    }

    private Node t() {
        return q(f());
    }

    private Node f() {
        Node n = null;
        switch(peek()) {
            case '(':
                match('(');
                n = s();
                match(')');
                break;
            case 'x':
                n = match('x');
                break;
            default:
                unexpected();
        }
        return n;
    }

    private Node q(Node left) {
        Node n = null;
        switch(peek()) {
            case '*':
                n = match('*');
                n.add(left);
                n.add(f());
                n = q(n);
                break;
            case '$':
            case '+':
            case ')':
                n = left;
                break;
            default:
                unexpected();
        }
        return n;
    }

}
