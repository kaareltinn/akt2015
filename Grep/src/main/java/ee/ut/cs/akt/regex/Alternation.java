package ee.ut.cs.akt.regex;

/**
 * 
 */
public class Alternation extends RegexNode {

    private final RegexNode left;
    private final RegexNode right;

    public Alternation(RegexNode left, RegexNode right) {
        super('|', left, right);
        this.left = left;
        this.right = right;
    }

    public RegexNode getLeft() {
        return left;
    }

    public RegexNode getRight() {
        return right;
    }

    @Override
    public <R> R accept(RegexVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
