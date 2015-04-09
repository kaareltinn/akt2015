package ee.ut.cs.akt.tutorial;

import ee.ut.cs.akt.parsers.Node;
import ee.ut.cs.akt.parsers.Parser;

public class FParser extends Parser {

    public FParser(String input) {
        super(input);
    }

    // Grammatika:
    // S -> fA  (1)
    // S -> fB  (2)
    // A -> <S> (1)
    // A -> ε   (1)
    // B -> !   (2)
    // B -> <>  (3)

    @Override
    protected Node s() {
        return null;
    }

    public static void main(String[] args) {
        FParser fParser = new FParser(args[0]);
        fParser.testParser();
    }

}
