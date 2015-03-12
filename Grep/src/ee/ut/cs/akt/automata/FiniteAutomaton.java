package ee.ut.cs.akt.automata;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class FiniteAutomaton {
	private Map<Integer, Map<Character, Set<Integer>>> transitions = new HashMap<>();
	private Integer startState = null;
	private Set<Integer> acceptingStates = new HashSet<>();
	private Set<Integer> states = new HashSet<>();

	public void addState(Integer id, boolean isAcceptingState) {
		
		states.add(id);
		transitions.put(id, new HashMap<>());
		
		if (isAcceptingState) {
			acceptingStates.add(id);
		} else {
			acceptingStates.remove(id);
		}
	}
	

	public int addState(boolean isAcceptingState) {
		for (int i=0; i <= Integer.MAX_VALUE; i++) {
			if (!this.states.contains(i)) {
				this.addState(i, isAcceptingState);
				return i;
			}
		}
		throw new RuntimeException("Too many states");
	}

	public void addTransition(Integer fromState, Character label, Integer toState) {
		assert transitions.containsKey(fromState);
		
		if (!transitions.get(fromState).containsKey(label)) {
			transitions.get(fromState).put(label, new HashSet<>());
		}
		
		transitions.get(fromState).get(label).add(toState);
	}
	
	public void setStartState(Integer id) {
		this.startState = id;
		this.states.add(id);
	}

	public boolean accepts(String input) {
		return accepts(input, 0, this.startState, new HashSet<>());
	}
	
	private boolean accepts(String input, int index, Integer state,
			Set<ExecutionState> executionStates) {
		
		// tsükli kontroll
		ExecutionState currentExecutionState = new ExecutionState(index, state);
		if (executionStates.contains(currentExecutionState)) {
			return false;
		}
		executionStates.add(currentExecutionState);
		
		// lõppseisu jõudmine
		if (index == input.length() && acceptingStates.contains(state)) {
			return true;
		}
		
		// seisundi vahetamine
		else {
			// tavalised üleminekud
			if (index < input.length()) {
				Character c = input.charAt(index);
				if (transitions.get(state).containsKey(c)) {
					for (Integer nextState : transitions.get(state).get(c)) {
						if (this.accepts(input, index+1, nextState, executionStates)) {
							return true;
						}
					}
				}
			}
			
			// epsilon-üleminekud
			if (transitions.get(state).containsKey(null)) {
				for (Integer nextState : transitions.get(state).get(null)) {
					if (this.accepts(input, index, nextState, executionStates)) {
						return true;
					}
				}
			}
			
			return false;
		}
	}
	
	// vajalik tsükli kontrolliks
	private static class ExecutionState {
		private final int inputIndex;
		private final Integer state;

		public ExecutionState(int inputIndex, Integer state) {
			this.inputIndex = inputIndex;
			assert state != null;
			this.state = state;
		}
		
		@Override
		public int hashCode() {
			return inputIndex + state;
		}
		
		@Override
		public boolean equals(Object obj) {
			return (obj instanceof ExecutionState)
					&& ((ExecutionState)obj).inputIndex == this.inputIndex
					&& ((ExecutionState)obj).state == this.state;
		}
	}
	
	public boolean isDeterministic() {
		for (Map<Character, Set<Integer>> trans : transitions.values()) {
			for (Character label : trans.keySet()) {
				if (label == null 							// epsilon serv
						|| trans.get(label).size() > 1) {	// mitu sihtseisundit
					return false;
				}
			}
		}
		return true;
	}
	
	public Integer getStartState() {
		return startState;
	}
	
	public Set<Integer> getStates() {
		return states;
	}
	
	public Set<Integer> getAcceptingStates() {
		return acceptingStates;
	}
	
	public Map<Integer, Map<Character, Set<Integer>>> getTransitions() {
		return transitions;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Olekud: " + this.states + "\n"
				+ "Algolek: " + this.startState + "\n"
				+ "Lõppolekud: " + this.acceptingStates + "\n"
				+ "Üleminekud:\n");
		
		for (Integer fromId : this.transitions.keySet()) {
			for (Character label : transitions.get(fromId).keySet()) {
				for (Integer toId : transitions.get(fromId).get(label)) {
					if (label == null) {
						label = 'ε';
					}
					sb.append("  " + fromId + " --[" + label + "]--> " + toId + "\n");
				}
			}
		}
		
		return sb.toString();
	}
}

