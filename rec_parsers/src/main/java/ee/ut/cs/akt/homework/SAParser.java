package ee.ut.cs.akt.homework;

import ee.ut.cs.akt.parsers.Node;
import ee.ut.cs.akt.parsers.Parser;

public class SAParser extends Parser {

    public SAParser(String input) {
        super(input);
    }

    // Testimiseks võib kasutada:
    public static void main(String[] args) {
        SAParser parser = new SAParser(args[0]);
        parser.testParser();
    }

    // Start sümbol:
    protected Node s() {
        return null;
    }

}
