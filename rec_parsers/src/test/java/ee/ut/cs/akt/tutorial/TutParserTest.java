package ee.ut.cs.akt.tutorial;

import ee.ut.cs.akt.TestParser;
import org.junit.Test;

public class TutParserTest {

    TestParser<TutParser> tutParser = new TestParser<>(TutParser.class);

    @Test
    public void testRecognizer() throws Exception {
        tutParser.accepts("x*x+x");
        tutParser.rejects("x+)");
    }

    @Test
    public void testParse() throws Exception {
        tutParser.accepts("x*x", "S(T(F(x),Q(*,F(x),Q(ε))),R(ε))");
    }

    @Test
    public void testExpected() throws Exception {
        tutParser.rejects("x+-x", 2, "(x", "+*$)");
    }

}