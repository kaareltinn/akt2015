package ee.ut.cs.akt.regex;

public interface RegexVisitor<R> {
    public R visit(Alternation node);
    public R visit(Concatenation node);
    public R visit(Epsilon node);
    public R visit(Letter node);
    public R visit(Repetition node);
}
