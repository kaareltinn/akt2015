package ee.ut.cs.akt.regex;

public class Concatenation extends RegexNode {

    private final RegexNode left;
    private final RegexNode right;

    public Concatenation(RegexNode left, RegexNode right) {
        super('.', left, right);
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
