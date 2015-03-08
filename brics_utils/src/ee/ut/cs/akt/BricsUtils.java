package ee.ut.cs.akt;

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
	        	
	        	int fromId = Integer.parseInt(getChildContent(transitionNode, "from"));
	        	int toId = Integer.parseInt(getChildContent(transitionNode, "to"));
	        	State fromState = states.get(fromId);
	        	State toState = states.get(toId);
	        	
	        	String label = getChildContent(transitionNode, "read");
	        	
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
	
	private static String getChildContent(Element element, String tagName) {
		NodeList children = element.getElementsByTagName(tagName);
		if (children.getLength() != 1) {
			throw new IllegalArgumentException("Expected single child with given name");
		}
		
		return children.item(0).getTextContent();
	}
}
