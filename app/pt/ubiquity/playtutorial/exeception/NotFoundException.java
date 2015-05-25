package pt.ubiquity.playtutorial.exeception;

public class NotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public NotFoundException() {
		super();
	}

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(Throwable cause) {
		super(cause);
	}

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
