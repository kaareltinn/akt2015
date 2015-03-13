package ee.ut.cs.akt.regex;

public class Concatenation extends RegexNode {

    public Concatenation(RegexNode left, RegexNode right) {
        super('.', left, right);
    }

    public RegexNode getLeft() {
        return getChild(0);
    }

    public RegexNode getRight() {
        return getChild(1);
    }

    @Override
    public <R,D> R accept(RegexVisitor<R,D> visitor, D data) {
        return visitor.visit(this, data);
    }
}
