import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PublicTest {
    public static String lastTestDescription = "";

    @Test
    public void dfaIlmaEpsilonita1() {
        FiniteAutomaton fa = automaton(
                0,
                Arrays.asList(1),
                tr(0, 'b', 0),
                tr(2, 'a', 1),
                tr(0, 'c', 2),
                tr(1, 'd', 0)
        );

        checkAutomaton(fa, "bca", true);
        checkAutomaton(fa, "bbbca", true);
        checkAutomaton(fa, "ca", true);
        checkAutomaton(fa, "cadbbbca", true);
        checkAutomaton(fa, "", false);
        checkAutomaton(fa, "b", false);
        checkAutomaton(fa, "a", false);
        checkAutomaton(fa, "c", false);
        checkAutomaton(fa, "cad", false);
        checkAutomaton(fa, "abca", false);
    }


    @Test
    public void nfaIlmaEpsilonita1() {
        FiniteAutomaton fa = automaton(
                0,
                Arrays.asList(0),
                tr(0, 'a', 0),
                tr(0, 'b', 0)
        );

        checkAutomaton(fa, "", true);
        checkAutomaton(fa, "a", true);
        checkAutomaton(fa, "b", true);
        checkAutomaton(fa, "abba", true);
        checkAutomaton(fa, "babbbbb", true);
        checkAutomaton(fa, "bbbbb", true);
        checkAutomaton(fa, "ababa", true);
        checkAutomaton(fa, "c", false);
        checkAutomaton(fa, "abcba", false);
        checkAutomaton(fa, "u", false);
    }

    @Test
    public void nfaKoosEpsiloniga1() {
        FiniteAutomaton fa = automaton(
                0,
                Arrays.asList(1),
                tr(0, 'a', 0),
                tr(0, 'b', 0),
                tr(0, 'c', 2),
                tr(1, 'd', 0),
                tr(0, null, 1),
                tr(0, null, 2),
                tr(2, null, 1)
        );

        checkAutomaton(fa, "", true);
        checkAutomaton(fa, "aabb", true);
        checkAutomaton(fa, "dd", true);
        checkAutomaton(fa, "bad", true);
        checkAutomaton(fa, "dad", true);
        checkAutomaton(fa, "acdc", true);
        checkAutomaton(fa, "abc", true);
        checkAutomaton(fa, "abcc", false);
        checkAutomaton(fa, "abca", false);
    }

    @Test(timeout=1000)
    public void nfaKoosTsükliga1() {
        // (a|ε)*
        FiniteAutomaton fa = new FiniteAutomaton();
        fa.addState(0, false);
        fa.addState(2, false);
        fa.addState(3, false);
        fa.addState(1, true);
        fa.setStartState(0);
        fa.addTransition(0, null, 1);
        fa.addTransition(0, null, 2);
        fa.addTransition(2, 'a', 3);
        fa.addTransition(2, null, 3);
        fa.addTransition(3, null, 1);
        fa.addTransition(3, null, 2);
        checkAutomaton(fa, "", true);
        checkAutomaton(fa, "aaaaaa", true);
        checkAutomaton(fa, "b", false);
        checkAutomaton(fa, "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", false);
    }

    @Test
    public void nfaPikaSisendiga_boonus() {
        FiniteAutomaton fa = automaton(
                0,
                Arrays.asList(0),
                tr(0, 'a', 1),
                tr(1, 'b', 0)
        );

        StringBuilder sb = new StringBuilder();
        for (int i=0; i < 1000; i++) {
            sb.append("abababab");
        }

        checkAutomaton(fa, sb.toString(), true);
    }

    private static void checkAutomaton(FiniteAutomaton fa, String input, boolean expectedAccepts) {
        String aStr = fa.toString().replace("\r\n", "\n");

        lastTestDescription = "Automaadi toString:\n>"
                + aStr.replaceAll("\n", "\n>")
                + "\n\nSisend:"
                +  (input.length() < 100 ? input : input.substring(0, 90) + "...<veel "
                + (input.length()-90) + " sümbolit>")
                + "\n";

        if (fa.accepts(input) != expectedAccepts) {
            fail("accepts peaks tagastama " + expectedAccepts
                    + ", aga tagastas " + !expectedAccepts);
        }
    }


    private static Transition tr(int from, Character label, int to) {
        return new Transition(from, label, to);
    }

    private static class Transition {
        final int from;
        final int to;
        final Character label;

        public Transition(int from, Character label, int to) {
            this.from = from;
            this.label = label;
            this.to = to;
        }

        public String toString() {
            return "(" + from + ")"
                    + " -[" + (label == null ? "ɛ" : label) + "]-> "
                    + "(" + to + ")";
        }
    }

    private static FiniteAutomaton automaton(Integer startState, List<Integer> acceptingStates,
                                             Transition... transitions) {
        FiniteAutomaton fa = new FiniteAutomaton();

        Set<Integer> addedStates = new HashSet<>();

        for (int state : acceptingStates) {
            fa.addState(state, true);
            addedStates.add(state);
        }

        if (!addedStates.contains(startState)) {
            fa.addState(startState, false);
            addedStates.add(startState);
        }

        fa.setStartState(startState);


        for (Transition trans : transitions) {
            if (!addedStates.contains(trans.from)) {
                fa.addState(trans.from, false);
                addedStates.add(trans.from);
            }
            if (!addedStates.contains(trans.to)) {
                fa.addState(trans.to, false);
                addedStates.add(trans.to);
            }

            fa.addTransition(trans.from, trans.label, trans.to);
        }

        return fa;
    }
}
