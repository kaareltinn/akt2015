package ee.ut.cs.akt.tutorial;

import ee.ut.cs.akt.TestParser;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FParserTest {

    TestParser<FParser> fParser = new TestParser<>(FParser.class);

    @Test
    public void test01() throws Exception {
        fParser.accepts("f");
        fParser.accepts("f<f>");
        fParser.accepts("f<f>");
        fParser.accepts("f<f<f>>");
        fParser.accepts("f<f<f<f>>>");
        fParser.accepts("f<f<f<f<f<f>>>>>");
        fParser.rejects("");
        fParser.rejects("f ");
        fParser.rejects(" f ");
        fParser.rejects("uba");
        fParser.rejects("f<");
        fParser.rejects("f>");
        fParser.rejects("<f>");
        fParser.rejects("ff");
        fParser.rejects("f<>f");
        fParser.rejects("f<>f<>");
        fParser.rejects("f<f><>");
        fParser.rejects("f<f<>");
        fParser.rejects("f<f<f<f<f<f>>f>>>");
        fParser.rejects("f<f<f<f<f<f>>>>f>");
    }

    @Test
    public void test02() throws Exception {
        fParser.accepts("f!");
        fParser.accepts("f<f>");
        fParser.accepts("f<f!>");
        fParser.rejects("!");
        fParser.rejects("f !");
        fParser.rejects("!f");
        fParser.rejects("f<");
        fParser.rejects("f>");
        fParser.rejects("f<!>");
        fParser.rejects("f!<f>");
        fParser.rejects("f<f>!");
        fParser.rejects("f<!f>");
    }

    @Test
    public void test03() throws Exception {
        fParser.accepts("f<>");
        fParser.accepts("f<f>");
        fParser.accepts("f<f<f<f<f<>>>>>");
        fParser.rejects("<>");
        fParser.rejects("f!<>");
        fParser.rejects("<>f");
        fParser.rejects("f<f<f<f<f<>>>f>>");
        fParser.rejects("f<<>>");
        fParser.rejects("f<!>");
    }

    @Test
    public void test04() throws Exception {
        fParser.accepts("f<f>", "S(f,A(<,S(f,A(ε)),>))");
        fParser.accepts("f<f!>", "S(f,A(<,S(f,B(!)),>))");
        fParser.accepts("f<f<>>", "S(f,A(<,S(f,B(<,>)),>))");
    }

}