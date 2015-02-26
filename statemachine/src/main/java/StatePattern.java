import java.util.function.Consumer;

public class StatePattern {

    private static Consumer<Character> state;
    private static StringBuilder sb;

    final static Consumer<Character> INI = new Consumer<Character>() {
        public void accept(Character c) {
            if (c == '<') state = TAG;
            else sb.append(c);        }
    };

    final static Consumer<Character> TAG = new Consumer<Character>() {
        public void accept(Character c) {
            if (c == '>') state = INI;
            if (c == '\'') state = QTE;
        }
    };

    final static Consumer<Character> QTE = new Consumer<Character>() {
        public void accept(Character c) {
            if (c == '\'') state = TAG;
        }
    };


    public static String removeHtmlMarkup(String s) {
        state = INI;
        sb = new StringBuilder();
        for (char c : s.toCharArray()) state.accept(c);
        return sb.toString();
    }
}