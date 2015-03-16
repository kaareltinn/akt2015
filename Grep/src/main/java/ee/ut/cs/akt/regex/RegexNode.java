package ee.ut.cs.akt.regex;

import ee.ut.cs.akt.automata.FiniteAutomaton;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

/**
 * Regulaaravaldise süntakspuu tipude ülemklass. Annab ka homogeense vaade ASTile.
 */

public abstract class RegexNode {
    public final char type;
    protected final int myID;
    private static int uniqueID = 1;
    private final List<RegexNode> children;

    private boolean empty = true;
    private final Set<Letter> frstSet, lastSet, nextSet;

    public RegexNode(char type, RegexNode... children) {
        this.myID = uniqueID++;
        this.type = type;
        this.children = Arrays.asList(children);
        frstSet = new HashSet<>();
        lastSet = new HashSet<>();
        nextSet = new HashSet<>();
    }

    public boolean isEmpty() {
        return empty;
    }

    public Set<Letter> getFirstSet() {
        return frstSet;
    }

    public Set<Letter> getLastSet() {
        return lastSet;
    }

    public Set<Letter> getNextSet() {
        return nextSet;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public void addToFirstSet(Collection<Letter> nodes) {
        frstSet.addAll(nodes);
    }

    public void addToFirstSet(Letter node) {
        frstSet.add(node);
    }

    public void addToLastSet(Collection<Letter> nodes) {
        lastSet.addAll(nodes);
    }

    public void addToLastSet(Letter node) {
        lastSet.add(node);
    }

    public void addToNextSet(Collection<Letter> nodes) {
        nextSet.addAll(nodes);
    }

    public void addToNextSet(Letter node) {
        nextSet.add(node);
    }


    public abstract <R,D> R accept(RegexVisitor<R,D> visitor, D data);

    public void acceptPre(RegexVisitorVoid visitor) {
        accept(visitor, null);
        for (RegexNode child : children) child.acceptPre(visitor);
    }

    public void acceptPost(RegexVisitorVoid visitor) {
        for (RegexNode child : children) child.acceptPost(visitor);
        accept(visitor, null);
    }

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
    	
    	try (PrintStream out = new PrintStream(fileName)) {
            out.print(toDotString());
    	} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
    	
    }

    public List<RegexNode> getChildren() {
        return children;
    }

    public RegexNode getChild(int i) {
        return children.get(i);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegexNode)) return false;
        RegexNode regexNode = (RegexNode) o;
        if (myID != regexNode.myID) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return myID;
    }

    public FiniteAutomaton toAutomaton() {
        int start = uniqueID++;
        FiniteAutomaton fa = new FiniteAutomaton();
        fa.addState(start, empty);
        fa.setStartState(start);
        Deque<RegexNode> todo = new LinkedList<RegexNode>();
        Set<RegexNode> done = new HashSet<RegexNode>();
        todo.addAll(frstSet);
        while (!todo.isEmpty()) {
            RegexNode n = todo.remove();
            fa.addState(n.myID, lastSet.contains(n));
            done.add(n);
            for (Letter m : n.nextSet) {
                fa.addTransition(n.myID, m.getSymbol(), m.myID);
                if (!done.contains(m) && !todo.contains(m))
                    todo.add(m);
            }
        }
        for (Letter n : frstSet) {
            fa.addTransition(start, n.getSymbol(), n.myID);
        }
        return fa;
    }

}


