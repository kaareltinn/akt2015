package ee.ut.cs.akt.regex;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

/**
 * The ee.ut.cs.akt.regex.RegexNode class represent Nodes in the AST of a regexp.
 *
 * @author Vesal Vojdani <vesal.vojdani@ut.ee>
 */

public abstract class RegexNode {
    public final char type;
    private final int myID;
    private static int uniqueID = 1;
    public List<RegexNode> children;

    public RegexNode(char type, RegexNode... children) {
        this.myID = uniqueID++;
        this.type = type;
        this.children = Arrays.asList(children);
    }

    public abstract <R> R accept(RegexVisitor<R> visitor);

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

    private void dotHelper(StringBuilder out) {
        out.append(myID).append(" [label = \"").append(type).append("\"]\n");
        for (RegexNode child : children) {
            out.append(myID).append(" -> ").append(child.myID).append("\n");
            child.dotHelper(out);
        }
    }

    public String toDotString() {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph AST {\n");
        dotHelper(sb);
        sb.append("}\n");
        return sb.toString();
    }

    public void makeDot() {
        try {
            PrintStream out = new PrintStream("tree.dot");
            out.print(toDotString());
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}


