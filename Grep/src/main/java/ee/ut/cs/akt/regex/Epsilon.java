package ee.ut.cs.akt.regex;

public class Epsilon extends RegexNode {
    public Epsilon() {
        super('ε');
    }

    @Override
    public <R,D> R accept(RegexVisitor<R,D> visitor, D data) {
        return visitor.visit(this, data);
    }

    @Override
    protected void dotAddAttributes(StringBuilder out) {
        out.append("shape=\"square\"");
    }
}
