package ee.ut.cs.akt.ast;

import ee.ut.cs.akt.lekser.TokenType;

import java.util.Map;

public class BinOp extends ExprNode {

    private final TokenType op;

    private final ExprNode left;
    private final ExprNode right;

    public BinOp(TokenType token, ExprNode left, ExprNode right) {
        if (!token.isOperator()) throw new IllegalArgumentException();
        this.op = token;
        this.left = left;
        this.right = right;
    }

    @Override
    public int eval(Map<String, Integer> env) {
        //TODO: Implementeeri mind!
        return 0;
    }

    @Override
    protected void buildString(StringBuilder sb) {
        sb.append(op.toString().toLowerCase());
        sb.append('(');
        left.buildString(sb);
        sb.append(',');
        right.buildString(sb);
        sb.append(')');
    }

    @Override
    protected void buildPretty(StringBuilder sb, int contextPriority) {
        // TODO: Implementeeri mind!
    }
}
