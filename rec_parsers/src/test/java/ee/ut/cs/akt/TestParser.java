package ee.ut.cs.akt;

import ee.ut.cs.akt.parsers.Node;
import ee.ut.cs.akt.parsers.ParseException;
import ee.ut.cs.akt.parsers.Parser;

import java.lang.reflect.Constructor;
import java.util.AbstractList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestParser<T extends Parser> {

    final Class<T> parserClass;

    public TestParser(Class<T> parserClass) {
        this.parserClass = parserClass;
    }

    private Node parse(String input) {
        Parser parser;
        try {
            Constructor<T> constructor = parserClass.getConstructor(String.class);
            parser = constructor.newInstance(input);
        } catch (Exception e) {
            throw new RuntimeException(parserClass.getSimpleName() + " is not a Parser subclass.");
        }
        return parser.parse();
    }

    public void accepts(String input, String parsetree) {
        assertEquals(input, parsetree, parse(input).toString());
    }

    public void accepts(String input) {
        parse(input);
    }

    public void rejects(String input, Integer loc, String expected, String unexpected) {
        try {
            parse(input);
            assertTrue("Must throw exception!", false);
        } catch (ParseException e) {
            if (loc != null) {
                assertEquals(loc.intValue(), e.getOffset());
                String msg = String.format("Expected set %s should include %s, but not %s.",
                        e.getExpected(), asList(expected), asList(unexpected));
                assertTrue(msg, e.getExpected().containsAll(asList(expected)));
                HashSet<Character> intersection = new HashSet<>(e.getExpected());
                intersection.retainAll(asList(unexpected));
                assertTrue(msg, intersection.isEmpty());
            }
        }
    }

    public void rejects(String input) {
        rejects(input, null, null, null);
    }

    // http://stackoverflow.com/questions/6319775/java-collections-convert-a-string-to-a-list-of-characters
    private static List<Character> asList(final String string) {
        return new AbstractList<Character>() {
            public int size() {
                return string.length();
            }

            public Character get(int index) {
                return string.charAt(index);
            }
        };
    }
}
