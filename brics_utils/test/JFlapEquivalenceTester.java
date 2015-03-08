import static org.junit.Assert.fail;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import dk.brics.automaton.Automaton;
import ee.ut.cs.akt.BricsUtils;

@RunWith(Parameterized.class)
public class JFlapEquivalenceTester {
	public static String lastTestDescription = "";
	
	private File benchmarkFile;

	public JFlapEquivalenceTester(File benchmarkFile) {
		this.benchmarkFile = benchmarkFile;
	}

	@Parameters
	public static Collection<Object[]> data() {
		File currentDir = new File(System.getProperty("user.dir"));
		List<Object[]> benchmarkFiles = new ArrayList<>();
		
		for (File file : currentDir.listFiles()) {
			if (file.getName().matches("bench.+jff")) {
				benchmarkFiles.add(new Object[]{file});
			}
		}
		
		return benchmarkFiles;
	}

	@Test
	public void test() {
		Automaton benchAutomaton = BricsUtils.fromJFlap(this.benchmarkFile);
		
		File testableFile = new File(this.benchmarkFile.getPath().replaceAll("bench", "yl"));
		
		lastTestDescription = testableFile.getName();
				
				
		if (testableFile.exists()) {
			Automaton testableAutomaton = BricsUtils.fromJFlap(testableFile);
			if (!testableAutomaton.equals(benchAutomaton)) {
				Automaton diff = benchAutomaton.minus(testableAutomaton);
				
				if (diff.isEmpty()) {
					diff = testableAutomaton.minus(benchAutomaton);
				}
				
				fail("Esitatud automaat ei anna õiget vastust sisendiga '" 
						+ diff.getShortestExample(true) + "'");
			}
		} else {
			fail("Ei leia lahenduse faili");
		}
	}

}
