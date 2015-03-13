package ee.ut.cs.akt.regex;

public interface RegexVisitor<R,D> {
    public R visit(Alternation node, D data);
    public R visit(Concatenation node, D data);
    public R visit(Epsilon node, D data);
    public R visit(Letter node, D data);
    public R visit(Repetition node, D data);
}
