package ee.ut.cs.akt.regex;

/**
 * Tähistab regulaaravaldist, mis sobitub näidatud sümboliga
 * (see sümbol ei pea olema tingimata täht, nagu klassi nimest võiks arvata).
 */
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
    public <R,D> R accept(RegexVisitor<R,D> visitor, D data) {
        return visitor.visit(this, data);
    }

    @Override
    protected void dotAddAttributes(StringBuilder out) {
        out.append("shape=\"square\"");
    }
}
