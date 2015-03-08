

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import dk.brics.automaton.Automaton;
import ee.ut.cs.akt.BricsUtils;

public class FromJFlapTests {

	@Test
	public void test_a_star() {
		test_a_star(BricsUtils.fromJFlap(new File("test/a_star.jff")));
		test_a_star(BricsUtils.fromJFlap(new File("test/a_star_with_epsilon.jff")));
	}
	
	private void test_a_star(Automaton a) {
		assertEquals(true, a.run("a"));
		assertEquals(true, a.run("aa"));
		assertEquals(true, a.run("aaaaaaa"));
		assertEquals(true, a.run(""));
		
		assertEquals(false, a.run("b"));
		assertEquals(false, a.run("bb"));
		assertEquals(false, a.run("ab"));
		assertEquals(false, a.run("ba"));
	}

	@Test
	public void test_a_plus() {
		test_a_plus(BricsUtils.fromJFlap(new File("test/a_plus.jff")));
		test_a_plus(BricsUtils.fromJFlap(new File("test/a_plus_with_epsilon.jff")));
	}
	
	private void test_a_plus(Automaton a) {
		assertEquals(true, a.run("a"));
		assertEquals(true, a.run("aa"));
		assertEquals(true, a.run("aaaaaaa"));
		
		assertEquals(false, a.run(""));
		assertEquals(false, a.run("b"));
		assertEquals(false, a.run("bb"));
		assertEquals(false, a.run("ab"));
		assertEquals(false, a.run("ba"));
	}
	
	@Test
	public void test_ab_plus() throws Exception {
		Automaton a = BricsUtils.fromJFlap(new File("test/ab_plus.jff"));
		assertEquals(true, a.run("ab"));
		assertEquals(true, a.run("abab"));
		assertEquals(true, a.run("ababab"));
		
		assertEquals(false, a.run("ba"));
		assertEquals(false, a.run(""));
		assertEquals(false, a.run("a"));
		assertEquals(false, a.run("b"));
	}

	@Test
	public void test_no_final_states() throws Exception {
		Automaton a = BricsUtils.fromJFlap(new File("test/no_final_states.jff"));
		assertEquals(false, a.run(""));
		assertEquals(false, a.run("a"));
		assertEquals(false, a.run("b"));
	}


	@Test
	public void test_only_empty_string() throws Exception {
		Automaton a = BricsUtils.fromJFlap(new File("test/only_empty_string.jff"));
		assertEquals(true, a.run(""));
		assertEquals(false, a.run("a"));
		assertEquals(false, a.run("b"));
	}

}
