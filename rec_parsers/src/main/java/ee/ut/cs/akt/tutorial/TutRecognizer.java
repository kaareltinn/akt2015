package ee.ut.cs.akt.tutorial;

import java.text.ParseException;

public class TutRecognizer {
    String input;
    int pos;

    public TutRecognizer(String input) {
        this.input = input;
    }

    void match(char x) throws ParseException {
        if (pos < input.length()) {
            char y = input.charAt(pos);
            if (y == x) {
                pos++;
            } else
                throw new ParseException("Unexpected char " + y, pos);
        } else
            throw new ParseException("Unexpected end of file.", pos);
    }

    public char peek() {
        if (pos < input.length())
            return input.charAt(pos);
        return '$'; // EOF sümbol
    }

    void epsilon() { }

    void done() throws ParseException {
        if (pos < input.length()) {
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
            System.out.println(input + '$');
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
        TutRecognizer recognizer = new TutRecognizer(args[0]);
        recognizer.parse();
    }

    // Start symbol S.
    void s() throws ParseException {
        switch(peek()) {
            case 'a':
                x();
                break;
            case 'b':
            case '$':
                y();
                break;
            default:
                unexpected();
        }
    }

    void y() throws ParseException {
        switch(peek()) {
            case '$':
            case 'a':
                epsilon();
                break;
            case 'b':
                match('b');
                x();
                match('a');
                break;
            default:
                unexpected();
        }
    }

    void x() throws ParseException {
        match('a');
        y();
    }

}
