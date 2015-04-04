package ee.ut.cs.akt.parsers;

import ee.ut.cs.akt.recognizers.ParseException;

import java.util.Random;

public class RDParser extends Parser {
    static int N = 1000;
    Random generator;

    public RDParser(String input) {
        super(input);
        generator = new Random();
    }

    public static void main(String[] args) {
        Parser parser = new RDParser(args[0]);
        // parser.check();

        // Ühest korrast ilmselt ei piisa, aga lühikeste sõnede puhul varem või hiljem tehakse
        // õiged valikud ikkagi ära.
        for (int i = 0; i < N; ) {
            try {
                parser.attemptParse();
                System.out.println("Attempts: " + i);
                return;
            } catch (ParseException e) {
                i++;
            }
        }
        System.out.printf("REJECT! (After %d random trials.)\n", N);
    }

    // Grammatika reeglid:
    // S -> aSb | ε
    protected Node s() {
        Node n = new Node("S");

        switch (generator.nextInt(2)) {
            case 0: // S -> aSb
                n.add(match('a'));
                n.add(s());
                n.add(match('b'));
                break;
            case 1: // S -> ε
                n.add(epsilon());
                break;
        }
        return n;
    }
}
