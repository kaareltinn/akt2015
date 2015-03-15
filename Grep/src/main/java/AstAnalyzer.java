import ee.ut.cs.akt.automata.FiniteAutomaton;
import ee.ut.cs.akt.regex.*;

/**
 * Funktsioonid siin failis peavad etteantud RegexNode'i erinevad väljad täitma.
 * Need väljad on empty, first, next ja last. Nendele vastavad getter/setterid
 * on defineeritud RegexNode.java failis.
 *
 * Selle ülesanne testimiseks on eraldi AstAnalyzerTest.java olemas, seda tasub ka
 * vaadata, kui miski on segane. Siin on ka näide erinevatest puude läbimise meetoditest.
 * Kui tundub keeruline, siis keskenduge ainult esimesele meetodile.
 */
public class AstAnalyzer {


    /**
     * See on nüüd kõige tüüpilisem lähenemine. Me eeldame, et kõik teevad endale
     * selgeks, kuidas süntaksipuu niimoodi läbida:
     */
    static void computeEmpty(RegexNode node) {
        if (node instanceof Epsilon) {
            node.setEmpty(true); // Kas on õige??
        }
        else if (node instanceof Letter) {
            node.setEmpty(true); // Kas on õige??
        }
        else if (node instanceof Alternation) {
            Alternation alt = (Alternation) node;
            RegexNode left = alt.getLeft();
            RegexNode right = alt.getRight();
            node.setEmpty(left.isEmpty() && right.isEmpty()); // Ehk oli nii?
            computeEmpty(left);
            computeEmpty(right);
        }
        else if (node instanceof Concatenation) {
            Concatenation conc = (Concatenation) node;
        }
        else {
            assert node instanceof Repetition;
            Repetition rep = (Repetition) node;
        }
    }

    /**
     * Siin on demo homogeense liidese kasutamise kohta.
     */
    static void computeFirst(RegexNode node) {
        switch (node.type) {
            case '*':
                node.addToFirstSet(node.getChild(0).getFirstSet());
                break;
            case '|':
                // Whaat?
                break;
            case '.':
                break;
            case 'ε':
                break;
            default :
                assert node instanceof Letter;
                node.addToFirstSet((Letter)node);
        }
        // Järgmine rida on peamine põhjus, miks tihti eelistatakse homogeenset liidest:
        for (RegexNode child : node.getChildren()) computeFirst(child);
    }

    /**
     * See on siis näide visitori kasutamisest. Me soovitakse seda kasutada ainult siis,
     * kui oskate juba käsitsi puu läbimist implementeerida. Parem on vastasel juhul ära kustutada
     * see visitor ja implementeerida ise.
     */
    static void computeLast(RegexNode node) {
        RegexVisitorVoid lastVisitor = new RegexVisitorVoid() {
            public void visit(Repetition node, RegexNode child) {
                // Wait, whaat?
            }
            public void visit(Alternation node, RegexNode left, RegexNode right) {
                node.addToLastSet(left.getLastSet());
                node.addToLastSet(right.getLastSet());
            }
            public void visit(Concatenation node, RegexNode left, RegexNode right) {

            }
            public void visit(Epsilon node) {
                // See võib isegi tühjaks jääda.
            }
            public void visit(Letter node) {

            }
        };
        node.acceptPost(lastVisitor);
    }

    // Siin saate ise valida, kuidas lahendada, aga pidage meeles, et siin tuleb puu
    // läbida eeljärjestuses (pre-order) ehk ülalt alla.
    static void computeNext(RegexNode node) {
        // TODO
    }

    // Kui kõik ülemised funktsioonid on tehtud, siis saab järgmise funktsiooniga automaat ehitatud.
    // Te võite seega selle failis sisu kopeerida Grep.java faili ja esitada ka kodutöö lahendusena.
    public static FiniteAutomaton regexToFiniteAutomaton(RegexNode regex) {
        computeEmpty(regex);
        computeFirst(regex);
        computeLast(regex);
        computeNext(regex);
        return regex.toAutomaton();
    }
}


