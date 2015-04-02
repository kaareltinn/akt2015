package ee.ut.cs.akt.parsers;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Node {
    public String label;
    public List<Node> children;
    public int myID;
    private static int uniqueID = 1;

    public Node (String label) {
        this.label = label;
        this.myID = uniqueID++;
        this.children = new ArrayList<Node>();
    }

    public Node (char label) {
        this(Character.toString(label));
    }

    public void add(Node n) {
        children.add(n);
    }

    public String toString() {
        if (children.size() == 0)
            return label;
        StringBuilder buf = new StringBuilder();
        buf.append(label).append("(");
        boolean first = true;
        for (Node child : children) {
            if (!first) buf.append(',');
            first = false;
            buf.append(child);
        }
        buf.append(")");
        return buf.toString();
    }

    public void dotHelper(PrintStream out) {
        out.println(myID + " [label = \"" + label + "\"]");
        for (Node child : children) {
            out.println(myID + " -> " + child.myID);
            child.dotHelper(out);
        }
    }

    public void makeDot() {
        try {
            PrintStream out = new PrintStream("tree.dot");
            out.println("digraph AST {");
            dotHelper(out);
            out.println("}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

