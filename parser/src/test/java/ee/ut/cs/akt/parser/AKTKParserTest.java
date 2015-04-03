package ee.ut.cs.akt.parser;

import ee.ut.cs.akt.ast.ExprNode;
import ee.ut.cs.akt.lekser.TokenType;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import static ee.ut.cs.akt.lekser.TokenType.*;
import static org.junit.Assert.assertEquals;

public class AKTKParserTest {

    @Test
    public void testParse() throws Exception {
        HashMap<String, Integer> env = new HashMap<>();
        env.put("kala", 2);
        ExprNode node = AKTKParser.parse("5+(kala-4)");
        assertEquals(node.toPrettyString(), 3, node.eval(env));
    }

    @Test
    public void testParseException1() throws Exception {
        checkException("5+5-+5", 4);
        checkException("5 5", 2);
    }

    @Test
    public void testParseException2() throws Exception {
        checkException("5+5-+5", 4, LPAREN, INTEGER, VARIABLE);
        checkException("5 5", 2, PLUS, MINUS, TIMES, DIV, EOF);
    }

    private void checkException(String input, int location, TokenType... expected) {
        try {
            AKTKParser.parse(input);
        } catch (AKTKParseException e) {
            assertEquals(location, e.getToken().getOffset());
            if (expected.length > 0) {
                assertEquals(new HashSet<TokenType>(Arrays.asList(expected)), e.getExpected());
            }
        }
    }


}