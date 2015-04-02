package ee.ut.cs.akt.recognizers;

import java.text.ParseException;

public class Recognizer {
    String input;
    int pos;

    public Recognizer(String input) {
        this.input = input + '$';
    }

    void match(char x) throws ParseException {
        char y = input.charAt(pos);
        if (y == x) {
            pos++;
        } else
            throw new ParseException("Unexpected char " + y, pos);
    }

    void match(String s) throws ParseException {
        for (char c : s.toCharArray()) match(c);
    }

    public char peek() {
        return input.charAt(pos);
    }

    void epsilon() { }

    void done() throws ParseException {
        if (pos < input.length()-1) {
            char y = input.charAt(pos);
            throw new ParseException("Unexpected char " + y, pos);
        }
    }

    void unexpected() throws ParseException {
        char y = input.charAt(pos);
        throw new ParseException("Unexpected char " + y, pos);
    }

    public void parse() {
        System.out.println("Parsing: " + input);
        try {
            attemptParse();
        } catch (ParseException e) {
            System.out.println("Parse error: " + e.getMessage());
            System.out.println(input);
            for (int i = 0; i < e.getErrorOffset(); i++)
                System.out.print(' ');
            System.out.println('^');
            //e.printStackTrace();
        }
    }

    public void attemptParse() throws ParseException {
        pos = 0;
        s();
        done();
        System.out.println("ACCEPT!");
    }

    public static void main(String[] args) {
        Recognizer recognizer = new Recognizer(args[0]);
        recognizer.parse();
    }

    // Start symbol S.
    void s() throws ParseException {

    }

}
