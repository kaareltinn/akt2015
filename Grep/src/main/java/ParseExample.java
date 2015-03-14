import ee.ut.cs.akt.regex.RegexNode;
import ee.ut.cs.akt.regex.RegexParser;

public class ParseExample {
    public static void main(String[] args) {
        RegexNode node = RegexParser.parse("(a|b)*a(a|b)");
        node.createDotFile("tree.dot");
        System.out.println(node);
    }
}
