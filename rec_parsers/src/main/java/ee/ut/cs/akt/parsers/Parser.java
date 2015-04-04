package ee.ut.cs.akt.parsers;

import ee.ut.cs.akt.recognizers.ParseException;

public abstract class Parser {
    String input;
    int pos;

    public Parser(String input) {
        this.input = input + '$';
    }

    public char peek() {
        return input.charAt(pos);
    }

    protected Node match(char x) throws ParseException {
        char y = input.charAt(pos);
        if (y == x) {
            pos++;
            return new Node(x);
        } else
            throw new ParseException(y, pos, x);
    }

    protected Node match(String s) throws ParseException {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) sb.append(match(c));
        return new Node(sb.toString());
    }

    protected void unexpected() throws ParseException {
        char y = input.charAt(pos);
        throw new ParseException(y, pos, '$');
    }

    protected Node epsilon() {
        return new Node('\u03B5');
    }

    private void done() throws ParseException {
        if (pos < input.length()-1) {
            char y = input.charAt(pos);
            throw new ParseException(y, pos, '$');
        }
    }

    protected void unexpected(Character... expected) {
        char y = input.charAt(pos);
        throw new ParseException(y, pos, expected);
    }

    public Node parse() {
        System.out.println("Parsing: " + input);
        try {
            return attemptParse();
        } catch (ParseException e) {
            System.out.println("Parse error: " + e.getMessage());
            System.out.println(input);
            for (int i = 0; i < e.getOffset(); i++)
                System.out.print(' ');
            System.out.println('^');
            //e.printStackTrace();
            return null;
        }
    }

    protected Node attemptParse() throws ParseException {
        pos = 0;
        Node root = s();
        done();
        System.out.print("ACCEPT: ");
        System.out.println(root);
        root.makeDot();
        return root;
    }

    // Start symbol S.
    protected abstract Node s() throws ParseException;

}
