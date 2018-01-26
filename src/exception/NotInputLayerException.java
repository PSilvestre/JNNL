package exception;

public class NotInputLayerException extends Exception {
	public NotInputLayerException() {
		super("Cant feed inputs to a non InputLayer.");
	}
}
