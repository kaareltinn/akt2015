package ee.ut.cs.akt.recognizers.coursera;

public abstract class Recognizer {
    String input;
    protected int pos;

    public Recognizer(String input) {
        this.input = input;
    }

    boolean match(char x) {
        if (pos < input.length() && input.charAt(pos) == x) {
                pos++;
                return true;
        }
        return false;
    }

    boolean epsilon() {
        return true;
    }

    boolean done() {
        return pos == input.length();
    }

    public void parse() {
        System.out.println("Parsing: " + input);
        if (!attemptParse())
            System.out.println("REJECT!");
    }

    public boolean attemptParse()  {
        pos = 0;
        if (s() && done()) {
            System.out.println("ACCEPT!");
            return true;
        }
        return false;
    }

    // Start symbol S.
    abstract boolean s();

}
