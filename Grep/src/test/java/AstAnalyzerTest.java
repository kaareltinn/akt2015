import ee.ut.cs.akt.regex.Letter;
import ee.ut.cs.akt.regex.RegexNode;
import ee.ut.cs.akt.regex.RegexParser;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class AstAnalyzerTest {

    @Test
    public void testEmpty() throws Exception {
        checkEmpty("a", false);
        checkEmpty("a*", true);
        checkEmpty("a*|b", true);
        checkEmpty("(a*|b*)a", false);
        checkEmpty("kal*a", false);
    }

    private void checkEmpty(String regex, boolean empty) {
        RegexNode node = RegexParser.parse(regex);
        AstAnalyzer.computeEmpty(node);
        assertEquals(node.isEmpty(), empty);
    }

    @Test
    public void testFirst() throws Exception {
        checkFirst("a", Arrays.asList('a'));
        checkFirst("(a|bb)*c", Arrays.asList('a','b','c'));
        checkFirst("(a|bb)c", Arrays.asList('a','b'));
    }

    private void checkFirst(String regex, List<Character> chars) {
        RegexNode node = RegexParser.parse(regex);
        AstAnalyzer.computeEmpty(node);
        AstAnalyzer.computeFirst(node);
        Set<Character> firstSet = new HashSet<>();
        for (Letter letter : node.getFirstSet()) {
            firstSet.add(letter.getSymbol());
        }
        assertEquals(new HashSet<>(chars), firstSet);
    }

    @Test
    public void testLast() throws Exception {
        checkLast("a", Arrays.asList('a'));
        checkLast("(a|bb)*c", Arrays.asList('c'));
        checkLast("(a|bb)c*", Arrays.asList('a', 'b', 'c'));
    }

    private void checkLast(String regex, List<Character> chars) {
        RegexNode node = RegexParser.parse(regex);
        AstAnalyzer.computeEmpty(node);
        AstAnalyzer.computeLast(node);
        Set<Character> lastSet = new HashSet<>();
        for (Letter letter : node.getLastSet()) {
            lastSet.add(letter.getSymbol());
        }
        assertEquals(new HashSet<>(chars), lastSet);
    }

    @Test
    public void testNext() throws Exception {
        checkNext("a(b|c)", Arrays.asList('b', 'c'));
        checkNext("a*b", Arrays.asList('a', 'b'));
        checkNext("(a|bb)*cd", Arrays.asList('a', 'b', 'c'));
    }

    private void checkNext(String regex, List<Character> chars) {
        RegexNode node = RegexParser.parse(regex);
        AstAnalyzer.computeEmpty(node);
        AstAnalyzer.computeFirst(node);
        AstAnalyzer.computeNext(node);
        RegexNode letter = node;
        while (! (letter instanceof Letter)) letter = letter.getChild(0);
        Set<Character> nextSet = new HashSet<>();
        for (Letter l : letter.getNextSet()) {
            nextSet.add(l.getSymbol());
        }
        assertEquals(new HashSet<>(chars), nextSet);
    }

}