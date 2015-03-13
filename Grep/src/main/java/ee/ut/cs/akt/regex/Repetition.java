package ee.ut.cs.akt.regex;

public class Repetition extends RegexNode {

    public Repetition(RegexNode child) {
        super('*', child);
    }

    public RegexNode getChild() {
        return getChild(0);
    }

    @Override
    public <R,D> R accept(RegexVisitor<R,D> visitor, D data) {
        return visitor.visit(this, data);
    }
}
