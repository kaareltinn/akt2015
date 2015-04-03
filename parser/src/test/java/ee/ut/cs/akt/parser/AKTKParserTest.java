package ee.ut.cs.akt.parser;

import ee.ut.cs.akt.ast.ExprNode;
import ee.ut.cs.akt.lekser.TokenType;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

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