package ee.ut.cs.akt.parser;

import ee.ut.cs.akt.ast.BinOp;
import ee.ut.cs.akt.ast.ExprNode;
import ee.ut.cs.akt.ast.IntLiteral;
import ee.ut.cs.akt.ast.Variable;
import ee.ut.cs.akt.lekser.Lexer;
import ee.ut.cs.akt.lekser.PositionedToken;
import ee.ut.cs.akt.lekser.TokenType;

import static ee.ut.cs.akt.lekser.TokenType.*;

public class AKTKParser {

    private Lexer lex;
    private PositionedToken current;

    public AKTKParser(Lexer lex) {
        this.lex = lex;
    }

    private void consume() {
        if (peek() == EOF) {
            // Korrektne parser ei tohiks siia jõuda.
            throw new AssertionError("Reading passed terminator!");
        }
        read();
    }

    private void read() {
        current = lex.readNextPositionedToken();
    }

    private TokenType peek() {
        if (current == null) read();
        return current.getToken().getType();
    }

    private void match(TokenType t) {
        if (peek() != t) throw new AKTKParseException(current, t);
        consume();
    }

    public ExprNode parse() {
        ExprNode result = avaldis();
        if (peek() != EOF) {
            throw new AKTKParseException(current, EOF);
        }
        return result;
    }

    public static ExprNode parse(String input) {
        Lexer lex = new Lexer(input);
        AKTKParser parser = new AKTKParser(lex);
        return parser.parse();
    }


    // TODO: Siin oleks vaja avaldisele vastav grammatika ja vajalikud abifunktsioone lisada.
    private ExprNode avaldis() {
        return null;
    }

}