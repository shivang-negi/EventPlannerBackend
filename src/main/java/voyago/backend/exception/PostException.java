package voyago.backend.exception;

public class PostException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public PostException(String message) {
		super(message);
	}
}
