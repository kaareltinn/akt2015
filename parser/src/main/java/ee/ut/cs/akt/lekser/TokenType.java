package ee.ut.cs.akt.lekser;

public enum TokenType {
	VARIABLE, INTEGER,
	LPAREN('('), RPAREN(')'),
	PLUS('+', 1, true), MINUS('-', 1, false),
	TIMES('*', 2, true), DIV('/', 2, false),
	EOF;

	private Character symbol;
	private int priority = 0;
	private boolean assoc = false;

	TokenType() {
		symbol = null;
	}
	TokenType(char c) {
		symbol = c;
	}

	TokenType(char c, int priority, boolean assoc) {
		this(c);
		this.priority = priority;
		this.assoc = assoc;
	}

	public char getSymbol() {
		return symbol;
	}

	public int getPriority() {
		return priority;
	}

	public boolean isAssoc() {
		return assoc;
	}

	public boolean isOperator() {
		return this == PLUS || this == MINUS || this == DIV || this == TIMES;
	}
}
