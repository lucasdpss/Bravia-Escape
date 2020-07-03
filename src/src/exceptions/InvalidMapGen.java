package exceptions;

public class InvalidMapGen extends Exception{
	private static final long serialVersionUID = 7194882384699830205L;

	public InvalidMapGen() {
		super();
	}

	public InvalidMapGen(String message) {
		super(message);
	}
}
