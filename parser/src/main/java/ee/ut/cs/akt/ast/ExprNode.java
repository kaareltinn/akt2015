package ee.ut.cs.akt.ast;

import java.util.Map;

public abstract class ExprNode {

    public abstract int eval(Map<String, Integer> env);

    protected abstract void buildString(StringBuilder sb);

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        buildString(sb);
        return sb.toString();
    }

    protected abstract void buildPretty(StringBuilder sb, int contextPriority);

    public final String toPrettyString() {
        StringBuilder sb = new StringBuilder();
        buildPretty(sb, 0);
        return sb.toString();
    }
}
