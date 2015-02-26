public class HtmlStrip {


    public static String removeHtmlMarkup(String s) {
        final int INI = 0;
        final int TAG = 1;
        final int QTE = 2;
        StringBuilder sb = new StringBuilder();
        int state = INI;
        for (char c : s.toCharArray()) {
            switch (state) {
                case INI:
                    if (c == '<') state = TAG;
                    else sb.append(c);
                    break;
                case TAG:
                    if (c == '>') state = INI;
                    if (c == '\'') state = QTE;
                    break;
                case QTE:
                    if (c == '\'') state = TAG;
                    break;
            }
        }
        return sb.toString();
    }
}