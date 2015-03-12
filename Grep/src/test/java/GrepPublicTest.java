import static org.junit.Assert.*;

import org.junit.Test;

import dk.brics.automaton.Automaton;
import ee.ut.cs.akt.automata.BricsUtils;
import ee.ut.cs.akt.automata.FiniteAutomaton;
import ee.ut.cs.akt.regex.RegexNode;
import ee.ut.cs.akt.regex.RegexParser;


public class GrepPublicTest {
    @Test
    public void test1() {
        String[] good = {"abcde"};
        String[] bad = {"ba", "c", "aba"};
        checkRegex("abcde", good, bad);
    }

    @Test
    public void test2() {
        String[] good = {"a", "bb"};
        String[] bad = {"aa", "c", "ab"};
        checkRegex("a|bb", good, bad);
    }

    @Test
    public void test3() {
        String[] good = {"", "aaa", "bbbbb", "abbbb", "aaaab", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbb"};
        String[] bad = {"ba", "c", "aba"};
        checkRegex("a*b*", good, bad);
    }


    @Test
    public void test4() {
        String[] good = {"", "ab", "abab", "abababab", "ababababab"};
        String[] bad = {"ba", "abba", "a", "b", "baba"};
        checkRegex("(ab)*", good, bad);
    }

    @Test
    public void testBoonus() {
    	FiniteAutomaton a1 = new FiniteAutomaton();
    	// TODO: koosta ise oma automaat, mida determineerida
    	
    	checkDeterminization(a1);
    }


    private void checkRegex(String re, String[] good, String[] bad) {
        RegexParser parser = new RegexParser(re);
        RegexNode root = parser.parse();
        FiniteAutomaton auto = Grep.regexToFiniteAutomaton(root);

        for (String g : good) {
            assertTrue("Sõna '" + g + "' peaks regexiga " + re + " sobituma!", auto.accepts(g));
        }

        for (String b : bad) {
            assertFalse("Sõna '" + b + "' ei tohiks regexiga " + re + " sobituda!", auto.accepts(b));
        }
    }
    
    private void checkDeterminization(FiniteAutomaton automaton) {
    	FiniteAutomaton determinized = Grep.optimize(automaton);
    	
    	if (determinized == automaton) {
    		fail("Grep.optimize tagastas sama automaadi");
    	}
    	else if (!determinized.isDeterministic()) {
    		fail("Grep.optimize tagastas automaadi, mis pole determineeritud");
    	}
    	else {
    		Automaton originalBrics = BricsUtils.fromAktAutomaton(automaton);
    		Automaton determinizedBrics = BricsUtils.fromAktAutomaton(determinized);
    		if (! determinizedBrics.equals(originalBrics)) {
    			fail ("Grep.optimize poolt tagastatud automaat pole esialgsega samaväärne");
    		}
    	}
    }
}
