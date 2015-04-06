package ee.ut.cs.akt.ast;

public class TVoid extends Type {

    public TVoid() {
        super("TVoid");
    }

    @Override
    protected String toSyntax(String rest) {
        return "void " + rest;
    }

    @Override
    protected String toEnglish(boolean singular) {
        if (singular) return "the void";
        else return "void";
    }
}
