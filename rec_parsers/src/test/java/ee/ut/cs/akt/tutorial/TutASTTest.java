package ee.ut.cs.akt.tutorial;

import ee.ut.cs.akt.TestParser;
import org.junit.Test;

public class TutASTTest {

    TestParser<TutAST> tutAst = new TestParser<>(TutAST.class);

    @Test
    public void testParse() throws Exception {
        tutAst.accepts("x*x", "*(x,x)");
    }

}