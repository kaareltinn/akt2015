package ee.ut.cs.akt.tutorial;

import ee.ut.cs.akt.parsers.Node;

import java.text.ParseException;

public class TutParser {
    String input;
    int pos;

    public TutParser(String input) {
        this.input = input;
    }

    public char peek() {
        if (pos < input.length())
            return input.charAt(pos);
        return '$'; // EOF sümbol
    }

    Node match(char x) throws ParseException {
        if (pos < input.length()) {
            char y = input.charAt(pos);
            if (y == x) {
                pos++;
                return new Node(x);
            } else
                throw new ParseException("Unexpected char " + y, pos);
        } else
            throw new ParseException("Unexpected end of file.", pos);
    }

    void unexpected() throws ParseException {
        char y = input.charAt(pos);
        throw new ParseException("Unexpected char " + y, pos);
    }

    Node epsilon() {
        return new Node('\u03B5');
    }

    void done() throws ParseException {
        if (pos < input.length()) {
            throw new ParseException("Unexpected input.", pos);
        }
    }

    public Node parse() {
        System.out.println("Parsing: " + input);
        try {
            return attemptParse();
        } catch (ParseException e) {
            System.out.println("Parse error: " + e.getMessage());
            System.out.println(input + '$');
            for (int i = 0; i < e.getErrorOffset(); i++)
                System.out.print(' ');
            System.out.println('^');
            //e.printStackTrace();
            return null;
        }
    }

    public Node attemptParse() throws ParseException {
        pos = 0;
        Node root = s();
        done();
        System.out.print("ACCEPT: ");
        System.out.println(root);
        root.makeDot();
        return root;
    }

    public static void main(String[] args) {
        TutParser parser = new TutParser(args[0]);
        parser.parse();
    }

    // Start symbol S.
    Node s() throws ParseException {
        Node _n = new Node("S");
        _n.add(t());
        _n.add(r());
        return _n;
    }

    Node r() throws ParseException {
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

    Node t() throws ParseException {
        Node _n = new Node("T");
        _n.add(f());
        _n.add(q());
        return _n;
    }

    Node q() throws ParseException {
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

    Node f() throws ParseException {
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
