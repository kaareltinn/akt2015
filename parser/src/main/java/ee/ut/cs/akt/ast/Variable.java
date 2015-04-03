package ee.ut.cs.akt.ast;

import java.util.Map;

public class Variable extends ExprNode {

    private String name;

    public Variable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int eval(Map<String, Integer> env) {
        // TODO: Implementeeri mind!
        return 0;
    }

    @Override
    protected void buildString(StringBuilder sb) {
        sb.append("var(\"").append(name).append("\")");
    }

    @Override
    protected void buildPretty(StringBuilder sb, int contextPriority) {
        // TODO: Implementeeri mind!
    }
}
