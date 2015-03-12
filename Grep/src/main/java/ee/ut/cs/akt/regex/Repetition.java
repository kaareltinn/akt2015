package ee.ut.cs.akt.regex;

public class Repetition extends RegexNode {
    private RegexNode child;

    public Repetition(RegexNode child) {
        super('*', child);
        this.child = child;
    }

    public RegexNode getChild() {
        return child;
    }

    @Override
    public <R> R accept(RegexVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
