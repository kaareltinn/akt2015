import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Lexer {

    private int number;
    private String var;
    private boolean hasToken;
    private int sum;
    private int sign;
    private Map<String, Integer> vars;

    public Lexer(Map<String, Integer> vars) {
        sum = 0;
        this.vars = vars;
        init();
    }

    private void init() {
        number = 0;
        var = "";
        hasToken = false;
        sign = 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        String in = br.readLine();
        Map<String, Integer> vars = new HashMap<String, Integer>();
        for(int i = 0; i < (args.length - 1) / 2; i++) {
            vars.put(args[i * 2 + 1], Integer.parseInt(args[i * 2 + 2]));
        }
        Lexer lexer = new Lexer(vars);
        int sum = lexer.lex(in);
        System.out.println(sum);
    }

    private int lex(String s) {
        for(char c : s.toCharArray()) {
            if (c == ' ') continue;
            if (c == '+' || c == '-') {
                if (hasToken) {
                    sum += sign * getValue();
                    init();
                }
                if (c == '-') sign *= -1;
            } else {
                if(Character.isDigit(c)) number = number * 10 + (c - '0');
                else var += c;
                hasToken = true;
            }
        }
        sum += sign * getValue();
        return sum;
    }

    private int getValue() {
        if (var.equals("")) return number;
        return vars.get(var);
    }

}
