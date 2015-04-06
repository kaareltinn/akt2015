package ee.ut.cs.akt.homework;

import ee.ut.cs.akt.TestParser;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TypeParserTest {

    TestParser<TypeParser> typeParser = new TestParser<>(TypeParser.class);

    @Test
    public void test01() throws Exception {
        typeParser.accepts("int");
        typeParser.accepts("void");
        typeParser.rejects("hyperboloid");
        typeParser.rejects("");

        typeParser.accepts("int*");
        typeParser.accepts("void*");
        typeParser.rejects("*");
        typeParser.rejects("*int");

        typeParser.accepts("int**");
        typeParser.accepts("void*****");
        typeParser.rejects("**");
    }


    @Test
    public void test02() throws Exception {
        typeParser.accepts("int()");
        typeParser.accepts("int(*)");
        typeParser.rejects("(int)");

        typeParser.accepts("int(())");
        typeParser.accepts("void*()");
        typeParser.rejects("int()()");
        typeParser.rejects("int(*)()");
    }

    @Test
    public void test03() throws Exception {
        typeParser.accepts("int[]");
        typeParser.accepts("void[]");
        typeParser.accepts("void[][][][][][][]");
        typeParser.rejects("[][][]");
        typeParser.rejects("[int]");
        typeParser.rejects("int[[]]");

        typeParser.accepts("void([])");
        typeParser.accepts("void(*)[]");
        typeParser.rejects("void(*)([])");
        typeParser.accepts("int()[]");
        typeParser.rejects("int()([])");
        typeParser.accepts("void([])");
        typeParser.accepts("int(*)[]");
        typeParser.accepts("int(*[])[]");
        typeParser.accepts("int*[][]");
        typeParser.rejects("int*[]([])");
        typeParser.accepts("int*(*(*[])[])[]");
    }

    @Test
    public void test04() throws Exception {
        typeParser.accepts("void[]", "TArray(TVoid)");
        typeParser.accepts("void(*)[]", "TPtr(TArray(TVoid))");
    }
}