package ee.ut.cs.akt.recognizers;

public abstract class Recognizer {
    String input;
    int pos;

    public Recognizer(String input) {
        this.input = input + '$';
    }

    protected void match(char x) {
        char y = input.charAt(pos);
        if (y == x) {
            pos++;
        } else
            throw new ParseException(y, pos, x);
    }

    protected void match(String s) throws ParseException {
        for (char c : s.toCharArray()) match(c);
    }

    protected char peek() {
        return input.charAt(pos);
    }

    protected void epsilon() {
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

    public void parse() {
        System.out.println("Parsing: " + input);
        try {
            attemptParse();
        } catch (ParseException e) {
            System.out.println("Parse error: " + e.getMessage());
            System.out.println(input);
            for (int i = 0; i < e.getOffset(); i++)
                System.out.print(' ');
            System.out.println('^');
            //e.printStackTrace();
        }
    }

    protected void attemptParse() throws ParseException {
        pos = 0;
        s();
        done();
        System.out.println("ACCEPT!");
    }

    // Start symbol S.
    protected abstract void s() throws ParseException;
}
