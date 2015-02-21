import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class ScriptMonkey {

    public static void main(String[] args) throws IOException, ScriptException {
        String content = new String(Files.readAllBytes(Paths.get(args[0])));
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        for (int i = 1;i<args.length;i+=2) {
            // NB! Engine'ile peab andma neid täisarvudena, mitte sõnedena:
            engine.put(args[i], Integer.parseInt(args[i+1]));
        }
        Double scriptEngineResult = Double.valueOf(engine.eval(content).toString());
        System.out.println(scriptEngineResult.intValue());
    }
}

