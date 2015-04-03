package ee.ut.cs.akt.parser;

import ee.ut.cs.akt.lekser.PositionedToken;
import ee.ut.cs.akt.lekser.TokenType;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class AKTKParseException extends RuntimeException {

    private final PositionedToken token;
    private final Set<TokenType> expected;

    public PositionedToken getToken() {
        return token;
    }

    public Set<TokenType> getExpected() {
        return expected;
    }

    public AKTKParseException(PositionedToken tok, TokenType... expected) {
        super("Unexpected token \'" + tok + "\' but expected: " + Arrays.toString(expected));
        this.token = tok;
        this.expected = new LinkedHashSet<>(Arrays.asList(expected));
    }

}
