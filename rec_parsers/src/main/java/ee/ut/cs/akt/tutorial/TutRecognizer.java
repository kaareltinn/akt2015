package ee.ut.cs.akt.tutorial;

import ee.ut.cs.akt.parsers.Node;
import ee.ut.cs.akt.recognizers.Recognizer;

public class TutRecognizer extends Recognizer {

    public TutRecognizer(String input) {
        super(input);
    }

    public static void main(String[] args) {
        TutRecognizer recognizer = new TutRecognizer(args[0]);
        recognizer.parse();
    }

    // S → T R
    protected void s() {
        t();
        r();
    }

    // R → '+' T R | ε
    private void r() {
        switch (peek()) {
            case '+':
                match('+');
                t();
                r();
                break;
            case '$':
            case ')':
                epsilon();
                break;
            default:
                unexpected();
        }
    }

    // T → F Q
    private void t() {
        f();
        q();
    }

    // Q → '*' F Q | ε
    private void q() {
        switch(peek()) {
            case '*':
                match('*');
                f();
                q();
                break;
            case '$':
            case '+':
            case ')':
                epsilon();
                break;
            default:
                unexpected();
        }
    }

    // F → 'x' | '(' S ')'
    private Node f() {
        Node _n = new Node("F");
        switch(peek()) {
            case '(':
                match('(');
                s();
                match(')');
                break;
            case 'x':
                match('x');
                break;
            default:
                unexpected();
        }
        return _n;
    }
}
