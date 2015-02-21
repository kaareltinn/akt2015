import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.*;

public class AKTKiTest {

    @Test
    public void testTokenize() throws Exception {
        checkTokenize("2+2",      "2", "+", "2");
        checkTokenize("2 -  8",   "2", "-", "8");
        checkTokenize("2 -- 8",   "2", "-", "-", "8");
    }

    private void checkTokenize(String input, String... strings) {
        assertEquals(Arrays.asList(strings), AKTKi.tokenize(input));
    }

    @Test
    public void testCompute() throws Exception {
        HashMap<String, Integer> map = new HashMap<>();
        checkCompute(4, "2+2", map);
        checkCompute(0, "2+2-4", map);
        checkCompute(8, "2+2--4", map);
        checkCompute(10,"2-2+10", map);
        checkCompute(10,"5 + - - + - + + - 5", map);

    }

    private void checkCompute(int result, String input, HashMap<String, Integer> env) {
        assertEquals(result, AKTKi.compute(AKTKi.tokenize(input), env));
    }
}