import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class MealyTest {

    private final MealyMachine formatter = MachineDefs.getFormatter();

    @Test
    public void testRemoveHtmlMarkup1() throws Exception {
        MealyMachine m = MachineDefs.getHtmlMachine();
        check(m, "foo", "<b>foo</b>");
        check(m, "f>5", "<b>f>5</b>");
        check(m, "foo", "<a href='>'>foo</a>");
        check(m, "f>5", "<a href='>'>f>5</a>");
        check(m, "'foo'", "'<b>foo</b>'");
        check(m, "'foo'", "'foo'");
        check(m, "''", "''");
    }

    @Test
    public void testRemoveHtmlMarkup2() throws Exception {
        MealyMachine m = MachineDefs.getHtmlMachine();
        check(m, "foo", "<a href=\">\">foo</a>");
        check(m, "\"foo\"", "\"<b>foo</b>\"");
        check(m, "foo", "<a href=\"'kala'>'ma<ja'\">foo</a>");
        check(m, "\"foo\"", "\"<b name='ka>la\"'>foo</b>\"");
    }


    @Test
    public void testXoxo() throws Exception {
        MealyMachine m = MachineDefs.getXoxoMachine();
        check(m, "x", "x");
        check(m, "xoxo", "xxxx");
        check(m, "kala", "kala");
        check(m, "xeroo", "xerox");
    }


    @Test
    public void testTokenizer() throws Exception {
        MealyMachine m = MachineDefs.getTokenizer('|');
        check(m, "kala|+|maja", "kala+maja");
        check(m, "1|+|+|5", "1++5");
    }


    @Test
    public void testDepth() throws Exception {
        MealyMachine m = MachineDefs.getDepthMachine();
        check(m, "0", "$");
        check(m, "1", "($)");
        check(m, "5", "((((($");
        check(m, "3", "((($((");
        check(m, "2", "(()($((");
        check(m, "0", "(((((())))))$");
    }


    @Test
    public void testFormatter1() throws Exception {
        check(formatter, "One Two Three Four",  "One Two  Three   Four");
    }

    @Test
    public void testFormatter2() throws Exception {
        check(formatter, "Tere, Maailm!", "Tere   ,Maailm!");
    }

    @Test
    public void testFormatter3() throws Exception {
        check(formatter, "This text (all of it) has occasional lapses... in\n" +
                        "punctuation (sometimes, pretty bad; sometimes, not so).\n" +
                        "\n" +
                        "(Ha!) Is this: fun!?! Or what?",
                "This     text (all of it   )has occasional lapses .. .in\n" +
                        "  punctuation( sometimes,pretty bad ; sometimes ,not so).\n" +
                        "\n" +
                        "( Ha ! )Is this  :fun ! ? !  Or   what  ?");
    }


    @Test
    public void testBinaryMachine3() throws Exception {
        MealyMachine m = MachineDefs.getBinaryMachine(3);
        check(m, "0", "0$");    // 0
        check(m, "1", "1$");    // 1
        check(m, "2", "101$");  // 5
        check(m, "0", "110$");  // 6
        check(m, "0", "1111$"); // 15
        check(m, "2", "101101101$");
        check(m, "0", "100000010$");
    }

    @Test
    public void testBinaryMachine5() throws Exception {
        MealyMachine m = MachineDefs.getBinaryMachine(5);
        check(m, "0", "0$");    // 0
        check(m, "1", "1$");    // 1
        check(m, "0", "101$");  // 5
        check(m, "1", "110$");  // 6
        check(m, "0", "1111$"); // 15
        check(m, "0", "101101101$");
        check(m, "4", "111101110$");
    }


    public void check(MealyMachine machine, String expected, String input) {
        assertEquals(input, expected, machine.run(input));
    }
}
