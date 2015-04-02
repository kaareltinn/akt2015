package ee.ut.cs.akt.tutorial;

import ee.ut.cs.akt.parsers.Node;

import java.text.ParseException;

public class TutAST {
    String input;
    int pos;

    public TutAST(String input) {
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
        TutAST parser = new TutAST(args[0]);
        parser.parse();
    }


    // Start symbol S.
    Node s() throws ParseException {
        Node _n;
        Node tmp = t();
        _n       = r(tmp);
        return _n;
        // ehk lihtsalt: return r(t())
    }

    Node r(Node left) throws ParseException {
        Node _n = null;
        switch(peek()) {
            case '+':
                Node tmp = match('+');
                tmp.add(left);
                tmp.add(t());
                _n = r(tmp);
                break;
            case '$':
            case ')':
                _n = left;
                break;
            default:
                unexpected();
        }
        return _n;
    }

    Node t() throws ParseException {
        return q(f());
    }

    Node f() throws ParseException {
        Node _n = null;
        switch(peek()) {
            case '(':
                match('(');
                _n = s();
                match(')');
                break;
            case 'x':
                _n = match('x');
                break;
            default:
                unexpected();
        }
        return _n;
    }

    Node q(Node left) throws ParseException {
        Node _n = null;
        switch(peek()) {
            case '*':
                _n = match('*');
                _n.add(left);
                _n.add(f());
                _n = q(_n);
                break;
            case '$':
            case '+':
            case ')':
                _n = left;
                break;
            default:
                unexpected();
        }
        return _n;
    }



}
