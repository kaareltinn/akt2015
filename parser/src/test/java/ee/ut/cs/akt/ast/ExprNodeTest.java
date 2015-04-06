package ee.ut.cs.akt.ast;

import ee.ut.cs.akt.lekser.TokenType;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ExprNodeTest {

    @Test
    public void testEval() throws Exception {
        HashMap<String, Integer> env = new HashMap<>();
        env.put("kala", 2);
        ExprNode node = minus(num(5),minus(var("kala"),num(5)));
        assertEquals(node.toString(), 8, node.eval(env));
    }

    @Test
    public void testPretty() throws Exception {
        ExprNode node = minus(num(5),minus(var("kala"),num(5)));
        assertEquals(node.toString(), "5-(kala-5)", node.toPrettyString());
    }




    private IntLiteral num(int v) {
        return new IntLiteral(v);
    }

    private Variable var(String s) {
        return new Variable(s);
    }

    private BinOp plus(ExprNode l, ExprNode r) {
        return new BinOp(TokenType.PLUS, l, r);
    }
    private BinOp minus(ExprNode l, ExprNode r) {
        return new BinOp(TokenType.MINUS, l, r);
    }
    private BinOp mul(ExprNode l, ExprNode r) {
        return new BinOp(TokenType.TIMES, l, r);
    }
    private BinOp div(ExprNode l, ExprNode r) {
        return new BinOp(TokenType.DIV, l, r);
    }
}