import org.junit.Test;

import static org.junit.Assert.*;

public class HtmlStripTest {

    @Test
    public void testRemoveHtmlMarkup() throws Exception {
        check("foo", "<b>foo</b>");
    }

    @Test
    public void testRemoveHtmlMarkupWithGT() throws Exception {
        check("f>5", "<b>f>5</b>");
    }

    @Test
    public void testRemoveHtmlMarkupWithQuotes() throws Exception {
        check("foo", "<a href='>'>foo</a>");
    }

    @Test
    public void testRemoveHtmlMarkupWithQuotesAndGe() throws Exception {
        check("f>5", "<a href='>'>f>5</a>");
    }

    @Test
    public void testRemoveHtmlMarkupWithJustQuotes() throws Exception {
        check("'foo'", "'<b>foo</b>'");
        check("'foo'", "'foo'");
        check("''", "''");
    }

    @Test
    public void testRemoveHtmlMarkupWithDoubleQuotes() throws Exception {
        check("foo", "<a href=\">\">foo</a>");
        check("\"foo\"", "\"<b>foo</b>\"");
    }

    @Test
    public void testRemoveHtmlMarkupWithMixedQuotes() throws Exception {
        check("foo", "<a href=\"'kala'>'ma<ja'\">foo</a>");
        check("\"foo\"", "\"<b name='ka>la\"'>foo</b>\"");
    }


    public void check(String expected, String input) {
        assertEquals(input, expected, HtmlStrip.removeHtmlMarkup(input));
    }
}
