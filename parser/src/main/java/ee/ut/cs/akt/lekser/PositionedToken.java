package ee.ut.cs.akt.lekser;

public class PositionedToken {
	private final Token token;
	private final int offset; // token-ile vastava teksti alguse indeks
	private final int length; // token-ile vastava teksti pikkus
	
	public PositionedToken(Token token, int offset, int length) {
		this.token = token;
		this.offset = offset;
		this.length = length;
	}
	
	public Token getToken() {
		return token;
	}
	
	public int getOffset() {
		return offset;
	}
	
	public int getLength() {
		return length;
	}

	@Override
	public String toString() {
		return token.toString() + "@" + offset + "-" + (offset+length);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (! (obj instanceof PositionedToken)) {
			return false;
		}
		
		PositionedToken that = (PositionedToken) obj;
		return this.token.equals(that.token)
				&& this.offset == that.offset
				&& this.length == that.length;
	}
	
	@Override
	public int hashCode() {
		return token.hashCode() + this.offset + this.length;
	}
}
