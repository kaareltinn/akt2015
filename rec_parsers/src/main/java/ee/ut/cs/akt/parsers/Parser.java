package ee.ut.cs.akt.parsers;

import java.text.ParseException;

public class Parser {
    String input;
    int pos;

    public Parser(String input) {
        this.input = input + '$';
    }

    public char peek() {
        return input.charAt(pos);
    }

    Node match(char x) throws ParseException {
        char y = input.charAt(pos);
        if (y == x) {
            pos++;
            return new Node(x);
        } else
            throw new ParseException("Unexpected char " + y, pos);
    }

    Node match(String s) throws ParseException {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) sb.append(match(c));
        return new Node(sb.toString());
    }

    void unexpected() throws ParseException {
        char y = input.charAt(pos);
        throw new ParseException("Unexpected char " + y, pos);
    }

    Node epsilon() {
        return new Node('\u03B5');
    }

    void done() throws ParseException {
        if (pos < input.length()-1) {
            throw new ParseException("Unexpected input.", pos);
        }
    }

    public Node parse() {
        System.out.println("Parsing: " + input);
        try {
            return attemptParse();
        } catch (ParseException e) {
            System.out.println("Parse error: " + e.getMessage());
            System.out.println(input);
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
        Parser parser = new Parser(args[0]);
        parser.parse();
    }

    // Start symbol S.
    Node s() throws ParseException {
        return null;
    }

}
