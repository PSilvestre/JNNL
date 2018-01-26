package exception;

public class NoSummingFunctionException extends Exception {
	public NoSummingFunctionException() {
		super("Neuron has no summing function.");
	}
}
