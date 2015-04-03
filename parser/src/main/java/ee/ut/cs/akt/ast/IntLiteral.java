package ee.ut.cs.akt.ast;

import java.util.Map;

public class IntLiteral extends ExprNode {
    final private int value;

    public int getValue() {
        return value;
    }

    public IntLiteral(int value) {
        this.value = value;
    }

    @Override
    public int eval(Map<String, Integer> env) {
        // Implementeeri mind!
        return 0;
    }

    @Override
    protected void buildString(StringBuilder sb) {
        sb.append("num(").append(value).append(')');
    }

    @Override
    protected void buildPretty(StringBuilder sb, int contextPriority) {
        // TODO: Implementeeri mind!
    }
}
