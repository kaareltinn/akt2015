package ee.ut.cs.akt.parsers;

import java.util.Arrays;

public abstract class Parser {
    private String input;
    protected int pos;

    public Parser(String input) {
        this.input = input + '$';
    }

    protected char peek() {
        return input.charAt(pos);
    }

    protected Node match(char x) {
        char y = input.charAt(pos);
        if (y == x) {
            pos++;
            return new Node(x);
        } else
            throw new ParseException(y, pos, x);
    }

    protected Node match(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) sb.append(match(c));
        return new Node(sb.toString());
    }

    protected Node epsilon() {
        return new Node('\u03B5');
    }

    private void done() {
        if (pos < input.length()-1) {
            char y = input.charAt(pos);
            throw new ParseException(y, pos, '$');
        }
    }

    protected void unexpected(Character... expected) {
        char y = input.charAt(pos);
        throw new ParseException(y, pos, expected);
    }

    protected void expect(Character... expected) {
        char y = input.charAt(pos);
        if (!Arrays.asList(expected).contains(y)) {
            throw new ParseException(y, pos, expected);
        }
    }

    public void testParser() {
        System.out.println("Parsing: " + input);
        try {
            Node root = parse();
            System.out.print("ACCEPT: ");
            System.out.println(root);
            root.makeDot();
        } catch (ParseException e) {
            System.out.println("Parse error: " + e.getMessage());
            System.out.println(input);
            for (int i = 0; i < e.getOffset(); i++)
                System.out.print(' ');
            System.out.println('^');
            //e.printStackTrace();
        }
    }

    public void testRecognizer() {
        System.out.println("Parsing: " + input);
        try {
            parse();
            System.out.print("ACCEPT!");
        } catch (ParseException e) {
            System.out.println("REJECT!");
        }
    }

    public Node parse() {
        pos = 0;
        Node root = s();
        done();
        return root;
    }

    // Start symbol S.
    protected abstract Node s();

}
