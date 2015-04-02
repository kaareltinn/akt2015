package ee.ut.cs.akt.recognizers;

/**
 * Selle parseri eesmärk on lihtsalt tuvastada, kas etteantud sõne on 
 * klassikaline regulaaravaldis.
 * 
 * Kui meetod isRegex tagastab true, siis on tegemist regulaaravaldisega
 * vastasel juhul mitte.
 * 
 * Lähtun sellisest grammatikast:
 * 
 * grammar Regex;
 * 
 * regex  : regex3 ;
 * regex3 : regex2 ('|' regex2)* ;
 * regex2 : regex1 regex1* ;
 * regex1 : regex0 '*'? ;	
 * regex0 : Symbol
 *        | '(' regex ')'
 *        | 'ε' ;
 *
 * Symbol : [a-zA-Z0-9]; 
 */
public class RegexRecognizer {
	
	private int i; // järjehoidja
	private String sisend;
	
	public static void main(String[] args) {
		RegexRecognizer p = new RegexRecognizer();
		
		System.out.println(p.isRegex("kalapala"));
		System.out.println(p.isRegex("a+b"));
		System.out.println(p.isRegex("(a|(b|c)*)"));
	}
	
	public boolean isRegex(String sisend) {
		this.sisend = sisend + '$';  // lisan lõputähise
		this.i = 0;
		try {
			this.parseRegex();
			return peek() == '$'; // asi on õige, kui sisendisse jäi vaid lõputähis
		} 
		catch (Exception e) {
			return false;
		}
	}

	/**
	 * Abifunktsioon järjehoidja edasiliigutamiseks
	 */
	private void next() {
		this.i += 1;
	}
	
	/**
	 * Abifunktsioon järjehoidja kohal oleva sümboli lugemiseks
	 */
	private char peek() {
		return sisend.charAt(i);
	}
	
	
	private void parseRegex0() {
		if (Character.isLetter(peek())) {
			next();
			return;
		}
		else if (peek() == '(') {
			next();
			parseRegex();
			assert peek() == ')';
			next();
		}
		else if (peek() == 'ε') {
			next();
		}
		else {
			throw new RuntimeException();
		}
	}
	
	private void parseRegex1() {
		parseRegex0();
		
		if (peek() == '*') {
			next();
		}
	}
	
	private void parseRegex2() {
		do {
			parseRegex1();
		} while (peek() == 'ε' || Character.isLetter(peek()) || peek() == '(');
	}
	
	private void parseRegex3() {
		parseRegex2();
		
		while (peek() == '|') {
			next();
			parseRegex2();
		}
	}
	
	private void parseRegex() {
		parseRegex3();
	}
}
