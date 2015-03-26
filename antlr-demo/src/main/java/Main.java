import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

public class Main {

    public static String parse(String input) {
        ExprLexer lexer = new ExprLexer(new ANTLRInputStream(input));
        ExprParser parser = new ExprParser(new CommonTokenStream(lexer));
        return parser.init().toStringTree(parser);
    }

    public static void main(String[] args) {
        System.out.println(parse("x+x*x"));
    }
}
