package ee.ut.cs.akt.automata;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.State;
import dk.brics.automaton.StatePair;
import dk.brics.automaton.Transition;

public class BricsUtils {
	public static Automaton fromJFlap(File file) {
		Automaton automaton = new Automaton();
		Map<Integer, State> states = new HashMap<Integer, State>();
		Set<StatePair> epsilons = new HashSet<StatePair>();

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);

			NodeList stateNodes = doc.getElementsByTagName("state");
			for (int i=0; i < stateNodes.getLength(); i++) {
				Element stateNode = (Element)stateNodes.item(i);
				int stateId = Integer.parseInt(stateNode.getAttribute("id"));

				State state = new State();
				state.setAccept(stateNode.getElementsByTagName("final").getLength() == 1);

				if (stateNode.getElementsByTagName("initial").getLength() == 1) {
					automaton.setInitialState(state);
				}

				states.put(stateId, state);
			}

			NodeList transitionNodes = doc.getElementsByTagName("transition");
			for (int i=0; i < transitionNodes.getLength(); i++) {
				Element transitionNode = (Element)transitionNodes.item(i);

				int fromId = Integer.parseInt(getXmlChildContent(transitionNode, "from"));
				int toId = Integer.parseInt(getXmlChildContent(transitionNode, "to"));
				State fromState = states.get(fromId);
				State toState = states.get(toId);

				String label = getXmlChildContent(transitionNode, "read");

				if (label.length() == 0) {
					epsilons.add(new StatePair(fromState, toState));
				}
				else if (label.length() == 1) {
					fromState.addTransition(new Transition(label.charAt(0), toState));
				}
				else {
					throw new IllegalArgumentException("Multichar transition label");
				}
			}

			automaton.addEpsilons(epsilons);
			automaton.restoreInvariant();
			automaton.setDeterministic(false);
			return automaton;
		} 
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}

	public static Automaton fromAktAutomaton(FiniteAutomaton source) {
		Automaton automaton = new Automaton();
		Map<Integer, State> states = new HashMap<Integer, State>();
		Set<StatePair> epsilons = new HashSet<StatePair>();

		for (int stateId : source.getStates()) {
			State state = new State();
			state.setAccept(source.getAcceptingStates().contains(stateId));
			states.put(stateId, state);
		}

		for (Integer fromId : source.getTransitions().keySet()) {
			State fromState = states.get(fromId);
			for (Character label : source.getTransitions().get(fromId).keySet()) {
				for (Integer toId : source.getTransitions().get(fromId).get(label)) {
					State toState = states.get(toId);
					if (label == null) {
						epsilons.add(new StatePair(fromState, toState));
					}
					else {
						fromState.addTransition(new Transition(label, toState));
					}
				}
			}
		}

		automaton.addEpsilons(epsilons);
		automaton.restoreInvariant();
		automaton.setDeterministic(false);
		return automaton;
	}

	private static String getXmlChildContent(Element element, String tagName) {
		NodeList children = element.getElementsByTagName(tagName);
		if (children.getLength() != 1) {
			throw new IllegalArgumentException("Expected single child with given name");
		}

		return children.item(0).getTextContent();
	}
}
