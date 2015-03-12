package ee.ut.cs.akt.regex;

public class Epsilon extends RegexNode {
    public Epsilon() {
        super('ε');
    }

    @Override
    public <R> R accept(RegexVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
