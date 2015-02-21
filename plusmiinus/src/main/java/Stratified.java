import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Stratified {


    public static void main(String[] args) throws FileNotFoundException {

        HashMap<String, Integer> map = new HashMap<>();

        String failinimi = args[0];
        for (int i = 1; i < args.length; i = i + 2) {
            map.put(args[i], Integer.parseInt(args[i + 1]));
        }

        File fail = new File(failinimi);
        Scanner sc = new Scanner(fail);
        String rida = sc.nextLine();
        sc.close();

        int summa = 0;
        String[] plussid = rida.replaceAll("\\s+", "").split("[+]");
        for (String n : plussid) {
            String[] numbrid = n.split("-");
            summa += väärtuseks(numbrid[0], map);
            for (int i = 1; i < numbrid.length; i++) {
                summa -= väärtuseks(numbrid[i], map);
            }
        }
        System.out.println(summa);
    }

    public static int väärtuseks(String x, HashMap<String, Integer> map) {
        try {
            return Integer.parseInt(x);
        } catch (NumberFormatException e) {
            return map.get(x);
        }
    }

}
