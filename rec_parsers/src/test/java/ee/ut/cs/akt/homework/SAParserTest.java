package ee.ut.cs.akt.homework;

import ee.ut.cs.akt.TestParser;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SAParserTest {

    TestParser<SAParser> saParser = new TestParser<>(SAParser.class);

    @Test
    public void test01() throws Exception {
        saParser.accepts("acb");
        saParser.accepts("abdb");
        saParser.accepts("ad");
        saParser.rejects("bdb");
        saParser.rejects("");
        saParser.rejects("a");
        saParser.rejects("b");
        saParser.rejects("d");
        saParser.accepts("aaaaacbbbbb");
        saParser.accepts("c");
        saParser.accepts("abbbbbbbdbbbbbbb");
        saParser.accepts("ad");
        saParser.rejects("bdb");
        saParser.rejects("abb");
        saParser.rejects("");
        saParser.rejects("a");
        saParser.rejects("b");
        saParser.rejects("d");
    }

    @Test
    public void test02() throws Exception {
        saParser.accepts("aaaaacbbbbb");
        saParser.accepts("c");
        saParser.accepts("abbbbbbbdbbbbbbb");
        saParser.accepts("ad");
        saParser.accepts("abbbbbbbdbbbbbbbccccccc");
        saParser.accepts("abbbbbbbdcccccbbbbbbbccccccc");
        saParser.accepts("adccc");
        saParser.rejects("bdb");
        saParser.rejects("abb");
        saParser.rejects("");
        saParser.rejects("a");
        saParser.rejects("b");
        saParser.rejects("d");
        saParser.rejects("ac");
    }

    @Test
    public void test03() throws Exception {
        saParser.accepts("abdb", "S(a,R(A(b,A(d,Q(ε)),b,Q(ε))))");
    }

    @Test
    public void test04() throws Exception {
        saParser.rejects("a", 1, "abcd", "$");
        saParser.rejects("aba", 2, "bd", "ac");
        saParser.rejects("add", 2, "bc$", "ad");
    }
}