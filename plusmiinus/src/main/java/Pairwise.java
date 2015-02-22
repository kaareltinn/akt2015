import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Pairwise {

    public static void main(String[] args) throws IOException {

        String text = (new BufferedReader(new FileReader(args[0]))).readLine();
        String[] tükid = tükkelda(text);

        Map<String, Integer> muutujad = new HashMap<>();
        for (int i = 1; i < args.length; i += 2) {
            muutujad.put(args[i], Integer.valueOf(args[i + 1]));
        }

        int tulemus = getInteger(tükid[0], muutujad);
        for (int i = 1; i < tükid.length; i += 2) {
            int value = getInteger(tükid[i + 1], muutujad);
            if (tükid[i].equals("+")) {
                tulemus += value;
            } else {
                tulemus -= value;
            }
        }

        System.out.println(tulemus);
    }

    private static String[] tükkelda(String text) {
        // http://stackoverflow.com/questions/2206378/how-to-split-a-string-but-also-keep-the-delimiters
        //   return text.replaceAll("\\s", "").split("(?=[+-])|(?<=[+-])");
        // aga tegelikult saab ka lihtsamini:
        return text.replace(" ", "").replace("+", "!+!").replace("-", "!-!").split("!");
    }

    private static int getInteger(String s, Map<String, Integer> muutujad) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return muutujad.get(s);
        }
    }

}
