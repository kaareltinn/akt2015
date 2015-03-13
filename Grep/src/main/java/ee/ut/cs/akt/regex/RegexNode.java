package ee.ut.cs.akt.regex;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

/**
 * Regulaaravaldise süntakspuu tipude ülemklass. Annab ka homogeense vaade ASTile.
 */

public abstract class RegexNode {
    public final char type;
    private final int myID;
    private static int uniqueID = 1;
    private final List<RegexNode> children;

    public RegexNode(char type, RegexNode... children) {
        this.myID = uniqueID++;
        this.type = type;
        this.children = Arrays.asList(children);
    }

    public abstract <R,D> R accept(RegexVisitor<R,D> visitor, D data);

    public String toString() {
        if (children.isEmpty()) {
            return Character.toString(type);
        }
        StringBuilder buf = new StringBuilder();
        buf.append(type).append("(");
        boolean first = true;
        for (RegexNode child : children) {
            if (!first) buf.append(',');
            first = false;
            buf.append(child);
        }
        buf.append(")");
        return buf.toString();
    }

    protected void dotAddAttributes(StringBuilder out) {
        return;
    }

    private void dotHelper(StringBuilder out) {
        out.append(myID).append(" [label = \"").append(type).append("\", ");
        dotAddAttributes(out);
        out.append("]\n");
        for (RegexNode child : children) {
            out.append(myID).append(" -> ").append(child.myID).append("\n");
            child.dotHelper(out);
        }
    }

    public String toDotString() {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph AST {\n");
        sb.append("bgcolor=transparent;\n");
        sb.append("node [style=filled, shape=circle, fixedsize=true];\n");
        dotHelper(sb);
        sb.append("}\n");
        return sb.toString();
    }

    public void createDotFile(String fileName) {
        try {
            PrintStream out = new PrintStream(fileName);
            out.print(toDotString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<RegexNode> getChildren() {
        return children;
    }

    public RegexNode getChild(int i) {
        return children.get(i);
    }
}


