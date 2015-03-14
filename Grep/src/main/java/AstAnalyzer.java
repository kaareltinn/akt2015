import ee.ut.cs.akt.automata.FiniteAutomaton;
import ee.ut.cs.akt.regex.*;

public class AstAnalyzer {

    public static FiniteAutomaton regexToFiniteAutomaton(RegexNode regex) {
        computeEmpty(regex);
        computeFirst(regex);
        computeLast(regex);
        computeNext(regex);
        return regex.toAutomaton();
    }

    static void computeEmpty(RegexNode node) {
        // TODO
    }

    static void computeFirst(RegexNode node) {
        // TODO
    }

    static void computeLast(RegexNode node) {
        // TODO
    }

    static void computeNext(RegexNode node) {
        // TODO
    }
}


