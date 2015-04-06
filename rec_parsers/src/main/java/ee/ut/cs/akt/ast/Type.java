package ee.ut.cs.akt.ast;

import ee.ut.cs.akt.parsers.Node;

public abstract class Type extends Node {

    public Type(String label) {
        super(label);
    }

    protected abstract String toSyntax(String rest);

    protected abstract String toEnglish(boolean singular);

    public String toEnglish() {
        return toEnglish(true);
    }

    public String toSyntax() {
        return toSyntax("");
    }
}
