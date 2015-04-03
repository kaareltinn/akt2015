package ee.ut.cs.akt.parser;

import ee.ut.cs.akt.ast.ExprNode;
import ee.ut.cs.akt.lekser.TokenType;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static ee.ut.cs.akt.lekser.TokenType.*;
import static org.junit.Assert.*;

public class AKTKParserTest {


    @Test
    public void test01_Recognize() throws Exception {
        check("5+(kala-4)", true);
        check("5 5", false);
    }

    private void check(String input, boolean accept) {
        try {
            AKTKParser.parse(input);
            assertTrue(input, accept);
        } catch (AKTKParseException e) {
            assertFalse(input, accept);
        }
    }

    @Test
    public void test02_Parse() throws Exception {
        HashMap<String, Integer> env = new HashMap<>();
        env.put("kala", 2);
        ExprNode node = AKTKParser.parse("5+(kala-4)");
        assertEquals(node.toPrettyString(), 3, node.eval(env));
    }

    @Test
    public void test03ParseExceptionBoonus1() throws Exception {
        checkException("5+5-+5", 4);
        checkException("5 5", 2);
    }

    @Test
    public void test04ParseExceptionBoonus2() throws Exception {
        List<TokenType> s1 = Arrays.asList(LPAREN, INTEGER, VARIABLE);
        List<TokenType> s2 = Arrays.asList(PLUS, MINUS, TIMES, DIV, EOF);
        List<TokenType> s3 = Arrays.asList(PLUS, MINUS, TIMES, DIV, RPAREN);
        checkException("5+5-+5", 4, s1, s2);
        checkException("5 5", 2, s2, s1);
        checkException("(5 5", 3, s3, s1);
    }

    private void checkException(String input, int location, List<TokenType> expected, List<TokenType> unexpected) {
        try {
            AKTKParser.parse(input);
            assertTrue("Must throw exception!", false);
        } catch (AKTKParseException e) {
            assertEquals(location, e.getToken().getOffset());
            if (expected != null) {
                String msg = String.format("Your expected set %s should include %s, but not %s.",
                        e.getExpected(), expected, unexpected);
                assertTrue(msg, e.getExpected().containsAll(expected));
                HashSet<TokenType> intersection = new HashSet<>(unexpected);
                intersection.retainAll(e.getExpected());
                assertTrue(msg, intersection.isEmpty());
            }
        }
    }

    private void checkException(String input, int location) {
        checkException(input, location, null, null);
    }


}