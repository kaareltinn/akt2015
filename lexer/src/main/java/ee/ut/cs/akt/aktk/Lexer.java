package ee.ut.cs.akt.aktk;

import static ee.ut.cs.akt.aktk.TokenType.*;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
	private static final char TERMINATOR = '\0';
	private final String input;

	public Lexer(String input) {
		this.input = input + "\0";
	}

	public List<Token> readAllTokens() {
		List<Token> tokens = new ArrayList<>();
		return tokens;
	}

}
