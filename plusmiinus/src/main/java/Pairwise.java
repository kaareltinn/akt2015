import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Pairwise {

    public static void main(String[] args) throws IOException {

        String text = (new BufferedReader(new FileReader(args[0]))).readLine();

        // http://stackoverflow.com/questions/2206378/how-to-split-a-string-but-also-keep-the-delimiters
        String[] tykid = text.replaceAll("\\s", "").split("(?=[+-])|(?<=[+-])");

        Map<String, Integer> muutujad = new HashMap<>();

        for (int i = 1; i < args.length; i += 2) {
            muutujad.put(args[i], Integer.valueOf(args[i + 1]));
        }

        int tulemus = getInteger(tykid[0], muutujad);
        for (int i = 1; i < tykid.length; i += 2) {
            int value = getInteger(tykid[i + 1], muutujad);
            if (tykid[i].equals("+")) {
                tulemus += value;
            } else {
                tulemus -= value;
            }
        }

        System.out.println(tulemus);
    }

    private static int getInteger(String s, Map<String, Integer> muutujad) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return muutujad.get(s);
        }
    }

}
