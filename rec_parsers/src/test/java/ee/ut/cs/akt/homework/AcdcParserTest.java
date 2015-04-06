package ee.ut.cs.akt.homework;

import ee.ut.cs.akt.TestParser;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AcdcParserTest {

    TestParser<AcdcParser> acdcParser = new TestParser<>(AcdcParser.class);

    @Test
    public void test01() throws Exception {
        acdcParser.accepts("acdc");
        acdcParser.accepts("aacdcdc");
        acdcParser.accepts("acaddc");
        acdcParser.accepts("acdcc");
        acdcParser.accepts("aaacddaaacdcdcdaaaadcdddacdcdadc");
        acdcParser.accepts("aaacddaaacdcdcdaaaadcdddacdcacdcdadc");
        acdcParser.accepts("aaacddaaacdcacaddcdcdaaaadcdddacdcadcdadc");
        acdcParser.rejects("a");
        acdcParser.rejects("ac");
        acdcParser.rejects("acdc  ");
        acdcParser.rejects("d");
        acdcParser.rejects("acadc");
        acdcParser.rejects("aaacddaaacdcdcdaaaadcddacdcdadc");
        acdcParser.rejects("aaacddaaacdcdcdaaadcdddacdcacdcdadc");
        acdcParser.rejects("aaacddaaacdcacaddcdcdaaaadcdddacdcadcadc");
    }

    @Test
    public void test02() throws Exception {
        acdcParser.accepts("ac/dc");
        acdcParser.accepts("ac////dc");
        acdcParser.accepts("aaacddaaacdcacaddcdcdaaaadcdddacdcadcdadc");
        acdcParser.accepts("aaacddaaac//acdcdcac/addcdcdaaaadcdddac///dcadcdadc");
        acdcParser.rejects("a");
        acdcParser.rejects("ac");
        acdcParser.rejects("acdc  ");
        acdcParser.rejects("d");
        acdcParser.rejects("aaacddaaacdcacaddcdcdaaaadcdddacdcadcadc");
        acdcParser.rejects("ac/d/c");
        acdcParser.rejects("aaacddaaac//acdcdca/c/addcdcdaaaadcdddac///dcadcdadc");
    }

    @Test
    public void test03() throws Exception {
        acdcParser.accepts("acdc", "S(a,S(C(c),S(ε)),d,S(C(c),S(ε)))");

    }

    @Test
    public void test04() throws Exception {
        acdcParser.accepts("ac/dc", "S(a,S(C(C(c),/),S(ε)),d,S(C(c),S(ε)))");
        acdcParser.accepts("ac//dc", "S(a,S(C(C(C(c),/),/),S(ε)),d,S(C(c),S(ε)))");
    }

    // Ei ole vaja... @Test
    public void test05() throws Exception {
        acdcParser.rejects("ab", 1, "acd$", "/");
    }
}