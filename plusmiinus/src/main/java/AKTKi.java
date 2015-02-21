import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AKTKi {
    public static void main(String[] args) throws IOException {
        String input = readLineFromFile(args[0]);
        List<String> remainingArgs = Arrays.asList(args).subList(1, args.length);
        Map<String, Integer> env = createEnvFromArray(remainingArgs);
        List<String> tokens = tokenize(input);
        int result = compute(tokens, env);
        System.out.println(result);
    }

    public static List<String> tokenize(String input) {
        List<String> tokens = new ArrayList<>();
        String currentToken = null;
        for (char c : input.toCharArray()) {
            if (c == ' ') continue;
            if (c == '+' || c == '-') {
                if (currentToken != null) {
                    tokens.add(currentToken);
                    currentToken = null;
                }
                tokens.add(Character.toString(c));
            } else {
                if (currentToken != null) currentToken += c;
                else currentToken = Character.toString(c);
            }
        }
        tokens.add(currentToken);
        return tokens;
    }

    public static int compute(List<String> tokens, Map<String, Integer> env) {
        int sign = 1;
        int sum = 0;
        for (String token : tokens) {
            if (token.equals("+")) continue;
            if (token.equals("-")) sign *= -1;
            else sum += sign * getValue(token, env);
        }
        return sum;
    }

    private static int getValue(String token, Map<String, Integer> env) {
        try {
            return Integer.parseInt(token);
        } catch (NumberFormatException e) {
            return env.get(token);
        }
    }

    private static Map<String, Integer> createEnvFromArray(List<String> params) {
        HashMap<String, Integer> env = new HashMap<>();
        for (Iterator<String> iterator = params.iterator(); iterator.hasNext(); ) {
            String var = iterator.next();
            String val = iterator.next();
            env.put(var, Integer.parseInt(val));
        }
        return env;
    }

    private static String readLineFromFile(String arg) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(arg));
        String line = br.readLine();
        br.close();
        return line;
    }
}
