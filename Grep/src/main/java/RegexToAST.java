import ee.ut.cs.akt.regex.RegexNode;
import ee.ut.cs.akt.regex.RegexParser;

public class RegexToAST {
    public static void main(String[] args) {
        RegexParser parser = new RegexParser("(a|b)*a(a|b)");
        RegexNode node = parser.parse();
        node.createDotFile("tree.dot");
        System.out.println(node);
    }
}
