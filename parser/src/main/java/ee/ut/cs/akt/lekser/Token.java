package ee.ut.cs.akt.lekser;

public class Token {
	private final Object data; // NB! data tüüp sõltub type-ist, vt. täpsemalt TokenType juurest
	private final TokenType type;
	
	public Token(TokenType type) {
		this(type, null);
	}

	public Token(TokenType type, Object data) {
		assert type != null;
		
		this.type = type;
		this.data = data;
	}
	
	public TokenType getType() {
		return type;
	}
	
	public Object getData() {
		return data;
	}
	
	@Override
	public String toString() {
		return "<" + type
				+ (data == null ? "" : (":" + data))
				+ ">";
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Token)) {
			return false;
		}
		Token that = (Token) obj;

		return this.type.equals(that.type)
				&& (this.data == null && that.data == null
				|| this.data != null && this.data.equals(that.data)
		);
	}

	@Override
	public int hashCode() {
		if (data != null) {
			return type.hashCode() + data.hashCode();
		}
		else {
			return type.hashCode();
		}
	}
}
