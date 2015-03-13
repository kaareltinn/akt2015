package ee.ut.cs.akt.regex;

public class RegexNodeCounter {
    public int transitions = 0;
    public int states = 2;

    public RegexNodeCounter(RegexNode n) {
        count(n);
    }

    private void count(RegexNode n) {
        switch (n.type) {
            case '*':
                states += 2;
                transitions += 4;
                break;
            case '|':
                break;
            case '.':
                states++;
                break;
            case 'ε':
            default :
                transitions++;
        }

        for (RegexNode child : n.getChildren())
            count(child);
    }

    public String toString() {
        return "Transitions: " + transitions + ", States: " + states;
    }
}
