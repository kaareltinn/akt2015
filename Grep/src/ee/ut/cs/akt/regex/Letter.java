package ee.ut.cs.akt.regex;

public class Letter extends RegexNode {
    private char symbol;

    public Letter(char symbol) {
        super(symbol);
        if (symbol == 'ε') {
            throw new IllegalArgumentException("ε-nodes should be created from class Epsilon");
        }
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    @Override
    public <R> R accept(RegexVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
