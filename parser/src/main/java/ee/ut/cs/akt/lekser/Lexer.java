package ee.ut.cs.akt.lekser;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import static ee.ut.cs.akt.lekser.TokenType.*;

public class Lexer {
	private static final char TERMINATOR = '\0';
	private final Reader reader;
	private Character current;
	private int pos = 0;

	public Lexer(Reader input) {
		this.reader = input;
	}

	public Lexer(String input) {
		this(new StringReader(input));
	}


	private void consume() {
		if (current == TERMINATOR) {
			// This should not happen.
			throw new AssertionError("Reading passed terminator!");
		}
		read();
		pos++;
	}

	private void read() {
		try {
			int i = reader.read();
			current = (i == -1) ? TERMINATOR : (char) i;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private char peek() {
		if (current == null) read();
		return current;
	}


	public PositionedToken readNextPositionedToken() {
		while (peek() != TERMINATOR) {
			if (Character.isDigit(peek())) {
				return readNumber();
			}
			else if (Character.isWhitespace(peek())) {
				consume();
			}
			else if (Character.isLetter(peek()) || peek() == '_') {
				return readVariable();
			}
			else if (peek() == '(') {
				consume();
				return singleCharPT(LPAREN);
			}
			else if (peek() == ')') {
				consume();
				return singleCharPT(RPAREN);
			}
			else if (peek() == '+') {
				consume();
				return singleCharPT(PLUS);
			}
			else if (peek() == '-') {
				consume();
				return singleCharPT(MINUS);
			}
			else if (peek() == '*') {
				consume();
				return singleCharPT(TIMES);
			}
			else if (peek() == '/') {
				consume();
				if (peek() == '/') {
					consume();
					skipSingleLineComment();
				}
				else {
					return singleCharPT(DIV);
				}
			}
			else {
				throw new RuntimeException("Unexpected symbol");
			}
		}
		return new PositionedToken(new Token(EOF), pos, 0);
	}

	private PositionedToken singleCharPT(TokenType type) {
		return new PositionedToken(new Token(type), pos-1, 1);
	}

	private void skipSingleLineComment() {
		do {
			consume();
		} while (peek() != '\n' && peek() != TERMINATOR);
	}

	private PositionedToken readVariable() {
		assert Character.isLetter(peek()) || peek() == '_';

		int initPos = this.pos;
		StringBuilder sb = new StringBuilder();
		sb.append(peek());
		consume();
		while ((Character.isLetter(peek())
				|| Character.isDigit(peek())
				|| peek() == '_') && peek() != '\0') {
			sb.append(peek());
			consume();
		}
		Token token = new Token(VARIABLE, sb.toString());
		return new PositionedToken(token, initPos, sb.length());
	}

	private PositionedToken readNumber() {
		assert Character.isDigit(peek());

		int initPos = this.pos;
		int result = 0;
		while (Character.isDigit(peek())) {
			result = result * 10 + Integer.parseInt(String.valueOf(peek()));
			consume();
		}
		Token token = new Token(INTEGER, result);
		return new PositionedToken(token, initPos, this.pos - initPos);
	}

}
