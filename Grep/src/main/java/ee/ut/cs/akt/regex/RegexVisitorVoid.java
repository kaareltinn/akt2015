package ee.ut.cs.akt.regex;


public abstract class RegexVisitorVoid implements RegexVisitor<Void,Void> {
    public abstract void visit(Alternation node, RegexNode left, RegexNode right);
    public abstract void visit(Concatenation node, RegexNode left, RegexNode right);
    public abstract void visit(Repetition node, RegexNode child);
    public abstract void visit(Epsilon node);
    public abstract void visit(Letter node);

    @Override
    public Void visit(Alternation node, Void data) {
        visit(node, node.getLeft(), node.getRight());
        return null;
    }

    @Override
    public Void visit(Concatenation node, Void data) {
        visit(node, node.getLeft(), node.getRight());
        return null;
    }

    @Override
    public Void visit(Repetition node, Void data) {
        visit(node, node.getChild());
        return null;
    }

    @Override
    public Void visit(Epsilon node, Void data) {
        visit(node);
        return null;
    }

    @Override
    public Void visit(Letter node, Void data) {
        visit(node);
        return null;
    }
}
